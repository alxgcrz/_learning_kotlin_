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
*   [Chapter 10]
*   - Standard library for collections
*   - Mutable and immutable collections
*   - Stream API
*/

fun main(args: Array<String>) {
    // [Class hierarchy]
    // Like Scala, Kotlin distinguishes between mutable and immutable collections. A mutable
    // collection can be updated in place by adding, removing or replacing an element, and it will
    // be reflected in its state. On the other side, an immutable collection, while it provides the
    // same operations-addition, removal, or replacement-via the operator functions will end up
    // producing a brand-new collection, leaving the initial one untouched.

    // One thing to notice, is that all read-only interfaces are covariant. Array is the only class
    // and the parameter type T is the only invariant). Covariant is a term referring to the ability
    // to change the generic type argument from a class to one of its parents. This means that you
    // can take a 'List<String>' and assign it to 'List<Any>' because the Any class is a parent of String.
    // In Kotlin, you indicate covariant generic type parameters with the 'out' keyword-interface
    // Iterable<out T>.

    // A list is an ordered collection of elements.
    // A set is an unordered collection of elements that does not allow duplicates to be present.
    // A map is a collection that stores pairs of objects, keys and values, and supports the
    // efficient retrieval of the value corresponding to a given key. The map keys are unique,
    // and a map can store only one value for each key.

    // [Arrays]
    // Declaring and initializing arrays can be done like this:
    val intArray = arrayOf(1, 2, 3, 4, 5)
    println("Int array:${intArray.joinToString(", ")}") // => Int array:1, 2, 3, 4, 5

    // The Kotlin standard library provides out-of-the-box support for primitive arrays:
    // intArrayOf, longArrayOf, charArrayOf, doubleArrayOf, and so on. For each one, you
    // will get an instance of their equivalent Kotlin class: IntArray, LongArray, CharArray,
    // DoubleArray, and so on.
    val ints = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Built in int array:${ints.joinToString(", ")}") // => Built in int array:1, 2, 3, 4, 5, 6, 7, 8, 9, 10

    // In order to improve performance you should make sure you always use '***ArrayOf' (intArrayOf,
    // longArrayOf, etc...) when dealing with primitive types instead of 'arrayOf'.
    // If you don't, your program will pay a performance cost associated with boxing/unboxing operations

    // [Lists]
    // Lists are ordered collections. With a list, you can insert an element at a very specific
    // location, as well as retrieve elements by the position in the collection. Kotlin provides a
    // couple of pre-built methods for constructing immutable and mutable lists.
    val intList: List<Int> = listOf(1, 2, 3, 4, 5) // immutable
    val emptyList: List<String> = emptyList() // immutable
    val nonNulls: List<String> = listOfNotNull("a", "b", null, "c") // immutable
    val doubleList: ArrayList<Double> = arrayListOf(10.0, 20.0, 30.0) // mutable
    val cartoonsList: MutableList<String> = mutableListOf("Tom&Jerry", "Dexter's Laboratory")  // mutable

    // [Maps]
    // A map collection, as the name implies, allows you to associated an object (key) to another
    // object (value). A map dictates that your collection can't contain duplicate keys, and each key
    // is mapped to at most one value.
    val map: Map<String, String> = mapOf("a" to "aston martin", "b" to
            "bmw", "m" to "mercedes", "f" to "ferrari")
    val mutableMap: MutableMap<String, String> = mutableMapOf("AL" to
            "Alabama", "AK" to "Alaska", "AZ" to "Arizona")
    val hashMap: java.util.HashMap<String, String> = hashMapOf("a" to "aston martin", "b" to
            "bmw", "m" to "mercedes", "f" to "ferrari")
    val linkedHashMap: java.util.LinkedHashMap<String, String> =
            linkedMapOf("red" to "#FF0000", "azure" to "#F0FFFF", "white" to "#FFFFFF")
    val sortedMap: java.util.SortedMap<Int, String> = sortedMapOf(4 to
            "d", 1 to "a", 3 to "c", 2 to "b")

    // HashMap
    // A table-based implementation for the map interface. While it allows
    // nulls as either key or values the class makes no guarantees on the items' order or
    // the fact it will remain constant over time. This implementation has constant-time
    // cost for the get and put methods, assuming the hash function distributes the
    // elements properly among the buckets. The class retains a load factor as a measure
    // of how full the map can be before its capacity is increased. When the number of
    // entries in the hash table exceeds the product of the load factor and the current
    // capacity, the map table is rehashed (that is, internal data structures are rebuilt) so
    //that the hash table has approximately twice the number of buckets.

    // LinkedHashMap
    // A combination of HashMap and linked-list implementation for
    // the map interface, with a predictable iteration order. This implementation differs
    // from HashMap in that it maintains a doubly-linked list running through all of its
    // entries. This linked list defines the iteration ordering, which is normally the order
    // in which the keys were inserted into the map. The insertion order is not changed
    // when a key is re-inserted into the map.

    // TreeMap
    // A map implementation based on a red-black tree implementation. The
    // map is sorted based on the default ordering of its keys, or by a comparator
    // provided at the map's creation time, depending on which constructor is used.
    // This implementation provides a guaranteed log(n) time cost for the
    // containsKey, get, put, and remove operations. A red-black tree is a special
    // case of a binary search tree, where each node has one color (red or black)
    // associated with it (in addition to its key and left and right children). The tree
    // structure is governed by the following rules: the root node is black; the
    // descendants of a red node are black; each leaf node is black, the number of black
    // nodes on the path from the root to the null child are the same

    // [Sets]
    // A set is a collection that contains no duplicate items.
    val intSet: Set<Int> = setOf(1, 21, 21, 2, 6, 3, 2) // => 1, 21, 2, 6, 3
    val hashSet: HashSet<Int> = hashSetOf(1, 21, 3, 26, 3, 2) // => 1, 26, 2, 3, 21
    val sortedIntegers: java.util.TreeSet<Int> = sortedSetOf(11, 0, 9, 11, 9, 8) //0, 8, 9, 11
    val charSet: java.util.LinkedHashSet<Char> = linkedSetOf('a', 'x', 'a', 'z', 'a') // a, x, z
    val longSet: MutableSet<Long> = mutableSetOf(20161028141216, 20161029121211, 20161029121211) //20161028141216, 20161029121211

    // LinkedHashSet
    // The hash table and linked list implementation of the set interface, with predictable
    // iteration order. This implementation differs from HashSet in that it maintains
    // a doubly-linked list running through all of its entries. This linked list defines
    // the iteration ordering, which is the order in which elements were inserted
    // into the collection. The implementation spares its clients from chaotic ordering
    // provided by HashSet, without incurring the increased cost associated with TreeSet.

    // HashSet
    // It implements the set interface, backed by a hash table (actually a
    // HashMap instance). It makes no guarantees as to the iteration order of the set; it
    // does not guarantee that the order will remain constant over time. This class offers
    // constant time performance for the basic operations (add, remove, contains, and
    // size), assuming the hash function disperses the elements properly among the map buckets.

    // TreeSet
    // A set implementation based on a TreeMap. The elements are ordered
    // using their natural ordering, or by a comparator provided at set creation time,
    // depending on which constructor is used. This implementation provides
    // guaranteed log(n) time cost for the basic operations (add, remove, and contains).

    // [Sequences]
    // Sequences are great for scenarios when the size of the collection is not known in advance.
    // Sequences are the Kotlin equivalent of 'Stream' types in Java.
    val charSequence: Sequence<Char> = charArrayOf('a', 'b', 'c').asSequence() // a, b, c
    val longsSequence: Sequence<Long> = listOf(12000L, 11L, -1999L).asSequence() // 1200, 11, -1999
    val mapSequence: Sequence<Map.Entry<Int, String>> = mapOf(1 to "A", 2 to "B", 3 to "C").asSequence() // 1=A, 2=B, 3=C
    val setSequence: Sequence<String> = setOf("Anna", "Andrew", "Jack", "Laura", "Anna").asSequence() //Anna, Andrew, Jack, Laura
    val intSeq: Sequence<Int> = sequenceOf(1, 2, 3, 4, 5)
    val emptySeq: Sequence<Int> = emptySequence<Int>()
    val infiniteSequence = generateSequence(100) {
        if ((it + 1) % 2 == 0) it + 1 else it + 2
    }
    println(infiniteSequence.takeWhile { it < 110 }.toList()) // => [100, 102, 104, 106, 108]
}