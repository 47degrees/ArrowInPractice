package com.fortysevendeg.arrowinpractice.castles

import com.fortysevendeg.arrowinpractice.authorizedRequest
import com.fortysevendeg.arrowinpractice.setupModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals


class GetJamieLannisterSeatsTest {

  @Test
  fun `should return Castles where Jamie has a seat`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Get, "/jamielannister/seats")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        [ {
          "castleId" : {
            "id" : 3
          },
          "name" : "Casterly Rock",
          "description" : "Casterly Rock is the ancestral stronghold of House Lannister. It is located on the Western coast of Westeros on a rocky promontory overlooking the Sunset Sea. It overlooks the major city of Lannisport. A major goldmine is located under Casterly Rock. It is one of the most productive in the realm and provides House Lannister with their wealth."
        }, {
          "castleId" : {
            "id" : 6
          },
          "name" : "Red Keep",
          "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
        } ]
        """.trimIndent(),
        response.content)
    }
  }
}
