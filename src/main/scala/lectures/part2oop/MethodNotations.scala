package lectures.part2oop

import scala.annotation.targetName

object MethodNotations extends App{

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck?!"  // need to add a space or compiler will think unary_!: is the operator
  }

  val Mary = new Person("Mary", "Inception")
  println(Mary.likes("Inception"))
  // infix notation works with single parameter
  println(Mary likes "Inception") // equivalent

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(Mary + tom)
  println(Mary.+(tom))  // equivalent

  // all operators are methods
  println(1 + 2)
  println(1.+(2))

  // prefix notation
  val x = -1  // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!Mary)
  println(Mary.unary_!)
  
  
}
