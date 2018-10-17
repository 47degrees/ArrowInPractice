package com.fortysevendeg.arrowinpractice.workshop.ex1

import arrow.core.*
import arrow.effects.IO
import com.fortysevendeg.arrowinpractice.authorizedRequest
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.model.Character
import com.fortysevendeg.arrowinpractice.workshop.setupModule
import com.fortysevendeg.arrowinpractice.workshop.utils.LocalApplicationCall
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.parametersOf
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class GetCharacterDetailsTest {

  @Test
  fun `should extract params from request`() =
    withTestApplication(Application::setupModule) {
      with(authorizedRequest(HttpMethod.Get, "/characters/1")) {
        val call = LocalApplicationCall(this, parametersOf("characterId" to listOf("1")))
        val result = paramOf("characterId", call)
        assertEquals(result, Some("1"))
      }
    }

  @Test
  fun `should return a character Id or a raised NotFound exception in the context of IO`() {
    assertEquals(
      IO.characterIdOrNotFound(Some("1")).unsafeRunSync(),
      IO.just("1").unsafeRunSync()
    )
  }

  @Test
  fun `should return a NotFound exception in the context of IO`() {
    assertEquals(
      IO.characterIdOrNotFound(None).attempt().unsafeRunSync(),
      NotFoundException().left()
    )
  }

  @Test
  fun `should properly handle String#toLong with valid Long values`() {
    assertEquals(
      IO.stringIdToLong("1").attempt().unsafeRunSync(),
      1L.right()
    )
  }

  @Test
  fun `should properly handle String#toLong raising errors as InvalidIdException`() {
    assertEquals(
      IO.stringIdToLong("x").attempt().unsafeRunSync(),
      InvalidIdException().left()
    )
  }

  @Test
  fun `fetch a character by id from the database for a given valid character id`() {
    val db = CharactersDatabase()
    val character = IO.fetchCharacterById(db, 16).unsafeRunSync()
    assertEquals(
      character.characterId.id,
      16L
    )
  }

  @Test
  fun `fetch a character by id from the database results in a NotFound raised error for invalid ids`() {
    val db = CharactersDatabase()
    assertEquals(
      IO.fetchCharacterById(db, 99).attempt().unsafeRunSync(),
      NotFoundException().left()
    )
  }

  @Test
  fun `handle DB exceptions preserving NotFoundExceptions`() {
    assertEquals(
      IO.handleDBExceptions(IO.raiseError(NotFoundException())).attempt().unsafeRunSync(),
      NotFoundException().left()
    )
  }

  @Test
  fun `handle DB exceptions preserving NotFoundExceptions but translating all others to InvalidIdException`() {
    assertEquals(
      IO.handleDBExceptions(IO.raiseError(RuntimeException())).attempt().unsafeRunSync(),
      InvalidIdException().left()
    )
  }

  @Test
  fun `should return character details by Id`() =
    withTestApplication(Application::setupModule) {
      with(authorizedRequest(HttpMethod.Get, "/characters/1")) {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals("""
        {
          "houseId" : {
            "id" : 1
          },
          "characterId" : {
            "id" : 1
          },
          "castleId" : {
            "id" : 4
          },
          "name" : "Eddard (Ned) Stark",
          "description" : "Patriarch, Lord of Winterfell, Warden of the North"
        }
        """.trimIndent(),
          response.content)
      }
    }

}
