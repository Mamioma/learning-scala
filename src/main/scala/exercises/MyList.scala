package exercises

trait MyPredicate[-T] {
  def test(t: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(a: A): B
}

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
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object EmptyList extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchMethodError
  override def next: MyList[Nothing] = throw new NoSuchMethodError
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](Node: B) = new List(Node, EmptyList)
  override def printElement: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = EmptyList
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = EmptyList
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = EmptyList
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class List[+A](h: A, n: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def next: MyList[A] = n
  override def isEmpty: Boolean = false
  override def add[B >: A](Node: B): MyList[B] = new List(Node, this)

  override def printElement: String =
    if (n.isEmpty) "" + h
    else h.toString + " " + n.printElement

  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new List(h, n.filter(predicate))
    else n.filter(predicate)
  }

  /*
    [1, 2, 3].map(n * 2)
      = new List(2, [2, 3].map(n * 2)
        = new List(2, 4, [3].map(n * 2)
          = new List(2, 4, 6, [].map(n * 2)
            = new List(2, 4, 6)
   */
  override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new List(transformer.transform(h), n.map(transformer))
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
  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ n.flatMap(transformer)
  }
}

object listTest extends App {
  val listOfIntegers: MyList[Int] = new List(1, new List(2, new List(3, EmptyList)))
  val listOfStrings: MyList[String] = new List("hello", new List("Scala", EmptyList))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println(listOfIntegers.add(4).toString)

  val anotherListOfIntegers: MyList[Int] = new List(4, new List(5, EmptyList))
  println(listOfIntegers.filter(predicate = new MyPredicate[Int] {
    override def test(t: Int): Boolean = t % 2 == 0
  }).toString)

  println(listOfIntegers.map(transformer = new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(transformer = new MyTransformer[Int, MyList[Int]] {
    override def transform(a: Int): MyList[Int] = new List(a, new List(a + 1, EmptyList))
  }).toString)
  
  // test case class
  val cloneListOfIntegers: MyList[Int] = new List(1, new List(2, new List(3, EmptyList)))
  println(cloneListOfIntegers == listOfIntegers)  // output: true, if not, need to write equals recursivly
  
  
}
