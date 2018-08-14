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

## Routes available

* `http://0.0.0.0:8080`: Welcome page.
* `http://0.0.0.0:8080/houses`: List of Houses with each one of their members.
* `http://0.0.0.0:8080/houses/stark`: House by name. (**one of**: `stark`, `lannister`, `baratheon`, `Targaryen`, `Greyjoy`, `Arryn`, `Martell`, `Tully`, `Tyrell`. We're probably missing some, so don't take it too seriously!)
