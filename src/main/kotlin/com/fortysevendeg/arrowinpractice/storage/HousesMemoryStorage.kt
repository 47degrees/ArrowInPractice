package com.fortysevendeg.arrowinpractice.storage

import com.fortysevendeg.arrowinpractice.model.Character
import com.fortysevendeg.arrowinpractice.model.House
import java.util.*

class HousesMemoryStorage {

  // Just a bunch of them, we're missing some houses for sure! ¯\_(ツ)_/¯
  private val houses = Collections.synchronizedList(listOf(
    House("Stark", Collections.synchronizedList(listOf(
      Character("Eddard (Ned) Stark", "Patriarch, Lord of Winterfell, Warden of the North"),
      Character("Catelyn Stark", "Ned’s wife"),
      Character("Robb Stark", "Ned and Catelyn’s oldest son, heir to Winterfell"),
      Character("Sansa Stark", "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon"),
      Character("Arya Star", "Catelyn’s youngest daughter"),
      Character("Bran Stark", "Ned and Catelyn’s middle son"),
      Character("Rickon Stark", "Ned and Catelyn’s youngest son"),
      Character("Jon Snow", "Ned’s illegitimate son, member of the Night’s Watch"),
      Character("Benjen Stark", "Ned’s younger brother, First Ranger of the Night’s Watch")
    ))),
    House("Lannister", Collections.synchronizedList(listOf(
      Character("Tywin Lannister", "Patriarch, Lord of Casterly Rock, Warden of the West"),
      Character("Cersei Lannister", "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister"),
      Character("Jaime Lannister", "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard"),
      Character("Tyrion Lannister", "Tywin’s youngest son, acting Hand of the King and Master of Coin"),
      Character("Joffrey Baratheon", "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son"),
      Character("Myrcella Baratheon", "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter"),
      Character("Tommen Baratheon", "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son")
    ))),
    House("Baratheon", Collections.synchronizedList(listOf(
      Character("Robert Baratheon", "Patriarch, King of the Seven Kingdoms"),
      Character("Stannis Baratheon", "Robert’s younger brother, Renley’s older brother, Lord of Dragonstone"),
      Character("Renly Baratheon", "Youngest of the Baratheon brothers, Lord of Storm’s End")
    ))),
    House("Targaryen", Collections.synchronizedList(listOf(
      Character("Daenerys Targereyn", "Mother of Dragons, Khaleesi"),
      Character("Viserys Targaryen", "Daenerys’s brother"),
      Character("Aerys II Targaryen", "Daenerys’s father, former King of the Seven Kingdoms, deceased")
    ))),
    House("Greyjoy", Collections.synchronizedList(listOf(
      Character("Balon Greyjoy", "Patriarch, Lord of the Iron Islands"),
      Character("Theon Greyjoy", "Balon’s first-born son, heir apparent to the Iron Islands"),
      Character("Yara Greyjoy", "Balon’s only daughter and oldest child")
    ))),
    House("Arryn", Collections.synchronizedList(listOf(
      Character("Jon Arryn", "Patriarch, former Hand of the King, deceased"),
      Character("Lysa Arryn", "Catelyn’s younger sister"),
      Character("Robert Arry", "Lysa’s son, Lord of the Eyrie")
    ))),
    House("Martell", Collections.synchronizedList(listOf(
      Character("Doran Martell", "Patriarch, Prince of Dorne"),
      Character("Oberyn Martell", "Doran’s youngest brother")
    ))),
    House("Tully", Collections.synchronizedList(listOf(
      Character("Hoster Tully", "Patriarch, Lord of Riverrun, Catelyn and Lysa’s father"),
      Character("Edmure Tully", "Hoster’s only son and heir to Riverrun")
    ))),
    House("Tyrell", Collections.synchronizedList(listOf(
      Character("Mace Tyrell", "Patriarch, Lord of Highgarden"),
      Character("Olenna Tyrell", "Mace’s mother"),
      Character("Margaery Tyrell", "Mace’s daughter, wife of Renly Baratheon"),
      Character("Loras Tyrell", "Mace’s son, heir to House Tyrell")
    )))))

  fun getAll(): List<House> = houses
}
