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
*   [Chapter 9]
*   - What destructuring is and how data classes are automatically eligible for destructuring operations
*   - How you get copy, toString, hashCode and equals methods implemented for you
*   - Rules to obey when defining data classes
*   - Limitations of data classes
*/

fun main(args: Array<String>) {
    // Data classes are intended for types that are meant to be data containers and nothing more.
    data class BlogEntry(var title: String, var description: String?, val publishTime: String?)

    // You will notice that a writable field is marked as 'var',
    // whereas a read-only field is marked as 'val'.
    // For a given 'var' declaration in the constructor, the compiler will create the getters and
    // setters automatically. For a 'val' declaration the compiler only create the getters.
    val blogEntry = BlogEntry(title = "Kotlin is awesome", description = null, publishTime = "30/04/2018")

    // [The copy method]
    // When using a data class, you get a copy method out of the box. This method allows you to
    // create a new instance of your type while cherry-picking the fields you want to change.
    val copyBlogEntry = blogEntry.copy(title = "Properties in Kotlin")

    println(blogEntry) // => BlogEntry(title=Kotlin is awesome, description=null, publishTime=30/04/2018)
    println(copyBlogEntry) // => BlogEntry(title=Properties in Kotlin, description=null, publishTime=30/04/2018)

    // [hashCode and equals methods]
    // Every type is derived from Any, which comes with a hashCode method declaration. This is
    // the equivalent of a Java Object class hashCode method. This method is important when you
    // want to place your instances in collections, such as a map.
    // When implementing the method, you need to adhere to a contract:
    //  1. When invoked on the same object more than once during the runtime, the
    //  hashCode method must consistently return the same value, given the object was
    //  not modified.
    //  2. If for two objects the equals method returns true, then calling the hashCode
    //  method on each of them should return the same integer value.
    //  3. If two objects are not equal – that means the equals method returns false for the
    //  pair-it is not a requirement to have each object hashCode method return distinct
    //  values. However, producing a distinct integer for unequal objects could improve
    //  the performance of hash-based collections.
    data class BlogEntry2(var title: String, var description: String?, val publishTime: Date?) {
        override fun hashCode(): Int {
            return super.hashCode()
        }
    }

    // The other method you would get out of the box is the equals method. This indicates
    // whether the other object is structurally equal to the current one.
    data class BlogEntry3(var title: String, var description: String?, val publishTime: Date?) {
        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }
    }

    // [Destructed declarations]
    val (title, description, publishTime) = blogEntry
    println("$title - $description - $publishTime") // => Kotlin is awesome - null - 30/04/2018

    // [Data class definition rules]
    //  When you define a data class, you need to follow the
    // following rules:
    //  - The primary constructor needs to have at least one parameter
    //  - All primary constructor parameters need to be marked as val or var
    //  - Data classes cannot be abstract, open, sealed, or inner
    //  - Data classes cannot extend other classes (but may implement interfaces)
}

