package com.fortysevendeg.arrowinpractice.houses

import com.fortysevendeg.arrowinpractice.authorizedRequest
import com.fortysevendeg.arrowinpractice.workshop.setupModule
import io.ktor.application.Application
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class PostNewHouseTest {

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
          "message" : "House created successfully."
        }
        """.trimIndent(),
        response.content)
    }
  }
}
