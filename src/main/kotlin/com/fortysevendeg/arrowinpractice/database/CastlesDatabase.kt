package com.fortysevendeg.arrowinpractice.database

import com.fortysevendeg.arrowinpractice.model.Castle
import com.fortysevendeg.arrowinpractice.model.CastleId
import com.fortysevendeg.arrowinpractice.serialization.castleId
import java.util.*

class CastlesDatabase {

  /**
   * Just a bunch of them, we're missing many castles here for sure! but ¯\_(ツ)_/¯.
   *
   * All collections are intentionally mutable since they mimic what a database would be. We need to be able to insert
   * (store) new elements at any level.
   */
  private val castles = Collections.synchronizedList(mutableListOf(
    Castle(1.castleId(), "Dragonstone", "Dragonstone is the castle that stands upon the eponymous island located in Blackwater Bay. It is the ancestral seat of House Targaryen and was the stronghold of a cadet branch of House Baratheon."),
    Castle(2.castleId(), "Harrenhal", "Harrenhal is a huge castle, the largest one in all of Westeros, though it is also the most ill-omened. It is located on the northern shore of the Gods Eye lake at the heart of the Riverlands, south of the River Trident and northwest of King's Landing."),
    Castle(3.castleId(), "Casterly Rock", "Casterly Rock is the ancestral stronghold of House Lannister. It is located on the Western coast of Westeros on a rocky promontory overlooking the Sunset Sea. It overlooks the major city of Lannisport. A major goldmine is located under Casterly Rock. It is one of the most productive in the realm and provides House Lannister with their wealth."),
    Castle(4.castleId(), "Winterfell", "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."),
    Castle(5.castleId(), "The Eyrie", "The Eyrie is the principal stronghold of House Arryn. It is located in the Vale of Arryn near the east coast of Westeros. The Eyrie straddles the top of a peak in the Mountains of the Moon several thousand feet above the valley floor below. It is approached by a narrow causeway and road. Those who would approach the Eyrie must pass through three way-castles guarding the ascent - and then, must proceed in single file, making them very vulnerable to archers. For these reasons, the Eyrie is considered impregnable to any attack that does not involve dragons, and its defenses have never been overcome."),
    Castle(6.castleId(), "Red Keep", "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."),
    Castle(7.castleId(), "Riverrun", "Riverrun is the former seat of House Tully, which is now occupied by its new lawful rulers House Frey. It is a large castle located in the central-western part of the Riverlands. It sits at the point where the Red Fork of the Trident River is joined by its major tributary, the Tumblestone River flowing out of the west. In times of danger, sluice gates can be opened to flood a channel cut to the west of the castle, turning Riverrun into an island. Its walls rise sheer from the waters and its towers command the opposite shores, making assaulting it almost impossible without huge casualties."),
    Castle(8.castleId(), "Castle Black", "Castle Black is the primary headquarters and redoubt of the Night's Watch. It is located roughly halfway along the length of the Wall on its southern side, at the northern end of the Kingsroad. It is a dark and chilling home to its garrison."),
    Castle(9.castleId(), "Storm's End", "Storm's End is the ancestral seat of House Baratheon, and one of three major castles held by the former royal house, the others being the Red Keep in King's Landing and the island fortress of Dragonstone. Stannis Baratheon was the Lord of Storm's End and Dragonstone. Storm's End is a formidable fortress, located on the southeastern coast of Westeros overlooking Shipbreaker Bay."),
    Castle(10.castleId(), "Pyke", "Pyke is the stronghold and seat of House Greyjoy, located on the island of the same name, which is one of the seven major Iron Islands. The castle is the regional capital of the Iron Islands as a whole. Pyke is an ancient stronghold and the cliff it was built on has been eroded by the sea leaving the towers standing on stone stacks. The towers are connected by swaying rope bridges. The rocky moss covered stone at its base is not suitable for ships landing so traffic to the island flows through the nearby harbor town Lordsport."),
    Castle(11.castleId(), "Sunspear", "Sunspear is the capital of Dorne, southernmost of the Seven Kingdoms, located in the far southeast of the continent on the Summer Sea. It consists of a strong, fortified castle and a town that sprawls around it. It is a town built largely of mud and straw. The town's largest structure is Spear Tower, a 150-feet structure with a pinnacle of shining steel."),
    Castle(12.castleId(), "Highgarden", "Highgarden was the seat of House Tyrell and the regional capital of the Reach. Located on the banks of the river Mander, Highgarden sits astride the Roseroad, a major thoroughfare linking Oldtown and King's Landing. Highgarden also forms the southern terminus of the the Searoad, which leads to Lannisport. As King's Landing, Oldtown, and Lannisport are the first, second, and third largest cities in the realm, heavy trade and traffic across a large swath of southern Westeros ultimately passes through Highgarden.")
  ))

  @Synchronized @Throws(RandomDBException::class)
  fun getAll(): List<Castle> = castles

  @Synchronized @Throws(RandomDBException::class)
  fun getById(id: CastleId): Castle? = castles.find { it.castleId == id }

  /**
   * Enabling indexed access operator for fetching by castleId, as in castlesDB[8].
   */
  @Throws(RandomDBException::class)
  operator fun get(id: CastleId): Castle? = getById(id)
}
