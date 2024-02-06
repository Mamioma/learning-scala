package lectures.part2oop

object AbstractDataTypes extends App{

  // abstract: don't give value to field
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  // abstract class cannot be instantiate

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }
  
  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }
  
  trait ColdBlooded
  
  // class Crocodile inherit both Animal and Carnivore
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I am a croc and I am eating ${animal.creatureType}")
  }
  
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)
  
  // difference between trait and abstract class
  // trait and abstract class can have abstract and non-abstract field
  // 1. traits do not have constructor parameter
  // 2. multiple traits may be inherited by the same class 
  // 3. traits are behavior, abstract class is a type of thing
  
  // scala type hierarchy 
  // Scala.Any -> Scala.AnyRef(String, List, Set) -> Scala.Null -> Scala.Nothing
  // Scala.Any -> Scala.AnyVal -> Scala.Nothing
  


}
