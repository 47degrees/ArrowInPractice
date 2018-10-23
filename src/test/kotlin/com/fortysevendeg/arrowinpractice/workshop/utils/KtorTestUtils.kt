package com.fortysevendeg.arrowinpractice.workshop.utils

import io.ktor.http.Parameters
import io.ktor.request.ApplicationRequest
import io.ktor.response.ApplicationResponse
import io.ktor.server.engine.BaseApplicationCall
import io.ktor.server.testing.TestApplicationCall

class LocalApplicationCall(val call: TestApplicationCall, val params: Parameters) : BaseApplicationCall(call.application) {
  override val request: ApplicationRequest
    get() = call.request
  override val response: ApplicationResponse
    get() = call.response

  override val parameters: Parameters
    get() = params
}
