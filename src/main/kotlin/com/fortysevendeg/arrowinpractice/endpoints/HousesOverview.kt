package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.HousesDatabase
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * GET: Provides a list of GoT Houses.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun Routing.housesOverviewEndpoint(housesDB: HousesDatabase) {
  authenticate {
    get("/houses") {
      call.respond(mapOf("houses" to housesDB.getAll()))
    }
  }
}
