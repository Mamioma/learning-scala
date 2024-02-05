package lectures.part2oop

object InheritanceAndTraits extends App{

  // single class inheritance
  class Animal {
    protected def eat = println("nomnom")
    val creatureType = "wild"
  }

  // inherit non-private member
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int)
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog extends Animal {
    override val creatureType: String = "domestic"
    override def eat = println("crunch crunch")
  }

  val dog = new Dog
  // if no override, then the protected method cannot be called outside the class
  dog.eat
  println(dog.creatureType)
  
  // type substitution
  val unknownAnimal: Animal = new Dog("K9")
  





}
