package com.fortysevendeg.arrowinpractice.characters

import com.fortysevendeg.arrowinpractice.authorizedRequest
import com.fortysevendeg.arrowinpractice.setupModule
import io.ktor.application.Application
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class PostNewCharacterTest {

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
