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

import io.kotlintest.*
import io.kotlintest.matchers.*
import io.kotlintest.specs.*
import java.util.*

/*
*   Programming Kotlin - Packt
*   [Chapter 11]
*   - Testing in Kotlin
*/

// A spec, or style, is just the manner in which the tests
// are laid out in the class files.
// FunSpec allows you to create tests similar to the junit style. You invoke a method called test,
// with a string parameter to describe the test, and then the test itself
class StringTestWithFunSpec : FunSpec() {
    // To write a unit test, we invoke a function called test, which takes two parameters. The first
    // is a description of the test, and the second is a function literal that contains the body of the
    // test. The description, or name, of the test will appear in the output so we know which tests
    // have failed and which have passed.
    init {
        test("String.startsWith should be true for a prefix") {
            "helloworld".startsWith("hello") shouldBe true
        }
        test("String.endsWith should be true for a prefix") {
            "helloworld".endsWith("world") shouldBe true
        }

    }
}

// StringSpec reduces the syntax to the absolute minimum. Just write a string followed by a
// lambda expression with your test code.
class StringTestWithStringSpec : StringSpec() {
    init {
        "strings.length should return size of string" {
            "hello".length shouldBe 5
            "hello" shouldBe haveLength(5)
        }
    }
}

// ShouldSpec is similar to fun spec, but uses the keyword should instead of test
class StringTestWithShouldSpec : ShouldSpec() {
    init {
        should("return the length of the string") {
            "sammy".length shouldBe 5
            "".length shouldBe 0
        }
        // Nested form
        "String.length" {
            should("return the length of the string") {
                "sammy".length shouldBe 5
                "".length shouldBe 0
            }
        }
    }
}

// WordSpec uses the keyword should and uses that to nest test blocks after a context string
class StringTestWithWordSpec : WordSpec() {
    init {
        "String.length" should {
            "return the length of the string" {
                "sammy".length shouldBe 5
                "".length shouldBe 0
            }
        }
    }
}

// FeatureSpec allows you to use feature, and scenario
class StringTestWithFeatureSpec : FeatureSpec() {
    init {
        feature("Hello World") {
            scenario("should starts with 'Hello'") {
                "Hello World".startsWith("Hello")
            }
            scenario("should ends with 'World'") {
                "Hello World".endsWith("World")
            }
        }
    }
}

// BehaviorSpec. The tests are
// nested in three blocks, named given, when, and then. When combined, these blocks read
// like a natural language sentence.
class StringTestWithBehaviorSpec : BehaviorSpec() {
    init {
        given("a stack") {
            val stack = Stack<String>()
            `when`("an item is pushed") {
                stack.push("kotlin")
                then("the stack should not be empty") {
                    stack.isEmpty() shouldBe true
                }
            }
            `when`("the stack is popped") {
                stack.pop()
                then("it should be empty") {
                    stack.isEmpty() shouldBe false
                }
            }
        }
    }
}

// Matchers test for some property, indicated by the name of the matcher, beyond simple
// equality. For example, a matcher may check whether a string is empty or whether an
// integer is positive.

// [String matchers]
class StringTestWithDifferentMatchers : StringSpec() {
    init {
        "Tests string prefixes" {
            "Hello".startsWith("He") shouldBe true
            "Hello" shouldBe startWith("He")
        }
        "Tests substrings"{
            "Hello" shouldBe include("el")
        }
        "Test string suffixes" {
            "Hello".endsWith("llo") shouldBe true
            "Hello" shouldBe endWith("llo")
        }
        "Tests the length of a string" {
            "Hello".length shouldBe 5
            "Hello" shouldBe haveLength(5)
        }
        "Tests the equality using a regular expression" {
            "Hello" shouldBe match("He...")
        }
    }
}

// [Collection matchers]
// The next most useful set of matchers operate on collections, including lists, sets, maps, and so on
class CollectionTestWithDifferentMatchers : StringSpec() {
    private val listWithDifferentIntegers = listOf(1, 2, 3, 4, 5)
    private val mapWithKeyAndValues = mapOf<Int, String>(1 to "Hello", 2 to "World")

    init {
        "Tests that a collection should contain the given element" {
            listWithDifferentIntegers shouldBe contain(3)
        }
        "Test the size of the collection" {
            listWithDifferentIntegers shouldBe haveSize<Int>(5)
        }
        "Tests that the collections should be sorted" {
            listWithDifferentIntegers shouldBe sorted<Int>()
        }
        "Tests that the collection has a single element that is equal to the given element" {
            listWithDifferentIntegers shouldNotBe singleElement(2)
        }
        "Tests that the collection contains all the given elements. The order of these elements does not matter." {
            listWithDifferentIntegers shouldBe containsAll(1, 2, 4)
        }
        "Tests whether the collection is empty or not" {
            listWithDifferentIntegers shouldNotBe beEmpty<Int>()
        }
        "Tests whether the map contains mapping from a key to any value" {
            mapWithKeyAndValues shouldBe haveKey(2)
        }
        "Tests whether the map contains the value for at least one key" {
            mapWithKeyAndValues shouldBe haveValue("Hello")
        }
        "Tests that the map contains the exact mapping of the key to the value" {
            mapWithKeyAndValues shouldBe contain(2, "World")
        }
    }
}

// [Floating point matchers]
// A very useful matcher is the tolerance matcher, which is defined on doubles. When testing
// the equality of doubles, one should not use simple equals. The safest and most
// correct way to do floating point comparison is to assert that the difference between
// two numbers is below some value. The value chosen is the tolerance, and
// it should be low enough to satisfy your criteria that the numbers are equal.
class FloatNumberTestWithTolerance : StringSpec() {
    private val randomDouble = 18.005
    private val enoughDouble = 18.006

    init {
        "Test if two numbers are equals" {
            randomDouble shouldNotBe equals(enoughDouble)
            randomDouble shouldBe (enoughDouble plusOrMinus 0.01)
        }
    }
}

// [Expecting exceptions]
// Sometimes, we want to assert that a function will throw an exception, perhaps to test a
// precondition we may have added.
// Note that 'shouldThrow' will also fail the test if the wrong type of exception is thrown.
class ExceptionTest : StringSpec() {
    init {
        shouldThrow<IllegalArgumentException> {
            addNumberToTwo(10.0)
        }
    }
}

@Throws(IllegalArgumentException::class)
fun addNumberToTwo(a: Any): Int {
    if (a !is Int) {
        throw IllegalArgumentException("Number must be an integer")
    }
    return 2 + a
}

// [Combining matchers]
// Matchers can be combined together using the usual Boolean logical operators of conjunction
// (and) and disjunction (o)
class CombiningMatchers : StringSpec() {
    init {
        "Combining matchers" {
            "Hello World" should (startWith("Hel") and endWith("rld"))
        }
    }
}

// [Inspectors]
// KotlinTest inspectors are an easy way to test the contents of collections.
val kings = listOf("Stephen I", "Henry I", "Henry II", "Henry III", "William I", "William III")

class InspectorTests : StringSpec() {
    init {
        "all kings should have a regal number" {
            forAll(kings) {
                it should endWith("I")
            }
        }
        "only one king has the name Stephen" {
            forOne(kings) {
                it should startWith("Stephen")
            }
        }
        "some kings have regal number II" {
            forSome(kings) {
                it should endWith("II")
            }
        }
        "at least one King has the name Henry" {
            forAtLeastOne(kings) {
                it should startWith("Henry")
            }
        }
    }
}

// [Interceptors]
// When moving beyond the scope of standalone unit tests and into tests that require
// resources, it is often the case that we would need to set up these resources before a test and
// tear them down again later.
// This functionality exists in KotlinTest under the name of interceptors. Each type of interceptor
// is defined to run before and after the code is tested.
class usingInterceptors : StringSpec() {

    val myinterceptor: (TestCaseContext, () -> Unit) -> Unit = { context, test ->
        val start = System.currentTimeMillis()
        test()
        val end = System.currentTimeMillis()
        val duration = end - start
        println("This test took $duration millis")
    }

    init {
        "Tests the equality using a regular expression" {
            "Hello" shouldBe match("He...")
        }.config(interceptors = listOf(myinterceptor))
    }
}

// [The spec interceptor]
// It is used to intercept all the tests in a single test class. The spec interceptor is very similar
// to the test case interceptor; the only difference is that the test case context is replaced by spec context.

// [Project config]
// Sometimes you may wish to execute some code before any tests are run at all or after all the
// tests are completed (whether successful or not). This can be achieved through the use of the
// 'ProjectConfig' abstract class. To use this, simply create an object that will extend from
// this abstract class and ensure it is on the class path. Then, KotlinTest will automatically find
// it and invoke it.
object codeExecutionBeforeAndAfterTestCases : ProjectConfig() {
    override fun beforeAll() {
        // ...code
    }

    override fun afterAll() {
        // ...code
    }
}

// [Tags, conditions and config]
// [Config]
// Each test case makes a config function available, which can be used to set specific
// configurations for that test, such as threading, tags, and whether the test is enabled or not.
class ConfigExampleWithTag : ShouldSpec() {
    init {
        should("run multiple times") {
            println("Test invocation")
        }.config(invocations = 5)
    }
}

// [Conditions]
// Conditions are a simple way of enabling or disabling a test based on runtime evaluation.
// The config block contains an enabled property, which is invoked before a test is executed in
// order to access whether that test should be executed or skipped
class ConfigExampleWithCondition : ShouldSpec() {
    private fun isMultiCore() = Runtime.getRuntime().availableProcessors() > 1

    init {
        should("test enabled") {
            "Hello".length shouldBe 5
        }.config(enabled = true)

        // We can extend this to use runtime lookup by defining a function instead of a hardcoded
        // value. In fact, anything that is an expression can be used.
        should("test enabled if the CPU is multicore") {
            "Hello".length shouldBe 5
        }.config(enabled = isMultiCore())
    }
}

// [Tags]
// Similar to conditions, tags allow a way of grouping tests so they can be enabled or disabled at runtime.
// A tag is just an object that extends from the abstract class 'Tag'.
object ElasticSearch : Tag()

object Windows : Tag()
class ConfigExampleWithTags : ShouldSpec() {
    init {
        should("test enabled") {
            "Hello".length shouldBe 5
        }.config(tags = setOf(ElasticSearch, Windows))
    }
}

// If we were using Gradle, we could execute only these tests using the following command:
// gradle test -DincludeTags=Windows,ElasticSearch
// or can exclude some tests with:
// gradle test -DexcludeTags=Windows

// [One instance]
// Sometimes, you may wish to have a fresh instance of a test class for each test that is executed.
// To do this, simply override the 'oneInstancePerTest' property and set it to true :
class OneInstanceOfTheseTests : ShouldSpec() {
    override val oneInstancePerTest = true

    init {
        // tests here
    }
}

// [Resources]
// One final neat feature of KotlinTest is the ability to automatically close resources once all
// the tests are completed. This is essentially a shortcut to writing an interceptor and closing
// them yourself, and is useful if all you need to do is ensure that some handle is closed:
class ResourceExample : StringSpec() {
    val input = autoClose(javaClass.getResourceAsStream("data.csv"))

    init {
        "your test case" {
            // use input stream here
        }
    }
}