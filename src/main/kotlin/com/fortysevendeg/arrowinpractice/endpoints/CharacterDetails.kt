package com.fortysevendeg.arrowinpractice.endpoints

import arrow.Kind
import arrow.core.Option
import arrow.core.toOption
import arrow.effects.*
import arrow.typeclasses.binding
import com.fortysevendeg.arrowinpractice.database.CharactersDatabase
import com.fortysevendeg.arrowinpractice.error.InvalidIdException
import com.fortysevendeg.arrowinpractice.error.NotFoundException
import com.fortysevendeg.arrowinpractice.model.Character
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.util.pipeline.ContextDsl
import io.ktor.util.pipeline.PipelineContext

private fun paramOf(name: String, app: PipelineContext<Unit, ApplicationCall>): Option<String> =
  app.call.parameters[name].toOption()

private fun IO.Companion.characterIdOrNotFound(maybeId: Option<String>): IO<String> =
  maybeId.fold(
    { raiseError(NotFoundException()) },
    { just(it) }
  )

private fun IO.Companion.stringIdToLong(characterIdString: String): IO<Long> =
  IO { characterIdString.toLong() }.handleErrorWith { raiseError(InvalidIdException()) }

private fun IO.Companion.fetchCharacterById(charactersDB: CharactersDatabase, characterId: Long): IO<Character> =
  charactersDB.getById(characterId).fold(
    { raiseError(NotFoundException()) },
    { just(it) }
  )

private fun IO.Companion.handleDBExceptions(charactersFetch: IO<Character>): IO<Character> =
  charactersFetch.handleErrorWith {
    when (it) {
      is NotFoundException -> raiseError(NotFoundException())
      else -> raiseError(InvalidIdException())
    }
  }

/**
 * GET: Provides the character details for a given character Id.
 *
 * Authentication: Basic (user:password encoded in Base64).
 */
fun Routing.characterDetailsEndpoint(charactersDB: CharactersDatabase) {
  authenticate {
    getIO("/characters/{characterId}") { app ->
      monad().binding {
        val maybeId: Option<String> = paramOf("characterId", app)
        val characterIdString: String = characterIdOrNotFound(maybeId).bind()
        val characterId = stringIdToLong(characterIdString).bind()
        val character = handleDBExceptions(fetchCharacterById(charactersDB, characterId)).bind()
        character
      }
    }
  }
}

@JvmName("kindedGet")
@ContextDsl
fun <A : Any> Route.getIO(
  path: String,
  body: suspend IO.Companion.(PipelineContext<Unit, ApplicationCall>) -> Kind<ForIO, A>
): Route =
  get(path) {
    call.respond(body(IO.Companion, this).fix().unsafeRunSync())
  }

@JvmName("monoGet")
@ContextDsl
fun <A : Any> Route.get(
  path: String,
  body: suspend PipelineContext<Unit, ApplicationCall>.() -> A
): Route =
  get(path) {
    call.respond(body())
  }


