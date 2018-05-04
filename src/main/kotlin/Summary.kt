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

import java.io.File

/*
*   Programming Kotlin - Packt
*   [Chapter 2]
*   - Variables and values
*   - Control flow and expressions
*   - Type inference
*   - Smart casting
*   - Basic types and the kotlin type hierarchy
*/

// Single-line comments start with //
/*
Multi-line comments look like this.
*/

// The "package" keyword works in the same way as in Java.
// The package name is used to give us the fully qualified name (FQN) for a class, object, interface, or function
//package com.example.kotlin

// Imports
// import com.packt.myproject.Foo
// import com.packt.otherproject.Foo as Foo2
// import com.packt.myproject.*

/*
The entry point to a Kotlin program is a function named "main".
The function is passed an array containing any command line arguments.
*/
fun main(args: Array<String>) {

    // [Vals and vars]
    // Declaring values is done using either "var" or "val".
    // "val" declarations cannot be reassigned, whereas "vars" can.
    val fooVal = 10 // we cannot later reassign fooVal to something else
    var fooVar = 10
    fooVar = 20 // fooVar can be reassigned

    // [Type inference]
    // In most cases, Kotlin can determine what the type of a variable is,
    // so we don't have to explicitly specify it every time.
    // We can explicitly declare the type of a variable like so:
    val foo: Int = 7
    val bar = 10
    val hello = "Hello"

    // [Basic types]
    val double = 64.0
    val float = 32F
    val long = 64L
    val int = 32
    val short = 16
    val byte = 8
    val hexadecimal = 0x16
    val binary = 0b101

    // Conversion must be invoked explicitly
    val otherLong = int.toLong()
    val direct = 25.toLong()

    // [Ranges]
    // A range is defined as an interval that has a start value and an end value.
    val oneToNine = 1..9
    // Once a range is created, the in operator can be used to test whether a given value is
    // included in the range.
    println("8 is in range? ${8 in oneToNine}") // => 8 is in range? true
    val oneToTen = (1..10).step(2).reversed() // => 9, 7, 5, 3, 1
    val tenToOne = 10.downTo(1)
    println("${tenToOne.first} - ${tenToOne.last}") // => 10 - 1

    // [Explicit casting]
    val any: Any = "I am a string"
    val string = any as String // true
    val file: File? = any as? File // null (avoid ClassCastException)
    println(string.takeLast(4)) // => ring

    // [Smart casts]
    // We can check if an object is a particular type by using the "is" operator.
    // If an object passes a type check then it can be used as that type without
    // explicitly casting it.
    fun smartCastExample(x: Any): Boolean {
        if (x is Boolean) {
            // x is automatically cast to Boolean
            return x
        } else if (x is Int) {
            // x is automatically cast to Int
            return x > 0
        } else if (x is String) {
            // x is automatically cast to String
            return x.isNotEmpty()
        } else {
            return false
        }
    }
    println(smartCastExample("Hello, world!")) // => true
    println(smartCastExample("")) // => false
    println(smartCastExample(5)) // => true
    println(smartCastExample(0)) // => false
    println(smartCastExample(true)) // => true

    // Smartcast also works with when block
    fun smartCastWhenExample(x: Any) = when (x) {
        is Boolean -> x
        is Int -> x > 0
        is String -> x.isNotEmpty()
        else -> false
    }

    // [Strings]
    // Strings are immutable.
    // Strings can be represented in a similar way as in Java.
    // Escaping is done with a backslash.
    val fooString = "My String Is Here!"
    val barString = "Printing on a new line?\nNo Problem!"
    val bazString = "Do you want to add a tab?\tNo Problem!"
    println(fooString)
    println(barString)
    println(bazString)
    println("John Doe"[2]) // => h
    println("John Doe".startsWith("J")) // => true

    // A raw string is delimited by a triple quote (""").
    // Raw strings can contain newlines and any other characters.
    val fooRawString = """
    fun helloWorld(val name : String) {
        println("Hello, world!")
    }
    """
    println(fooRawString)

    // Strings can contain template expressions.
    // A template expression starts with a dollar sign ($).
    // String templates are a simple and effective way of embedding values, variables, or even
    // expressions inside a string without the need for pattern replacement or string concatenation
    val fooTemplateString = "$fooString has ${fooString.length} characters"
    println(fooTemplateString) // => My String Is Here! has 18 characters 

    // For a variable to hold null it must be explicitly specified as nullable.
    // A variable can be specified as nullable by appending a ? to its type.
    // We can access a nullable variable by using the ?. operator.
    // We can use the ?: operator to specify an alternative value to use
    // if a variable is null.
    var fooNullable: String? = "abc"
    println(fooNullable?.length) // => 3
    println(fooNullable?.length ?: -1) // => 3
    fooNullable = null
    println(fooNullable?.length) // => null
    println(fooNullable?.length ?: -1) // => -1

    // A "for" loop can be used with anything that provides an iterator.
    for (c in "hello") {
        println(c)
    }

    // "while" loops work in the same way as other languages.
    var ctr = 0
    while (ctr < 5) {
        println(ctr)
        ctr++
    }
    do {
        println(ctr)
        ctr++
    } while (ctr < 10)


    // "if" can be used as an expression that returns a value.
    // For this reason the ternary ?: operator is not needed in Kotlin.
    val num = 5
    val message = if (num % 2 == 0) "even" else "odd"
    println("$num is $message") // => 5 is odd

    // "when" can be used as an alternative to "if-else if" chains.
    val i = 10
    when {
        i < 7 -> println("first block")
        fooString.startsWith("hello") -> println("second block")
        else -> println("else block")
    }

    // "when" can be used with an argument.
    when (i) {
        0, 21 -> println("0 or 21")
        in 1..20 -> println("in the range 1 to 20")
        else -> println("none of the above")
    }

    // "when" can be used as a function that returns a value.
    val result = when (i) {
        0, 21 -> "0 or 21"
        in 1..20 -> "in the range 1 to 20"
        else -> "none of the above"
    }
    println(result)

    // [Functions]
    // Functions can be declared using the "fun" keyword.
    // Function arguments are specified in brackets after the function name.
    // Function arguments can optionally have a default value.
    // The function return type, if required, is specified after the arguments.
    fun hello(name: String = "world"): String {
        return "Hello, $name!"
    }

    fun bye(bye: String = "Bye", name: String): String {
        return "$bye, $name!!"
    }
    println(hello("foo")) // => Hello, foo!
    println(hello(name = "bar")) // => Hello, bar!
    println(hello()) // => Hello, world!
    println(bye(name = "John", bye = "Good bye")) // => Good bye, John!!
    println(bye(name = "John")) // => Bye, John!!

    // A function parameter may be marked with the "vararg" keyword
    // to allow a variable number of arguments to be passed to the function.
    fun varargExample(vararg names: Int) {
        println("Argument has ${names.size} elements")
    }
    varargExample() // => Argument has 0 elements
    varargExample(1) // => Argument has 1 elements
    varargExample(1, 2, 3) // => Argument has 3 elements

    // Using array to supply variable arguments Use the * operator in front of the array variable
    val intArray = intArrayOf(1, 2, 3, 4)
    val array = Array(5, { i -> i + 1 })
    varargExample(*intArray) // => Argument has 4 elements
    varargExample(*array.toIntArray()) // => Argument has 5 elements

    // When a function consists of a single expression then the curly brackets can
    // be omitted. The body is specified after a = symbol.
    fun odd(x: Int): Boolean = x % 2 == 1
    println(odd(6)) // => false
    println(odd(7)) // => true

    // If the return type can be inferred then we don't need to specify it.
    fun even(x: Int) = x % 2 == 0
    println(even(6)) // => true
    println(even(7)) // => false

    // A function type is a type consisted of a function signature and function return type that
    // are separated by -> operator.
    // Example of a type for a function that takes no parameters and returns a Unit
    // () -> Unit
    //
    // Example of a type for a function that takes no parameters and returns a String
    // () -> String
    //
    // Example of a type for a function that takes a string and returns nothing
    // (String) -> Unit
    //
    // Example of a type for a function that takes two parameters and returns nothing
    // (String, Float) -> Unit
    //
    // Because a function type is just a type, it means that you can assign it to a variable, you can
    // pass it as a function argument and you can return it from a function
    val morning: (String) -> Unit = { x -> println(x) }
    morning("good morning")


    // Functions can take functions as arguments and return functions.
    fun not(f: (Int) -> Boolean): (Int) -> Boolean {
        return { n -> !f.invoke(n) }
    }

    // Named functions can be specified as arguments using the :: operator.
    val notOdd = not(::odd)
    val notEven = not(::even)
    // Lambda expressions can be specified as arguments.
    val notZero = not { n -> n == 0 }

    // If a lambda has only one parameter
    // then its declaration can be omitted (along with the ->).
    // The name of the single parameter will be "it".
    val notPositive = not { it > 0 }
    for (i in 0..4) {
        println("${notOdd(i)} ${notEven(i)} ${notZero(i)} ${notPositive(i)}")
    }

    // The "class" keyword is used to declare classes.
    class ExampleClass(val x: Int) {
        fun memberFunction(y: Int): Int {
            return x + y
        }

        infix fun infixMemberFunction(y: Int): Int {
            return x * y
        }
    }

    class AnotherExample /*constructor*/(val x: Int, y: Int) {
        private val z = "z"

        init {
            require(x > 0) { "X must be positive" }
            require(y in 1..10) { "Y is out of range" }
        }

        // Secondary constructor. For any secondary constructor you need to call
        // the primary constructor via 'this'
        constructor(x: Int) : this(x, 5)

        inner class innerClass {
            fun accessZ() {
                println("Accessing $z from a inner class")
            }
        }
    }

    // To create a new instance we call the constructor.
    // Note that Kotlin does not have a "new" keyword.
    val fooExampleClass = ExampleClass(7)
    // Member functions can be called using dot notation.
    println(fooExampleClass.memberFunction(4)) // => 11

    // If a function has been marked with the "infix" keyword then it can be
    // called using infix notation.
    println(fooExampleClass infixMemberFunction 4) // => 28

    // val fooAnotherExample = AnotherExample(10, 12) // throw IllegalArgumentException
    val barAnotherExample = AnotherExample(10, 4) // Primary constructor
    val fooAnotherExample = AnotherExample(10) // Secondary constructor
    println("${barAnotherExample.x}") // We can't access the 'y' value without val
    println("${barAnotherExample.innerClass().accessZ()}") // => Accessing z from a inner class

    // Data classes are a concise way to create classes that just hold data.
    // The "hashCode"/"equals" and "toString" methods are automatically generated.
    data class DataClassExample(val x: Int, val y: Int, val z: Int)

    val fooData = DataClassExample(1, 2, 4)
    println(fooData) // => DataClassExample(x=1, y=2, z=4)

    // Data classes have a "copy" function.
    val fooCopy = fooData.copy(y = 100)
    println(fooCopy) // => DataClassExample(x=1, y=100, z=4)

    // Objects can be destructured into multiple variables.
    val (a, b, c) = fooCopy
    println("$a $b $c") // => 1 100 4

    // destructuring in "for" loop
    for ((a, b, c) in listOf(fooData)) {
        println("$a $b $c") // => 1 100 4
    }

    val mapData = mapOf("a" to 1, "b" to 2)
    // Map.Entry is destructurable as well
    for ((key, value) in mapData) {
        println("$key -> $value")
    }

    // The "with" function is similar to the JavaScript "with" statement.
    data class MutableDataClassExample(var x: Int, var y: Int, var z: Int)

    val fooMutableData = MutableDataClassExample(7, 4, 9)
    with(fooMutableData) {
        x -= 2
        y += 2
        z--
    }
    println(fooMutableData) // => MutableDataClassExample(x=5, y=6, z=8)

    // [Arrays]
    // In Kotlin, we can create an array by using the library function arrayOf():
    val cardNames = arrayOf("Jack", "Queen", "King")
    println(cardNames[1]) // => Queen
    for (index in cardNames.indices) {
        println("Element $index is ${cardNames[index]}")
    }

    val cards = intArrayOf(10, 11, 12)
    println("${cards[1]}") // => 11
    // Alternatively, we can create an Array from an initial size and a function, which is used to
    // generate each element:
    val allCards = Array(12, { i -> i + 1 })
    println("${allCards.first()} - ${allCards.last()}") // => 1 - 12

    //We can create a list using the "listOf" function.
    // The list will be immutable - elements cannot be added or removed.
    val fooList = listOf("a", "b", "c")
    println(fooList.size) // => 3
    println(fooList.first()) // => a
    println(fooList.last()) // => c
    // Elements of a list can be accessed by their index.
    println(fooList[1]) // => b

    // A mutable list can be created using the "mutableListOf" function.
    val fooMutableList = mutableListOf("a", "b", "c")
    fooMutableList.add("d")
    println(fooMutableList.last()) // => d
    println(fooMutableList.size) // => 4

    // We can create a set using the "setOf" function.
    val fooSet = setOf("a", "b", "c")
    println(fooSet.contains("a")) // => true
    println(fooSet.contains("z")) // => false

    // We can create a map using the "mapOf" function.
    val fooMap = mapOf("a" to 8, "b" to 7, "c" to 9)
    // Map values can be accessed by their key.
    println(fooMap["a"]) // => 8

    // Sequences represent lazily-evaluated collections.
    // We can create a sequence using the "generateSequence" function.
    val fooSequence = generateSequence(1, { it + 1 })
    val x = fooSequence.take(10).toList()
    println(x) // => [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    // An example of using a sequence to generate Fibonacci numbers:
    fun fibonacciSequence(): Sequence<Long> {
        var a = 0L
        var b = 1L

        fun next(): Long {
            val result = a + b
            a = b
            b = result
            return a
        }

        return generateSequence(::next)
    }

    val y = fibonacciSequence().take(10).toList()
    println(y) // => [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]

    // Kotlin provides higher-order functions for working with collections.
    val z = (1..9).map { it * 3 }
            .filter { it < 20 }
            .groupBy { it % 2 == 0 }
            .mapKeys { if (it.key) "even" else "odd" }
    println(z) // => {odd=[3, 9, 15], even=[6, 12, 18]}

    // Extensions are a way to add new functionality to a class.
    // This is similar to C# extension methods.
    fun String.remove(c: Char): String {
        return this.filter { it != c }
    }
    println("Hello, world!".remove('l')) // => Heo, word!

    println(EnumExample.A) // => A
    println(ObjectExample.hello()) // => Hello

    // Enum classes
    direction() // => Angle: 270 - Name: South - Ordinal: 2
    println(Direction.values().size) // => 4
    println(Direction.valueOf("West").angle) // => 180
}

// Enum classes are similar to Java enum types.
enum class EnumExample {
    A, B, C
}

// Enum class with constructor
enum class Direction(val angle: Int) {
    North(90), West(180), South(270), East(0)
}

// All enumeration instances come with two properties predefined.
// One is name of type String and the second one is ordinal of type int.
fun direction() {
    val south = Direction.South
    println("Angle: ${south.angle} - Name: ${south.name} - Ordinal: ${south.ordinal}")
}


// The "object" keyword can be used to create SINGLETON objects.
// We cannot instantiate it but we can refer to its unique instance by its name.
// This is similar to Scala singleton objects.
object ObjectExample {
    fun hello(): String {
        return "Hello"
    }
}

fun useObject() {
    ObjectExample.hello() // => Hello
    val someRef: Any = ObjectExample // we use objects name just as is
}