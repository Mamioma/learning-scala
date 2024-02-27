package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1)) // output: List(2, 3, 4)
  println(list.map(_ + " is a number")) // output: List(1 is a number, 2 is a number, 3 is a number)

  // filter
  println(list.filter(_ % 2 == 0)) // output: List(2)

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))  // output: List(1, 2, 2, 3, 3, 4)

  // print all combination between two lists
  // iteration
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  // List("a1", "a2", ... "d4")
  println(chars.flatMap(
    (x: Char) => numbers.map(x + _.toString)
  ))

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0  // if here filter some number
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)
  
  // syntax overload
  list.map {
    x => x * 2
  }
  
  
}
