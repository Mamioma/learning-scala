package lectures.part3fp

object WhatsAFunction extends App {

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler.apply(2))  // instance can be called like function

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)

  // Scala supports these function types up to 22 parameter
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A, B) => R
  // All Scala Functions are objects

  /*
    1. define a function which takes 2 strings and concatenates them

    2. transform the MyPredicate and MyTransformer into Function types

    3. Define a Function which takes an int and returns another function which takes an int and returns a int
      - what's the type of this function
      - how tot do it
   */

  val concatenateString: (String, String) => String = new Function2[String, String, String] {
    override def apply(str1: String, str2: String): String = str1 + str2
  }

  println(concatenateString("Jim", "Bob"))

  val returnFunction: (Int) => (Int => Int) = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }
  println(returnFunction(3)(4))  // curried function
  
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
