package com.fortysevendeg.arrowinpractice.database

import com.fortysevendeg.arrowinpractice.model.Character
import com.fortysevendeg.arrowinpractice.model.CharacterId
import com.fortysevendeg.arrowinpractice.model.HouseId
import com.fortysevendeg.arrowinpractice.model.PostCharacter
import com.fortysevendeg.arrowinpractice.serialization.cId
import com.fortysevendeg.arrowinpractice.serialization.hId
import java.util.*

class CharactersDatabase {

  private val characters = Collections.synchronizedList(mutableListOf(
    Character(1.hId(), 1.cId(), "Eddard (Ned) Stark", "Patriarch, Lord of Winterfell, Warden of the North"),
    Character(1.hId(), 2.cId(), "Catelyn Stark", "Ned’s wife"),
    Character(1.hId(), 3.cId(), "Robb Stark", "Ned and Catelyn’s oldest son, heir to Winterfell"),
    Character(1.hId(), 4.cId(), "Sansa Stark", "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon"),
    Character(1.hId(), 5.cId(), "Arya Star", "Catelyn’s youngest daughter"),
    Character(1.hId(), 6.cId(), "Bran Stark", "Ned and Catelyn’s middle son"),
    Character(1.hId(), 7.cId(), "Rickon Stark", "Ned and Catelyn’s youngest son"),
    Character(1.hId(), 8.cId(), "Jon Snow", "Ned’s illegitimate son, member of the Night’s Watch"),
    Character(1.hId(), 9.cId(), "Benjen Stark", "Ned’s younger brother, First Ranger of the Night’s Watch"),
    Character(2.hId(), 10.cId(), "Tywin Lannister", "Patriarch, Lord of Casterly Rock, Warden of the West"),
    Character(2.hId(), 11.cId(), "Cersei Lannister", "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister"),
    Character(2.hId(), 12.cId(), "Jaime Lannister", "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard"),
    Character(2.hId(), 13.cId(), "Tyrion Lannister", "Tywin’s youngest son, acting Hand of the King and Master of Coin"),
    Character(2.hId(), 14.cId(), "Joffrey Baratheon", "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son"),
    Character(2.hId(), 15.cId(), "Myrcella Baratheon", "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter"),
    Character(2.hId(), 16.cId(), "Tommen Baratheon", "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son"),
    Character(3.hId(), 17.cId(), "Robert Baratheon", "Patriarch, King of the Seven Kingdoms"),
    Character(3.hId(), 18.cId(), "Stannis Baratheon", "Robert’s younger brother, Renley’s older brother, Lord of Dragonstone"),
    Character(3.hId(), 19.cId(), "Renly Baratheon", "Youngest of the Baratheon brothers, Lord of Storm’s End"),
    Character(4.hId(), 20.cId(), "Daenerys Targereyn", "Mother of Dragons, Khaleesi"),
    Character(4.hId(), 21.cId(), "Viserys Targaryen", "Daenerys’s brother"),
    Character(4.hId(), 22.cId(), "Aerys II Targaryen", "Daenerys’s father, former King of the Seven Kingdoms, deceased"),
    Character(5.hId(), 23.cId(), "Balon Greyjoy", "Patriarch, Lord of the Iron Islands"),
    Character(5.hId(), 24.cId(), "Theon Greyjoy", "Balon’s first-born son, heir apparent to the Iron Islands"),
    Character(5.hId(), 25.cId(), "Yara Greyjoy", "Balon’s only daughter and oldest child"),
    Character(6.hId(), 26.cId(), "Jon Arryn", "Patriarch, former Hand of the King, deceased"),
    Character(6.hId(), 27.cId(), "Lysa Arryn", "Catelyn’s younger sister"),
    Character(6.hId(), 28.cId(), "Robert Arry", "Lysa’s son, Lord of the Eyrie"),
    Character(7.hId(), 29.cId(), "Doran Martell", "Patriarch, Prince of Dorne"),
    Character(7.hId(), 30.cId(), "Oberyn Martell", "Doran’s youngest brother"),
    Character(8.hId(), 31.cId(), "Hoster Tully", "Patriarch, Lord of Riverrun, Catelyn and Lysa’s father"),
    Character(8.hId(), 32.cId(), "Edmure Tully", "Hoster’s only son and heir to Riverrun"),
    Character(9.hId(), 33.cId(), "Mace Tyrell", "Patriarch, Lord of Highgarden"),
    Character(9.hId(), 34.cId(), "Olenna Tyrell", "Mace’s mother"),
    Character(9.hId(), 35.cId(), "Margaery Tyrell", "Mace’s daughter, wife of Renly Baratheon"),
    Character(9.hId(), 36.cId(), "Loras Tyrell", "Mace’s son, heir to House Tyrell")
  ))

  @Synchronized
  fun getAll(): List<Character> = characters

  @Synchronized
  fun getByHouseId(houseId: HouseId): List<Character> = characters.filter { it.houseId == houseId }

  @Synchronized
  fun getByName(characterName: String): Character? = characters.findLast { it.name == characterName }

  @Synchronized
  fun getById(id: CharacterId): Character? = characters.find { it.characterId == id }

  operator fun get(id: CharacterId): Character? = getById(id)

  operator fun get(characterName: String): Character? = getByName(characterName)

  operator fun get(houseId: HouseId): List<Character> = getByHouseId(houseId)

  /**
   * Inserts a new Character in the Database or updates an existent one in case there's already one stored with the same
   * name.
   *
   * @return true if created, false if updated.
   */
  @Synchronized
  fun createOrUpdate(postedCharacter: PostCharacter): Boolean {
    val storedCharacter = getByName(postedCharacter.name)
    return if (storedCharacter != null) {
      val position = characters.indexOf(storedCharacter)
      characters[position] = Character(
        postedCharacter.houseId.hId(),
        storedCharacter.characterId,
        postedCharacter.name,
        postedCharacter.description)
      false
    } else {
      characters.add(Character(
        postedCharacter.houseId.hId(),
        (characters.last().characterId.id + 1).cId(),
        postedCharacter.name,
        postedCharacter.description))
      true
    }
  }
}
