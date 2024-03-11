package lectures_advanced_scala.lectures.part1as

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recap extends App {

  val aCondition: Boolean = true
  val aConditionedVal = if (aCondition) 42 else 65
  // instructions vs expressions

  // compiler infers types for us
  val aCodeBlock = {  // value of codeBlock is the last expression
    if (aCondition) 54
    56
  }

  // Unit = void
  val theUnit = println("hello, Scala")

  // functions
  def aFunction(x: Int): Int = x + 1

  // recursion: stack and tail
  @tailrec def factorial(n: Int, accumulator: Int): Int =
    if (n <= 0) accumulator
    else factorial(n - 1, n * accumulator)

  // object orientation programming
  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog  // subtyping polymorphism

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("crunch!")
  }

  // method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog  // compiler rewrite it into aCroc.eat(Dog)

  // 1 + 2 compiler rewrite 1.+(2)

  // anonymous classes
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("roar!")
  }

  // generics
  abstract class MyList[+A]  // variance and variance problem in this course
  // singleton and companions
  object MyList  // object MyList and abstract class MyList are called companions

  // case class
  case class Person(name: String, age: Int)

  // exceptions and try/catch/finally
//  val throwsException = throw new RuntimeException  // Nothing
  val aPotentialFailure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "I caught an exception"
  } finally {
    println("some logs")
  }

  // functional programming
  val incrementer = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  incrementer(1)

  val anonymousIncrementor = (x: Int) => x + 1
  println(anonymousIncrementor(1)) // output: 2
  List(1, 2, 3).map(anonymousIncrementor)  // HOF

  // for-comprehension
  val pairs = for {
    num <- List(1, 2, 3)  // can also put if condition
    char <- List('a', 'b', 'c')
  } yield num.toString + "-" + char

  // Scala collections: Seq, Arrays, Lists, Vector, Map, Tuple
  val aMap = Map(
    "Daniel" -> 789,
    "Jess" -> 555
  )

  // collections: Option, Try
  val anOption = Some(2)

  // pattern matching
  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case _ =>
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(name, _) => s"greeting $name"
  }
  println(greeting)  // output: greeting Bob

  // all the patterns
  
}
