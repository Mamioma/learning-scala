package lectures.part3fp

import javax.management.RuntimeMBeanException
import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))  // output: Failure(java.lang.RuntimeException: SUPER FAILURE)

  println(aSuccess)  // output: Success(3)
  println(aFailure)  // output: Failure(java.lang.RuntimeException: SUPER FAILURE)

  def unsafeMethods(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  val potentialFailure = Try(unsafeMethods())
  println(potentialFailure)  // output: Failure(java.lang.RuntimeException: NO STRING FOR YOU BUSTER)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw

  }

  // utilities
  println(potentialFailure.isSuccess)  // output: false
  println(potentialFailure.isFailure)  // output: true

  // orElse
  def backupMethod(): String = "A Valid Result"
  val fallBackTry = Try(unsafeMethods()).orElse(Try(backupMethod()))
  println(fallBackTry)  // output: Success(A Valid Result)

  // If you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid method")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map flatmap filter
  println(aSuccess.map(_ * 2))  // output: Success(6)
  println(aSuccess.flatMap(x => Success(x * 10)))  // output: Success(30)
  println(aSuccess.filter(_ > 10))  // output: Failure(java.util.NoSuchElementException: Predicate does not hold for 3)
  // => for-comprehension

  /*
    Exercise
   */
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HTTPService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print to the console i.e. call renderHTML
  val conn = HTTPService.getSafeConnection(hostname, port)
  val possibleHTML = conn.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  HTTPService.getSafeConnection(hostname, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  for {
    connection <- HTTPService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  } yield renderHTML(html)

  /*
    try {
      connection = HTTPService.getConnection(host, port)
      try {
        connection.get("/home")
      } catch ( some other exception )
    } catch (some other exception) {

    } catch (exception) {

    }
   */
}
