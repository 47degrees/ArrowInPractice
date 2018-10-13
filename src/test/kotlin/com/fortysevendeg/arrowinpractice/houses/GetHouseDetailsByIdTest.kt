package com.fortysevendeg.arrowinpractice.houses

import com.fortysevendeg.arrowinpractice.authorizedRequest
import com.fortysevendeg.arrowinpractice.setupModule
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class GetHouseDetailsByIdTest {

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
}
