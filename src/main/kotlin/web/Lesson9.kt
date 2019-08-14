import com.example.kotlin.Car
import com.example.kotlin.GraduateStudent
import com.example.kotlin.Programmer

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

    val car = Car()
    println(car.getColor(255, 0, 0)) // => RED

    // Inheritance
    // A new class (subclass) is created by acquiring an existing class's (superclass) members and
    // perhaps redefining their default implementation. This mechanism is known as inheritance in
    // object-oriented programming (OOP).
    val graduateStudentA = GraduateStudent(firstName = "John", lastName = "Doe")
    val graduateStudentB = GraduateStudent(firstName = "Robert", lastName = "Downey Jr.", country = "USA")
    println("Graduate: ${graduateStudentA.name()} - ${graduateStudentA.country}") // => Graduate: John Doe -
    println("Graduate: ${graduateStudentB.name()} - ${graduateStudentB.country}") // => Graduate: Robert Downey Jr. - USA

}

