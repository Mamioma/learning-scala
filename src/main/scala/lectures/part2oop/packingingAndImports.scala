package lectures.part2oop

import playground.{Cinderella, PrinceCharming => Prince}

object packingingAndImports extends App {
  
  // package member are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 12)
  
  val person = new Person("haha", 13)
  
  // import package
  val princess = new Cinderella
  
  // package object
  sayHello
  println(SPEED_OF_LIGHT)
  
  // imports
  val prince = new Prince  // name alias
  
  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function 
  // scala.Predef -println, ???
}
