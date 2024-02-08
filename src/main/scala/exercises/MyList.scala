package exercises

abstract class MyList[+A] {
  /*
    head = first element of the linked list
    next = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head: A
  def next: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](Node: B): MyList[B]
  def printElement: String
  override def toString: String = "[" + printElement + "]"
}

object EmptyList extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchMethodError
  override def next: MyList[Nothing] = throw new NoSuchMethodError
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](Node: B) = new List(Node, EmptyList)
  override def printElement: String = ""
}

class List[+A](h: A, n: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def next: MyList[A] = n
  override def isEmpty: Boolean = false
  override def add[B >: A](Node: B): MyList[B] = new List(Node, this)

  override def printElement: String =
    if (n.isEmpty) "" + h
    else h.toString + " " + n.printElement
}

object listTest extends App {
  val listOfIntegers: MyList[Int] = new List(1, new List(2, new List(3, EmptyList)))
  val listOfStrings: MyList[String] = new List("hello", new List("Scala", EmptyList))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println(listOfIntegers.add(4).toString)

}
