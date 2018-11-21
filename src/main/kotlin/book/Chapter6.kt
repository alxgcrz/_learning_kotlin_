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

package book

import kotlin.properties.Delegates

/*
*   Programming Kotlin - Packt
*   [Chapter 6]
*   - General properties
*   - Visibility
*   - Lazy and late initialized
*   - Delegated properties
*   - When to use properties instead of methods
*/

fun main(args: Array<String>) {
    class Rectangle(val width: Double, val height: Double) {
        val Area: Double
            get() = width * height

        val isSquare: Boolean = width == height
    }

    val rectangle = Rectangle(width = 10.0, height = 20.0)
    println("Area: ${rectangle.Area} and ${if (!rectangle.isSquare) "is not" else "is"} a square")

    // [Visibility]
    class WithPrivateSetter(property: Int) {
        var someProperty: Int = 0
            private set(value) {
                field = value
            }

        init {
            someProperty = property
        }
    }

    val withPrivateSetter = WithPrivateSetter(10)
    println("withPrivateSetter:${withPrivateSetter.someProperty}")

    // [Late initialization]
    class DelayedInstance(val number: Int)

    class Container {
        lateinit var delayedInitProperty: DelayedInstance
        fun initProperty(instance: DelayedInstance) {
            this.delayedInitProperty = instance
        }
    }

    val container = Container()
    container.initProperty(DelayedInstance(10))
    println("Delayed initialization : Number = ${container.delayedInitProperty.number}")

    // [Lazy initializations]
    class WithLazyProperty {
        val foo: Int by lazy {
            println("Initializing foo")
            2
        }
    }

    val withLazyProperty = WithLazyProperty()
    val total = withLazyProperty.foo + withLazyProperty.foo
    println("Lazy property total: $total")

    // [Observable]
    class WithObservableProp {
        var value: Int by Delegates.observable(0) { p, oldNew, newVal ->
            onValueChanged()
        }

        private fun onValueChanged() {
            println("value has changed:$value")
        }
    }

    val onChange = WithObservableProp()
    onChange.value = 10
    onChange.value = -20

    // [A non-null property delegate]
    class NonNullProp {
        var value: String by Delegates.notNull<String>()
    }

    val nonNull = NonNullProp()
    nonNull.value = "Kotlin rocks"
    println("Non null value is: ${nonNull.value}")
    //this will not compile
    //nonNull.value = null
}

