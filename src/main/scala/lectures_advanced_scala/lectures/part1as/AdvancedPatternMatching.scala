package lectures_advanced_scala.lectures.part1as

object AdvancedPatternMatching extends App {

  val numbers = List(1)
  val description = numbers match {
    case head :: Nil => println(s"the only element is $head.")
    case _ =>
  }

  /*
    - constants
    - wildcards
    - case classes
    - tuples
    - some special magic like above
   */

  // how to make class (not case class) compatible with pattern matching
  class Person(val name: String, val age: Int)

  object Person {
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21) None
      else Some((person.name, person.age))

    def unapply(age: Int): Option[String] =
      Some(if (age < 21) "minor" else "major")
  }

  val bob = new Person(name = "bob", age = 25)
  val greeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
  }

  println(greeting)  // output: Hi, my name is bob and I am 25 years old

  val legalStatus = bob.age match {
    case Person(status) => s"My legal status is $status"
  }
  println(legalStatus)  // output: My legal status is major

  /*
    Exercise.
   */

  object matchCondition {
    def unapply(n: Int): Option[String] =
      if (n < 10) Some("single digit")
      else if (n % 2 == 0) Some("an even number")
      else Some("no property")
  }

  val n: Int = 45
  val mathProperty = n match {
    case matchCondition(status) => s"$status"
  }
  println(mathProperty)  // output: no property
}
