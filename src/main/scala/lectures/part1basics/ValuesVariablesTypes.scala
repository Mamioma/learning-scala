package lectures.part1basics

object ValuesVariablesTypes extends App{
  val x = 42
  println(x)

  // x = 2 Wrong: ValS are immutable

  // compiler can infer types, however if you write val x: Int = "hello world", then compiler will confuse

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 64565465465L  // a long you need to put 'L' in the back
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4  // var instead of val means variables
  // variables are mutable
  aVariable = 5

}
