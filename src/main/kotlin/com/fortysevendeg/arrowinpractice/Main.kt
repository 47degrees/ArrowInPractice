package com.fortysevendeg.arrowinpractice

import com.fasterxml.jackson.databind.SerializationFeature
import com.fortysevendeg.arrowinpractice.database.HousesMemoryDatabase
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
  val housesStorage = HousesMemoryDatabase()

  embeddedServer(Netty, 8080) {
    install(ContentNegotiation) {
      jackson {
        enable(SerializationFeature.INDENT_OUTPUT) // pretty print json
      }
    }

    routing {
      get("/") {
        call.respondText("Welcome to the Game of Thrones API!", ContentType.Text.Html)
      }
      get("/houses") {
        call.respond(mapOf("houses" to housesStorage.getAll()))
      }
      get("/houses/{houseName}") {
        val houseName = call.request.path().substringAfterLast("/")
        call.respond(mapOf(houseName to housesStorage.getByName(houseName)))
      }
    }
  }.start(wait = true)
}
