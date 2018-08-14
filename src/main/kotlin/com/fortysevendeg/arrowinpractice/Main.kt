package com.fortysevendeg.arrowinpractice

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
  embeddedServer(Netty, 8080) {
    install(ContentNegotiation) {
      jackson {}
    }

    routing {
      get("/") {
        call.respondText("Welcome to the Game of Thrones API!", ContentType.Text.Html)
      }
      get("/houses") {
        call.respond(mapOf("OK" to true))
      }
    }
  }.start(wait = true)
}
