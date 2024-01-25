package lectures.part1basics

object Expressions extends App{

  val x = 1 + 2 // Expression
  println(x)

  println(2 + 3 * 4)
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  println(1 == x)

  var aVariable = 2
  aVariable += 3
  println(aVariable)

  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // if Expression

  // Never write it again
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  // Everything in Scala is an Expression!

  val aWeriedValue = (aVariable = 3)
  println(aWeriedValue) // output = ()

  // if you write val aWhile = while (i < 10) {}, aWhile is also a unit

  // side effects: println(), whiles, reassigning, will return a unit

  // Code blocks, the value is the last element, and it is also a expression. variables defined in code block cannot be
  // visible outside

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  println(aCodeBlock)

  // 1. difference between "hello world" versus println("hello world")  value string versus expression
  // 2.

  val someValue = {
    2 < 3
  }  // true

  val someOtherValue = {
    if(someValue) 239 else 986
    43
  } // 43
}
