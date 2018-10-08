package com.fortysevendeg.arrowinpractice.database

import com.fortysevendeg.arrowinpractice.model.Character
import com.fortysevendeg.arrowinpractice.model.CharacterId
import com.fortysevendeg.arrowinpractice.model.HouseId
import com.fortysevendeg.arrowinpractice.model.PostCharacter
import com.fortysevendeg.arrowinpractice.serialization.castleId
import com.fortysevendeg.arrowinpractice.serialization.characterId
import com.fortysevendeg.arrowinpractice.serialization.houseId
import java.util.*

class CharactersDatabase {

  private val characters = Collections.synchronizedList(mutableListOf(
    Character(1.houseId(), 1.characterId(), 1.castleId(), "Eddard (Ned) Stark", "Patriarch, Lord of Winterfell, Warden of the North"),
    Character(1.houseId(), 2.characterId(), 1.castleId(), "Catelyn Stark", "Ned’s wife"),
    Character(1.houseId(), 3.characterId(), 1.castleId(), "Robb Stark", "Ned and Catelyn’s oldest son, heir to Winterfell"),
    Character(1.houseId(), 4.characterId(), 1.castleId(), "Sansa Stark", "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon"),
    Character(1.houseId(), 5.characterId(), 1.castleId(), "Arya Star", "Catelyn’s youngest daughter"),
    Character(1.houseId(), 6.characterId(), 1.castleId(), "Bran Stark", "Ned and Catelyn’s middle son"),
    Character(1.houseId(), 7.characterId(), 1.castleId(), "Rickon Stark", "Ned and Catelyn’s youngest son"),
    Character(1.houseId(), 8.characterId(), 1.castleId(), "Jon Snow", "Ned’s illegitimate son, member of the Night’s Watch"),
    Character(1.houseId(), 9.characterId(), 1.castleId(), "Benjen Stark", "Ned’s younger brother, First Ranger of the Night’s Watch"),
    Character(2.houseId(), 10.characterId(), 1.castleId(), "Tywin Lannister", "Patriarch, Lord of Casterly Rock, Warden of the West"),
    Character(2.houseId(), 11.characterId(), 1.castleId(), "Cersei Lannister", "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister"),
    Character(2.houseId(), 12.characterId(), 1.castleId(), "Jaime Lannister", "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard"),
    Character(2.houseId(), 13.characterId(), 1.castleId(), "Tyrion Lannister", "Tywin’s youngest son, acting Hand of the King and Master of Coin"),
    Character(2.houseId(), 14.characterId(), 1.castleId(), "Joffrey Baratheon", "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son"),
    Character(2.houseId(), 15.characterId(), 1.castleId(), "Myrcella Baratheon", "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter"),
    Character(2.houseId(), 16.characterId(), 1.castleId(), "Tommen Baratheon", "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son"),
    Character(3.houseId(), 17.characterId(), 1.castleId(), "Robert Baratheon", "Patriarch, King of the Seven Kingdoms"),
    Character(3.houseId(), 18.characterId(), 1.castleId(), "Stannis Baratheon", "Robert’s younger brother, Renley’s older brother, Lord of Dragonstone"),
    Character(3.houseId(), 19.characterId(), 1.castleId(), "Renly Baratheon", "Youngest of the Baratheon brothers, Lord of Storm’s End"),
    Character(4.houseId(), 20.characterId(), 1.castleId(), "Daenerys Targereyn", "Mother of Dragons, Khaleesi"),
    Character(4.houseId(), 21.characterId(), 1.castleId(), "Viserys Targaryen", "Daenerys’s brother"),
    Character(4.houseId(), 22.characterId(), 1.castleId(), "Aerys II Targaryen", "Daenerys’s father, former King of the Seven Kingdoms, deceased"),
    Character(5.houseId(), 23.characterId(), 1.castleId(), "Balon Greyjoy", "Patriarch, Lord of the Iron Islands"),
    Character(5.houseId(), 24.characterId(), 1.castleId(), "Theon Greyjoy", "Balon’s first-born son, heir apparent to the Iron Islands"),
    Character(5.houseId(), 25.characterId(), 1.castleId(), "Yara Greyjoy", "Balon’s only daughter and oldest child"),
    Character(6.houseId(), 26.characterId(), 1.castleId(), "Jon Arryn", "Patriarch, former Hand of the King, deceased"),
    Character(6.houseId(), 27.characterId(), 1.castleId(), "Lysa Arryn", "Catelyn’s younger sister"),
    Character(6.houseId(), 28.characterId(), 1.castleId(), "Robert Arry", "Lysa’s son, Lord of the Eyrie"),
    Character(7.houseId(), 29.characterId(), 1.castleId(), "Doran Martell", "Patriarch, Prince of Dorne"),
    Character(7.houseId(), 30.characterId(), 1.castleId(), "Oberyn Martell", "Doran’s youngest brother"),
    Character(8.houseId(), 31.characterId(), 1.castleId(), "Hoster Tully", "Patriarch, Lord of Riverrun, Catelyn and Lysa’s father"),
    Character(8.houseId(), 32.characterId(), 1.castleId(), "Edmure Tully", "Hoster’s only son and heir to Riverrun"),
    Character(9.houseId(), 33.characterId(), 1.castleId(), "Mace Tyrell", "Patriarch, Lord of Highgarden"),
    Character(9.houseId(), 34.characterId(), 1.castleId(), "Olenna Tyrell", "Mace’s mother"),
    Character(9.houseId(), 35.characterId(), 1.castleId(), "Margaery Tyrell", "Mace’s daughter, wife of Renly Baratheon"),
    Character(9.houseId(), 36.characterId(), 1.castleId(), "Loras Tyrell", "Mace’s son, heir to House Tyrell")
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
        postedCharacter.houseId.houseId(),
        storedCharacter.characterId,
        postedCharacter.name,
        postedCharacter.description)
      false
    } else {
      characters.add(Character(
        postedCharacter.houseId.houseId(),
        (characters.last().characterId.id + 1).characterId(),
        postedCharacter.name,
        postedCharacter.description))
      true
    }
  }
}
