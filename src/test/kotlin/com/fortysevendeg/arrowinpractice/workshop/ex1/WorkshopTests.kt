package com.fortysevendeg.arrowinpractice.workshop.ex1

import arrow.core.*
import arrow.effects.IO
import com.fortysevendeg.arrowinpractice.authorizedRequest
import com.fortysevendeg.arrowinpractice.database.CastlesDatabase
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.database.HousesDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.model.HouseLocation
import com.fortysevendeg.arrowinpractice.workshop.setupModule
import com.fortysevendeg.arrowinpractice.workshop.utils.LocalApplicationCall
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.parametersOf
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class WorkshopTests {

  @Test
  fun `1a should extract params from request`() =
    withTestApplication(Application::setupModule) {
      with(authorizedRequest(HttpMethod.Get, "/characters/1")) {
        val call = LocalApplicationCall(this, parametersOf("characterId" to listOf("1")))
        val result = paramOf("characterId", call)
        assertEquals(result, Some("1"))
      }
    }

  @Test
  fun `1b should return a character Id or a raised NotFound exception in the context of IO`() {
    assertEquals(
      IO.idOrNotFound(Some("1")).unsafeRunSync(),
      IO.just("1").unsafeRunSync()
    )
  }

  @Test
  fun `1b should return a NotFound exception in the context of IO when an id is not found`() {
    assertEquals(
      IO.idOrNotFound(None).attempt().unsafeRunSync(),
      NotFoundException().left()
    )
  }

  @Test
  fun `1c should properly handle String#toLong with valid Long values`() {
    assertEquals(
      IO.stringIdToLong("1").attempt().unsafeRunSync(),
      1L.right()
    )
  }

  @Test
  fun `1c should properly handle String#toLong raising errors as InvalidIdException`() {
    assertEquals(
      IO.stringIdToLong("x").attempt().unsafeRunSync(),
      InvalidIdException().left()
    )
  }

  @Test
  fun `1d fetch a character by id from the database for a given valid character id`() {
    val db = CharactersDatabase()
    val character = IO.fetchCharacterById(db, 16).unsafeRunSync()
    assertEquals(
      character.characterId,
      16L
    )
  }

  @Test
  fun `1d fetch a character by id from the database results in a NotFound raised error for invalid ids`() {
    val db = CharactersDatabase()
    assertEquals(
      IO.fetchCharacterById(db, 99).attempt().unsafeRunSync(),
      NotFoundException().left()
    )
  }

  @Test
  fun `1e handle DB exceptions preserving NotFoundExceptions`() {
    assertEquals(
      IO.handleDBExceptions(IO.raiseError(NotFoundException())).attempt().unsafeRunSync(),
      NotFoundException().left()
    )
  }

  @Test
  fun `1e handle DB exceptions preserving NotFoundExceptions but translating all others to InvalidIdException`() {
    assertEquals(
      IO.handleDBExceptions(IO.raiseError(RuntimeException())).attempt().unsafeRunSync(),
      InvalidIdException().left()
    )
  }

  @Test
  fun `2a Return a not found when house or castle id are invalid`() {
    val housesDB = HousesDatabase()
    val castleDB = CastlesDatabase()
    assertEquals(
      IO
        .houseAndLocationEndpoint(housesDB, castleDB, 99, 99)
        .attempt()
        .unsafeRunSync(),
      NotFoundException().left()
    )
  }

  @Test
  fun `2a Return a house Location pairing a house and castle`() {
    val housesDB = HousesDatabase()
    val castleDB = CastlesDatabase()
    val house = housesDB[2].toOption().getOrElse { fail("house 2 not found") }
    val castle = castleDB[6].toOption().getOrElse { fail("castle 6 not found") }
    assertEquals(
      IO.houseAndLocationEndpoint(
        housesDB,
        castleDB,
        house.houseId,
        castle.castleId
      ).attempt().unsafeRunSync(),
      HouseLocation(house, castle).right()
    )
  }

}
