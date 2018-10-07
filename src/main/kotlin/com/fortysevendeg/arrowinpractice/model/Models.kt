package com.fortysevendeg.arrowinpractice.model

data class HouseId(val id: Long)

data class House(val id: HouseId, val name: String, val description: String)

data class CharacterId(val id: Long)

data class Character(val houseId: HouseId,
                     val id: CharacterId,
                     val name: String,
                     val description: String)
