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

package com.example.lesson5

class Circle {

    var area = 0.0

    // Member function
    fun calculateArea(radius: Double): Double {
        require(radius > 0, { "Radius must be greater than 0" })
        return Math.PI * Math.pow(radius, 2.0)
    }

    // Infix function
    infix fun area(radius: Double): Double {
        area = Math.PI * Math.pow(radius, 2.0)
        return area
    }

}