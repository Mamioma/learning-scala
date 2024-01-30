package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  // println(person.age) will show error, class parameter is not fields
  val person2 = new Person2("John", 26)
  println(person2.age)  // to change it into fields, add val in front of it

  // 2. every time a new object is created, the whole block of code will be executed
  println(person2.x)  // output: 4 26 2

  person2.greet("Daniel")  // output: John says: Hi, Daniel
}

class Person(name: String, age: Int) // constructor

class Person2(name: String, val age: Int) {
  // body
  // 1. the val or var defined in the class called fields
  val x = 2

  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)


}