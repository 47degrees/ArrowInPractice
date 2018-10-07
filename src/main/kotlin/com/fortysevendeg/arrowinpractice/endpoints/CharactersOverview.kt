package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * GET: Provides the complete list of GoT characters available overall.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun Routing.charactersOverviewEndpoint(charactersDB: CharactersDatabase) {
  authenticate {
    get("/characters/") {
      call.respond(mapOf("characters" to charactersDB.getAll()))
    }
  }
}
