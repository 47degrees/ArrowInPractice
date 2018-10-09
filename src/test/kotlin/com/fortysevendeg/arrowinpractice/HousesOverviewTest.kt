package com.fortysevendeg.arrowinpractice

import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class HousesOverviewTest {

  @Test
  fun testRequests() = withTestApplication(Application::setupModule) {
    with(handleRequest(HttpMethod.Get, "/")) {
      assertEquals(HttpStatusCode.OK, response.status())
      assertEquals("Hello from Ktor Testable sample application", response.content)
    }
  }
}
