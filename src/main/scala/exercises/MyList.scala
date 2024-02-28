package exercises

//trait MyPredicate[-T] {
//  def test(t: T): Boolean
//}
//
//trait MyTransformer[-A, B] {
//  def transform(a: A): B
//}

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
  // hihger-order function
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def ++[B >: A](list: MyList[B]): MyList[B]
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C]
  def fold[B >: A](start: B)(f: (A, B) => B): B
}

case object EmptyList extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchMethodError
  override def next: MyList[Nothing] = throw new NoSuchMethodError
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](Node: B) = new List(Node, EmptyList)
  override def printElement: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = EmptyList
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = EmptyList
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = EmptyList

  override def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] = {
    if (list.isEmpty) EmptyList
    else throw new RuntimeException("Cannot zip a list with an empty list")
  }

  override def fold[B >: Nothing](start: B)(f: (Nothing, B) => B): B = start
}

case class List[+A](h: A, n: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def next: MyList[A] = n
  override def isEmpty: Boolean = false
  override def add[B >: A](Node: B): MyList[B] = new List(Node, this)

  override def printElement: String =
    if (n.isEmpty) "" + h
    else h.toString + " " + n.printElement

  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new List(h, n.filter(predicate))
    else n.filter(predicate)
  }

  /*
    [1, 2, 3].map(n * 2)
      = new List(2, [2, 3].map(n * 2)
        = new List(2, 4, [3].map(n * 2)
          = new List(2, 4, 6, [].map(n * 2)
            = new List(2, 4, 6)
   */
  override def map[B](transformer: A => B): MyList[B] = {
    new List(transformer(h), n.map(transformer))
  }

  /*
    [1, 2] ++ [3, 4]
    = new List(1, [2] ++ [3, 4]
    = new List(1, new List(2, Empty ++ [3, 4])
    = new List(1, new List(2, [3, 4])
   */
  override def ++[B >: A](list: MyList[B]): MyList[B] = {
    new List(h, n ++ list)
  }

  /*
    [1, 3].flatMap(n => [n, n+1])
    = [1, 2] ++ [3].flatMap(n => [n, n+1])
    = [1, 2] ++ [3, 4] ++ Empty.flatMap(n => [n, n+1])
    = [1, 2] ++ [3, 4] ++ Empty
   */
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ n.flatMap(transformer)
  }

  override def foreach(f: A => Unit): Unit =
    f(h)
    n.foreach(f)

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(h: A, sortedTail: MyList[A]): MyList[A] = {
      if (sortedTail.isEmpty) new List(h, EmptyList)
      else if ((compare(h, sortedTail.head)) < 0) new List(h, sortedTail)
      else new List(sortedTail.head, insert(h, sortedTail.next))
    }

    val sortedTail = n.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C] = {
    if (list.isEmpty) new List(f(h, list.head), EmptyList)
    else new List(f(h, list.head), n.zipWith(list.next, f))
  }

  override def fold[B >: A](start: B)(f: (A, B) => B): B = {
    if (n.isEmpty) f(h, start)
    else n.fold(f(h, start))(f)
  }
}

object listTest extends App {
  val listOfIntegers: MyList[Int] = new List(1, new List(2, new List(3, EmptyList)))
  val listOfStrings: MyList[String] = new List("hello", new List("Scala", EmptyList))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println(listOfIntegers.add(4).toString)

  val anotherListOfIntegers: MyList[Int] = new List(4, new List(5, new List(6, EmptyList)))
  println(listOfIntegers.filter(elem => elem % 2 == 0).toString)

  println(listOfIntegers.map(elem => elem * 2).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(elem => new List(elem, new List(elem + 1, EmptyList))).toString)

  // test case class
  val cloneListOfIntegers: MyList[Int] = new List(1, new List(2, new List(3, EmptyList)))
  println(cloneListOfIntegers == listOfIntegers)  // output: true, if not, need to write equals recursivly

  // test for each
  listOfIntegers.foreach((x: Int) => println(x))

  listOfIntegers.sort((x: Int, y: Int) => y - x).foreach((x: Int) => println(x))
  
  println(listOfIntegers.zipWith(anotherListOfIntegers, (x, y) => x * y).toString)
  
  println(listOfIntegers.fold(0)((x, y) => x + y).toString)
  
  // for comprehension
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string
  
  println(combinations)
}
