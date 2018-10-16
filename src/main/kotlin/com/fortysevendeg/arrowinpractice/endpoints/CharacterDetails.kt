package com.fortysevendeg.arrowinpractice.endpoints

import arrow.core.Option
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.model.Character
import com.fortysevendeg.arrowinpractice.serialization.characterId
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import java.lang.NumberFormatException

/**
 * GET: Provides the character details for a given character Id.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun Routing.characterDetailsEndpoint(charactersDB: CharactersDatabase) {
  authenticate {
    get("/characters/{characterId}") {
      val param = call.request.path().substringAfterLast("/")
      try {
        val characterId = param.toLong().characterId()
        val maybeCharacter: Option<Character> = charactersDB.getById(characterId)
        // if not found throw NotFoundException
        maybeCharacter.fold({ throw NotFoundException() }) { character ->
          call.respond(character)
        }
      } catch (e: NumberFormatException) {
        throw InvalidIdException()
      }
    }
  }
}
