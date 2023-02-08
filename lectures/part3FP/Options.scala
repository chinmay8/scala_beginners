package lectures.part3FP

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  //Work with unsafe APIs
  def unsafemethod(): String = null
//  val result = Some(unsafemethod()) //Wrong
  val result = Option(unsafemethod()) //Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafemethod).orElse(Option(backupMethod))

  // Design a unsafe APIs
  def betterUnsafeMethod: Option[String] = None
  def betterSafeMethod: Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod orElse betterSafeMethod

  // functions on Options

  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE -- DO NOT USE

  // map, flatmap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions
  /*
  Exercise
  */

  val config: Map[String,String] = Map(
    "host" -> "127.0.0.1",
    "port" -> "80"
  )

  class Connection {
      def connect: String = "Connected" // connect to some server
  }

  object Connection{
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if(random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection if so print connect method
  val host = config.get("host")
  val port = config.get("port")

  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))

/*  if (c != null)
    return c.connect
  return null*/
  val connectionStatus = connection.map(c => c.connect)

  //if (connectionStatus == null) print(None) else print(Some(connectionStatus.get))
  println(connectionStatus)

  /*
  * If(status != null) println(status)
  * */
  connectionStatus.foreach(println)

  // chain calls
  config.get("host")
    .flatMap(host => config.get("port")
    .flatMap(port => Connection(host, port))
    .map(connection => connection.connect))
    .foreach(println)

  // for-comprehension
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  
  forConnectionStatus.foreach(println)
}
