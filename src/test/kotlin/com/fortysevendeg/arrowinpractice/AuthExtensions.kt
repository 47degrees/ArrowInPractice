package com.fortysevendeg.arrowinpractice

import com.sun.org.apache.xml.internal.security.utils.Base64
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.testing.TestApplicationCall
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.TestApplicationRequest
import kotlinx.io.core.toByteArray

fun TestApplicationEngine.authorizedRequest(
  method: HttpMethod,
  uri: String,
  setup: TestApplicationRequest.() -> Unit = {}
): TestApplicationCall = handleRequest {
  this.uri = uri
  this.method = method
  addHeader(
    HttpHeaders.Authorization,
    "Basic ${Base64.encode("georgerrmartin:lambdaworldrules".toByteArray(Charsets.UTF_8))}")
  setup()
}
