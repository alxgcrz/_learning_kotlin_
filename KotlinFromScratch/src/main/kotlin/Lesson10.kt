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

package com.example.java

/**
 * Kotlin From Scratch: Exception Handling
 * {@link https://code.tutsplus.com/tutorials/kotlin-from-scratch-exception-handling--cms-29820}
 *
 * @author @alxgcrz <alxgcrz@outlook.com>
 * @year 2017
 */

fun main(args: Array<String>) {

    // [Exceptions]
    // In Kotlin all exceptions are unchecked. Unchecked exceptions that are thrown because of
    // flaws in your code. They are a direct or indirect subclass of the RuntimeException superclass.
    // In Java a method that might throw a exception needs to declare so in its method signature
    // using the 'throws' keyword. This are checked exceptions
    fun foo() {
        try {
            throw Exception("Exception message")
        } catch (e: Exception) {
            println("Exception handled")
        } finally {
            println("inside finally block")
        }
    }

    foo()

    // The try construct can be used as an expression.
    fun foo(number: Int) {
        val result = try {
            if (number != 1) {
                throw IllegalArgumentException()
            }
            true
        } catch (e: IllegalArgumentException) {
            false
        }

        println(result)
    }

    foo(2) // false
}

// However, if we want to communicate with Java callers that the addNumberToTwo() top-level function
// throws an exception, we simply add the @Throws annotation to the function signature.
@Throws(IllegalArgumentException::class)
fun addNumberToTwo(a: Any): Int {
    if (a !is Int) {
        throw IllegalArgumentException("Number must be an integer")
    }
    return 2 + a
}