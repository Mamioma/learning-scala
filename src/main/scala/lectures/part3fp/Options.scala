package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)  // output: Some(4)
  println(noOption)  // output: None

  // option is invented to deal with unsafe api
  def unsafeMethod(): String = null
//  val result = Some(unsafeMethod()) // WRONG, neve do Some(null)
  val result = Option(unsafeMethod()) // Some or None depending on the result
  println(result) // output: None

  // chained methods (how you work with unsafe api)
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)  // output: Some(A valid result)

  // Design unsafe api
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChianedResult = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterChianedResult)  // output: Some(A valid result)

  println(myFirstOption.isEmpty) // output: false
//  println(myFirstOption.get)  // unsafe

  // map flatMap filter
  println(myFirstOption.map(_ * 2)) // output: Some(8)
  println(myFirstOption.filter(x => x > 10)) // output: None
  println(myFirstOption.flatMap(x => Option(x * 10)))  // output: Some(40)

  // for-comprehensions

  /*
    Exercises
   */
  val config: Map[String, String] = Map(
    // fetched from elsewhere, so it is not certain to have value
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"  // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if not - print the connect method
  val host = config.get("host")
  val port = config.get("port")
  /*
    host.flatMap means:
    if (h != null) {
      if (p != null)
        return Connection.apply(h, p)

    return null
    }
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /*
    connection.map means
    if (c != null) return c.connect
    else return null
   */
  val connectionStatus = connection.map(c => c.connect)
  println(connectionStatus)  // output: none
  connectionStatus.foreach(println)


  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehension
  val ForConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  ForConnectionStatus.foreach(println)

}
