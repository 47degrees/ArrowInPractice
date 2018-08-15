package com.fortysevendeg.arrowinpractice

import com.fasterxml.jackson.databind.SerializationFeature
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.database.HousesMemoryDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NoCharactersFoundForHouse
import com.fortysevendeg.arrowinpractice.error.NotFoundException
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
      exception<InvalidIdException> { exception ->
        call.respond(HttpStatusCode.BadRequest, mapOf("error" to (exception.message ?: "")))
      }
      exception<NotFoundException> { exception ->
        call.respond(HttpStatusCode.NotFound, mapOf("error" to (exception.message ?: "")))
      }
      exception<NoCharactersFoundForHouse> { exception ->
        call.respond(HttpStatusCode.NotFound, mapOf("error" to (exception.message ?: "")))
      }
    }

    routing {
      get("/") {
        call.respond(mapOf("message" to "Welcome to the Game of Thrones API!"))
      }

      get("/houses") {
        call.respond(mapOf("houses" to housesDB.getAll()))
      }

      get("/houses/{param}") {
        val param = call.request.path().substringAfterLast("/")
        try {
          val houseId = param.toLong()
          val house = housesDB.getById(houseId)
          house?.let { call.respond(mapOf(houseId to house)) } ?: throw NotFoundException()
        } catch (e: NumberFormatException) {
          val house = housesDB.getByName(param)
          house?.let { call.respond(mapOf(param to house)) } ?: throw NotFoundException()
        }
      }

      get("/houses/{houseId}/characters") {
        val houseId = call.request.path().substringAfter("/houses/").substringBefore("/characters")
        try {
          val id = houseId.toLong()
          val characters = charactersDB.getByHouseId(id)
          if (characters.isNotEmpty()) {
            call.respond(mapOf("characters" to characters))
          } else {
            throw NoCharactersFoundForHouse()
          }
        } catch (e: NumberFormatException) {
          throw InvalidIdException()
        }
      }

      get("/characters/") {
        call.respond(mapOf("characters" to charactersDB.getAll()))
      }

      get("/characters/{characterId}") {
        val param = call.request.path().substringAfterLast("/")
        try {
          val characterId = param.toLong()
          val character = charactersDB.getById(characterId)
          character?.let { call.respond(mapOf(characterId to character)) } ?: throw NotFoundException()
        } catch (e: NumberFormatException) {
          throw InvalidIdException()
        }
      }
    }
  }.start(wait = true)
}
