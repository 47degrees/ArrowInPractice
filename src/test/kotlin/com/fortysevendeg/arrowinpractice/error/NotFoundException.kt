package com.fortysevendeg.arrowinpractice.error

data class NotFoundException(override val message: String = "Not found.") : RuntimeException(message)