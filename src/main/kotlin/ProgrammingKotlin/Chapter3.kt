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

import java.io.InputStream
import java.io.OutputStream
import java.math.BigDecimal
import java.util.*

/*
*   Programming Kotlin - Packt
*   [Chapter 3]
*   - Object-Oriented Programming in Kotlin
*   - Object and companion
*/

fun main(args: Array<String>) {
    // [Classes]
    // Classes are the main building blocks of any object-oriented programming language.
    // Classes are declared by using the 'class' keyword
    class Deposit

    // Compared to Java, you can define multiple classes within the same source file.
    // The class keyword can be preceded by the access level. If it is not specified,
    // it will default to public. This means anyone can create objects of this class.
    // The name of the class follows the keyword and the curly braces contain the class
    // body where the behavior and data are defined: fields, properties, and methods
    class Person constructor(val firstName: String, val lastName: String, val age: Int?)

    val person1 = Person("Alex", "Smith", 29)
    val person2 = Person("Jane", "Smith", null)
    println("${person1.firstName},${person1.lastName} is ${person1.age} years old")
    println("${person2.firstName},${person2.lastName} is ${person2.age?.toString()
            ?: "?"} years old")

    // Inside the 'init' block we can validate the incoming parameters.
    // This params are properties, not fields
    class PersonWithValidation /*constructor*/(val firstName: String, val lastName: String, val age: Int?) {
        init {
            require(firstName.trim().isNotEmpty()) {
                "Invalid firstName argument."
            }
            require(lastName.trim().isNotEmpty()) {
                "Invalid lastName argument."
            }
            if (age != null) {
                require(age in 0..149) { "Invalid age argument." }
            }
        }

        // Secondary constructor
        // For any secondary constructor you need to call the primary constructor via this, and pass
        // all the parameters required.
        constructor(firstName: String, lastName: String) : this(firstName, lastName, null)
    }

    val personWithValidation = PersonWithValidation(firstName = "John", lastName = "Doe")
    val personWithValidation2 = PersonWithValidation(firstName = "Will", lastName = "Smith", age = 45)

    // Prefixing your constructor arguments with val or var is not a must; if you don't want the
    // getter (or setter if you use var) to be generated, you can always do the following:
    class Person2(firstName: String, lastName: String, howOld: Int?) {
        private val name: String
        private val age: Int?

        init {
            this.name = "$firstName,$lastName"
            this.age = howOld
        }

        fun getName(): String = this.name
        fun getAge(): Int? = this.age
    }

    val person = Person2("John", "Doe", null)
    println(person.getName()) // => John,Doe

    // [Data classes]
    // It happens quite often we need to define classes for the sole purpose of holding data.
    data class Customer(val id: Int, val name: String, var address: String)

    val customer = Customer(id = 1, name = "John Doe", address = "Sesame Street 1")
    println(customer) // => Customer(id=1, name=John Doe, address=Sesame Street 1)

    // Singleton object
    println(SingletonObjectExample.hello()) // => Hello

    // Companion object
    val myClass = MyClass.create()

    // [Inheritance]
    // The preexisting class is called the super (or base or parent) class, and the brand
    // new class we are creating is called the derived class. There is a restriction on how many
    // super classes we can inherit from; on a JVM, you can only have one base class. But you can
    // inherit from multiple interfaces.
    open class Payment(val amount: BigDecimal)

    class CardPayment(amount: BigDecimal, val number: String, val expiryDate: Date,
                      val type: CardType) : Payment(amount)

    // [Visibility modifiers]
    // When you define your class, the contained methods, properties, or fields can have various
    // visibility levels. In Kotlin, there are four possible values:
    //  - Public: This can be accessed from anywhere
    //  - Internal: This can only be accessed from the module code
    //  - Protected: This can only be accessed from the class defining it and any derived classes
    //  - Private: This can only be accessed from the scope of the class defining it

    // [Abstract classes]
    // An abstract class is a partially defined class; properties and methods that have no
    // implementation must be implemented in a derived class, unless the derived class is meant
    // to be an abstract class as well
    abstract class A() {
        abstract fun doSomething()

        // You need to add the 'open' keyword as a prefix if you wanna override the function
        open fun doSomethingElse() {
            // ...
        }
    }

    // [Overriding rules]
    // In Kotlin, you would have to tag the function as being opened to redefine it.
    // To do so, you need to add the open keyword as a prefix to the method definition, and when
    // you redefine the method, you specifically have to mark it using the 'override' keyword:
    class B() : A() {
        override fun doSomething() {
            // ...
        }

        override fun doSomethingElse() {
            // ..
        }
    }
}

// [Enum classes]
// Enumeration is a specific type of class; a variable of a given enum type is limited to a set of
// predefined constants: the ones that have been defined by the type.
enum class CardType {
    VISA, MASTERCARD, AMEX
}

// Enum class with constructor
enum class Orientation(val angle: Int) {
    North(90), West(180), South(270), East(0)
}

// All enumeration instances come with two properties predefined.
// One is name of type String and the second one is ordinal of type int.
fun orientation() {
    val south = Orientation.South
    println("Angle: ${south.angle} - Name: ${south.name} - Ordinal: ${south.ordinal}")
    println("${Orientation.values()} - ${Orientation.valueOf("West")}")
}

/*
A singleton is a design pattern that limits the instantiation of a given class to one instance.
The "object" keyword can be used to create SINGLETON objects.
We cannot instantiate it but we can refer to its unique instance by its name.
This is similar to Scala singleton objects.
*/
object SingletonObjectExample {
    fun hello(): String {
        return "Hello"
    }
}

// [Companion object]
// There is a way to call a static method as you would do in Java. To achieve this, you will
// have to place your object within a class and mark it as a companion object.
class MyClass private constructor() {
    // The companion object has full visibility for all the methods and members of the class
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
    // The name of the companion object can be omitted, in which case the name Companion will be used:
    /*companion object {
        fun create(): MyClass = MyClass()
    }*/
}

// [Interfaces]
// An interface is nothing more than a contract; it contains definitions for a set of related
// functionalities. Just like Java 8, a Kotlin interface contains the
//declarations of abstract methods as well as method implementations. Unlike abstract
//classes, an interface cannot contain state; however, it can contain properties.
interface Document {
    val version: Long
    val size: Long

    // Default implementation
    val name: String
        get() = "NoName"

    fun save(input: InputStream)
    fun load(stream: OutputStream)

    // Default implementation
    fun getDescription(): String {
        return "Document $name has $size byte(-s)"
    }
}

// Implementing the interface
class DocumentImpl : Document {
    override val size: Long
        get() = 0

    override fun load(stream: OutputStream) {
    }

    override fun save(input: InputStream) {
    }

    override val version: Long
        get() = 0
}

// [Sealed classes]
// A sealed class in Kotlin is an abstract class, which can be extended by subclasses defined as
// nested classes within the sealed class itself.  Just like Enum, a sealed class hierarchy
// contains a fixed set of possible choices.
sealed class IntBinaryTree {
    class EmptyNode : IntBinaryTree()
    class IntBinaryTreeNode(val left: IntBinaryTree, val value: Int, val right: IntBinaryTree) : IntBinaryTree()
}



