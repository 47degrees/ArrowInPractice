package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidCharacterFormatException
import com.fortysevendeg.arrowinpractice.model.PostCharacter
import io.ktor.application.call
import io.ktor.request.ContentTransformationException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

/**
 * Creates a new Character or alternatively updates an already existing one. It expects a [[PostCharacter]] passed in
 * the json payload.
 */
fun Routing.createOrUpdateCharacterEndpoint(charactersDB: CharactersDatabase) {
  post("/characters/") {
    try {
      val postedCharacter = call.receive<PostCharacter>()
      val created = charactersDB.createOrUpdate(postedCharacter)
      if (created) {
        call.respond(mapOf("message" to "Character created successfully."))
      } else {
        call.respond(mapOf("message" to "A Character with the same name was found and updated successfully."))
      }
    } catch (e: ContentTransformationException) {
      throw InvalidCharacterFormatException()
    }
  }
}
