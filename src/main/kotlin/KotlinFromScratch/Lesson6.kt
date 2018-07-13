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

import com.example.lesson6.ClassA
import com.example.lesson6.ClassB

/**
 * Kotlin From Scratch: Advanced Functions
 * {@link https://code.tutsplus.com/tutorials/kotlin-from-scratch-advanced-functions--cms-29534}
 *
 * @author @alxgcrz <alxgcrz@outlook.com>
 * @year 2017
 */

fun main(args: Array<String>) {

    // [Extension functions]
    // Extension functions give us the ability to extend a class with new functionality without having to inherit
    // from that class. In other words, we don't need to create a new subtype or alter the original type.
    // An extension function is declared outside the class it wants to extend. In other words, it is also a top-level function
    // To create an extension function, you have to prefix the name of the class that you're extending
    // before the function name.
    fun String.upperCaseFirstLetter(): String {

        // Note that the this keyword inside the function body references the receiver object or instance.
        return this.substring(0, 1).toUpperCase().plus(this.substring(1))
    }

    val name = "john".upperCaseFirstLetter()
    println("My name is $name") // => My name is John
    println("My name is ${"Williams".upperCaseFirstLetter()}") // => My name is Williams

    // Member extension functions
    val classA = ClassA()
    classA.callExFunction(ClassB())

    // [Higher-Order Functions]
    // A higher-order function is just a function that takes another function
    // (or lambda expression) as a parameter, returns a function, or does both.
    fun sayHello(name: String, fn: (String) -> String): String {
        return fn(name).toUpperCase()
    }

    // ::hello
    fun hello(name: String): String {
        return "Hello, my name is $name"
    }

    // Invoking a Higher-Order function with :: and omitting the brackets
    println(sayHello("Greg", ::hello)) // => HELLO, MY NAME IS GREG

    // We can also pass a lambda
    println(sayHello("Martin", { "Hello, my name is $it" })) // => HELLO, MY NAME IS MARTIN

    // higher-order functions can also return a function to callers.
    fun multiplier(factor: Double): (Double) -> Double = { number -> number * factor }

    // now can invoke a function with the variable number
    val number = multiplier(2.0)
    println(number(3.0))

    // Closures
    // A closure is a function that has access to variables and parameters which are defined in an outer scope.
    fun printFilteredNamesByLength(length: Int) {
        val names = arrayListOf("Adam", "Andrew", "Chike", "Kechi")
        val filterResult = names.filter {
            it.length == length //length is defined outside the scope of the lambda
        }
        println(filterResult)
    }

    printFilteredNamesByLength(5) // [Chike, Kechi]
}