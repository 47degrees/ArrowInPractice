package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * Provides the complete list of GoT characters available overall.
 */
fun Routing.charactersOverviewEndpoint(charactersDB: CharactersDatabase) {
  get("/characters/") {
    call.respond(mapOf("characters" to charactersDB.getAll()))
  }
}
