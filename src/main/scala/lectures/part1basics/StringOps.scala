package lectures.part1basics

object StringOps extends App{

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))  // output: l
  println(str.substring(7, 11))  // output: I am
  println(str.split(" ").toList)  // output: Hello, I, am, learning, Scala
  println(str.startsWith("Hello"))  // output: true
  println(str.replace(" ", "-"))  // output: Hello,-I-am-learning-Scala
  println(str.toLowerCase())  // output: hello, i am learning scala
  println(str.length)  // output: 26

  println(str.reverse)  // output: alacS gninrael ma I ,olleH
  println(str.take(2))  // output: He


  // Scala-specific: String interpolators.

  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherString = s"Hello, my name is $name and I will be ${age + 1} years old"

  // F-interpolators
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f"  // 2 character minimum and 2 decimal precision

  println(myth) // output: David can eat 1.20

  // raw-interpolator
  println(s"This is a \n newline")  // the \n will work
  println(raw"This is a \n newline") // output: This is a \n newline




}
