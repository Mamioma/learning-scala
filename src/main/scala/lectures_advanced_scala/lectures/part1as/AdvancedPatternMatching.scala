package lectures_advanced_scala.lectures.part1as

object AdvancedPatternMatching extends App {

  val numbers = List(1)
  
  

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

  case class Or[A, B](a: A, b: B) // Either
  val either = Or(2, "two")
  val humanDescription = either match {
    case number Or string => s"$number is written as $string"
  }
  println(humanDescription)

  // decomposing sequences
  val vararg = numbers match {
    case List(1, _*) => "starting with 1"
  }
  println(vararg)  // output: starting with 1

  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match {
    case MyList(1, 2, _*) => "starting with 1 and 2"
    case _ =>
  }
  println(decomposed)  // output: starting with 1 and 2



}
