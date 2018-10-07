package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.HousesDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidHouseFormatException
import com.fortysevendeg.arrowinpractice.model.PostHouse
import io.ktor.application.call
import io.ktor.request.ContentTransformationException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

/**
 * Create a new House or alternatively update an already existing one. It expects a [[PostHouse]] passed in the json
 * payload.
 */
fun Routing.createOrUpdateHouseEndpoint(housesDB: HousesDatabase) {
  post("/houses/") {
    try {
      val postedHouse = call.receive<PostHouse>()
      val created = housesDB.createOrUpdate(postedHouse)
      if (created) {
        call.respond(mapOf("message" to "House created successfully."))
      } else {
        call.respond(mapOf("message" to "A House with the same name was found and updated successfully."))
      }
    } catch (e: ContentTransformationException) {
      throw InvalidHouseFormatException()
    }
  }
}
