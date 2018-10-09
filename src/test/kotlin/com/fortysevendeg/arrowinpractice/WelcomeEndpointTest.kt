package com.fortysevendeg.arrowinpractice

import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class WelcomeEndpointTest {

  @Test
  fun `should welcome the user`() = withTestApplication(Application::setupModule) {
    with(handleRequest(HttpMethod.Get, "/")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("""
        {
          "message" : "Welcome to the Game of Thrones API!"
        }
      """.trimIndent(),
        response.content)
    }
  }
}
