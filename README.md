# Arrow in practice

Lambda.World Cádiz 2018 workshop hosted by [@raulraja](https://twitter.com/raulraja) and [@JorgeCastilloPR](https://twitter.com/JorgeCastilloPR).

This workshop starts with a basic **RESTful API** coded using [ktor](https://ktor.io/) ([@JetBrains](https://www.jetbrains.com/)). Our intention is to iterate over it converting it to a more functional style by putting [Arrow](https://arrow-kt.io/) into practice.

Some key points you'll learn:
* How to model your application immutable data using Arrow datatypes.
* How to operate over and transform the data using Typeclass defined behaviors.
* How to achieve polymorphism using the power of abstraction of typeclasses.
* How to use Optics to read and modify nested immutable data structures.
* How to encode sequential operations with a fancier syntax using Monad Comprehensions.
* How to encode non-dependent operations using the Applicative Builder.

# Serialization

All content negotiation (serialization / deserialization) is handled using the `Ktor` built in `Jackson` support.

# Unauthenticated Routes

## [GET] [http://0.0.0.0:8080](http://0.0.0.0:8080)
Welcome page.

### Response
```json
{
  "message": "Welcome to the Game of Thrones API!"
}
```

# Authenticated Routes

Authenticated routes use Basic Auth and require passing the following credentials encoded in Base64:
```
georgerrmartin:lambdaworldrules
```

## [GET] [http://0.0.0.0:8080/houses](http://0.0.0.0:8080/houses)
List of Houses.

### Response
```json
{
  "houses" : [ {
    "id" : 1,
    "name" : "Stark"
  }, {
    "id" : 2,
    "name" : "Lannister"
  }, {
    "id" : 3,
    "name" : "Baratheon"
  }, {
    "id" : 4,
    "name" : "Targaryen"
  }, {
    "id" : 5,
    "name" : "Greyjoy"
  }, {
    "id" : 6,
    "name" : "Arryn"
  }, {
    "id" : 7,
    "name" : "Martell"
  }, {
    "id" : 8,
    "name" : "Tully"
  }, {
    "id" : 9,
    "name" : "Tyrell"
  } ]
}
```

## [GET] [http://0.0.0.0:8080/houses/{houseName}](http://0.0.0.0:8080/houses/stark)
House by name. (**one of**: `stark`, `lannister`, `baratheon`, `Targaryen`, `Greyjoy`, `Arryn`, `Martell`, `Tully`, `Tyrell`. We're probably missing some, so don't take it too seriously!)

### Response

```json
{
  "stark" : {
    "id" : 1,
    "name" : "Stark"
  }
}
```

## [GET] [http://0.0.0.0:8080/houses/{id}](http://0.0.0.0:8080/houses/1)
House by id. (**an int in**: `[1..9]`)

### Response
```json
{
  "1" : {
    "id" : 1,
    "name" : "Stark"
  }
}
```

## [GET] [http://0.0.0.0:8080/houses/{id}/characters](http://0.0.0.0:8080/houses/1/characters)
Characters per house Id. (`id` must be **an int in the range**: `[1..9]`)

### Response

```json
{
  "characters" : [ {
    "houseId" : 1,
    "id" : 1,
    "name" : "Eddard (Ned) Stark",
    "description" : "Patriarch, Lord of Winterfell, Warden of the North"
  }, {
    "houseId" : 1,
    "id" : 2,
    "name" : "Catelyn Stark",
    "description" : "Ned’s wife"
  }, {
    "houseId" : 1,
    "id" : 3,
    "name" : "Robb Stark",
    "description" : "Ned and Catelyn’s oldest son, heir to Winterfell"
  }, {
    "houseId" : 1,
    "id" : 4,
    "name" : "Sansa Stark",
    "description" : "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon"
  }, {
    "houseId" : 1,
    "id" : 5,
    "name" : "Arya Star",
    "description" : "Catelyn’s youngest daughter"
  }, {
    "houseId" : 1,
    "id" : 6,
    "name" : "Bran Stark",
    "description" : "Ned and Catelyn’s middle son"
  }, {
    "houseId" : 1,
    "id" : 7,
    "name" : "Rickon Stark",
    "description" : "Ned and Catelyn’s youngest son"
  }, {
    "houseId" : 1,
    "id" : 8,
    "name" : "Jon Snow",
    "description" : "Ned’s illegitimate son, member of the Night’s Watch"
  }, {
    "houseId" : 1,
    "id" : 9,
    "name" : "Benjen Stark",
    "description" : "Ned’s younger brother, First Ranger of the Night’s Watch"
  } ]
}
```

## [GET] [http://0.0.0.0:8080/characters](http://0.0.0.0:8080/characters)
Overall list of characters.

### Response

```json
{
  "characters" : [ {
    "houseId" : 1,
    "id" : 1,
    "name" : "Eddard (Ned) Stark",
    "description" : "Patriarch, Lord of Winterfell, Warden of the North"
  }, {
    "houseId" : 1,
    "id" : 2,
    "name" : "Catelyn Stark",
    "description" : "Ned’s wife"
  }, {
    "houseId" : 1,
    "id" : 3,
    "name" : "Robb Stark",
    "description" : "Ned and Catelyn’s oldest son, heir to Winterfell"
  }, {
    "houseId" : 1,
    "id" : 4,
    "name" : "Sansa Stark",
    "description" : "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon"
  }, {
    "houseId" : 1,
    "id" : 5,
    "name" : "Arya Star",
    "description" : "Catelyn’s youngest daughter"
  }, {
    "houseId" : 1,
    "id" : 6,
    "name" : "Bran Stark",
    "description" : "Ned and Catelyn’s middle son"
  }, {
    "houseId" : 1,
    "id" : 7,
    "name" : "Rickon Stark",
    "description" : "Ned and Catelyn’s youngest son"
  }, {
    "houseId" : 1,
    "id" : 8,
    "name" : "Jon Snow",
    "description" : "Ned’s illegitimate son, member of the Night’s Watch"
  }, {
    "houseId" : 1,
    "id" : 9,
    "name" : "Benjen Stark",
    "description" : "Ned’s younger brother, First Ranger of the Night’s Watch"
  }, {
    "houseId" : 2,
    "id" : 10,
    "name" : "Tywin Lannister",
    "description" : "Patriarch, Lord of Casterly Rock, Warden of the West"
  }, {
    "houseId" : 2,
    "id" : 11,
    "name" : "Cersei Lannister",
    "description" : "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister"
  }, {
    "houseId" : 2,
    "id" : 12,
    "name" : "Jaime Lannister",
    "description" : "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard"
  }, {
    "houseId" : 2,
    "id" : 13,
    "name" : "Tyrion Lannister",
    "description" : "Tywin’s youngest son, acting Hand of the King and Master of Coin"
  }, {
    "houseId" : 2,
    "id" : 14,
    "name" : "Joffrey Baratheon",
    "description" : "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son"
  }, {
    "houseId" : 2,
    "id" : 15,
    "name" : "Myrcella Baratheon",
    "description" : "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter"
  }, {
    "houseId" : 2,
    "id" : 16,
    "name" : "Tommen Baratheon",
    "description" : "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son"
  }, {
    "houseId" : 3,
    "id" : 17,
    "name" : "Robert Baratheon",
    "description" : "Patriarch, King of the Seven Kingdoms"
  }, {
    "houseId" : 3,
    "id" : 18,
    "name" : "Stannis Baratheon",
    "description" : "Robert’s younger brother, Renley’s older brother, Lord of Dragonstone"
  }, {
    "houseId" : 3,
    "id" : 19,
    "name" : "Renly Baratheon",
    "description" : "Youngest of the Baratheon brothers, Lord of Storm’s End"
  }, {
    "houseId" : 4,
    "id" : 20,
    "name" : "Daenerys Targereyn",
    "description" : "Mother of Dragons, Khaleesi"
  }, {
    "houseId" : 4,
    "id" : 21,
    "name" : "Viserys Targaryen",
    "description" : "Daenerys’s brother"
  }, {
    "houseId" : 4,
    "id" : 22,
    "name" : "Aerys II Targaryen",
    "description" : "Daenerys’s father, former King of the Seven Kingdoms, deceased"
  }, {
    "houseId" : 5,
    "id" : 23,
    "name" : "Balon Greyjoy",
    "description" : "Patriarch, Lord of the Iron Islands"
  }, {
    "houseId" : 5,
    "id" : 24,
    "name" : "Theon Greyjoy",
    "description" : "Balon’s first-born son, heir apparent to the Iron Islands"
  }, {
    "houseId" : 5,
    "id" : 25,
    "name" : "Yara Greyjoy",
    "description" : "Balon’s only daughter and oldest child"
  }, {
    "houseId" : 6,
    "id" : 26,
    "name" : "Jon Arryn",
    "description" : "Patriarch, former Hand of the King, deceased"
  }, {
    "houseId" : 6,
    "id" : 27,
    "name" : "Lysa Arryn",
    "description" : "Catelyn’s younger sister"
  }, {
    "houseId" : 6,
    "id" : 28,
    "name" : "Robert Arry",
    "description" : "Lysa’s son, Lord of the Eyrie"
  }, {
    "houseId" : 7,
    "id" : 29,
    "name" : "Doran Martell",
    "description" : "Patriarch, Prince of Dorne"
  }, {
    "houseId" : 7,
    "id" : 30,
    "name" : "Oberyn Martell",
    "description" : "Doran’s youngest brother"
  }, {
    "houseId" : 8,
    "id" : 31,
    "name" : "Hoster Tully",
    "description" : "Patriarch, Lord of Riverrun, Catelyn and Lysa’s father"
  }, {
    "houseId" : 8,
    "id" : 32,
    "name" : "Edmure Tully",
    "description" : "Hoster’s only son and heir to Riverrun"
  }, {
    "houseId" : 9,
    "id" : 33,
    "name" : "Mace Tyrell",
    "description" : "Patriarch, Lord of Highgarden"
  }, {
    "houseId" : 9,
    "id" : 34,
    "name" : "Olenna Tyrell",
    "description" : "Mace’s mother"
  }, {
    "houseId" : 9,
    "id" : 35,
    "name" : "Margaery Tyrell",
    "description" : "Mace’s daughter, wife of Renly Baratheon"
  }, {
    "houseId" : 9,
    "id" : 36,
    "name" : "Loras Tyrell",
    "description" : "Mace’s son, heir to House Tyrell"
  } ]
}
```

## [GET] [http://0.0.0.0:8080/characters/{id}](http://0.0.0.0:8080/characters/13)
Character details by Id. `id` must be an **int** in the range `[1..36]`.

### Response

```json
{
  "13" : {
    "houseId" : 2,
    "id" : 13,
    "name" : "Tyrion Lannister",
    "description" : "Tywin’s youngest son, acting Hand of the King and Master of Coin"
  }
}
```

## [GET] [http://0.0.0.0:8080/castles](http://0.0.0.0:8080/castles)
Overall list of castles.

### Response

```json
{
  "castles": [
    {
      "castleId": {
        "castleId": 1
      },
      "name": "Dragonstone",
      "description": "Dragonstone is the castle that stands upon the eponymous island located in Blackwater Bay. It is the ancestral seat of House Targaryen and was the stronghold of a cadet branch of House Baratheon."
    },
    {
      "castleId": {
        "castleId": 2
      },
      "name": "Harrenhal",
      "description": "Harrenhal is a huge castle, the largest one in all of Westeros, though it is also the most ill-omened. It is located on the northern shore of the Gods Eye lake at the heart of the Riverlands, south of the River Trident and northwest of King's Landing."
    },
    {
      "castleId": {
        "castleId": 3
      },
      "name": "Casterly Rock",
      "description": "Casterly Rock is the ancestral stronghold of House Lannister. It is located on the Western coast of Westeros on a rocky promontory overlooking the Sunset Sea. It overlooks the major city of Lannisport. A major goldmine is located under Casterly Rock. It is one of the most productive in the realm and provides House Lannister with their wealth."
    },
    {
      "castleId": {
        "castleId": 4
      },
      "name": "Winterfell",
      "description": "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
    },
    {
      "castleId": {
        "castleId": 5
      },
      "name": "The Eyrie",
      "description": "The Eyrie is the principal stronghold of House Arryn. It is located in the Vale of Arryn near the east coast of Westeros. The Eyrie straddles the top of a peak in the Mountains of the Moon several thousand feet above the valley floor below. It is approached by a narrow causeway and road. Those who would approach the Eyrie must pass through three way-castles guarding the ascent - and then, must proceed in single file, making them very vulnerable to archers. For these reasons, the Eyrie is considered impregnable to any attack that does not involve dragons, and its defenses have never been overcome."
    },
    {
      "castleId": {
        "castleId": 6
      },
      "name": "Red Keep",
      "description": "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
    },
    {
      "castleId": {
        "castleId": 7
      },
      "name": "Riverrun",
      "description": "Riverrun is the former seat of House Tully, which is now occupied by its new lawful rulers House Frey. It is a large castle located in the central-western part of the Riverlands. It sits at the point where the Red Fork of the Trident River is joined by its major tributary, the Tumblestone River flowing out of the west. In times of danger, sluice gates can be opened to flood a channel cut to the west of the castle, turning Riverrun into an island. Its walls rise sheer from the waters and its towers command the opposite shores, making assaulting it almost impossible without huge casualties."
    },
    {
      "castleId": {
        "castleId": 8
      },
      "name": "Castle Black",
      "description": "Castle Black is the primary headquarters and redoubt of the Night's Watch. It is located roughly halfway along the length of the Wall on its southern side, at the northern end of the Kingsroad. It is a dark and chilling home to its garrison."
    }
  ]
}
```

## [GET] [http://0.0.0.0:8080/castles/{id}](http://0.0.0.0:8080/castles/1)
Castle details by Id. `id` must be an **int** in the range `[1..12]`.

### Response

```json
{
  "CastleId(castleId=1)": {
    "castleId": {
      "castleId": 1
    },
    "name": "Dragonstone",
    "description": "Dragonstone is the castle that stands upon the eponymous island located in Blackwater Bay. It is the ancestral seat of House Targaryen and was the stronghold of a cadet branch of House Baratheon."
  }
}
```

## [POST] [http://0.0.0.0:8080/houses](http://0.0.0.0:8080/houses)

Create a new House or update an existent one. If there's already a House with the same name than the one posted, it will 
get updated with the new details.

### Request Payload

```json
{
  "name" : "NewHouse",
  "description" : "Some random description"
}
```

### Response

**Created**:
```json
{
  "message": "House created successfully."
}
```

**Updated**: (Found a house with the same name)
```json
{
  "message": "A House with the same name was found and updated successfully."
}
```

## [POST] [http://0.0.0.0:8080/characters](http://0.0.0.0:8080/characters)

Create a new Character or update an existent one. If there's already a Character with the same name than the one posted, 
it will get updated with the new details.

### Request Payload

```json
{
  "name" : "NewCharacter",
  "description" : "Some random description"
}
```

### Response

**Created**:
```json
{
  "message": "Character created successfully."
}
```

**Updated**: (Found a Character with the same name)
```json
{
  "message": "A Character with the same name was found and updated successfully."
}
```

# Testing / Running the endpoints

## Http Request Built-in support

There are `HTTP Request` files for each one of the endpoints into the `httpqueries` folder in the root directory. If you 
have IntellIJ Ultimate installed, you can run them just by using the IDE and perform a real request to the endpoint. (Don't 
forget to run the app so you get your local server deployed to localhost).

![Http Requests Screenshot](./assets/HttpRequestsSS.png)
![Http Requests Screenshot2](./assets/HttpRequestsSS2.png)

## Tests

* TBD

## Curl (Import in Postman, Paw, or use in command line)

* TBD
