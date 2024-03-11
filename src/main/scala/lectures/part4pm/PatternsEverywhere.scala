package lectures.part4pm

object PatternsEverywhere extends App {

  // big idea
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually matches

  /*
    try {
      // code
    } catch (e) {
      e match {
        case e: RuntimeException => "runtime"
        case npe: NullPointerException => "npe"
        case _ => "something else"
      }
    }
   */
  // big idea
  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  // generators are also based on pattern matching
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // big idea
  val tuple = (1,2,3)
  val (a,b,c) = tuple
  println(b)  // output: 2

  val head :: tail = list
  println(head)  // output: 1
  println(tail)  // output: List(2, 3, 4)

  // big idea #4 - NEW
  // partial function
  val mappedList = list.map {
    case v if v % 2 == 0 => v.toString + " is even"
    case 1 => "the one"
    case _ => "something else "
  }
  /*
    this is equivalent as:
    val mappedList = list.map { x => x match {
        case v if v % 2 == 0 => v.toString + " is even"
        case 1 => "the one"
        case _ =>
      }
    }
   */
  println(mappedList)  // output: List(the one, 2 is even, something else , 4 is even)
}
