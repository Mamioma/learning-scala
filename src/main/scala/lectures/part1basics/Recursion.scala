package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App{
  def factorial(n: Int): Int = {
    if (n == 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }
  }

//  println(factorial(5000)) // it will show a stack over flow

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator)  // Tail recursion = use recursive call as the last expression
    }

    factorialHelper(n, 1)
  }

  /*
    anotherFactorial(10) = factorialHelper(10, 1)
    = factorialHelper(9, 10 * 1)
    = factorialHelper(8, 9 * 10 * 1)
    .
    .
    .
    = factorialHelper(2, 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 1)
    = factorialHelper(1, 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 1)
    = 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 1
   */

  println(anotherFactorial(20000))


  // When you need loops, use tail recursion

  /*
    1. concatenates a string n time
   */
  def stringConcatenation(s: String, n: Int): String = {
    @tailrec
    def helper(s: String, n: Int, result: String): String = {
      if (n == 0) result
      else helper(s, n - 1, result + s)
    }

    helper(s, n, "")
  }

  println(stringConcatenation("hello", 5))

  /*
    2. IsPrime function tail recursive
   */
  def IsPrime(n: Int): Boolean = {
    @tailrec
    def helper(accumulator: Int, result: Boolean): Boolean = {
      if (accumulator == 1) true
      else if (!result) false
      else helper(accumulator - 1, result && (n % accumulator != 0))
    }

    helper(n / 2, true)
  }

  println(IsPrime(12))

  /*
    Fibonacci function, tail recursive
   */
  def Fibonacci(n: Int): Int = {
    @tailrec
    def helper(first:Int, second: Int, accumulator: Int): Int = {
      if (n == 1 || n == 2) 1
      else if (accumulator == n) second
      else helper(second, first + second, accumulator + 1)
    }

    helper(1, 1, 2)
  }

  println(Fibonacci(8))



}
