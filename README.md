# Arrow in practice

Lambda.World Cádiz 2018 workshop hosted by [@raulraja](https://twitter.com/raulraja) and [@JorgeCastilloPR](https://twitter.com/JorgeCastilloPR).

On this workshop we will take a basic REST client coded using [ktor](https://ktor.io/) ([@JetBrains](https://www.jetbrains.com/)) and will iterate over it to put [Arrow](https://arrow-kt.io/) into practice.

Some key points you'll learn:
* How to model your application data using Arrow datatypes.
* How to operate and transform the data using Typeclasses defined behaviors.
* How typeclasses are considered abstractions.
* How to achieve polymorphism using typeclasses.
* How to use Optics to query and modify nested immutable data structures.
* How to port an OOP oriented codebase to a pure typed functional style using Arrow.
* How to encode sequential operations with a fancier syntax using Monad Comprehensions.
* How to encode non-dependent operations using the Applicative Builder.

# Unauthenticated Routes

## [GET] [http://0.0.0.0:8080](http://0.0.0.0:8080)
Welcome page.

### Response
```json
{
  "message": "Welcome to the Game of Thrones API!"
}
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

### Response

**Created**: (Found a house with the same name)
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

# Authenticated Routes

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