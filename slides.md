autoscale: true
slidenumbers: true
footer:/           [@jorgecastillopr](https://twitter.com/jorgecastillopr) -> [@raulraja](https://twitter.com/raulraja) -> [@47deg](https://twitter.com/47deg) -> [Sources](https://github.com/47deg/arrowinpractice)

--

# Arrow in practice

Arrow is a Typed FP modular library for Kotin

https://arrow-kt.io/

---

# Ktor

Ktor is an Async DSL web framework for Kotlin

https://ktor.io/

---

# Ktor

Ktor allows you to define endpoints easily with its DSL

```kotlin
fun Routing.characterDetailsEndpoint(charactersDB: CharactersDatabase) {
  authenticate {
    get("/characters/{characterId}") { 
      TODO()
    }
  }
}
```

---

# Arrow

Arrow provides data types and type classes to program in a pure typed FP style

```kotlin
fun Routing.characterDetailsEndpoint(charactersDB: CharactersDatabase) {
  authenticate {
    getIO("/characters/{characterId}") { pipelineContext ->
      monad().binding {
        val maybeId: Option<String> = paramOf("characterId", pipelineContext.call)
        val characterIdString: String = idOrNotFound(maybeId).bind()
        val characterId = stringIdToLong(characterIdString).bind()
        val character = handleDBExceptions(fetchCharacterById(charactersDB, characterId)).bind()
        character
      }
    }
  }
}
```

---

# Arrow

In this workshop you will learn some of the Arrow basics and implement the methods powering this endpoint

[.code-highlight: 5-8]
```kotlin
fun Routing.characterDetailsEndpoint(charactersDB: CharactersDatabase) {
  authenticate {
    getIO("/characters/{characterId}") { pipelineContext ->
      monad().binding {
        val maybeId: Option<String> = paramOf("characterId", pipelineContext.call)
        val characterIdString: String = idOrNotFound(maybeId).bind()
        val characterId = stringIdToLong(characterIdString).bind()
        val character = handleDBExceptions(fetchCharacterById(charactersDB, characterId)).bind()
        character
      }
    }
  }
}
```

---

# Arrow :: Workshop

- Instructions for each one of the exercises are in the `README.md` file.
- As you complete exercises tests will start passing.
- If you have any questions or get stuck `raiseError(🙋)` and someone will come to help you!

---

# Arrow :: fold

We may use `fold` to contemplate all cases of a `Foldable` data type.

```kotlin
import arrow.core.Option

Option(1).fold({ 0 }, { it }) //1
None.fold({ 0 }, { it }) //0
```

---

# Arrow :: just :: raiseError

We may use `just` to to lift pure values into a data type.

```kotlin
import arrow.core.Option

IO.just(1) //IO<Int>
IO.raiseError<Int>(RuntimeException("BOOM")).attempt() //IO<Either<Throwable, Int>>
```

---

# Arrow :: handleErrorWith

`handleErrorWith` allows us to recover from failed context and translate exceptions

```kotlin
import arrow.effects.IO

val recovered: IO<Int> =
  IO { "x".toInt() }.handleErrorWith { e: Throwable ->
    when (e) {
      is RuntimeException -> IO.raiseError(SomeOtherException())
      else -> IO.raiseError(e)
    }
  }
```

---

# Arrow :: map

`map` allows us to map multiple independent values preserving type information

```kotlin
import arrow.instances.option.applicative.map

data class Profile(val name: String, val phone: Int, val address: List<String>)

map(profileService(), phoneService(), addressService()) { (name, phone, addresses) ->
  Profile(name, phone, addresses)
}
// Some(Profile(name=Alfredo Lambda, phone=55555555, address=[1 Main Street, 11130, NYC]))
```

---

# Arrow :: Workshop

Implementations may be completed with the following API but you can also be creative if you are familiar with FP and use other Arrow combinators. The following methods may be used to implement the exercises.

- Option#fold
- IO.just
- IO.raiseError
- IO.handleErrorWith
- Applicative#map (arrow.instances.map.applicative.map)

---

# Arrow :: Workshop Solutions

You can get a working workspace where all test pass with solutions in the `solutions` git tag.

```bash
git fetch --all --tags --prune
git checkout solutions
```

Or revert back to the original workshop state with

```bash
git fetch --all --tags --prune
git checkout workshop
```
