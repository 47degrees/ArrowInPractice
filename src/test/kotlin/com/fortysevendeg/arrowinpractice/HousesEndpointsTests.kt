package com.fortysevendeg.arrowinpractice

import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class HousesEndpointsTests {

  @Test
  fun `should return expected houses overview`() = withTestApplication(Application::setupModule) {
    with(authorizedRequest(HttpMethod.Get, "/houses")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("{\n" +
        "  \"houses\": [\n" +
        "    {\n" +
        "      \"houseId\": {\n" +
        "        \"id\": 1\n" +
        "      },\n" +
        "      \"name\": \"Stark\",\n" +
        "      \"description\": \"They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell.\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"houseId\": {\n" +
        "        \"id\": 2\n" +
        "      },\n" +
        "      \"name\": \"Lannister\",\n" +
        "      \"description\": \"They are the warden of the west or the main house of the west. The rule from the Casterly Rock of the Westerlands.\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"houseId\": {\n" +
        "        \"id\": 3\n" +
        "      },\n" +
        "      \"name\": \"Baratheon\",\n" +
        "      \"description\": \"Their dynasty starts after Robert Baratheon defeated The Mad King.\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"houseId\": {\n" +
        "        \"id\": 4\n" +
        "      },\n" +
        "      \"name\": \"Targaryen\",\n" +
        "      \"description\": \"They ruled the seven kingdoms for 300 years with the help of their Dragons. But during the rule of the Mad King Aerys they lost their battle against Robert and Ned and lost their claim in the Iron Throne.\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"houseId\": {\n" +
        "        \"id\": 5\n" +
        "      },\n" +
        "      \"name\": \"Greyjoy\",\n" +
        "      \"description\": \"They are the lords of Iron Islands rule from the Pyke in the Iron Islands.\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"houseId\": {\n" +
        "        \"id\": 6\n" +
        "      },\n" +
        "      \"name\": \"Arryn\",\n" +
        "      \"description\": \"They are the main House of the Vale and rule form a small castle called the Eyrie in the mountain.\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"houseId\": {\n" +
        "        \"id\": 7\n" +
        "      },\n" +
        "      \"name\": \"Martell\",\n" +
        "      \"description\": \"They are the ruler of the Dorne , rule from the Sunspear Castle .\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"houseId\": {\n" +
        "        \"id\": 8\n" +
        "      },\n" +
        "      \"name\": \"Tully\",\n" +
        "      \"description\": \"House Tully rule from Riverrun in the Riverlands. Ned Strak’s wife Cathlyn is the daughter of this house.\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"houseId\": {\n" +
        "        \"id\": 9\n" +
        "      },\n" +
        "      \"name\": \"Tyrell\",\n" +
        "      \"description\": \"Targaryens made the Tyrells Lords of Highgardens . They are the main house in the Reach and rule from Highgarden.\"\n" +
        "    }\n" +
        "  ]\n" +
        "}",
        response.content)
    }
  }
}
