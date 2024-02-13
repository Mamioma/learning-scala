package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int)

  // 1. class parameter are fields
  val jim = new Person("Jim", 34)
  println(jim.name) // if no case, then name and age are not fields

  // 2. sensible to string
  println(jim.toString) // output: Person(Jim,34), if no case, then output is lectures.part2oop.CaseClasses$Person@6bdf28bb

  // 3. equals and hashCode implemented out of box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // output: true, if no case, then output is false

  // 4. case class have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. Case class have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23) // it has factory method

  // 6. Case class are serializable, Akka
  
  // 7. Case class have extractor patterns = can be used in Pattern Matching
  
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
  
  /*
    Expand MyList
   */
  
}