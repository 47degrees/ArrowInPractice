package com.fortysevendeg.arrowinpractice.model

/**
 * HttpMethod.POST version of a House sent by client to create or update a House.
 */
data class PostHouse(val name: String, val description: String)

/**
 * HttpMethod.POST version of a Character sent by client to create or update a Character.
 */
data class PostCharacter(val houseId: Long,
                         val castleId: Long,
                         val name: String,
                         val description: String)
