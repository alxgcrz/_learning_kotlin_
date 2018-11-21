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

// [Interfaces]
//  - A class can implement as many interfaces as you want, but it can only extend a
//  single class (similar to Java).
//  - The override modifier is compulsory in Kotlin—unlike in Java.
//  - Along with methods, we can also declare properties in a Kotlin interface.
//  - A Kotlin interface method can have a default implementation (similar to Java 8).
interface Vehicle {

    // As stated, a Kotlin interface can have properties—but note that it can't maintain state.
    // val new : Boolean = true // will not compile
    val new: Boolean

    fun getBrand(): String
    fun getColor(): String

    // Default implementation
    fun getColor(r: Int, g: Int, b: Int): String {
        return when {
            r == 255 && g == 0 && b == 0 -> "RED"
            r == 0 && g == 255 && b == 0 -> "GREEN"
            r == 0 && g == 0 && b == 255 -> "BLUE"
            else -> "WHITE"
        }
    }

    fun funD() {}
}

interface Electric {
    fun funD() {}
}

// Class circle implements Shape interface
class Car : Vehicle, Electric {

    override val new = true

    override fun getBrand(): String {
        return "Circle"
    }

    override fun getColor(): String {
        return "Blue"
    }

    override fun funD() {
        // Now the Kotlin compiler knows which function must call
        super<Vehicle>.funD()
    }

}