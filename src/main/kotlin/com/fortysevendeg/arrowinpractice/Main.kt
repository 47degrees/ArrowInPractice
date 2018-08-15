package com.fortysevendeg.arrowinpractice

import com.fasterxml.jackson.databind.SerializationFeature
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.database.HousesMemoryDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidHouseIdException
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
  val housesDB = HousesMemoryDatabase()
  val charactersDB = CharactersDatabase()

  embeddedServer(Netty, 8080) {
    install(ContentNegotiation) {
      jackson {
        enable(SerializationFeature.INDENT_OUTPUT) // pretty print json
      }
    }

    install(StatusPages) {
      exception<InvalidHouseIdException> { exception ->
        call.respond(HttpStatusCode.BadRequest, mapOf("error" to (exception.message ?: "")))
      }
    }

    routing {
      get("/") {
        call.respondText("Welcome to the Game of Thrones API!", ContentType.Text.Html)
      }

      get("/houses") {
        call.respond(mapOf("houses" to housesDB.getAll()))
      }

      get("/houses/{param}") {
        val param = call.request.path().substringAfterLast("/")
        try {
          val houseId = param.toLong()
          call.respond(mapOf(houseId to housesDB.getById(houseId)))
        } catch (e: NumberFormatException) {
          call.respond(mapOf(param to housesDB.getByName(param)))
        }
      }

      get("/houses/{houseId}/characters") {
        val houseId = call.request.path().substringAfter("/houses/").substringBefore("/characters")
        try {
          val id = houseId.toLong()
          call.respond(mapOf("characters" to charactersDB.getByHouseId(id)))
        } catch (e: NumberFormatException) {
          throw InvalidHouseIdException("Invalid credentials")
        }
      }

      get("/characters/") {
        call.respond(mapOf("characters" to charactersDB.getAll()))
      }

      get("/characters/{param}") {
        val param = call.request.path().substringAfterLast("/")
        try {
          val characterId = param.toLong()
          call.respond(mapOf(characterId to charactersDB.getById(characterId)))
        } catch (e: NumberFormatException) {
          call.respond(mapOf(param to charactersDB.getByName(param)))
        }
      }
    }
  }.start(wait = true)
}
