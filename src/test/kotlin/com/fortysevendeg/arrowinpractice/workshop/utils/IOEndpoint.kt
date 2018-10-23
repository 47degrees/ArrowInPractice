package com.fortysevendeg.arrowinpractice.workshop.utils

import arrow.Kind
import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.fix
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.util.pipeline.ContextDsl
import io.ktor.util.pipeline.PipelineContext

@JvmName("kindedGet")
@ContextDsl
fun <A : Any> Route.getIO(
  path: String,
  body: suspend IO.Companion.(PipelineContext<Unit, ApplicationCall>) -> Kind<ForIO, A>
): Route =
  get(path) {
    call.respond(body(IO.Companion, this).fix().unsafeRunSync())
  }