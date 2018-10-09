package com.fortysevendeg.arrowinpractice

import io.ktor.application.Application
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class HousesEndpointsTests {

  @Test
  fun `should return expected houses overview`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Get, "/houses")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        {
          "houses" : [ {
            "houseId" : {
              "id" : 1
            },
            "name" : "Stark",
            "description" : "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell."
          }, {
            "houseId" : {
              "id" : 2
            },
            "name" : "Lannister",
            "description" : "They are the warden of the west or the main house of the west. The rule from the Casterly Rock of the Westerlands."
          }, {
            "houseId" : {
              "id" : 3
            },
            "name" : "Baratheon",
            "description" : "Their dynasty starts after Robert Baratheon defeated The Mad King."
          }, {
            "houseId" : {
              "id" : 4
            },
            "name" : "Targaryen",
            "description" : "They ruled the seven kingdoms for 300 years with the help of their Dragons. But during the rule of the Mad King Aerys they lost their battle against Robert and Ned and lost their claim in the Iron Throne."
          }, {
            "houseId" : {
              "id" : 5
            },
            "name" : "Greyjoy",
            "description" : "They are the lords of Iron Islands rule from the Pyke in the Iron Islands."
          }, {
            "houseId" : {
              "id" : 6
            },
            "name" : "Arryn",
            "description" : "They are the main House of the Vale and rule form a small castle called the Eyrie in the mountain."
          }, {
            "houseId" : {
              "id" : 7
            },
            "name" : "Martell",
            "description" : "They are the ruler of the Dorne , rule from the Sunspear Castle ."
          }, {
            "houseId" : {
              "id" : 8
            },
            "name" : "Tully",
            "description" : "House Tully rule from Riverrun in the Riverlands. Ned Strak’s wife Cathlyn is the daughter of this house."
          }, {
            "houseId" : {
              "id" : 9
            },
            "name" : "Tyrell",
            "description" : "Targaryens made the Tyrells Lords of Highgardens . They are the main house in the Reach and rule from Highgarden."
          } ]
        }
        """.trimIndent(),
        response.content)
    }
  }

  @Test
  fun `should return house details by Id`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Get, "/houses/1")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        {
          "houseId" : {
            "id" : 1
          },
          "name" : "Stark",
          "description" : "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell."
        }
        """.trimIndent(),
        response.content)
    }
  }

  @Test
  fun `should return house details by name`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Get, "/houses/stark")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        {
          "houseId" : {
            "id" : 1
          },
          "name" : "Stark",
          "description" : "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell."
        }
        """.trimIndent(),
        response.content)
    }
  }

  @Test
  fun `should add new house to the database on post`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Post, "/houses") {
      addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
      setBody("""
        {
          "name": "NewHouse",
          "description": "Super interesting new house description. Oh wow."
        }
      """.trimIndent())
    }) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        {
          "houseId" : {
            "id" : 1
          },
          "name" : "Stark",
          "description" : "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell."
        }
        """.trimIndent(),
        response.content)
    }
  }
}
