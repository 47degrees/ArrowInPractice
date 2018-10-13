package com.fortysevendeg.arrowinpractice.got

import com.fortysevendeg.arrowinpractice.authorizedRequest
import com.fortysevendeg.arrowinpractice.setupModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class GetGotTest {

  @Test
  fun `should return the overall complete GoT combined data`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Get, "/got")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        {
          "houses" : [ {
            "houseId" : {
              "id" : 1
            },
            "name" : "Stark",
            "description" : "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell.",
            "characters" : [ {
              "id" : {
                "id" : 1
              },
              "name" : "Eddard (Ned) Stark",
              "description" : "Patriarch, Lord of Winterfell, Warden of the North",
              "castle" : {
                "castleId" : {
                  "id" : 4
                },
                "name" : "Winterfell",
                "description" : "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
              }
            }, {
              "id" : {
                "id" : 2
              },
              "name" : "Catelyn Stark",
              "description" : "Ned’s wife",
              "castle" : {
                "castleId" : {
                  "id" : 4
                },
                "name" : "Winterfell",
                "description" : "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
              }
            }, {
              "id" : {
                "id" : 3
              },
              "name" : "Robb Stark",
              "description" : "Ned and Catelyn’s oldest son, heir to Winterfell",
              "castle" : {
                "castleId" : {
                  "id" : 4
                },
                "name" : "Winterfell",
                "description" : "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
              }
            }, {
              "id" : {
                "id" : 4
              },
              "name" : "Sansa Stark",
              "description" : "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon",
              "castle" : {
                "castleId" : {
                  "id" : 4
                },
                "name" : "Winterfell",
                "description" : "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
              }
            }, {
              "id" : {
                "id" : 5
              },
              "name" : "Arya Stark",
              "description" : "Catelyn’s youngest daughter",
              "castle" : {
                "castleId" : {
                  "id" : 4
                },
                "name" : "Winterfell",
                "description" : "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
              }
            }, {
              "id" : {
                "id" : 6
              },
              "name" : "Bran Stark",
              "description" : "Ned and Catelyn’s middle son",
              "castle" : {
                "castleId" : {
                  "id" : 4
                },
                "name" : "Winterfell",
                "description" : "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
              }
            }, {
              "id" : {
                "id" : 7
              },
              "name" : "Rickon Stark",
              "description" : "Ned and Catelyn’s youngest son",
              "castle" : {
                "castleId" : {
                  "id" : 4
                },
                "name" : "Winterfell",
                "description" : "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
              }
            }, {
              "id" : {
                "id" : 8
              },
              "name" : "Jon Snow",
              "description" : "Ned’s illegitimate son, member of the Night’s Watch",
              "castle" : {
                "castleId" : {
                  "id" : 4
                },
                "name" : "Winterfell",
                "description" : "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
              }
            }, {
              "id" : {
                "id" : 9
              },
              "name" : "Benjen Stark",
              "description" : "Ned’s younger brother, First Ranger of the Night’s Watch",
              "castle" : {
                "castleId" : {
                  "id" : 8
                },
                "name" : "Castle Black",
                "description" : "Castle Black is the primary headquarters and redoubt of the Night's Watch. It is located roughly halfway along the length of the Wall on its southern side, at the northern end of the Kingsroad. It is a dark and chilling home to its garrison."
              }
            } ]
          }, {
            "houseId" : {
              "id" : 2
            },
            "name" : "Lannister",
            "description" : "They are the warden of the west or the main house of the west. The rule from the Casterly Rock of the Westerlands.",
            "characters" : [ {
              "id" : {
                "id" : 10
              },
              "name" : "Tywin Lannister",
              "description" : "Patriarch, Lord of Casterly Rock, Warden of the West",
              "castle" : {
                "castleId" : {
                  "id" : 6
                },
                "name" : "Red Keep",
                "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
              }
            }, {
              "id" : {
                "id" : 11
              },
              "name" : "Cersei Lannister",
              "description" : "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister",
              "castle" : {
                "castleId" : {
                  "id" : 6
                },
                "name" : "Red Keep",
                "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
              }
            }, {
              "id" : {
                "id" : 12
              },
              "name" : "Jaime Lannister",
              "description" : "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard",
              "castle" : {
                "castleId" : {
                  "id" : 6
                },
                "name" : "Red Keep",
                "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
              }
            }, {
              "id" : {
                "id" : 13
              },
              "name" : "Tyrion Lannister",
              "description" : "Tywin’s youngest son, acting Hand of the King and Master of Coin",
              "castle" : {
                "castleId" : {
                  "id" : 6
                },
                "name" : "Red Keep",
                "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
              }
            }, {
              "id" : {
                "id" : 14
              },
              "name" : "Joffrey Baratheon",
              "description" : "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son",
              "castle" : {
                "castleId" : {
                  "id" : 6
                },
                "name" : "Red Keep",
                "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
              }
            }, {
              "id" : {
                "id" : 15
              },
              "name" : "Myrcella Baratheon",
              "description" : "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter",
              "castle" : {
                "castleId" : {
                  "id" : 6
                },
                "name" : "Red Keep",
                "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
              }
            }, {
              "id" : {
                "id" : 16
              },
              "name" : "Tommen Baratheon",
              "description" : "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son",
              "castle" : {
                "castleId" : {
                  "id" : 6
                },
                "name" : "Red Keep",
                "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
              }
            } ]
          }, {
            "houseId" : {
              "id" : 3
            },
            "name" : "Baratheon",
            "description" : "Their dynasty starts after Robert Baratheon defeated The Mad King.",
            "characters" : [ {
              "id" : {
                "id" : 17
              },
              "name" : "Robert Baratheon",
              "description" : "Patriarch, King of the Seven Kingdoms",
              "castle" : {
                "castleId" : {
                  "id" : 6
                },
                "name" : "Red Keep",
                "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
              }
            }, {
              "id" : {
                "id" : 18
              },
              "name" : "Stannis Baratheon",
              "description" : "Robert’s younger brother, Renley’s older brother, Lord of Dragonstone",
              "castle" : {
                "castleId" : {
                  "id" : 1
                },
                "name" : "Dragonstone",
                "description" : "Dragonstone is the castle that stands upon the eponymous island located in Blackwater Bay. It is the ancestral seat of House Targaryen and was the stronghold of a cadet branch of House Baratheon."
              }
            }, {
              "id" : {
                "id" : 19
              },
              "name" : "Renly Baratheon",
              "description" : "Youngest of the Baratheon brothers, Lord of Storm’s End",
              "castle" : {
                "castleId" : {
                  "id" : 9
                },
                "name" : "Storm's End",
                "description" : "Storm's End is the ancestral seat of House Baratheon, and one of three major castles held by the former royal house, the others being the Red Keep in King's Landing and the island fortress of Dragonstone. Stannis Baratheon was the Lord of Storm's End and Dragonstone. Storm's End is a formidable fortress, located on the southeastern coast of Westeros overlooking Shipbreaker Bay."
              }
            } ]
          }, {
            "houseId" : {
              "id" : 4
            },
            "name" : "Targaryen",
            "description" : "They ruled the seven kingdoms for 300 years with the help of their Dragons. But during the rule of the Mad King Aerys they lost their battle against Robert and Ned and lost their claim in the Iron Throne.",
            "characters" : [ {
              "id" : {
                "id" : 20
              },
              "name" : "Daenerys Targereyn",
              "description" : "Mother of Dragons, Khaleesi",
              "castle" : {
                "castleId" : {
                  "id" : 1
                },
                "name" : "Dragonstone",
                "description" : "Dragonstone is the castle that stands upon the eponymous island located in Blackwater Bay. It is the ancestral seat of House Targaryen and was the stronghold of a cadet branch of House Baratheon."
              }
            }, {
              "id" : {
                "id" : 21
              },
              "name" : "Viserys Targaryen",
              "description" : "Daenerys’s brother",
              "castle" : {
                "castleId" : {
                  "id" : 1
                },
                "name" : "Dragonstone",
                "description" : "Dragonstone is the castle that stands upon the eponymous island located in Blackwater Bay. It is the ancestral seat of House Targaryen and was the stronghold of a cadet branch of House Baratheon."
              }
            }, {
              "id" : {
                "id" : 22
              },
              "name" : "Aerys II Targaryen",
              "description" : "Daenerys’s father, former King of the Seven Kingdoms, deceased",
              "castle" : {
                "castleId" : {
                  "id" : 1
                },
                "name" : "Dragonstone",
                "description" : "Dragonstone is the castle that stands upon the eponymous island located in Blackwater Bay. It is the ancestral seat of House Targaryen and was the stronghold of a cadet branch of House Baratheon."
              }
            } ]
          }, {
            "houseId" : {
              "id" : 5
            },
            "name" : "Greyjoy",
            "description" : "They are the lords of Iron Islands rule from the Pyke in the Iron Islands.",
            "characters" : [ {
              "id" : {
                "id" : 23
              },
              "name" : "Balon Greyjoy",
              "description" : "Patriarch, Lord of the Iron Islands",
              "castle" : {
                "castleId" : {
                  "id" : 10
                },
                "name" : "Pyke",
                "description" : "Pyke is the stronghold and seat of House Greyjoy, located on the island of the same name, which is one of the seven major Iron Islands. The castle is the regional capital of the Iron Islands as a whole. Pyke is an ancient stronghold and the cliff it was built on has been eroded by the sea leaving the towers standing on stone stacks. The towers are connected by swaying rope bridges. The rocky moss covered stone at its base is not suitable for ships landing so traffic to the island flows through the nearby harbor town Lordsport."
              }
            }, {
              "id" : {
                "id" : 24
              },
              "name" : "Theon Greyjoy",
              "description" : "Balon’s first-born son, heir apparent to the Iron Islands",
              "castle" : {
                "castleId" : {
                  "id" : 10
                },
                "name" : "Pyke",
                "description" : "Pyke is the stronghold and seat of House Greyjoy, located on the island of the same name, which is one of the seven major Iron Islands. The castle is the regional capital of the Iron Islands as a whole. Pyke is an ancient stronghold and the cliff it was built on has been eroded by the sea leaving the towers standing on stone stacks. The towers are connected by swaying rope bridges. The rocky moss covered stone at its base is not suitable for ships landing so traffic to the island flows through the nearby harbor town Lordsport."
              }
            }, {
              "id" : {
                "id" : 25
              },
              "name" : "Yara Greyjoy",
              "description" : "Balon’s only daughter and oldest child",
              "castle" : {
                "castleId" : {
                  "id" : 10
                },
                "name" : "Pyke",
                "description" : "Pyke is the stronghold and seat of House Greyjoy, located on the island of the same name, which is one of the seven major Iron Islands. The castle is the regional capital of the Iron Islands as a whole. Pyke is an ancient stronghold and the cliff it was built on has been eroded by the sea leaving the towers standing on stone stacks. The towers are connected by swaying rope bridges. The rocky moss covered stone at its base is not suitable for ships landing so traffic to the island flows through the nearby harbor town Lordsport."
              }
            } ]
          }, {
            "houseId" : {
              "id" : 6
            },
            "name" : "Arryn",
            "description" : "They are the main House of the Vale and rule form a small castle called the Eyrie in the mountain.",
            "characters" : [ {
              "id" : {
                "id" : 26
              },
              "name" : "Jon Arryn",
              "description" : "Patriarch, former Hand of the King, deceased",
              "castle" : {
                "castleId" : {
                  "id" : 5
                },
                "name" : "The Eyrie",
                "description" : "The Eyrie is the principal stronghold of House Arryn. It is located in the Vale of Arryn near the east coast of Westeros. The Eyrie straddles the top of a peak in the Mountains of the Moon several thousand feet above the valley floor below. It is approached by a narrow causeway and road. Those who would approach the Eyrie must pass through three way-castles guarding the ascent - and then, must proceed in single file, making them very vulnerable to archers. For these reasons, the Eyrie is considered impregnable to any attack that does not involve dragons, and its defenses have never been overcome."
              }
            }, {
              "id" : {
                "id" : 27
              },
              "name" : "Lysa Arryn",
              "description" : "Catelyn’s younger sister",
              "castle" : {
                "castleId" : {
                  "id" : 5
                },
                "name" : "The Eyrie",
                "description" : "The Eyrie is the principal stronghold of House Arryn. It is located in the Vale of Arryn near the east coast of Westeros. The Eyrie straddles the top of a peak in the Mountains of the Moon several thousand feet above the valley floor below. It is approached by a narrow causeway and road. Those who would approach the Eyrie must pass through three way-castles guarding the ascent - and then, must proceed in single file, making them very vulnerable to archers. For these reasons, the Eyrie is considered impregnable to any attack that does not involve dragons, and its defenses have never been overcome."
              }
            }, {
              "id" : {
                "id" : 28
              },
              "name" : "Robert Arryn",
              "description" : "Lysa’s son, Lord of the Eyrie",
              "castle" : {
                "castleId" : {
                  "id" : 5
                },
                "name" : "The Eyrie",
                "description" : "The Eyrie is the principal stronghold of House Arryn. It is located in the Vale of Arryn near the east coast of Westeros. The Eyrie straddles the top of a peak in the Mountains of the Moon several thousand feet above the valley floor below. It is approached by a narrow causeway and road. Those who would approach the Eyrie must pass through three way-castles guarding the ascent - and then, must proceed in single file, making them very vulnerable to archers. For these reasons, the Eyrie is considered impregnable to any attack that does not involve dragons, and its defenses have never been overcome."
              }
            } ]
          }, {
            "houseId" : {
              "id" : 7
            },
            "name" : "Martell",
            "description" : "They are the ruler of the Dorne , rule from the Sunspear Castle .",
            "characters" : [ {
              "id" : {
                "id" : 29
              },
              "name" : "Doran Martell",
              "description" : "Patriarch, Prince of Dorne",
              "castle" : {
                "castleId" : {
                  "id" : 11
                },
                "name" : "Sunspear",
                "description" : "Sunspear is the capital of Dorne, southernmost of the Seven Kingdoms, located in the far southeast of the continent on the Summer Sea. It consists of a strong, fortified castle and a town that sprawls around it. It is a town built largely of mud and straw. The town's largest structure is Spear Tower, a 150-feet structure with a pinnacle of shining steel."
              }
            }, {
              "id" : {
                "id" : 30
              },
              "name" : "Oberyn Martell",
              "description" : "Doran’s youngest brother",
              "castle" : {
                "castleId" : {
                  "id" : 11
                },
                "name" : "Sunspear",
                "description" : "Sunspear is the capital of Dorne, southernmost of the Seven Kingdoms, located in the far southeast of the continent on the Summer Sea. It consists of a strong, fortified castle and a town that sprawls around it. It is a town built largely of mud and straw. The town's largest structure is Spear Tower, a 150-feet structure with a pinnacle of shining steel."
              }
            } ]
          }, {
            "houseId" : {
              "id" : 8
            },
            "name" : "Tully",
            "description" : "House Tully rule from Riverrun in the Riverlands. Ned Strak’s wife Cathlyn is the daughter of this house.",
            "characters" : [ {
              "id" : {
                "id" : 31
              },
              "name" : "Hoster Tully",
              "description" : "Patriarch, Lord of Riverrun, Catelyn and Lysa’s father",
              "castle" : {
                "castleId" : {
                  "id" : 7
                },
                "name" : "Riverrun",
                "description" : "Riverrun is the former seat of House Tully, which is now occupied by its new lawful rulers House Frey. It is a large castle located in the central-western part of the Riverlands. It sits at the point where the Red Fork of the Trident River is joined by its major tributary, the Tumblestone River flowing out of the west. In times of danger, sluice gates can be opened to flood a channel cut to the west of the castle, turning Riverrun into an island. Its walls rise sheer from the waters and its towers command the opposite shores, making assaulting it almost impossible without huge casualties."
              }
            }, {
              "id" : {
                "id" : 32
              },
              "name" : "Edmure Tully",
              "description" : "Hoster’s only son and heir to Riverrun",
              "castle" : {
                "castleId" : {
                  "id" : 7
                },
                "name" : "Riverrun",
                "description" : "Riverrun is the former seat of House Tully, which is now occupied by its new lawful rulers House Frey. It is a large castle located in the central-western part of the Riverlands. It sits at the point where the Red Fork of the Trident River is joined by its major tributary, the Tumblestone River flowing out of the west. In times of danger, sluice gates can be opened to flood a channel cut to the west of the castle, turning Riverrun into an island. Its walls rise sheer from the waters and its towers command the opposite shores, making assaulting it almost impossible without huge casualties."
              }
            } ]
          }, {
            "houseId" : {
              "id" : 9
            },
            "name" : "Tyrell",
            "description" : "Targaryens made the Tyrells Lords of Highgardens . They are the main house in the Reach and rule from Highgarden.",
            "characters" : [ {
              "id" : {
                "id" : 33
              },
              "name" : "Mace Tyrell",
              "description" : "Patriarch, Lord of Highgarden",
              "castle" : {
                "castleId" : {
                  "id" : 12
                },
                "name" : "Highgarden",
                "description" : "Highgarden was the seat of House Tyrell and the regional capital of the Reach. Located on the banks of the river Mander, Highgarden sits astride the Roseroad, a major thoroughfare linking Oldtown and King's Landing. Highgarden also forms the southern terminus of the the Searoad, which leads to Lannisport. As King's Landing, Oldtown, and Lannisport are the first, second, and third largest cities in the realm, heavy trade and traffic across a large swath of southern Westeros ultimately passes through Highgarden."
              }
            }, {
              "id" : {
                "id" : 34
              },
              "name" : "Olenna Tyrell",
              "description" : "Mace’s mother",
              "castle" : {
                "castleId" : {
                  "id" : 12
                },
                "name" : "Highgarden",
                "description" : "Highgarden was the seat of House Tyrell and the regional capital of the Reach. Located on the banks of the river Mander, Highgarden sits astride the Roseroad, a major thoroughfare linking Oldtown and King's Landing. Highgarden also forms the southern terminus of the the Searoad, which leads to Lannisport. As King's Landing, Oldtown, and Lannisport are the first, second, and third largest cities in the realm, heavy trade and traffic across a large swath of southern Westeros ultimately passes through Highgarden."
              }
            }, {
              "id" : {
                "id" : 35
              },
              "name" : "Margaery Tyrell",
              "description" : "Mace’s daughter, wife of Renly Baratheon",
              "castle" : {
                "castleId" : {
                  "id" : 12
                },
                "name" : "Highgarden",
                "description" : "Highgarden was the seat of House Tyrell and the regional capital of the Reach. Located on the banks of the river Mander, Highgarden sits astride the Roseroad, a major thoroughfare linking Oldtown and King's Landing. Highgarden also forms the southern terminus of the the Searoad, which leads to Lannisport. As King's Landing, Oldtown, and Lannisport are the first, second, and third largest cities in the realm, heavy trade and traffic across a large swath of southern Westeros ultimately passes through Highgarden."
              }
            }, {
              "id" : {
                "id" : 36
              },
              "name" : "Loras Tyrell",
              "description" : "Mace’s son, heir to House Tyrell",
              "castle" : {
                "castleId" : {
                  "id" : 12
                },
                "name" : "Highgarden",
                "description" : "Highgarden was the seat of House Tyrell and the regional capital of the Reach. Located on the banks of the river Mander, Highgarden sits astride the Roseroad, a major thoroughfare linking Oldtown and King's Landing. Highgarden also forms the southern terminus of the the Searoad, which leads to Lannisport. As King's Landing, Oldtown, and Lannisport are the first, second, and third largest cities in the realm, heavy trade and traffic across a large swath of southern Westeros ultimately passes through Highgarden."
              }
            } ]
          } ]
        }
        """.trimIndent(),
        response.content)
    }
  }
}
