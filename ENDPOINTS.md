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
    "houseId" : {
      "id" : 1
    },
    "name" : "Stark",
    "description" : "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell."
  }, {
    "houseId" : {
      "id" : 2
    },
    "name" : "Lannister",
    "description" : "They are the warden of the west or the main house of the west. The rule from the Casterly Rock of the Westerlands."
  }, {
    "houseId" : {
      "id" : 3
    },
    "name" : "Baratheon",
    "description" : "Their dynasty starts after Robert Baratheon defeated The Mad King."
  }, {
    "houseId" : {
      "id" : 4
    },
    "name" : "Targaryen",
    "description" : "They ruled the seven kingdoms for 300 years with the help of their Dragons. But during the rule of the Mad King Aerys they lost their battle against Robert and Ned and lost their claim in the Iron Throne."
  }, {
    "houseId" : {
      "id" : 5
    },
    "name" : "Greyjoy",
    "description" : "They are the lords of Iron Islands rule from the Pyke in the Iron Islands."
  }, {
    "houseId" : {
      "id" : 6
    },
    "name" : "Arryn",
    "description" : "They are the main House of the Vale and rule form a small castle called the Eyrie in the mountain."
  }, {
    "houseId" : {
      "id" : 7
    },
    "name" : "Martell",
    "description" : "They are the ruler of the Dorne , rule from the Sunspear Castle ."
  }, {
    "houseId" : {
      "id" : 8
    },
    "name" : "Tully",
    "description" : "House Tully rule from Riverrun in the Riverlands. Ned Strak’s wife Cathlyn is the daughter of this house."
  }, {
    "houseId" : {
      "id" : 9
    },
    "name" : "Tyrell",
    "description" : "Targaryens made the Tyrells Lords of Highgardens . They are the main house in the Reach and rule from Highgarden."
  } ]
}
```

## [GET] [http://0.0.0.0:8080/houses/{houseName}](http://0.0.0.0:8080/houses/stark)
House by name. (**one of**: `stark`, `lannister`, `baratheon`, `Targaryen`, `Greyjoy`, `Arryn`, `Martell`, `Tully`, `Tyrell`. We're probably missing some, so don't take it too seriously!)

### Response

```json
{
  "houseId" : {
    "id" : 1
  },
  "name" : "Stark",
  "description" : "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell."
}
```

## [GET] [http://0.0.0.0:8080/houses/{id}](http://0.0.0.0:8080/houses/1)
House by id. (**an int in**: `[1..9]`)

### Response
```json
{
  "houseId" : {
    "id" : 1
  },
  "name" : "Stark",
  "description" : "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell."
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

## [GET] [http://0.0.0.0:8080/characters](http://0.0.0.0:8080/characters)
Overall list of characters.

### Response

```json
{
  "characters" : [ {
    "houseId" : {
      "id" : 1
    },
    "characterId" : {
      "id" : 1
    },
    "castleId" : {
      "id" : 4
    },
    "name" : "Eddard (Ned) Stark",
    "description" : "Patriarch, Lord of Winterfell, Warden of the North"
  }, {
    "houseId" : {
      "id" : 1
    },
    "characterId" : {
      "id" : 2
    },
    "castleId" : {
      "id" : 4
    },
    "name" : "Catelyn Stark",
    "description" : "Ned’s wife"
  }, {
    "houseId" : {
      "id" : 1
    },
    "characterId" : {
      "id" : 3
    },
    "castleId" : {
      "id" : 4
    },
    "name" : "Robb Stark",
    "description" : "Ned and Catelyn’s oldest son, heir to Winterfell"
  }, {
    "houseId" : {
      "id" : 1
    },
    "characterId" : {
      "id" : 4
    },
    "castleId" : {
      "id" : 4
    },
    "name" : "Sansa Stark",
    "description" : "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon"
  }, {
    "houseId" : {
      "id" : 1
    },
    "characterId" : {
      "id" : 5
    },
    "castleId" : {
      "id" : 4
    },
    "name" : "Arya Stark",
    "description" : "Catelyn’s youngest daughter"
  }, {
    "houseId" : {
      "id" : 1
    },
    "characterId" : {
      "id" : 6
    },
    "castleId" : {
      "id" : 4
    },
    "name" : "Bran Stark",
    "description" : "Ned and Catelyn’s middle son"
  }, {
    "houseId" : {
      "id" : 1
    },
    "characterId" : {
      "id" : 7
    },
    "castleId" : {
      "id" : 4
    },
    "name" : "Rickon Stark",
    "description" : "Ned and Catelyn’s youngest son"
  }, {
    "houseId" : {
      "id" : 1
    },
    "characterId" : {
      "id" : 8
    },
    "castleId" : {
      "id" : 4
    },
    "name" : "Jon Snow",
    "description" : "Ned’s illegitimate son, member of the Night’s Watch"
  }, {
    "houseId" : {
      "id" : 1
    },
    "characterId" : {
      "id" : 9
    },
    "castleId" : {
      "id" : 8
    },
    "name" : "Benjen Stark",
    "description" : "Ned’s younger brother, First Ranger of the Night’s Watch"
  }, {
    "houseId" : {
      "id" : 2
    },
    "characterId" : {
      "id" : 10
    },
    "castleId" : {
      "id" : 6
    },
    "name" : "Tywin Lannister",
    "description" : "Patriarch, Lord of Casterly Rock, Warden of the West"
  }, {
    "houseId" : {
      "id" : 2
    },
    "characterId" : {
      "id" : 11
    },
    "castleId" : {
      "id" : 6
    },
    "name" : "Cersei Lannister",
    "description" : "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister"
  }, {
    "houseId" : {
      "id" : 2
    },
    "characterId" : {
      "id" : 12
    },
    "castleId" : {
      "id" : 6
    },
    "name" : "Jaime Lannister",
    "description" : "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard"
  }, {
    "houseId" : {
      "id" : 2
    },
    "characterId" : {
      "id" : 13
    },
    "castleId" : {
      "id" : 6
    },
    "name" : "Tyrion Lannister",
    "description" : "Tywin’s youngest son, acting Hand of the King and Master of Coin"
  }, {
    "houseId" : {
      "id" : 2
    },
    "characterId" : {
      "id" : 14
    },
    "castleId" : {
      "id" : 6
    },
    "name" : "Joffrey Baratheon",
    "description" : "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son"
  }, {
    "houseId" : {
      "id" : 2
    },
    "characterId" : {
      "id" : 15
    },
    "castleId" : {
      "id" : 6
    },
    "name" : "Myrcella Baratheon",
    "description" : "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter"
  }, {
    "houseId" : {
      "id" : 2
    },
    "characterId" : {
      "id" : 16
    },
    "castleId" : {
      "id" : 6
    },
    "name" : "Tommen Baratheon",
    "description" : "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son"
  }, {
    "houseId" : {
      "id" : 3
    },
    "characterId" : {
      "id" : 17
    },
    "castleId" : {
      "id" : 6
    },
    "name" : "Robert Baratheon",
    "description" : "Patriarch, King of the Seven Kingdoms"
  }, {
    "houseId" : {
      "id" : 3
    },
    "characterId" : {
      "id" : 18
    },
    "castleId" : {
      "id" : 1
    },
    "name" : "Stannis Baratheon",
    "description" : "Robert’s younger brother, Renley’s older brother, Lord of Dragonstone"
  }, {
    "houseId" : {
      "id" : 3
    },
    "characterId" : {
      "id" : 19
    },
    "castleId" : {
      "id" : 9
    },
    "name" : "Renly Baratheon",
    "description" : "Youngest of the Baratheon brothers, Lord of Storm’s End"
  }, {
    "houseId" : {
      "id" : 4
    },
    "characterId" : {
      "id" : 20
    },
    "castleId" : {
      "id" : 1
    },
    "name" : "Daenerys Targereyn",
    "description" : "Mother of Dragons, Khaleesi"
  }, {
    "houseId" : {
      "id" : 4
    },
    "characterId" : {
      "id" : 21
    },
    "castleId" : {
      "id" : 1
    },
    "name" : "Viserys Targaryen",
    "description" : "Daenerys’s brother"
  }, {
    "houseId" : {
      "id" : 4
    },
    "characterId" : {
      "id" : 22
    },
    "castleId" : {
      "id" : 1
    },
    "name" : "Aerys II Targaryen",
    "description" : "Daenerys’s father, former King of the Seven Kingdoms, deceased"
  }, {
    "houseId" : {
      "id" : 5
    },
    "characterId" : {
      "id" : 23
    },
    "castleId" : {
      "id" : 10
    },
    "name" : "Balon Greyjoy",
    "description" : "Patriarch, Lord of the Iron Islands"
  }, {
    "houseId" : {
      "id" : 5
    },
    "characterId" : {
      "id" : 24
    },
    "castleId" : {
      "id" : 10
    },
    "name" : "Theon Greyjoy",
    "description" : "Balon’s first-born son, heir apparent to the Iron Islands"
  }, {
    "houseId" : {
      "id" : 5
    },
    "characterId" : {
      "id" : 25
    },
    "castleId" : {
      "id" : 10
    },
    "name" : "Yara Greyjoy",
    "description" : "Balon’s only daughter and oldest child"
  }, {
    "houseId" : {
      "id" : 6
    },
    "characterId" : {
      "id" : 26
    },
    "castleId" : {
      "id" : 5
    },
    "name" : "Jon Arryn",
    "description" : "Patriarch, former Hand of the King, deceased"
  }, {
    "houseId" : {
      "id" : 6
    },
    "characterId" : {
      "id" : 27
    },
    "castleId" : {
      "id" : 5
    },
    "name" : "Lysa Arryn",
    "description" : "Catelyn’s younger sister"
  }, {
    "houseId" : {
      "id" : 6
    },
    "characterId" : {
      "id" : 28
    },
    "castleId" : {
      "id" : 5
    },
    "name" : "Robert Arryn",
    "description" : "Lysa’s son, Lord of the Eyrie"
  }, {
    "houseId" : {
      "id" : 7
    },
    "characterId" : {
      "id" : 29
    },
    "castleId" : {
      "id" : 11
    },
    "name" : "Doran Martell",
    "description" : "Patriarch, Prince of Dorne"
  }, {
    "houseId" : {
      "id" : 7
    },
    "characterId" : {
      "id" : 30
    },
    "castleId" : {
      "id" : 11
    },
    "name" : "Oberyn Martell",
    "description" : "Doran’s youngest brother"
  }, {
    "houseId" : {
      "id" : 8
    },
    "characterId" : {
      "id" : 31
    },
    "castleId" : {
      "id" : 7
    },
    "name" : "Hoster Tully",
    "description" : "Patriarch, Lord of Riverrun, Catelyn and Lysa’s father"
  }, {
    "houseId" : {
      "id" : 8
    },
    "characterId" : {
      "id" : 32
    },
    "castleId" : {
      "id" : 7
    },
    "name" : "Edmure Tully",
    "description" : "Hoster’s only son and heir to Riverrun"
  }, {
    "houseId" : {
      "id" : 9
    },
    "characterId" : {
      "id" : 33
    },
    "castleId" : {
      "id" : 12
    },
    "name" : "Mace Tyrell",
    "description" : "Patriarch, Lord of Highgarden"
  }, {
    "houseId" : {
      "id" : 9
    },
    "characterId" : {
      "id" : 34
    },
    "castleId" : {
      "id" : 12
    },
    "name" : "Olenna Tyrell",
    "description" : "Mace’s mother"
  }, {
    "houseId" : {
      "id" : 9
    },
    "characterId" : {
      "id" : 35
    },
    "castleId" : {
      "id" : 12
    },
    "name" : "Margaery Tyrell",
    "description" : "Mace’s daughter, wife of Renly Baratheon"
  }, {
    "houseId" : {
      "id" : 9
    },
    "characterId" : {
      "id" : 36
    },
    "castleId" : {
      "id" : 12
    },
    "name" : "Loras Tyrell",
    "description" : "Mace’s son, heir to House Tyrell"
  } ]
}
```

## [GET] [http://0.0.0.0:8080/characters/{id}](http://0.0.0.0:8080/characters/1)
Character details by Id. `id` must be an **int** in the range `[1..36]`.

### Response

```json
{
  "houseId" : {
    "id" : 1
  },
  "characterId" : {
    "id" : 1
  },
  "castleId" : {
    "id" : 4
  },
  "name" : "Eddard (Ned) Stark",
  "description" : "Patriarch, Lord of Winterfell, Warden of the North"
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

## [GET] [http://0.0.0.0:8080/jamielannister/seats](http://0.0.0.0:8080/jamielanniester/seats)

This endpoint represent **independent computations**. It retrieves castle details from the DB using two different `CastleIds` 
for the Castles where Jamie Lannister has a seat in. Afterwards, when both fetches are complete, it combines them into a single 
result (a `List<Castle>`).

### Response

```json
[ {
  "castleId" : {
    "id" : 3
  },
  "name" : "Casterly Rock",
  "description" : "Casterly Rock is the ancestral stronghold of House Lannister. It is located on the Western coast of Westeros on a rocky promontory overlooking the Sunset Sea. It overlooks the major city of Lannisport. A major goldmine is located under Casterly Rock. It is one of the most productive in the realm and provides House Lannister with their wealth."
}, {
  "castleId" : {
    "id" : 6
  },
  "name" : "Red Keep",
  "description" : "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
} ]
```

## [GET] [http://0.0.0.0:8080/got](http://0.0.0.0:8080/got)
Overall combined endpoint. It's like a complete wiki, since it returns all the info available in the DB: All the houses, 
including all their characters per house, which include the castle they have a seat in.

This endpoint represents **sequential computation**, since it needs to fetch from Houses endpoint, then use the result to 
fetch characters per `HouseId`, and then use the result of that one to fetch Castles per `CastleId` (included on each 
character).

### Response

```json

{
  "houses": [
    {
      "houseId": {
        "id": 1
      },
      "name": "Stark",
      "description": "They are the ruler of the north or in other words the main house of the north. They rule from the Castle of Winterfell.",
      "characters": [
        {
          "id": {
            "id": 1
          },
          "name": "Eddard (Ned) Stark",
          "description": "Patriarch, Lord of Winterfell, Warden of the North",
          "castle": {
            "castleId": {
              "castleId": 4
            },
            "name": "Winterfell",
            "description": "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
          }
        },
        {
          "id": {
            "id": 2
          },
          "name": "Catelyn Stark",
          "description": "Ned’s wife",
          "castle": {
            "castleId": {
              "castleId": 4
            },
            "name": "Winterfell",
            "description": "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
          }
        },
        {
          "id": {
            "id": 3
          },
          "name": "Robb Stark",
          "description": "Ned and Catelyn’s oldest son, heir to Winterfell",
          "castle": {
            "castleId": {
              "castleId": 4
            },
            "name": "Winterfell",
            "description": "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
          }
        },
        {
          "id": {
            "id": 4
          },
          "name": "Sansa Stark",
          "description": "Ned and Catelyn’s oldest daughter, betrothed to King Joffrey Baratheon",
          "castle": {
            "castleId": {
              "castleId": 4
            },
            "name": "Winterfell",
            "description": "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
          }
        },
        {
          "id": {
            "id": 5
          },
          "name": "Arya Stark",
          "description": "Catelyn’s youngest daughter",
          "castle": {
            "castleId": {
              "castleId": 4
            },
            "name": "Winterfell",
            "description": "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
          }
        },
        {
          "id": {
            "id": 6
          },
          "name": "Bran Stark",
          "description": "Ned and Catelyn’s middle son",
          "castle": {
            "castleId": {
              "castleId": 4
            },
            "name": "Winterfell",
            "description": "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
          }
        },
        {
          "id": {
            "id": 7
          },
          "name": "Rickon Stark",
          "description": "Ned and Catelyn’s youngest son",
          "castle": {
            "castleId": {
              "castleId": 4
            },
            "name": "Winterfell",
            "description": "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
          }
        },
        {
          "id": {
            "id": 8
          },
          "name": "Jon Snow",
          "description": "Ned’s illegitimate son, member of the Night’s Watch",
          "castle": {
            "castleId": {
              "castleId": 4
            },
            "name": "Winterfell",
            "description": "Winterfell is the seat of the ruler of the North and the ancestral home of House Stark. It is a very large castle located at the center of the North, from where the head of House Stark rules over his or her people. A small Godswood is enclosed within the walls. It is the regional capital of the North under Jon Snow The castle is located alongside the Kingsroad as it makes its way from the Wall to the capital of the Seven Kingdoms, King's Landing, more than a thousand miles to the south."
          }
        },
        {
          "id": {
            "id": 9
          },
          "name": "Benjen Stark",
          "description": "Ned’s younger brother, First Ranger of the Night’s Watch",
          "castle": {
            "castleId": {
              "castleId": 8
            },
            "name": "Castle Black",
            "description": "Castle Black is the primary headquarters and redoubt of the Night's Watch. It is located roughly halfway along the length of the Wall on its southern side, at the northern end of the Kingsroad. It is a dark and chilling home to its garrison."
          }
        }
      ]
    },
    {
      "houseId": {
        "id": 2
      },
      "name": "Lannister",
      "description": "They are the warden of the west or the main house of the west. The rule from the Casterly Rock of the Westerlands.",
      "characters": [
        {
          "id": {
            "id": 10
          },
          "name": "Tywin Lannister",
          "description": "Patriarch, Lord of Casterly Rock, Warden of the West",
          "castle": {
            "castleId": {
              "castleId": 6
            },
            "name": "Red Keep",
            "description": "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
          }
        },
        {
          "id": {
            "id": 11
          },
          "name": "Cersei Lannister",
          "description": "Tywin’s daughter, Queen of the Seven Kingdoms, Jaime’s twin sister",
          "castle": {
            "castleId": {
              "castleId": 6
            },
            "name": "Red Keep",
            "description": "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
          }
        },
        {
          "id": {
            "id": 12
          },
          "name": "Jaime Lannister",
          "description": "Tywin’s oldest son, Joffrey’s biological father, member of the Kingsguard",
          "castle": {
            "castleId": {
              "castleId": 6
            },
            "name": "Red Keep",
            "description": "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
          }
        },
        {
          "id": {
            "id": 13
          },
          "name": "Tyrion Lannister",
          "description": "Tywin’s youngest son, acting Hand of the King and Master of Coin",
          "castle": {
            "castleId": {
              "castleId": 6
            },
            "name": "Red Keep",
            "description": "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
          }
        },
        {
          "id": {
            "id": 14
          },
          "name": "Joffrey Baratheon",
          "description": "Cersei’s eldest son, thought to be King Robert’s son, but is really Jaime’s son",
          "castle": {
            "castleId": {
              "castleId": 6
            },
            "name": "Red Keep",
            "description": "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
          }
        },
        {
          "id": {
            "id": 15
          },
          "name": "Myrcella Baratheon",
          "description": "Cersei’s eldest daughter, thought to be King Robert’s daughter, but is really Jaime’s daughter",
          "castle": {
            "castleId": {
              "castleId": 6
            },
            "name": "Red Keep",
            "description": "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
          }
        },
        {
          "id": {
            "id": 16
          },
          "name": "Tommen Baratheon",
          "description": "Cersei’s youngest son, thought to be King Robert’s son, but is really Jaime’s son",
          "castle": {
            "castleId": {
              "castleId": 6
            },
            "name": "Red Keep",
            "description": "The Red Keep, previously known as Aegonfort, is the residence of the King of the Andals and the First Men, his family and his court, located within King's Landing, the capital of the Seven Kingdoms. It dominates the skyline of the city, and serves as the city's primary fortress and redoubt."
          }
        }
      ]
    }
    // more houses...
  ]
}
```
