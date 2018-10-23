[![Build Status](https://travis-ci.org/47deg/ArrowInPractice.svg?branch=master)](https://travis-ci.org/47deg/ArrowInPractice)

<img height="100" src="https://avatars2.githubusercontent.com/u/29458023?v=4&amp;s=200" width="100"> <img height="100" src="https://avatars1.githubusercontent.com/u/479857?s=200&v=4" with="100">

# Arrow in practice

Lambda.World Cádiz 2018 workshop hosted by [@JorgeCastilloPR](https://twitter.com/JorgeCastilloPR) and [@raulraja](https://twitter.com/raulraja) from [@47Degrees](https://www.47deg.com/).

This workshop starts with a basic **RESTful API** coded using [ktor](https://ktor.io/) ([@JetBrains](https://www.jetbrains.com/)). Our intention is to iterate over it converting it to a more functional style by putting [Arrow](https://arrow-kt.io/) into practice.

Some key points you'll learn:
* How to handle absent values incoming from an HttpRequest with the Arrow data types.
* How lift values and raise errors inside the context of `IO`
* How to defer side effecting and exception throwing computations with `IO`
* How to recover from failed computations in the context of `IO`
* How to reduce a set of potential values into a single one with `fold` 
* How to encode sequential operations with Monad Comprehensions.
* How to encode non-dependent operations using the Applicative Builder.

# Exercises

## 1a. Handling nullability

When starting a project with Arrow first go to [arrow-kt.io](https://arrow-kt.io/docs/#basic-setup) to include the necessary dependencies.
The dependencies used in this work shop are included below for convenience

```groovy
compile "io.arrow-kt:arrow-effects-instances:$arrow_version"
compile "io.arrow-kt:arrow-instances-data:$arrow_version"
```

To enable Arrow in your project include these dependencies in the `dependencies` section in build.gradle file:
the run:

```groovy
./gradlew clean build
```

All details endpoint implementations are non optional typed values now coming from the Database. These values may be absent when using an Id to look them up. You must 
translate that concern to a FP related data type such as `arrow.core.Option`. 
Make the function `com.fortysevendeg.arrowinpractice.workshop.ex1.paramOf` located at
`com/fortysevendeg/arrowinpractice/endpoints/CharacterDetails.kt` return an `Option<String>` instead of a `String?`.

To double check your changes, run `com.fortysevendeg.arrowinpractice.workshop.ex1.WorkshopTests` suite. 

You may run this test in IntelliJ IDEA (right click and Run in the test file) or via the command line with:

```groovy
./gradlew test --tests "com.fortysevendeg.arrowinpractice.workshop.ex1.WorkshopTests"
```

Once this exercise is completed the following test should pass:
* `1a should extract params from request`

If you want to keep test running while making changes you may prepend the `-t` modifier to the after `--tests` in the command above.

Reference Links:
 * [arrow.core.Option data type](https://arrow-kt.io/docs/datatypes/option/)
 * [Nullable types](https://kotlinlang.org/docs/reference/null-safety.html)

## 1b. Folding over optional values

Once we receive the endpoint parameters as `Option<String>` we need to contemplate the `Some` and `None` cases. You may use here [`when`](https://kotlinlang.org/docs/reference/control-flow.html#when-expression) or [`fold`](https://arrow-kt.io/docs/datatypes/option/) in order to contemplate both `Some` and `None` cases.

Modify `com.fortysevendeg.arrowinpractice.workshop.ex1.idOrNotFound` such as that if a value is found it's returned in `IO` and if the value is missing we raise a `NotFoundException` error in `IO`.

Once this exercise is completed the following test should pass:
* `1b should return a character Id or a raised NotFound exception in the context of IO`
* `1b should return a NotFound exception in the context of IO when an id is not found`

Reference Links:

[IO.just](https://arrow-kt.io/docs/effects/io/#just)
[IO.raiseError](https://arrow-kt.io/docs/effects/io/#raiseerror)

## 1c. Handling and recovering from errors

Converting string to long values may fail with an exception since it relies in a third party api `String.toLong`. Modify `com.fortysevendeg.arrowinpractice.workshop.ex1.stringIdToLong` so it captures this effect in `IO` and translates any thrown exceptions to a `InvalidIdException`. This may be implemented in a few different ways depending on whether you use `handleErrorWith`, `attempt + fold + just/raiseError`, etc.

Once this exercise is completed the following test should pass:
* `1c should properly handle String#toLong with valid Long values`
* `1c should properly handle String#toLong raising errors as InvalidIdException`

Reference Links:

[IO.raiseError](https://arrow-kt.io/docs/effects/io/#raiseerror)


## 1d. Returning db results and raising errors for missing db objects

When querying the database with a set of `Long` values the returned objects may not be found if the `ids` are not recognized in the db.
Return the db object in the context of `IO` or raise a `NotFoundException` when the db returns an absent value.

Once this exercise is completed the following test should pass:
* `1d fetch a character by id from the database for a given valid character id`
* `1d fetch a character by id from the database results in a NotFound raised error for invalid ids`

Reference Links:

[IO](https://arrow-kt.io/docs/effects/io/)
[Control structures `when`] (https://kotlinlang.org/docs/reference/control-flow.html#when-expression)

## 1e. Translating raised errors in the context of `IO`

Handle all database errors in `com.fortysevendeg.arrowinpractice.workshop.ex1.handleDBExceptions` so that `NotFoundException` is preserved but all other exceptions are translated into `InvalidIdException`. You may use `handleErrorWith` to recover from existing errors.

Once this exercise is completed the following test should pass:

* `1e handle DB exceptions preserving NotFoundExceptions`
* `1e handle DB exceptions preserving NotFoundExceptions but translating all others to InvalidIdException`

## 2a. Translating raised errors in the context of `IO`

When handling multiple ids for unrelated database objects we may independently fetch the objects `Applicative` vs explicitly fetching them one after another `Monad`.

Once this exercise is completed the following test should pass:

* `1e handle DB exceptions preserving NotFoundExceptions`
* `1e handle DB exceptions preserving NotFoundExceptions but translating all others to InvalidIdException`

Reference Links:

[Applicative Builder](https://arrow-kt.io/docs/typeclasses/applicative/#applicative-builder-examples)
[IO.just](https://arrow-kt.io/docs/effects/io/#just)
[IO.raiseError](https://arrow-kt.io/docs/effects/io/#raiseerror)

# License

    Copyright (C) 2018 47 Degrees

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.