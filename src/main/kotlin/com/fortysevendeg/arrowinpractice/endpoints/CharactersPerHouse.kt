package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NoCharactersFoundForHouse
import com.fortysevendeg.arrowinpractice.serialization.houseId
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * GET: Provides a list of characters for a given house Id.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun Routing.charactersPerHouseEndpoint(charactersDB: CharactersDatabase) {
  authenticate {
    get("/houses/{houseId}/characters") {
      val houseId = call.request.path().substringAfter("/houses/").substringBefore("/characters")
      try {
        val id = houseId.toLong().houseId()
        val characters = charactersDB.getByHouseId(id)
        if (characters.isNotEmpty()) {
          call.respond(characters)
        } else {
          throw NoCharactersFoundForHouse()
        }
      } catch (e: NumberFormatException) {
        throw InvalidIdException()
      }
    }
  }
}
