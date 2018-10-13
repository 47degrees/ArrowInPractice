package com.fortysevendeg.arrowinpractice.characters

import com.fortysevendeg.arrowinpractice.authorizedRequest
import com.fortysevendeg.arrowinpractice.setupModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class GetCharacterDetailsTest {

  @Test
  fun `should return character details by Id`() = withTestApplication(Application::setupModule) {
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
