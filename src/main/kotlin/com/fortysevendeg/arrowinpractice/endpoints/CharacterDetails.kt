package com.fortysevendeg.arrowinpractice.endpoints

import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.serialization.cId
import io.ktor.application.call
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

/**
 * GET: Provides the character details for a given character Id.
 */
fun Routing.characterDetailsEndpoint(charactersDB: CharactersDatabase) {
  get("/characters/{characterId}") {
    val param = call.request.path().substringAfterLast("/")
    try {
      val characterId = param.toLong().cId()
      val maybeCharacter = charactersDB.getById(characterId)
      maybeCharacter?.let { character -> call.respond(mapOf(characterId to character)) } ?: throw NotFoundException()
    } catch (e: NumberFormatException) {
      throw InvalidIdException()
    }
  }
}
