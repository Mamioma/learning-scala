package lectures.part2oop

import scala.annotation.targetName
import scala.language.postfixOps

object MethodNotations extends App{

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck?!"  // need to add a space or compiler will think unary_!: is the operator
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    // overload + operator
    def +(nickName:String): String = s"$name + $nickName"
    
    // add a unary + operator
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    
    // add a learns method in Scala
    def learns(thing: String): String = s"$name learns $thing"
    // add a learnsScala method that calls learns method with Scala
    def learnsScala: String = this learns "Scala"
    
    // overload apply method
    def apply(times: Int): String = s"Mary watched Inceptions $times times "
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

  // postfix notation
  println(Mary.isAlive)
  println(Mary isAlive)

  // apply
  println(Mary.apply())
  println(Mary())  // equivalent, if the compiler see like this, it will look for apply function

  println("==========Exercises==============")

  /*
    1. Overload the + operator, receives String and return nickname
      for example mary + "the rockstar" => new person "Mary (the rockstar)"
  */

  println(Mary + "rockstar")

  /*
    2. Add age to the Person class
       Add a unary + operator => new person with the age + 1
       +mary => mary with the age incrementor
  */
  
  println((+Mary).age)
  
  /*
    3. Add a "learns" method in the Person class => "Mary learns Scala"
      Add a learnsScala method, calls learns method with "Scala".
      Use it in postfix notation.
  */
  
  println(Mary learnsScala)
  
  /*
    4. Overload the apply method
      mary.apply(2) => "Mary watched Inception 2 times"
   */
  
  println(Mary apply 2)
  println(Mary(2))
  
  // conclusion:
  // mary.likes("Inception") == mary    likes    "Inception"
  //                            object method     parameter
  
}
