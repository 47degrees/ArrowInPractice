package com.fortysevendeg.arrowinpractice.endpoints

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * Standard Home / Welcome page endpoint. Just a static read only response that we'll not care much about.
 */
fun Routing.welcomeEndpoint() {
  get("/") {
    call.respond(mapOf("message" to "Welcome to the Game of Thrones API!"))
  }
}
