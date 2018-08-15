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

# Routes

## [http://0.0.0.0:8080](http://0.0.0.0:8080)
Welcome page.

### Response
```json
{
  "message": "Welcome to the Game of Thrones API!"
}
```

## [http://0.0.0.0:8080/houses](http://0.0.0.0:8080/houses)
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

## [http://0.0.0.0:8080/houses/{houseName}](http://0.0.0.0:8080/houses/stark)
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

## [http://0.0.0.0:8080/houses/{id}](http://0.0.0.0:8080/houses/1)
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