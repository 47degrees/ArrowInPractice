package com.fortysevendeg.arrowinpractice.workshop

import com.fasterxml.jackson.databind.SerializationFeature
import com.fortysevendeg.arrowinpractice.database.CastlesDatabase
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.database.HousesDatabase
import com.fortysevendeg.arrowinpractice.workshop.ex1.characterDetailsEndpoint
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.workshop.ex1.houseAndLocationEndpoint
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.basic
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
  embeddedServer(
    factory = Netty,
    watchPaths = listOf("arrowinpractice"),
    port = 8080,
    module = Application::setupModule
  ).apply { start(wait = true) }
}

fun Application.setupModule() {
  val housesDB = HousesDatabase()
  val charactersDB = CharactersDatabase()
  val castlesDB = CastlesDatabase()

  setupAuthentication()
  setupContentNegotiation()
  setupStatusCodes()
  setupRoutes(housesDB, charactersDB, castlesDB)
}

private fun Application.setupAuthentication() {
  install(Authentication) {
    basic {
      realm = "got-api"
      validate { credentials -> if (isValid(credentials)) UserIdPrincipal("georgerrmartin") else null }
    }
  }
}

private fun isValid(it: UserPasswordCredential) =
  it.name == "georgerrmartin" && it.password == "lambdaworldrules"

private fun Application.setupContentNegotiation() {
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
  }
}

private fun Application.setupRoutes(housesDB: HousesDatabase, charactersDB: CharactersDatabase, castlesDB: CastlesDatabase) {
  routing {
    characterDetailsEndpoint(charactersDB)
    houseAndLocationEndpoint(housesDB, castlesDB)
  }
}
