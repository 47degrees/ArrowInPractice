package com.fortysevendeg.arrowinpractice.serialization

import com.fortysevendeg.arrowinpractice.model.CharacterId
import com.fortysevendeg.arrowinpractice.model.HouseId

fun Long.hId(): HouseId = HouseId(this)
fun Int.hId(): HouseId = HouseId(this.toLong())
fun Long.cId(): CharacterId = CharacterId(this)
fun Int.cId(): CharacterId = CharacterId(this.toLong())
