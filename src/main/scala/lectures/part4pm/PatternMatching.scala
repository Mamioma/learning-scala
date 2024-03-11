package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"  // _ = wildcard, means match anything
  }

  println(x)  // output: 3
  println(description)  // output: third time is the charm

  // 1. decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  println(greeting)  // output: Hi, my name is Bob and I can't drink in the US

  // case are matched in order
  // if no case is matched? MatchError

  // pattern matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  // case class works well with pattern matching
  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a Dog of the $someBreed breed")
  }

  /*
    Exercise
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(s1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  /*
    Simple function uses PM
    takes an Expr => human readable form

    Sum(Number(2), Number(3)) => 2 + 3
    Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
    Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
    Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
   */

  def showFunction(expression: Expr): String = {
    expression match {
      case Number(n) => n.toString
      case Sum(s1, s2) => showFunction(s1) + " + " + showFunction(s2)
      case Prod(e1, e2) => {
        def maybeShowParentheses(exp: Expr) = exp match {
          case Prod(_, _) => showFunction(exp)
          case Number(_) => showFunction(exp)
          case _ => "(" + showFunction(exp) + ")"
        }

        maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
      }
    }
  }

  println(showFunction(Sum(Number(2), Number(3))))
  println(showFunction(Prod(Sum(Number(2), Number(1)), Number(3))))

}
