package com.fortysevendeg.arrowinpractice.error

data class InvalidIdException(override val message: String = "Invalid Id format. It must be parsable to Long.") : RuntimeException(message)