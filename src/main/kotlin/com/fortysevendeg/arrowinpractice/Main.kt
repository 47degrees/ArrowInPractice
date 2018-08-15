package com.fortysevendeg.arrowinpractice

import com.fasterxml.jackson.databind.SerializationFeature
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.database.HousesDatabase
import com.fortysevendeg.arrowinpractice.error.*
import com.fortysevendeg.arrowinpractice.model.PostCharacter
import com.fortysevendeg.arrowinpractice.model.PostHouse
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.ContentTransformationException
import io.ktor.request.path
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
  val housesDB = HousesDatabase()
  val charactersDB = CharactersDatabase()

  embeddedServer(Netty, 8080) {
    enableJacksonContentNegotiation()
    setupStatusCodes()

    routing {
      welcomeEndpoint()
      housesOverviewEndpoint(housesDB)
      houseDetailsEndpoint(housesDB)
      createOrUpdateHouseEndpoint(housesDB)
      charactersPerHouseEndpoint(charactersDB)
      charactersOverviewEndpoint(charactersDB)
      characterDetailsEndpoint(charactersDB)
      createOrUpdateCharacterEndpoint(charactersDB)
    }
  }.start(wait = true)
}

private fun Application.enableJacksonContentNegotiation() {
  install(ContentNegotiation) {
    jackson {
      enable(SerializationFeature.INDENT_OUTPUT) // pretty print json
    }
  }
}

private fun Application.setupStatusCodes() {
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
    exception<InvalidHouseFormatException> { exception ->
      call.respond(HttpStatusCode.BadRequest, mapOf("error" to (exception.message ?: "")))
    }
  }
}

private fun Routing.welcomeEndpoint() {
  get("/") {
    call.respond(mapOf("message" to "Welcome to the Game of Thrones API!"))
  }
}

private fun Routing.housesOverviewEndpoint(housesDB: HousesDatabase) {
  get("/houses") {
    call.respond(mapOf("houses" to housesDB.getAll()))
  }
}

private fun Routing.houseDetailsEndpoint(housesDB: HousesDatabase) {
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
}

private fun Routing.createOrUpdateHouseEndpoint(housesDB: HousesDatabase) {
  post("/houses/") {
    try {
      val postedHouse = call.receive<PostHouse>()
      val created = housesDB.createOrUpdate(postedHouse)
      if (created) {
        call.respond(mapOf("message" to "House created successfully."))
      } else {
        call.respond(mapOf("message" to "A House with the same name was found and updated successfully."))
      }
    } catch (e: ContentTransformationException) {
      throw InvalidHouseFormatException()
    }
  }
}

private fun Routing.charactersPerHouseEndpoint(charactersDB: CharactersDatabase) {
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
}

private fun Routing.charactersOverviewEndpoint(charactersDB: CharactersDatabase) {
  get("/characters/") {
    call.respond(mapOf("characters" to charactersDB.getAll()))
  }
}

private fun Routing.characterDetailsEndpoint(charactersDB: CharactersDatabase) {
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

private fun Routing.createOrUpdateCharacterEndpoint(charactersDB: CharactersDatabase) {
  post("/characters/") {
    try {
      val postedCharacter = call.receive<PostCharacter>()
      val created = charactersDB.createOrUpdate(postedCharacter)
      if (created) {
        call.respond(mapOf("message" to "Character created successfully."))
      } else {
        call.respond(mapOf("message" to "A Character with the same name was found and updated successfully."))
      }
    } catch (e: ContentTransformationException) {
      throw InvalidCharacterFormatException()
    }
  }
}
