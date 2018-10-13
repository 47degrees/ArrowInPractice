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

# Cheat Sheet

There's a cheat sheet you can double check in case you need some easy peasy clues on how proceed with the Workshop. 
<details><summary>CLICK HERE TO REVEAL THE CHEATS!</summary>
<p>
<ul>
<li>All details endpoints are taking <b>nullables</b> now from the Database (they're optional), since the given Id could not exist.</li>
<li>Any data base access is prone to throw exceptions, since it mimics a real DB access. That means we should <b>try</b> to cover that case. :wink:</li>
<li>All the calls are being <b>validated</b> in terms of Authentication. That's something we can take care of with FP.</li>
<li>The [jamielannister/seats](https://github.com/47deg/ArrowInPractice/blob/master/ENDPOINTS.md#get-http00008080jamielanniesterseats) endpoint encodes two <b>independent computations</b> that require to combine results in the end. You probably know how to do that using FP.</li>
<li>The [got](https://github.com/47deg/ArrowInPractice/blob/master/ENDPOINTS.md#get-http00008080got) endpoint encodes three <b>sequential (dependent) computations</b> to compose a combined result in the end. You know what that means, right? :stuck_out_tongue:.
<li>Every call is done asynchronously using Ktor framework behind the scenes. You should be able to translate that to <b>Async</b> constraints with Arrow.</li>
</ul>
</p>
</details>

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
