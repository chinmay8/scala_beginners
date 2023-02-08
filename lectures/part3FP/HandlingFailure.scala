package lectures.part3FP

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App{

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))

  println(aSuccess)
  println(aFailure)

  def unSafeMethod: String = throw new RuntimeException("No string for you!!")

  //Try objects via apply method
  val potentialFailure = Try(unSafeMethod)
  println(potentialFailure)

  //syntax sugar
  val anotherPotentialFailure = Try {
    // add code here that might throw exception
  }

  // utilities
  println(potentialFailure.isSuccess)

  //orElse
  def backupMethod(): String = "A valid string"
  val fallbackTry = Try(unSafeMethod).orElse(Try(backupMethod))
  println(fallbackTry)

  // if you design a API

  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  //map ,flatmap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // => for-comprehensions

  /*
  Exercise
   */

  val host = "localhost"
  val port = "8080"
  def renderHtml(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime)
      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted!")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime)

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port.")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  //if you get html page from the connection, print it to console i.e call renderHtml
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHtml = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHtml.foreach(renderHtml)

  // Shorthand version
  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHtml)

  // for-comprehension
  for {
    connection <- HttpService.getSafeConnection(host, port)
    page <- connection.getSafe("/home")
  } renderHtml(page)

  /* imperative way of the above implementation.
  try {
    connection = HttpService.getConnection(host, port)
    try{
      page = connection.get("/home")
      renderHtml(page)
    }catch(some other exception){

    }
  } catch(exception){

  }
   */
}
