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
*   Programming Kotlin - Packt
*   [Chapter 8]
*   - Type parametrization
*   - Type bounds and recursive type bounds
*   - Invariance, covariance and contravariance
*   - Algebraic data types
*/

fun main(args: Array<String>) {
    // Parameterised functions
    // To define a function with a type parameter, we use angle
    // bracket (<...>) syntax, giving the type parameter a name, before the function name.
    fun <T> random(param: T): T = param

    fun <K, V> mapping(key: K, value: V) {}

    val randomInt = random(1)
    val randomDouble = random(10.0)
    val map = mapping(1, "ES")

    // [Parameterized types]
    // It is not just functions that can be parameterized types themselves can be parameterized as
    // well. Such types are sometimes referred to as container types because of the close association
    // with collections and the fact that they contain one or more type parameters
    class Sequence<T>

    class Dictionary<K, V>

    // When a type has been declared with a type parameter, we must “fill in” that type when we
    // instantiate it by replacing the parameters with concrete or proper types.
    val seq = Sequence<Int>()
    val dict = Dictionary<Int, String>()
}