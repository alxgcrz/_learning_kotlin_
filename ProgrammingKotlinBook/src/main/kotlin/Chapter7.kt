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
    var name: String? = null
    name = "John"
    name = null
    println(name) // => null

    // Functions
    fun getName(): String? = name // This function may or may not return a null reference

    fun getNotNullName(): String = name ?: "John" // This function cannot return a null reference

    println(getName()) // => null
    println(getNotNullName()) // => John

    // [Checking for null in conditions]
    var l = if (name != null) name.length else -1

    // [Safe calls with ?.]
    fun getCountryNameSafe(person: Person?): String? {
        return person?.address?.city?.country?.name
    }

    // [The !! Operator]
    // val length: Int = name!!.length

    // [Elvis operator ?:]
    val length = name?.length ?: -1
    println(length) // => -1

    // [Safe casting as?]
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


    // [@Throws]
    @Throws(FileNotFoundException::class)
    fun fileExists(path: String) {
        // ...
    }

}

// [@JvmName]
@JvmName("filterStrings")
fun filter(list: List<String>): Unit {
    // ...
}

@JvmName("filterInts")
fun filter(list: List<Int>): Unit {
    // ...
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

