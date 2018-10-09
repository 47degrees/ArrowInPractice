package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.CastlesDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.serialization.castleId
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * GET: Provides the castle details for a given castle Id.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun Routing.castleDetailsEndpoint(castlesDB: CastlesDatabase) {
  authenticate {
    get("/castles/{castleId}") {
      val param = call.request.path().substringAfterLast("/")
      try {
        val castleId = param.toLong().castleId()
        val maybeCastle = castlesDB[castleId]
        maybeCastle?.let { castle -> call.respond(mapOf(castleId to castle)) } ?: throw NotFoundException()
      } catch (e: NumberFormatException) {
        throw InvalidIdException()
      }
    }
  }
}
