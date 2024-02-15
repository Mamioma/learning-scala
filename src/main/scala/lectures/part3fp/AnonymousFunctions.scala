package lectures.part3fp

object AnonymousFunctions extends App {
  
  // anonymous function (LAMBDA)
  val doubler: Int => Int = x => x * 2
  
  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b
  
  // no params 
  val justDoSomething: () => Int = () => 3
  println(justDoSomething)  // function itself, output: lectures.part3fp.AnonymousFunctions$$$Lambda$18/0x0000000800b6ec40@880ec60
  println(justDoSomething()) // call output: 3
  
  // curly braces with lambdas
  val stringToInt = (str: String) => str.toInt
  
  
  // MOAR syntactic sugar
  
  
  
  

}
