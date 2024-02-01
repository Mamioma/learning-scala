package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  // println(person.age) will show error, class parameter is not fields
  val person2 = new Person2("John", 26)
  println(person2.age)  // to change it into fields, add val in front of it

  // 2. every time a new object is created, the whole block of code will be executed
  println(person2.x)  // output: 4 26 2

  person2.greet("Daniel")  // output: John says: Hi, Daniel

  val author = new Writer("Charles", "Dickens", 1912)
  val author2 = new Writer("Charles", "Dickens", 1912)
  val novel = new Novel("Great Expectations", 1961, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(author2))  // is actually false, because the equality is special in OOD

  val counter = new Counter(0)
  counter.inc(3).print
  counter.inc.inc.dec.print

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

/*
  Novel and a Write

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    - copy (new year of release) - new instance of Novel
 */

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName(): String = firstName + " " + surname
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge = year - author.year

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newYear: Int): Novel = new Novel(name, newYear, author)

}
/*
  Counter class
    - receives an int value
    - method return current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */

class Counter(val count: Int) {
  // whenever you need to modify the instance, you need to create a new one
  def inc: Counter = {
    println("incrementing")
    new Counter(count + 1)
  } // immutability

  def dec: Counter = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)



}