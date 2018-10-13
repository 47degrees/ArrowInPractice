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

class CharacterEndpointsTests {

  @Test
  fun `should return expected characters overview`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Get, "/characters")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        {
          "characters" : [ {
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
          }, {
            "houseId" : {
              "id" : 1
            },
            "characterId" : {
              "id" : 2
            },
            "castleId" : {
              "id" : 4
            },
            "name" : "Catelyn Stark",
            "description" : "Ned’s wife"
          }, {
            "houseId" : {
              "id" : 1
            },
            "characterId" : {
              "id" : 3
            },
            "castleId" : {
              "id" : 4
            },
            "name" : "Robb Stark",
            "description" : "Ned and Catelyn’s oldest son, heir to Winterfell"
          }, {
            "houseId" : {
              "id" : 1
            },
            "characterId" : {
              "id" : 4
            },
            "castleId" : {
              "id" : 4
            },
            "name" : "Sansa Stark",
            "description" : "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon"
          }, {
            "houseId" : {
              "id" : 1
            },
            "characterId" : {
              "id" : 5
            },
            "castleId" : {
              "id" : 4
            },
            "name" : "Arya Stark",
            "description" : "Catelyn’s youngest daughter"
          }, {
            "houseId" : {
              "id" : 1
            },
            "characterId" : {
              "id" : 6
            },
            "castleId" : {
              "id" : 4
            },
            "name" : "Bran Stark",
            "description" : "Ned and Catelyn’s middle son"
          }, {
            "houseId" : {
              "id" : 1
            },
            "characterId" : {
              "id" : 7
            },
            "castleId" : {
              "id" : 4
            },
            "name" : "Rickon Stark",
            "description" : "Ned and Catelyn’s youngest son"
          }, {
            "houseId" : {
              "id" : 1
            },
            "characterId" : {
              "id" : 8
            },
            "castleId" : {
              "id" : 4
            },
            "name" : "Jon Snow",
            "description" : "Ned’s illegitimate son, member of the Night’s Watch"
          }, {
            "houseId" : {
              "id" : 1
            },
            "characterId" : {
              "id" : 9
            },
            "castleId" : {
              "id" : 8
            },
            "name" : "Benjen Stark",
            "description" : "Ned’s younger brother, First Ranger of the Night’s Watch"
          }, {
            "houseId" : {
              "id" : 2
            },
            "characterId" : {
              "id" : 10
            },
            "castleId" : {
              "id" : 6
            },
            "name" : "Tywin Lannister",
            "description" : "Patriarch, Lord of Casterly Rock, Warden of the West"
          }, {
            "houseId" : {
              "id" : 2
            },
            "characterId" : {
              "id" : 11
            },
            "castleId" : {
              "id" : 6
            },
            "name" : "Cersei Lannister",
            "description" : "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister"
          }, {
            "houseId" : {
              "id" : 2
            },
            "characterId" : {
              "id" : 12
            },
            "castleId" : {
              "id" : 6
            },
            "name" : "Jaime Lannister",
            "description" : "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard"
          }, {
            "houseId" : {
              "id" : 2
            },
            "characterId" : {
              "id" : 13
            },
            "castleId" : {
              "id" : 6
            },
            "name" : "Tyrion Lannister",
            "description" : "Tywin’s youngest son, acting Hand of the King and Master of Coin"
          }, {
            "houseId" : {
              "id" : 2
            },
            "characterId" : {
              "id" : 14
            },
            "castleId" : {
              "id" : 6
            },
            "name" : "Joffrey Baratheon",
            "description" : "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son"
          }, {
            "houseId" : {
              "id" : 2
            },
            "characterId" : {
              "id" : 15
            },
            "castleId" : {
              "id" : 6
            },
            "name" : "Myrcella Baratheon",
            "description" : "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter"
          }, {
            "houseId" : {
              "id" : 2
            },
            "characterId" : {
              "id" : 16
            },
            "castleId" : {
              "id" : 6
            },
            "name" : "Tommen Baratheon",
            "description" : "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son"
          }, {
            "houseId" : {
              "id" : 3
            },
            "characterId" : {
              "id" : 17
            },
            "castleId" : {
              "id" : 6
            },
            "name" : "Robert Baratheon",
            "description" : "Patriarch, King of the Seven Kingdoms"
          }, {
            "houseId" : {
              "id" : 3
            },
            "characterId" : {
              "id" : 18
            },
            "castleId" : {
              "id" : 1
            },
            "name" : "Stannis Baratheon",
            "description" : "Robert’s younger brother, Renley’s older brother, Lord of Dragonstone"
          }, {
            "houseId" : {
              "id" : 3
            },
            "characterId" : {
              "id" : 19
            },
            "castleId" : {
              "id" : 9
            },
            "name" : "Renly Baratheon",
            "description" : "Youngest of the Baratheon brothers, Lord of Storm’s End"
          }, {
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
          }, {
            "houseId" : {
              "id" : 5
            },
            "characterId" : {
              "id" : 23
            },
            "castleId" : {
              "id" : 10
            },
            "name" : "Balon Greyjoy",
            "description" : "Patriarch, Lord of the Iron Islands"
          }, {
            "houseId" : {
              "id" : 5
            },
            "characterId" : {
              "id" : 24
            },
            "castleId" : {
              "id" : 10
            },
            "name" : "Theon Greyjoy",
            "description" : "Balon’s first-born son, heir apparent to the Iron Islands"
          }, {
            "houseId" : {
              "id" : 5
            },
            "characterId" : {
              "id" : 25
            },
            "castleId" : {
              "id" : 10
            },
            "name" : "Yara Greyjoy",
            "description" : "Balon’s only daughter and oldest child"
          }, {
            "houseId" : {
              "id" : 6
            },
            "characterId" : {
              "id" : 26
            },
            "castleId" : {
              "id" : 5
            },
            "name" : "Jon Arryn",
            "description" : "Patriarch, former Hand of the King, deceased"
          }, {
            "houseId" : {
              "id" : 6
            },
            "characterId" : {
              "id" : 27
            },
            "castleId" : {
              "id" : 5
            },
            "name" : "Lysa Arryn",
            "description" : "Catelyn’s younger sister"
          }, {
            "houseId" : {
              "id" : 6
            },
            "characterId" : {
              "id" : 28
            },
            "castleId" : {
              "id" : 5
            },
            "name" : "Robert Arryn",
            "description" : "Lysa’s son, Lord of the Eyrie"
          }, {
            "houseId" : {
              "id" : 7
            },
            "characterId" : {
              "id" : 29
            },
            "castleId" : {
              "id" : 11
            },
            "name" : "Doran Martell",
            "description" : "Patriarch, Prince of Dorne"
          }, {
            "houseId" : {
              "id" : 7
            },
            "characterId" : {
              "id" : 30
            },
            "castleId" : {
              "id" : 11
            },
            "name" : "Oberyn Martell",
            "description" : "Doran’s youngest brother"
          }, {
            "houseId" : {
              "id" : 8
            },
            "characterId" : {
              "id" : 31
            },
            "castleId" : {
              "id" : 7
            },
            "name" : "Hoster Tully",
            "description" : "Patriarch, Lord of Riverrun, Catelyn and Lysa’s father"
          }, {
            "houseId" : {
              "id" : 8
            },
            "characterId" : {
              "id" : 32
            },
            "castleId" : {
              "id" : 7
            },
            "name" : "Edmure Tully",
            "description" : "Hoster’s only son and heir to Riverrun"
          }, {
            "houseId" : {
              "id" : 9
            },
            "characterId" : {
              "id" : 33
            },
            "castleId" : {
              "id" : 12
            },
            "name" : "Mace Tyrell",
            "description" : "Patriarch, Lord of Highgarden"
          }, {
            "houseId" : {
              "id" : 9
            },
            "characterId" : {
              "id" : 34
            },
            "castleId" : {
              "id" : 12
            },
            "name" : "Olenna Tyrell",
            "description" : "Mace’s mother"
          }, {
            "houseId" : {
              "id" : 9
            },
            "characterId" : {
              "id" : 35
            },
            "castleId" : {
              "id" : 12
            },
            "name" : "Margaery Tyrell",
            "description" : "Mace’s daughter, wife of Renly Baratheon"
          }, {
            "houseId" : {
              "id" : 9
            },
            "characterId" : {
              "id" : 36
            },
            "castleId" : {
              "id" : 12
            },
            "name" : "Loras Tyrell",
            "description" : "Mace’s son, heir to House Tyrell"
          } ]
        }
        """.trimIndent(),
        response.content)
    }
  }

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

  @Test
  fun `should add new character to the database on post`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Post, "/characters") {
      addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
      setBody("""
        {
          "houseId" : "1",
          "name": "New Stark Character",
          "description": "Super interesting new character for the Starks. Oh wow."
        }
      """.trimIndent())
    }) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        {
          "message" : "Character created successfully."
        }
        """.trimIndent(),
        response.content)
    }
  }
}
