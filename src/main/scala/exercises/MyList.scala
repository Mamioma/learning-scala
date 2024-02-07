package exercises

abstract class MyList {
  /*
    head = first element of the linked list
    next = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head: Int
  def next: MyList
  def isEmpty: Boolean
  def add(Node: Int): MyList
  def printElement: String
  override def toString: String = "[" + printElement + "]"
}

object EmptyList extends MyList {
  override def head: Int = throw new NoSuchMethodError
  override def next: MyList = throw new NoSuchMethodError
  override def isEmpty: Boolean = true
  override def add(Node: Int) = new List(Node, EmptyList)
  override def printElement: String = ""
}

class List(h: Int, n: MyList) extends MyList {
  override def head: Int = h
  override def next: MyList = n
  override def isEmpty: Boolean = false
  override def add(Node: Int) = new List(Node, this)

  override def printElement: String =
    if (n.isEmpty) "" + h
    else h + " " + n.printElement
}

object listTest extends App {
  val aList = new List(1, new List(2, new List(3, EmptyList)))
  println(aList.head)
  println(aList.isEmpty)
  println(aList.add(4).head)
  println(aList.toString)
}
