package lectures_advanced_scala.lectures.part4implicits

object OrganizingImplicits extends App {

  implicit val reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  println(List(1, 4, 5, 3, 2).sorted)

  // scala.Predef

  /*
    Implicits:
      - val
      - object
      - def with no parentheses:
        not work: implicit val reverseOrdering(): Ordering[Int] = Ordering.fromLessThan(_ > _)
   */

  case class Person(name: String, age: Int)

  val persons = List(
    Person("Steve", 30),
    Person("Amy", 22),
    Person("John", 66)
  )

  object AlphabeticNameOrdering {
    implicit val alphabetOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }
  object AgeOrdering {
    implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan(_.age < _.age)
  }

  /*
    implicit scope
    - normal scope - LOCAL scope
    - imported scope
    - all types involved = A or any supertypes
   */
  // def sorted[B >: A](implicit ord: Ordering[B]): C

  import AlphabeticNameOrdering._
  println(persons.sorted)

  /*
    Exercise

    - totalPrice = most used (50%)
    - by unit count = 25%
    - by unit price = 25%
   */
  case class Purchase(nUnits: Int, unitPrice: Double)
  object Purchase {
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((a, b) => a.nUnits * a.unitPrice < b.nUnits * b.unitPrice)
  }

  object UnitCountOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.nUnits < _.nUnits)
  }

  object UnitPriceOrdering {
    implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.unitPrice < _.unitPrice)
  }
}
