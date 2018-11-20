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

/*
*   Programming Kotlin - Packt
*   [Chapter 5]
*   - High order functions and closures
*   - Anonymous functions
*   - Function references
*   - Functional programming idioms
*   - Custom DSLs
*/

fun main(args: Array<String>) {

    // [High order functions]
    fun foo(str: String, fn: (String) -> String) {
        val applied = fn(str)
        println(applied)
    }

    // To invoke this function, we can pass in a function literal
    foo("hello", { it.reversed() }) // => olleh
    foo("hello world", { str: String -> str.reversed() }) // => dlrow olleh

    val ints = listOf(1, 2, 3, 4, 5, 6)
    val odds = ints.filter { it % 2 == 1 }
    val evens = ints.filter { it % 2 == 0 }

    // [Returning a function]
    fun bar(): (String) -> String = { it.reversed() }

    fun modulo(k: Int): (Int) -> Boolean = { it % k == 0 }

    val reverse = bar()
    println(reverse("Hello")) // => olleH
    println(ints.filter(modulo(2))) // => [2, 4, 6]

    val isEven: (Int) -> Boolean = { it % 2 == 0 }
    val isEven2: (Int) -> Boolean = { k: Int -> k % 2 == 0 }

    // [Closure]
    var sum = 0
    ints.filter { it > 0 }.forEach {
        sum += it
    }
    println(sum) // => 21

    // [Anonymous function]
    val m = fun(a: String, b: String): String = a + b
    println(m("Hello", "World")) // => HelloWorld

    // [Top-level function references]
    fun isEven(k: Int): Boolean = k % 2 == 0
    println(ints.filter { isEven(it) }) // => [2, 4, 6]
    println(ints.filter(::isEven)) // => [2, 4, 6]

    // [Member and extension functions references]
    fun Int.isOdd(): Boolean = this % 2 != 0
    println(ints.filter { it.isOdd() }) // => [1, 3, 5]
    println(ints.filter(Int::isOdd)) // => [1, 3, 5]
}

// [Type alias]
typealias Cache = Map<String, String>


