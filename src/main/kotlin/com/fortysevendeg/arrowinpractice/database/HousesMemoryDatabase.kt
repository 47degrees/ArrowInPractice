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
    House(1, "Stark", "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell."),
    House(2, "Lannister", "They are the warden of the west or the main house of the west. The rule from the Casterly Rock of the Westerlands."),
    House(3, "Baratheon", "Their dynasty starts after Robert Baratheon defeated The Mad King."),
    House(4, "Targaryen", "They ruled the seven kingdoms for 300 years with the help of their Dragons. But during the rule of the Mad King Aerys they lost their battle against Robert and Ned and lost their claim in the Iron Throne."),
    House(5, "Greyjoy", "They are the lords of Iron Islands rule from the Pyke in the Iron Islands."),
    House(6, "Arryn", "They are the main House of the Vale and rule form a small castle called the Eyrie in the mountain."),
    House(7, "Martell", "They are the ruler of the Dorne , rule from the Sunspear Castle ."),
    House(8, "Tully", "House Tully rule from Riverrun in the Riverlands. Ned Strak’s wife Cathlyn is the daughter of this house."),
    House(9, "Tyrell", "Targaryens made the Tyrells Lords of Highgardens . They are the main house in the Reach and rule from Highgarden.")))

  @Synchronized
  fun getAll(): List<House> = houses

  @Synchronized
  fun getByName(houseName: String): House? = houses.find { it.name.toLowerCase() == houseName.toLowerCase() }

  @Synchronized
  fun getById(id: Long): House? = houses.find { it.id == id }
}
