package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.CastlesDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.model.CastleId
import com.fortysevendeg.arrowinpractice.serialization.castleId
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * GET: Provides Castle details for both Castles where Jamie Lannister has a seat (Casterly Rock & Red Keep).
 *
 * Both database queries are completely independent and can fail, but we want to combine results in the end into a
 * single list of Castles.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun Routing.jamieLannisterSeatsEndpoint(castlesDB: CastlesDatabase) {
  authenticate {
    get("/jamielannister/seats") { // CastleIds 3 & 6 (Casterly Rock and Red Keep)
      val casterlyRock = try {
        castlesDB[CastleId(3)] ?: throw NotFoundException()
      } catch (e: NumberFormatException) {
        throw InvalidIdException()
      }

      val redKeep = try {
        castlesDB[CastleId(6)] ?: throw NotFoundException()
      } catch (e: NumberFormatException) {
        throw InvalidIdException()
      }

      call.respond(listOf(casterlyRock, redKeep))
    }
  }
}
