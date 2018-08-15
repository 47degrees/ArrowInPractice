package com.fortysevendeg.arrowinpractice.error

class InvalidHouseIdException(message: String = "Invalid Id format. It must be parsable to Long.") : RuntimeException(message)