[![Build Status](https://travis-ci.org/47deg/ArrowInPractice.svg?branch=master)](https://travis-ci.org/47deg/ArrowInPractice)

# Arrow in practice

Lambda.World Cádiz 2018 workshop hosted by [@raulraja](https://twitter.com/raulraja) and [@JorgeCastilloPR](https://twitter.com/JorgeCastilloPR) from [@47Degrees](https://www.47deg.com/).

This workshop starts with a basic **RESTful API** coded using [ktor](https://ktor.io/) ([@JetBrains](https://www.jetbrains.com/)). Our intention is to iterate over it converting it to a more functional style by putting [Arrow](https://arrow-kt.io/) into practice.

Some key points you'll learn:
* How to model your application immutable data using Arrow datatypes.
* How to operate over and transform the data using Typeclass defined behaviors.
* How to achieve polymorphism using the power of abstraction of typeclasses.
* How to use Optics to read and modify nested immutable data structures.
* How to encode sequential operations with a fancier syntax using Monad Comprehensions.
* How to encode non-dependent operations using the Applicative Builder.

# Exercises

## 1. Handling nullability

All details endpoint implementations are taking **nullables** now from the Database (they're optional), since the given Id could not exist. You must 
translate that concern to a FP related data type. Do it for the [GetCharacterDetails Endpoint](https://github.com/47deg/ArrowInPractice/blob/master/ENDPOINTS.md#get-http00008080charactersid).

To double check your changes, run [GetCharacterDetailsTest.kt](https://github.com/47deg/ArrowInPractice/blob/master/src/test/kotlin/com/fortysevendeg/arrowinpractice/characters/GetCharacterDetailsTest.kt) suite. You've got tests for both scenarios there (found and not found). They should keep passing. 

## 2. Handling exceptions

Any data base access is prone to throw exceptions, since it mimics a real DB access. That means we should <b>try</b> to cover that case. :wink: 

Staying on the same endpoint, you'll see how it's implementation ([GetCharacterDetails Endpoint](https://github.com/47deg/ArrowInPractice/blob/master/ENDPOINTS.md#get-http00008080charactersid)) uses `try catch` imperative style block to catch all possible database / parsing exceptions and return an `InvalidIdException` in that case, which translates to an HTTP `BadRequest` error response.

Again, run  [GetCharacterDetailsTest.kt](https://github.com/47deg/ArrowInPractice/blob/master/src/test/kotlin/com/fortysevendeg/arrowinpractice/characters/GetCharacterDetailsTest.kt) suite to validate your changes are correct. There's a test covering this case. Response should not vary and tests should keep passing.

## 3. Handling authentication

All endpoints (except the welcome one) are authenticated using `Basic` auth. The auth credentials are the following string encoded in Base64:
```
Basic georgerrmartin:lambdaworldrules
```

This is a validation and there's a data type for that type of error control in Arrow. Please switch the implementation to it for the [GetCharacterDetails Endpoint](https://github.com/47deg/ArrowInPractice/blob/master/ENDPOINTS.md#get-http00008080charactersid)).

## 4. Independent computations

There's an endpoint called [/jammielannister/seats](https://github.com/47deg/ArrowInPractice/blob/master/ENDPOINTS.md#get-http00008080jamielannisterseats) that 
encodes two **independent computations** that require to combine results in the end. You probably know how to do that using FP.

To validate this one, you can go to the [GetJamieLannisterSeatsTest](https://github.com/47deg/ArrowInPractice/blob/master/src/test/kotlin/com/fortysevendeg/arrowinpractice/castles/GetJamieLannisterSeatsTest.kt) suite and run it.

## 5. Dependent (sequential) computations

There's an endpoint called [got](https://github.com/47deg/ArrowInPractice/blob/master/ENDPOINTS.md#get-http00008080got) that encodes 
three **sequential (dependent) computations** to compose a combined result in the end. You know what that means, right? :stuck_out_tongue:

To validate this one, go to [GetGotTest.kt](https://github.com/47deg/ArrowInPractice/blob/master/src/test/kotlin/com/fortysevendeg/arrowinpractice/got/GetGotTest.kt) and run the suite.

## 6. Async computations

Every call is done asynchronously using Ktor framework behind the scenes. You should be able to translate that to <b>Async</b> constraints with Arrow.

Please, iterate [GetCharacterDetails Endpoint](https://github.com/47deg/ArrowInPractice/blob/master/ENDPOINTS.md#get-http00008080charactersid) one more time to achieve asynchrony without relying on the Ktor framework as is.

# Serialization

All content negotiation (serialization / deserialization) is handled using the `Ktor` built in `Jackson` support.

# Endpoints

For detailed docs per endpoint providing `Request` and `Response` **Json** formats check the [endpoint docs](./ENDPOINTS.md).

# Testing / Running the endpoints

## Http Request Built-in support

There are `HTTP Request` files for each one of the endpoints into the `httpqueries` folder in the root directory. If you 
have IntellIJ Ultimate installed, you can run them just by using the IDE and perform a real request to the endpoint. (Don't 
forget to run the app so you get your local server deployed to localhost).

![Http Requests Screenshot](./assets/HttpRequestsSS.png)
![Http Requests Screenshot2](./assets/HttpRequestsSS2.png)

## Tests

There are also Unit tests per endpoint covering all the required cases we're interested in. So don't worry if you don't own a IntellIJ Idea Ultimate serial, since 
we expect you to validate endpoints when you need using the unit tests. Run them like:
```
./gradlew test
``` 
You can also use the IDE tools (play icon to run tests on test class declarations or right click in test packages -> "run all tests in"...)

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