package com.fortysevendeg.arrowinpractice.database

import com.fortysevendeg.arrowinpractice.model.House
import java.util.*

class HousesMemoryDatabase {

  /**
   * Just a bunch of them, we're missing some houses for sure! ¯\_(ツ)_/¯.
   *
   * All collections are intentionally mutable since they mimic what a database would be. We need to be able to insert
   * (store) new elements at any level.
   */
  private val houses = Collections.synchronizedList(mutableListOf(
    House(1, "Stark"),
    House(2, "Lannister"),
    House(3, "Baratheon"),
    House(4, "Targaryen"),
    House(5, "Greyjoy"),
    House(6, "Arryn"),
    House(7, "Martell"),
    House(8, "Tully"),
    House(9, "Tyrell")))

  @Synchronized
  fun getAll(): List<House> = houses

  @Synchronized
  fun getByName(houseName: String): House? = houses.find { it.name.toLowerCase() == houseName.toLowerCase() }

  @Synchronized
  fun getById(id: Long): House? = houses.find { it.id == id }
}
