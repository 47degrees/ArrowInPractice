package com.fortysevendeg.arrowinpractice.endpoints

import arrow.Kind
import arrow.core.Try
import arrow.core.recoverWith
import arrow.effects.typeclasses.Effect
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.serialization.characterId
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.pipeline.PipelineContext
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.util.pipeline.ContextDsl

/**
 * GET: Provides the character details for a given character Id.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun <F> Routing.characterDetailsEndpoint(effect: Effect<F>, charactersDB: CharactersDatabase) {
  authenticate {
    get("/characters/{characterId}") {
      val param = call.request.path().substringAfterLast("/")
      Try {
        val characterId = param.toLong().characterId()
        val maybeCharacter = charactersDB.getById(characterId)
        maybeCharacter.fold(
          ifEmpty = { throw NotFoundException() },
          ifSome = { character -> effect.just(call.respond(character)) })
      }.recoverWith { error ->
        when (error) {
          is NotFoundException -> throw NotFoundException()
          else -> throw InvalidIdException()
        }
      }
    }
  }
}

@ContextDsl
fun <F, A : Any> Route.get(path: String, body: suspend PipelineContext<Unit, ApplicationCall>.() -> Kind<F, A>): Route =
  get(path) {
    body()
  }
