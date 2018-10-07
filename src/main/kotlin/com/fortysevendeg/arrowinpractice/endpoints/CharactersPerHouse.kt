package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NoCharactersFoundForHouse
import com.fortysevendeg.arrowinpractice.serialization.hId
import io.ktor.application.call
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * Provides a list of characters for a given house Id.
 */
fun Routing.charactersPerHouseEndpoint(charactersDB: CharactersDatabase) {
  get("/houses/{houseId}/characters") {
    val houseId = call.request.path().substringAfter("/houses/").substringBefore("/characters")
    try {
      val id = houseId.toLong().hId()
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
