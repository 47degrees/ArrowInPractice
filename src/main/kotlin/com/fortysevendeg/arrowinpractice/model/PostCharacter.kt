package com.fortysevendeg.arrowinpractice.model

/**
 * HttpMethod.POST version of a Character sent by client to create or update a Character.
 */
data class PostCharacter(val houseId: Long,
                         val name: String,
                         val description: String)
