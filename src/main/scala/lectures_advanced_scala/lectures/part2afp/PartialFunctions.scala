package lectures_advanced_scala.lectures.part2afp

object PartialFunctions extends App {

  val aFunction = (x: Int) => x + 1 // function1[Int, Int] === Int => Int

  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

  private class FunctionNotApplicableException extends RuntimeException

  val aNiceFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 3 => 999
  }
  // {1, 2, 5} => Int
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 3 => 999
  }  // partial function value

  println(aPartialFunction(2)) // output: 56

  // utility use for partial function
  println(aPartialFunction.isDefinedAt(67)) // test if partial function is applicable for 67

  // lift
  val lifted = aPartialFunction.lift  // transform to Int => Option[Int]
  println(lifted(2))  // output: Some(56)
  println(lifted(98))  // output: None

  val pfCahin = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(pfCahin(45))  // output: 67

  // PF extends normal function
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOF accept partial function as well
  val aMappedList = List(1, 2, 3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)  // output: List(42, 78, 1000)

  /*
    Note: PF can only have ONE parameter type
   */
  /**
   * Exercise
   *
   * 1 - construct a PF instance yourself (anonymous class)
   * 2 - chatbot as a partial function
   *      scala.io.Source.stdin.getLines().foreach(line => println("you said: " + line))
   */
  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(x: Int): Int = x match {
      case 1 => 42
      case 2 => 65
      case 5 => 999
    }

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 5
  }

  val chatbot: PartialFunction[String, String] = {
    case "hello" => "Hi, my name is HAL9000"
    case "goodbye" => "Once you start talking to me, there is no return, human!"
    case "call mom" => "Unable to find your phone without your credit card"
  }
  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)
}
