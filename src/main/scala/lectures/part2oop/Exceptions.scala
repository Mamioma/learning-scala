package lectures.part2oop

object Exceptions extends App {

  val x: String = null
//  println(x.length)  // output: Caused by: java.lang.NullPointerException

  // throwing and catching exceptions

//  val aWeirdValue:String = throw new NullPointerException // new here is because NullPointerException is a class

  // throwable classes extends the Throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => "caught runtime exception"
  } finally {
    // code will get executed No matter what
    // optional
    // does not influence the return type of this exception
    println("finally")
  }

  println(potentialFail) // output: caught runtime exception

  // 3. how to define your own exceptions
  class MyException extends Exception
//  val exception = new MyException
//
//  throw exception

  /*
    1. Crash your program with an OutOfMemoryError

    2. Crash with stack overflow error

    3. PocketCalculator
      - add(x, y)
      - multiply(x, y)
      - divide(x, y)

      Throw
      - OverflowException if add(x, y) exceeds INT.MAX_VALUE
      - UnderflowException if subtract(x, y) exceeds INT.MIN_VALUE
      - MathCalculationException if division by 0
   */

  //  val array = Array.ofDim[Int](Int.MaxValue)  // output: OutOfMemory Error

  def recursiveCall(n: Int): Int = {
    if (n > 0) n + recursiveCall(n - 1)
    else 0
  }

  //  println(recursiveCall(10000000))  // output: StackOverFlowError
  class OverFlowException extends Exception 
  class UnderFlowException extends Exception
  class MathCalculationException extends RuntimeException("divide by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      if (x < Int.MinValue || y < Int.MinValue) throw new UnderFlowException
      else if (x >= Int.MaxValue - y) throw new OverFlowException
      else x + y
    }
    def multiply(x: Int, y: Int): Int = x * y
    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      x / y
    }
  }
  
  PocketCalculator.divide(10, 0)
}
