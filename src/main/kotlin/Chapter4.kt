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

import java.util.*

/*
*   Programming Kotlin - Packt
*   [Chapter 4]
*   - Functions and functions literals
*   - Extension functions
*   - Named parameters and default parameters
*   - Operator overloading
*   - Recursion and tail recursion
*/

fun main(args: Array<String>) {
    // [Defining functions]
    // Functions are defined using the fun keyword with optional parameters and a return value.
    // The parameter list must always be present, even if no parameters are defined
    // Each parameter is in the form 'name: type'
    fun hello(): String = "Hello World"

    // Every function must return a value, and this value could be 'Unit' if a functions does not
    // return any meaningful value
    fun hello2(): Unit = println("Hello") // => Hello

    fun hello3() = println("Hello") // => Hello

    // Return type inferred by the compiler
    fun bye() = "Good bye"

    // [Single expression functions]
    // Such functions can use a shortened syntax that omits the braces and uses the '='
    // symbol before the expression rather than the 'return' keyword:
    fun hello4() = "Hello"

    // Or
    fun hello5(): String {
        return "Hello"
    }

    // [Member functions]
    // These functions are defined inside a class, object, or interface. A member function is
    // invoked using the name of the containing class or object instance with a dot, followed by
    // the function name and the arguments in parentheses
    val length = "Hello".take(5) // take(n: Int) is a member function of String class

    // [Local or nested functions]
    // Kotlin allows us to take this a step further by supporting functions declared inside other
    // functions. These are called local or nested functions. Functions can even be nested multiple
    // times.
    fun reverse(str: String) = str.reversed() // This function is nested because it is inside of the main function

    // Functions can even be nested multiple times.
    fun fizzbuzz(start: Int, end: Int) {
        for (k in start..end) {
            fun isFizz(): Boolean = k % 3 == 0
            fun isBuzz(): Boolean = k % 5 == 0
            when {
                isFizz() && isBuzz() -> println("Fizz Buzz")
                isFizz() -> println("Fizz")
                isBuzz() -> println("Buzz")
                else -> println(k)
            }
        }
    }

    // [Named and default parameters]
    // Named parameters allow us to be explicit about naming arguments when passed to a function
    fun bar(k: Int, m: Long = 1L, j: Boolean = true) = println("$k - $m - $j")

    // Once a parameter has been named, all the following parameters must be named too.
    bar(10) // => Omit default parameters
    bar(15, 30L)
    bar(20, 2L, true)
    bar(m = 30L, j = false, k = 10)
    bar(k = 10, m = 20L, j = true)
    bar(5, m = 2L, j = true)
    bar(6, 1L, j = true)

    // [Extension functions]
    // Kotlin provides the ability to extend a class with new functionality
    // without having to inherit from the class
    // The word 'this' is used to reference the object that the function was invoked on
    fun String.reverse() = this.reversed()
    println("Hello World".reverse()) // => dlroW olleH

    // [Infix functions]
    // Infix notation is the notation where an operator or function is placed
    // between the operands or arguments
    class InfixAccount {
        var balance = 0.0
        // To define your own infix function, use the infix keyword before the fun keyword,
        // remembering that infix functions have only one explicit parameter
        infix fun add(amount: Double) {
            this.balance = balance + amount
        }
    }

    val account = InfixAccount()
    // Infix notation
    account add 100.0
    println("Account balance: ${account.balance}")

    // Normal notation
    val map1 = mapOf(Pair("London", "UK"), Pair("Bucharest", "Romania"))
    // Infix notation
    val map2 = mapOf("London" to "UK", "Bucharest" to "Romania")

    // [Function literal]
    // we can define function literals. To do so, we simply enclose the code in braces
    val printHello = { println("Hello") }
    printHello()

    // Function literals can also accept parameters
    val printMessage = { message: String -> println(message) }
    printMessage("Hello")

    // [Multiple return value]
    // Let's say we need to return two things from a function. For example, a result object and a
    // status of some sort. A compact way of doing this in Kotlin is to declare a data class and
    // return its instance:
    data class Result(val result: Int, val status: Boolean)

    fun checkStatus() = Result(10, true)
    val (result, status) = checkStatus()


    // An alternative to a custom class is using the Kotlin standard library 'Pair' type.
    // This type simply wraps two values, which are accessed via the first and second fields
    fun roots(k: Int): Pair<Double, Double> {
        require(k >= 0)
        val root = Math.sqrt(k.toDouble())
        return Pair(root, -root)
    }

    val (a, b) = roots(9)
    println("$a # $b") // => 3.0 # -3.0

    // [varargs]
    // Kotlin allows functions to be defined such that they would accept a variable number of arguments
    // Varargs allow users to pass in a comma-separated list of arguments, which the compiler
    // will automatically wrap into an array
    fun brands(vararg brand: String) {
        for (brand in brand) {
            println("Brand: $brand")
        }
    }

    brands("BMW", "Audi", "Ford")

    // The vararg parameter is usually the last parameter, but it does not always have to be. If
    // there are other parameters after vararg, then arguments must be passed in using named parameters
    fun car(vararg model: String, year: Int) {}
    car("Audi", "A6", year = 2005)

    // [Spread operator]
    // The spread operator * unwraps the elements of the array and passes them in as
    // individual arguments
    val spread = arrayOf("Sony", "Nintendo", "Microsoft")
    brands(*spread)

    // [Standard Library Functions]
    // Apply, Let, Use, Run, Lazy, With, Repeat
    repeat(10, { println("Hello $it") }) // => Hello 0, Hello 1, Hello 2 ... Hello 9

    // [Assertions]
    // require, requireNotNull, check, error
    fun foo(k: Int, value: Boolean) {
        require(k > 10, { "k should be greater than 10" }) // => Throws an IllegalArgumentException with the result of calling lazyMessage if the value is false.
        requireNotNull(k) // => Throws an IllegalArgumentException if the value is null. Otherwise returns the not null value.
        check(value) // => Throws an IllegalStateException if the value is false
        if (k == 20) error("Error: k == 20") // => Throws an IllegalStateException with the given message.
    }

    // [Generic functions]
    // Functions can be generic in the types they use. This allows a function
    // to be written that can work with any type, rather than a specific type only. To do this, we
    // define the type parameters in the function signature:
    fun <T> choose(t1: T, t2: T, t3: T): T {
        return when (Random().nextInt(3)) {
            0 -> t1
            1 -> t2
            else -> t3
        }
    }
    // We can use the function with integers
    val r = choose(5, 7, 9)
    println(r)
    // Also we can use the function with strings
    val s = choose("BMW", "Audi", "Ford")
    println(s)
}

// [Top-level functions]
// These are functions that exist outside of any class, object, or interface and
// are defined directly inside a file
fun hello(): String = "Hello World!!"

// Top-level functions are especially useful for defining helper or utility functions
fun sayHello(name: String) = println("Hello $name")








