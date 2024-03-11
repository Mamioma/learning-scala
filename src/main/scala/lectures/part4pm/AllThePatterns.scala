package lectures.part4pm

import exercises.{EmptyList, MyList, List}

object AllThePatterns extends App {

  // 1- constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "The scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2. match anything
  // 2.1 wildcard

  val matchAnything = x match {
    case _ =>   // match anything, just pass
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3. tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something"
  }

  // nested pattern matching
  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  // 4 case classes - constructor pattern
  val aList: MyList[Int] = List(1, List(2, EmptyList))
  val matchList = aList match {
    case EmptyList =>
    case List(head, List(subHead, subTail)) =>
  }

  // 5 list pattern
  val aStandardList = Seq(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case Seq(1, _, _, _) =>
    case Seq(1, _ *) =>  // list of arbitrary length
    case 1 :: Seq(_)  => // infix pattern
    case Seq(1, 2, 3) :+ 42 => // infix pattern
  }

  // 6 type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: Seq[Int] =>   // explicit type specifier
    case _ =>
  }

  // 7 name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Seq(_, _) =>  // name binding => use the name later
    case _ =>
  }

  // 8 - multi patterns
  val multipattern = aList match {
    case EmptyList | Seq(0, _)  =>  // co
    case _ =>
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case List(_, List(specialElement, _)) if specialElement % 2 == 0 =>
  }

  /*
    Question
   */

  val numbers = Seq(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: Seq[String] => "a list of strings"
    case listOfNumbers: Seq[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)  // output: a list of strings
  // JVM trick question, JVM ignore the generic pattern, so it will treat Seq[String] as Seq
}
