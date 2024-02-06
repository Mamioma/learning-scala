package lectures.part2oop

object InheritanceAndTraits extends App{

  // single class inheritance
  class Animal {
    def eat = println("nomnom")
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
  class Dog(override val creatureType: String) extends Animal {
    override def eat = {
      super.eat
      println("crunch crunch")
    }
  }

  val dog = new Dog("K9")
  // if no override, then the protected method cannot be called outside the class
  dog.eat
  println(dog.creatureType)

  // type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRiding VS overLoading

  // super

  // preventing overrides
  // 1 - use final on member: cannot be override
  // 2 - use final on class: cannot be inheritance
  // 3 - seal he class = extend classes in this file, prevent extension in other files
  
}
