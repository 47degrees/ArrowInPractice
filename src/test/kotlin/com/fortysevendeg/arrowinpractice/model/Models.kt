package com.fortysevendeg.arrowinpractice.model

data class HouseId(val id: Long)

data class House(
  val houseId: Long,
  val name: String,
  val description: String
)

data class CharacterId(val id: Long)

data class CastleId(val id: Long)

data class Character(val houseId: Long,
                     val characterId: Long,
                     val castleId: Long,
                     val name: String,
                     val description: String)

data class Castle(val castleId: Long, val name: String, val description: String)

data class HouseLocation(val house: House, val castle: Castle)
