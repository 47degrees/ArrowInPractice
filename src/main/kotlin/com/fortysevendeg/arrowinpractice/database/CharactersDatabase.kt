package com.fortysevendeg.arrowinpractice.database

import com.fortysevendeg.arrowinpractice.model.Character
import java.util.*

class CharactersDatabase {

  private val characters = Collections.synchronizedList(mutableListOf(
    Character(1, 1, "Eddard (Ned) Stark", "Patriarch, Lord of Winterfell, Warden of the North"),
    Character(1, 2, "Catelyn Stark", "Ned’s wife"),
    Character(1, 3, "Robb Stark", "Ned and Catelyn’s oldest son, heir to Winterfell"),
    Character(1, 4, "Sansa Stark", "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon"),
    Character(1, 5, "Arya Star", "Catelyn’s youngest daughter"),
    Character(1, 6, "Bran Stark", "Ned and Catelyn’s middle son"),
    Character(1, 7, "Rickon Stark", "Ned and Catelyn’s youngest son"),
    Character(1, 8, "Jon Snow", "Ned’s illegitimate son, member of the Night’s Watch"),
    Character(1, 9, "Benjen Stark", "Ned’s younger brother, First Ranger of the Night’s Watch"),
    Character(2, 10, "Tywin Lannister", "Patriarch, Lord of Casterly Rock, Warden of the West"),
    Character(2, 11, "Cersei Lannister", "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister"),
    Character(2, 12, "Jaime Lannister", "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard"),
    Character(2, 13, "Tyrion Lannister", "Tywin’s youngest son, acting Hand of the King and Master of Coin"),
    Character(2, 14, "Joffrey Baratheon", "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son"),
    Character(2, 15, "Myrcella Baratheon", "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter"),
    Character(2, 16, "Tommen Baratheon", "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son"),
    Character(3, 17, "Robert Baratheon", "Patriarch, King of the Seven Kingdoms"),
    Character(3, 18, "Stannis Baratheon", "Robert’s younger brother, Renley’s older brother, Lord of Dragonstone"),
    Character(3, 19, "Renly Baratheon", "Youngest of the Baratheon brothers, Lord of Storm’s End"),
    Character(4, 20, "Daenerys Targereyn", "Mother of Dragons, Khaleesi"),
    Character(4, 21, "Viserys Targaryen", "Daenerys’s brother"),
    Character(4, 22, "Aerys II Targaryen", "Daenerys’s father, former King of the Seven Kingdoms, deceased"),
    Character(5, 23, "Balon Greyjoy", "Patriarch, Lord of the Iron Islands"),
    Character(5, 24, "Theon Greyjoy", "Balon’s first-born son, heir apparent to the Iron Islands"),
    Character(5, 25, "Yara Greyjoy", "Balon’s only daughter and oldest child"),
    Character(6, 26, "Jon Arryn", "Patriarch, former Hand of the King, deceased"),
    Character(6, 27, "Lysa Arryn", "Catelyn’s younger sister"),
    Character(6, 28, "Robert Arry", "Lysa’s son, Lord of the Eyrie"),
    Character(7, 29, "Doran Martell", "Patriarch, Prince of Dorne"),
    Character(7, 30, "Oberyn Martell", "Doran’s youngest brother"),
    Character(8, 31, "Hoster Tully", "Patriarch, Lord of Riverrun, Catelyn and Lysa’s father"),
    Character(8, 32, "Edmure Tully", "Hoster’s only son and heir to Riverrun"),
    Character(9, 33, "Mace Tyrell", "Patriarch, Lord of Highgarden"),
    Character(9, 34, "Olenna Tyrell", "Mace’s mother"),
    Character(9, 35, "Margaery Tyrell", "Mace’s daughter, wife of Renly Baratheon"),
    Character(9, 36, "Loras Tyrell", "Mace’s son, heir to House Tyrell")
  ))

  @Synchronized
  fun getAll(): List<Character> = characters

  @Synchronized
  fun getByHouseId(houseId: Long): List<Character> = characters.filter { it.houseId == houseId }

  @Synchronized
  fun getByName(houseName: String): Character? = characters.find { it.name.toLowerCase() == houseName.toLowerCase() }

  @Synchronized
  fun getById(id: Long): Character? = characters.find { it.id == id }
}
