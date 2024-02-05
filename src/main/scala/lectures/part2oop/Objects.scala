package lectures.part2oop

object Objects {

  // Scala does not have class-level functionality (doesn't have static)

  // Scala use object to achieve class level functionality
  // object doesn't receive parameter
  object person {  // type + its only instance
    // "static" - level functionality
    val N_EYES = 2
    val canFLy: Boolean = false

    // factory method
    def apply(mother: person, father: person): person = new person("Bobbie")
  }

  class person(val name: String) {
    // instance-level functionality
    val N_EYES = 2
    val canFLy: Boolean = false
  }

  println(person.N_EYES)
  println(person.canFLy)

  // Scala object = SINGLETON INSTANCE
  val mary = person  // instance of the person type, which is the only instance the person can have
  val john = person
  println(mary == john)  // output: true

  val Mary = new person("Mary")
  val John = new person("John")
  println(Mary == John)  // output: false, because they refer to the different instance

  val bobbie = person.apply(Mary, John)
  
  // Scala Application = Scala Object
  // def main(args: Array[String]): Unit
  // Scala Application has to be Scala objects with this method implemented
  final def main(args: Array[String]): Unit = {
    println(person.N_EYES)
    println(person.canFLy)

    // Scala object = SINGLETON INSTANCE
    val mary = person // instance of the person type, which is the only instance the person can have
    val john = person
    println(mary == john) // output: true

    val Mary = new person("Mary")
    val John = new person("John")
    println(Mary == John) // output: false, because they refer to the different instance

    val bobbie = person.apply(Mary, John)
  }
  




}
