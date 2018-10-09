package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.CastlesDatabase
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.database.HousesDatabase
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * GET: This basically dumps the whole DB in a json, so you get all the houses, including all their characters, which
 * at the same time include their respective castle seats.
 *
 * For a flatMapping scenario, you'd get houses first, flatMap them to fetch characters by houseId, then flatMap those
 * to get character castle by castleId, and therefore conform a combined response equivalent to the one we are manually
 * composing here.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun Routing.gameOfThronesEndpoint(
  housesDB: HousesDatabase,
  charactersDB: CharactersDatabase,
  castlesDB: CastlesDatabase
) {
  authenticate {
    get("/got") {
      call.respond(mapOf("houses" to housesDB.getAll().map {
        mapOf(
          "houseId" to it.houseId,
          "name" to it.name,
          "description" to it.description,
          "characters" to charactersDB.getByHouseId(it.houseId).map {
            mapOf(
              "id" to it.characterId,
              "name" to it.name,
              "description" to it.description,
              "castle" to castlesDB[it.castleId]
            )
          })
      }))
    }
  }
}
