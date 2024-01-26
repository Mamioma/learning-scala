package lectures.part1basics

object Functions extends App{

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("hello", 3))

  // When you need loops, use recursion

  def aFunctionWithSideEffects(aString: String): Unit = {
    println(aString)
  }

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = {a + b}

    aSmallerFunction(n, n - 1)
  }

  /*
  *  1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
  * */

  def greetingFunction(name: String, age: Int): Unit = {
    println("Hi, my name is " + name + "and I am " + age + " years old")
  }

  greetingFunction("bruce", 23)

  /*
  *  2. Factorial function 1 * 2 * 3 ... * n
  * */

  def FactorialFunction(n: Int): Int = {
    if (n == 1) 1
    else n * FactorialFunction(n - 1)
  }

  println(FactorialFunction(4))

  /*
  *  3. A Fibonacci function
  *  f(1) = 1
  *  f(2) = 1
  *  f(n) = f(n - 1) + f(n - 2)
  * */

  def Fibonacci(n: Int): Int = {
    if (n == 1) 1
    else if (n == 2) 1
    else Fibonacci(n - 1) + Fibonacci(n - 2)
  }

  println(Fibonacci(8))
  /*
  *  4. Tests if a number is prime
  * */

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }

    isPrimeUntil(n / 2)
  }
  
  println(isPrime(17))
}
