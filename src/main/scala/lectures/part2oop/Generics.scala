package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }

  val listOfIntegers = new MyList[Int]
  val listOfString = new MyList[String]

  class MyMap[Key, Value]

  // it also works for traits

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

//  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes List[Cat] extends List[Animal] = COVARIANCE
  class CoveriantList[+A]
  val animal: Animal = new Cat
  val animalList: CoveriantList[Animal] = new CoveriantList[Cat]
  // animalList.add(new Dog) ==> return a new list of animals

  // 2. No List[Cat] and List[Dog] are different = INVARIANCE
  class InvariantList[A]
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Dog] cannot compile

  // 3. CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)  // only accept subtypes of type Animal
  val cage = new Cage(new Dog)

  // expand MyList to be generic
  
}
