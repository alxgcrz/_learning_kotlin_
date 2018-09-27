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

// [Syntax and variations]
// The syntax for declaring a property is as follows:
//
//  var/val<propertyName>:<PropertyType>[=<property_initializer>]
//      [<getter>]
//      [<setter>]
//
// Both the initializer and the setter parts are optional.
// If you define a read-only property by using the val keyword, you only have the getter and
// no setter.

fun main(args: Array<String>) {
    class Rectangle(val width: Double, val height: Double) {
        val Area: Double
            get() = width * height

        val isSquare: Boolean = width == height
    }

    val rectangle = Rectangle(width = 10.0, height = 20.0)
    println("Area: ${rectangle.Area} and ${if (!rectangle.isSquare) "is not" else "is"} a square")

    // [Visibility]
    // The visibility access rules we have discussed for fields apply to properties as well.
    // Therefore, you can have private, protected, or public (default) properties.
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
    // Kotlin comes with support for delayed initialization. All you have to do is use the
    // 'lateinit' keyword.
    // There are a few restrictions when using delayed properties. Firstly, the property type
    // cannot be a primitive type. Secondly, your property cannot make use of custom getter or
    // setter code. And last but not least, accessing your property before it has been initialized will
    // end up in 'kotlin.UninitializedPropertyAccessException'.
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
    // There are cases when you want to delay the creation of an instance of your object until its
    // first usage. This technique is known as lazy initialization or lazy instantiation. The main
    // purpose of lazy initialization is to boost performance and reduce your memory footprint.
    // To make use of the lazy initialized delegated property all you have to do is write 'by lazy'
    // and provide the logic for creating your instance
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
    // What if you want to know when the delegated property is changed? You might need to
    // react to the change and call some other code. The Delegates object comes with the
    // following construct to allow you to achieve exactly that:
    //    fun <T> observable(initialValue: T, crossinline onChange: (property: KProperty<*>,
    //          oldValue: T, newValue: T) -> Unit): ReadWriteProperty<Any?, T>
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
    // Kotlin provides support for a delegated property for non-null values.
    // All you have to do is use 'Delegates.nonNull'. If you try to set a null to it, you will
    //get a compilation error.
    class NonNullProp {
        var value: String by Delegates.notNull<String>()
    }

    val nonNull = NonNullProp()
    nonNull.value = "Kotlin rocks"
    println("Non null value is: ${nonNull.value}")
    //this will not compile
    //nonNull.value = null
}

