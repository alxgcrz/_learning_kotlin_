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

import com.example.lesson9.Circle
import com.example.lesson9.GraduateStudent
import com.example.lesson9.Programmer

/**
 * Kotlin From Scratch: Abstract Classes, Interfaces, Inheritance, and Type Alias
 * {@link https://code.tutsplus.com/tutorials/kotlin-from-scratch-abstract-classes-interfaces-inheritance-and-type-alias--cms-29744}
 *
 * @author @alxgcrz <alxgcrz@outlook.com>
 * @year 2017
 */

fun main(args: Array<String>) {

    // [Abstract class]
    // An abstract class is incomplete or useless without some concrete (non-abstract) subclasses,
    // from which you can instantiate objects.
    val employee = Programmer("John", "Doe")
    println("${employee.fullName()} - ${employee.company} - " +
            "${employee.earnings()}\$") // => Doe John - Company SA - 2000.0$

    val circle = Circle()
    println(circle.getColor(255, 0, 0)) // => RED

    // Inheritance
    // A new class (subclass) is created by acquiring an existing class's (superclass) members and
    // perhaps redefining their default implementation. This mechanism is known as inheritance in
    // object-oriented programming (OOP).
    val graduateStudentA = GraduateStudent(firstName = "John", lastName = "Doe")
    val graduateStudentB = GraduateStudent(firstName = "Robert", lastName = "Downey Jr.", country = "USA")
    println("Graduate: ${graduateStudentA.name()} - ${graduateStudentA.country}") // => Graduate: John Doe -
    println("Graduate: ${graduateStudentB.name()} - ${graduateStudentB.country}") // => Graduate: Robert Downey Jr. - USA

}

