package com.fortysevendeg.arrowinpractice.database

import arrow.core.Option
import arrow.core.toOption
import com.fortysevendeg.arrowinpractice.model.Character
import com.fortysevendeg.arrowinpractice.model.HouseId
import com.fortysevendeg.arrowinpractice.model.PostCharacter
import java.util.*

class CharactersDatabase {

  private val characters = Collections.synchronizedList(mutableListOf(
    Character(1, 1, 4, "Eddard (Ned) Stark", "Patriarch, Lord of Winterfell, Warden of the North"),
    Character(1, 2, 4, "Catelyn Stark", "Ned’s wife"),
    Character(1, 3, 4, "Robb Stark", "Ned and Catelyn’s oldest son, heir to Winterfell"),
    Character(1, 4, 4, "Sansa Stark", "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon"),
    Character(1, 5, 4, "Arya Stark", "Catelyn’s youngest daughter"),
    Character(1, 6, 4, "Bran Stark", "Ned and Catelyn’s middle son"),
    Character(1, 7, 4, "Rickon Stark", "Ned and Catelyn’s youngest son"),
    Character(1, 8, 4, "Jon Snow", "Ned’s illegitimate son, member of the Night’s Watch"),
    Character(1, 9, 8, "Benjen Stark", "Ned’s younger brother, First Ranger of the Night’s Watch"),
    Character(2, 10, 6, "Tywin Lannister", "Patriarch, Lord of Casterly Rock, Warden of the West"),
    Character(2, 11, 6, "Cersei Lannister", "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister"),
    Character(2, 12, 6, "Jaime Lannister", "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard"),
    Character(2, 13, 6, "Tyrion Lannister", "Tywin’s youngest son, acting Hand of the King and Master of Coin"),
    Character(2, 14, 6, "Joffrey Baratheon", "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son"),
    Character(2, 15, 6, "Myrcella Baratheon", "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter"),
    Character(2, 16, 6, "Tommen Baratheon", "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son"),
    Character(3, 17, 6, "Robert Baratheon", "Patriarch, King of the Seven Kingdoms"),
    Character(3, 18, 1, "Stannis Baratheon", "Robert’s younger brother, Renley’s older brother, Lord of Dragonstone"),
    Character(3, 19, 9, "Renly Baratheon", "Youngest of the Baratheon brothers, Lord of Storm’s End"),
    Character(4, 20, 1, "Daenerys Targereyn", "Mother of Dragons, Khaleesi"),
    Character(4, 21, 1, "Viserys Targaryen", "Daenerys’s brother"),
    Character(4, 22, 1, "Aerys II Targaryen", "Daenerys’s father, former King of the Seven Kingdoms, deceased"),
    Character(5, 23, 10, "Balon Greyjoy", "Patriarch, Lord of the Iron Islands"),
    Character(5, 24, 10, "Theon Greyjoy", "Balon’s first-born son, heir apparent to the Iron Islands"),
    Character(5, 25, 10, "Yara Greyjoy", "Balon’s only daughter and oldest child"),
    Character(6, 26, 5, "Jon Arryn", "Patriarch, former Hand of the King, deceased"),
    Character(6, 27, 5, "Lysa Arryn", "Catelyn’s younger sister"),
    Character(6, 28, 5, "Robert Arryn", "Lysa’s son, Lord of the Eyrie"),
    Character(7, 29, 11, "Doran Martell", "Patriarch, Prince of Dorne"),
    Character(7, 30, 11, "Oberyn Martell", "Doran’s youngest brother"),
    Character(8, 31, 7, "Hoster Tully", "Patriarch, Lord of Riverrun, Catelyn and Lysa’s father"),
    Character(8, 32, 7, "Edmure Tully", "Hoster’s only son and heir to Riverrun"),
    Character(9, 33, 12, "Mace Tyrell", "Patriarch, Lord of Highgarden"),
    Character(9, 34, 12, "Olenna Tyrell", "Mace’s mother"),
    Character(9, 35, 12, "Margaery Tyrell", "Mace’s daughter, wife of Renly Baratheon"),
    Character(9, 36, 12, "Loras Tyrell", "Mace’s son, heir to House Tyrell")
  ))

  @Synchronized @Throws(RandomDBException::class)
  fun getAll(): List<Character> = characters

  @Synchronized @Throws(RandomDBException::class)
  fun getByHouseId(houseId: Long): List<Character> = characters.filter { it.houseId == houseId }

  @Synchronized @Throws(RandomDBException::class)
  fun getByName(characterName: String): Character? = characters.findLast { it.name == characterName }

  @Synchronized @Throws(RandomDBException::class)
  fun getById(id: Long): Option<Character> = characters.find { it.characterId == id }.toOption()

  @Throws(RandomDBException::class)
  operator fun get(id: Long): Option<Character> = getById(id)

  @Throws(RandomDBException::class)
  operator fun get(characterName: String): Character? = getByName(characterName)

  /**
   * Inserts a new Character in the Database or updates an existent one in case there's already one stored with the same
   * name.
   *
   * @return true if created, false if updated.
   */
  @Synchronized @Throws(RandomDBException::class)
  fun createOrUpdate(postedCharacter: PostCharacter): Boolean {
    val storedCharacter = getByName(postedCharacter.name)
    return if (storedCharacter != null) {
      val position = characters.indexOf(storedCharacter)
      characters[position] = Character(
        postedCharacter.houseId,
        storedCharacter.characterId,
        postedCharacter.castleId,
        postedCharacter.name,
        postedCharacter.description)
      false
    } else {
      characters.add(Character(
        postedCharacter.houseId,
        (characters.last().characterId + 1),
        postedCharacter.castleId,
        postedCharacter.name,
        postedCharacter.description))
      true
    }
  }
}
