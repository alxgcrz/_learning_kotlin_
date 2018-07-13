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

package com.example.lesson9

open class Student(val firstName: String, val lastName: String) {
    open fun schoolFees(): Double {
        return 0.0
    }
}

// Classes are final in Kotlin. We have to explicitly mark the superclass with the open modifier
class GraduateStudent(firstName: String, lastName: String) : Student(firstName, lastName) {

    var country = ""

    // Secondary constructor
    constructor(firstName: String, lastName: String, country: String = "USA") : this(firstName, lastName) {
        this.country = country
    }

    // Note that if you override a member of a superclass or interface,
    // the overriding member will also be open by default
    override fun schoolFees(): Double {
        return super.schoolFees() + calculateSchoolFees()
    }

    private fun calculateSchoolFees(): Double {
        return 0.0
    }

    fun name(): String {
        return "$firstName $lastName"
    }
}