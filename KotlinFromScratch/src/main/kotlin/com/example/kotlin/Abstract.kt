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

package com.example.kotlin

// Abstract class
abstract class Employee(val firstName: String, val lastName: String) {

    // We can also store state in abstract classes.
    val company = "Company SA"

    abstract fun earnings(): Double

    // Note that not all members have to be abstract.
    fun fullName(): String {
        return "$lastName $firstName"
    }

    open fun email() = "$firstName@company.com"
}

// Class that extends the abstract class
class Programmer(firstName: String, lastName: String) : Employee(firstName, lastName) {

    override fun earnings(): Double {
        return 2000.0
    }

    // Concrete classes (subclasses of the abstract class) can override a method's default implementation
    // but only if the method has the open modifier specified
    override fun email(): String {
        return "${firstName}_programing@company.com"
    }
}