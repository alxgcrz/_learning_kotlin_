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

// Shape.kt

/* A sealed class in Kotlin is an abstract class (you never intend to create objects from it)
which other classes can extend. These subclasses are defined inside the sealed class body—in
the same file. Because all of these subclasses are defined inside the sealed class body,
we can know all the possible subclasses by simply viewing the file.
*/
sealed class Shape

class Square : Shape()
class Triangle : Shape()
class Rectangle : Shape()