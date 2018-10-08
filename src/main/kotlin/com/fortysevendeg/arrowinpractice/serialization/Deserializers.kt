package com.fortysevendeg.arrowinpractice.serialization

import com.fortysevendeg.arrowinpractice.model.CastleId
import com.fortysevendeg.arrowinpractice.model.CharacterId
import com.fortysevendeg.arrowinpractice.model.HouseId

fun Long.houseId(): HouseId = HouseId(this)
fun Int.houseId(): HouseId = HouseId(this.toLong())
fun Long.characterId(): CharacterId = CharacterId(this)
fun Int.characterId(): CharacterId = CharacterId(this.toLong())
fun Long.castleId(): CastleId = CastleId(this)
fun Int.castleId(): CastleId = CastleId(this.toLong())
