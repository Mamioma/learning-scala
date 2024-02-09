package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahahaha")
  }

  println(funnyAnimal.getClass)  // output: class lectures.part2oop.AnonymousClasses$$anon$1

  // Scala actually created an anonymous class
  /*
    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("ahahahaha")
    }

    val funnyAnimal = new AnonymousClasses$$anon$1
   */

  // anonymous class works for abstract and non-abstract class
  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help")
  }
  
  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, this is Jim, how can I help")
  }

}
