package lectures.part1basics

object CBNvsCBV extends App{

  def callByValue(x: Long): Unit = {
    println("by value " + x)
    println("by value " + x)
  }

  def callByName(x: => Long): Unit = {  // => tells comipler it will call by name
    println("by name " + x)
    println("by name " + x)
  }

  // output:
  // by value 170511766688416
  // by value 170511766688416
  // by name 170511844981250
  // by name 170511846612375
  callByValue(System.nanoTime()) // System.nanoTime() is computed before calling the function
  callByName(System.nanoTime()) // System.nanoTime() is computed every time before function executes

  def infinite(): Int = {
    1 + infinite()
  }

  def printFirst(x: Int, y: => Int) = println(x)

//  printFirst(infinite(), 34)  it will crash
  printFirst(34, infinite())  // it delays the analyze of the expression, so it is never evaluated during the function



}
