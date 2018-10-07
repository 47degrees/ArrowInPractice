package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.HousesDatabase
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * GET: Provides a list of GoT Houses.
 */
fun Routing.housesOverviewEndpoint(housesDB: HousesDatabase) {
  get("/houses") {
    call.respond(mapOf("houses" to housesDB.getAll()))
  }
}
