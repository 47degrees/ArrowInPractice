package com.fortysevendeg.arrowinpractice

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HousesOverviewTest {

  @Test
  fun `GET houses overview returns expected serialized housesasdasd`() = withTestApplication {
    val call = handleRequest(HttpMethod.Get, "/") {
      addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
    }
    with(call) {
      assertEquals(HttpStatusCode.OK, response.status())
    }
  }

  @Test
  fun `GET houses overview returns expected serialized houses`(): Unit {
    withTestApplication(
      moduleFunction = {
        main(arrayOf())
      }
    ) {
      handleRequest(HttpMethod.Get, "/").apply {
        assertTrue { response.content!!.contains("You need to upload some videos to watch them") }
      }
    }
  }
}
