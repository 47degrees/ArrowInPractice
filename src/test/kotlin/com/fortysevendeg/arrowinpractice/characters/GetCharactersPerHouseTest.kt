package com.fortysevendeg.arrowinpractice.characters

import com.fortysevendeg.arrowinpractice.authorizedRequest
import com.fortysevendeg.arrowinpractice.setupModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class GetCharactersPerHouseTest {

  @Test
  fun `should return expected characters per house Id`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Get, "/houses/4/characters")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        [ {
          "houseId" : {
            "id" : 4
          },
          "characterId" : {
            "id" : 20
          },
          "castleId" : {
            "id" : 1
          },
          "name" : "Daenerys Targereyn",
          "description" : "Mother of Dragons, Khaleesi"
        }, {
          "houseId" : {
            "id" : 4
          },
          "characterId" : {
            "id" : 21
          },
          "castleId" : {
            "id" : 1
          },
          "name" : "Viserys Targaryen",
          "description" : "Daenerys’s brother"
        }, {
          "houseId" : {
            "id" : 4
          },
          "characterId" : {
            "id" : 22
          },
          "castleId" : {
            "id" : 1
          },
          "name" : "Aerys II Targaryen",
          "description" : "Daenerys’s father, former King of the Seven Kingdoms, deceased"
        } ]
        """.trimIndent(),
        response.content)
    }
  }
}
