package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.HousesDatabase
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.serialization.hId
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * GET: Provides the details of a GoT House. {param} can be a house Id or alternatively a house name.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun Routing.houseDetailsEndpoint(housesDB: HousesDatabase) {
  authenticate {
    get("/houses/{param}") {
      val param = call.request.path().substringAfterLast("/")
      try {
        val houseId = param.toLong().hId()
        val maybeHouse = housesDB[houseId]
        maybeHouse?.let { house -> call.respond(mapOf(houseId to house)) } ?: throw NotFoundException()
      } catch (e: NumberFormatException) { // then param is not a long, so we assume it's a house name.
        val maybeHouse = housesDB[param]
        maybeHouse?.let { house -> call.respond(mapOf(param to house)) } ?: throw NotFoundException()
      }
    }
  }
}
