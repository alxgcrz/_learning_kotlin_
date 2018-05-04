/*
 * ************************************************************************
 *  Copyright (c) 2018 @alxgcrz <alxgcrz@outlook.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ***********************************************************************
 */

import java.io.FileNotFoundException
import kotlin.reflect.full.createInstance

/*
*   Programming Kotlin - Packt
*   [Chapter 7]
*   - Nullable and non-nullable types
*   - Null safe operators
*   - Reflection and runtime code inspection
*   - Annotations
*/

fun main(args: Array<String>) {

    // [Nullable types]
    // To inform the Kotlin compiler that we will allow a variable to contain a null, we must suffix
    // the type with a ?:
    var name: String? = null
    name = "John"
    name = null
    println(name) // => null

    // Similarly, we can return nullable and non-nullable types from a function, use them as
    // function parameters, and so on:
    fun getName(): String? = name // This function may or may not return a null reference

    fun getNotNullName(): String = name ?: "John" // This function cannot return a null reference

    println(getName()) // => null
    println(getNotNullName()) // => John

    // [Checking for null in conditions]
    // With smart cast, Kotlin compiler tracks conditions inside an if expression
    // As long as we perform a check that the variable is not null, then the compiler
    // will allow us to access the variable as if it was declared as a non-nullable type:
    var l = if (name != null) name.length else -1

    // [Safe calls with ?.]
    // Second option is the safe call operator, written ?.:
    // Safe calls are useful in chains.
    fun getCountryNameSafe(person: Person?): String? {
        return person?.address?.city?.country?.name
    }

    // [The !! Operator]
    // The not-null assertion operator (!!) converts any value to a non-null
    // type and throws an exception if the value is null.
    // val length: Int = name!!.length

    // [Elvis operator ?:]
    // If the expression to the left of ?: is not null, the elvis operator returns it,
    // otherwise it returns the expression to the right.
    val length = name?.length ?: -1
    println(length) // => -1

    // [Safe casting as?]
    // If we want to safely cast to a type, or null if the cast would fail, then we can use the safe cast
    //operator as?.
    val location: Any = "London"
    val safeString: String? = location as? String
    val safeInt: Int? = location as? Int
    println(safeString) // => London
    println(safeInt) // => null (avoid ClassCastException)

    // [Reflection]
    // Reflection is the name given to inspecting code at runtime instead of compile time. It can be
    // used to create instances of classes, look up functions and invoke them, inspect annotations,
    // find fields, and discover parameters and generics, all without knowing those details at
    // compile time.
    // The Kotlin reflection classes are not part of the kotlin-stdlib library, but
    // are instead part of an additional dependency called kotlin-reflect.

    // [KClass]
    // KClass is the central type used in Kotlin reflection. Each type has a KClass instance at
    // runtime that contains details of the functions, properties, annotations, and so on for that
    // type. To get an instance of a KClass for any type, we use the special ::class syntax on an
    // instance of that type:
    val kPerson = Person::class
    println(kPerson.qualifiedName) // => Person

    // Instantiation using reflection
    // One of the most common uses of reflection is to create instances of types without knowing
    // those types at compile time. The simplest way of doing this is to use
    // the createInstance function on a KClass reference
    // The drawback with createInstance is that it will only work for classes with no
    // parameters, or where all parameters are optional. A parameter is considered optional if it
    // has a default value supplied.
    val country = Country::class.createInstance()
    println(country.name) // => No country (default value in the constructor)

    // [Annotations]
    // Annotations allow developers to add extra meaning to classes, interfaces, parameters, and
    // so on at compile time. Annotations can then be used by the compiler or by your own code
    // via reflection at runtime. Depending on the annotation value, the meaning of the program
    // or data can change.

    // [Standard annotations]
    // The Kotlin standard library includes several annotations that affect the output of the
    // compiler

    // [@JvmStatic]
    // The @JvmStatic annotation informs the compiler that you wish the function or property
    // annotated to have a Java static method generated in the compiled output. This annotation
    // can only be used on objects or companion objects.

    // [@Throws]
    // Since all exceptions in Kotlin are unchecked exceptions, there is no need to add a list of
    // possible exceptions to method signatures like there is in Java. However, we may wish to
    // inform Java users that our API throws exceptions in certain situations. We can do this using
    // the @Throws annotation, which is used to instruct the compiler to generate throw clauses
    // on generated methods.
    @Throws(FileNotFoundException::class)
    fun fileExists(path: String) {
    }

    // [@JvmOverloads]
    // Given a function with default parameters, @JvmOverloads will
    // result in the compiler creating multiple, overloaded, methods for each default parameter.
}

// [@JvmName]
// Due to erasure in the JVM, it is impossible to declare two functions with the same name and
// the same erased signature.
// The most commonly used solution to this problem is to name the methods differently. But
// sometimes that isn't desirable. In Kotlin, we can retain the same names as long as we
// provide alternative names for when they are compiled. To do this we annotate the functions
// using @JvmName with a supplied alternative, as the following examples show:
@JvmName("filterStrings")
fun filter(list: List<String>): Unit {
}

@JvmName("filterInts")
fun filter(list: List<Int>): Unit {
}


class Person(name: String, val address: Address?)
class Address(name: String, postcode: String, val city: City?)
class City(name: String, val country: Country?)
class Country(val name: String = "No country")

fun getCountryName(person: Person?): String? {
    var countryName: String? = null
    if (person != null) {
        val address = person.address
        if (address != null) {
            val city = address.city
            if (city != null) {
                val country = city.country
                if (country != null) {
                    countryName = country.name
                }
            }
        }
    }
    return countryName
}

