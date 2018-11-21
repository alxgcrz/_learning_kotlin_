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

package book

import java.io.FileNotFoundException

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
    val kPerson = Person::class
    println(kPerson.qualifiedName) // => book.Person

    // [@Throws]
    @Throws(FileNotFoundException::class)
    fun fileExists(path: String) {
        // ...
    }

}

class Person constructor(val firstName: String, val lastName: String, val age: Int?)

// [@JvmName]
@JvmName("filterStrings")
fun filter(list: List<String>): Unit {
    // ...
}

@JvmName("filterInts")
fun filter(list: List<Int>): Unit {
    // ...
}


