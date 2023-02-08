package playground

object MyScalaClass extends App{
  println("Hello Scala!")

  val str = "vav"
  val strlist = str.toList
  var result = ""

/*  for (i <- strlist){
    result = i + result
  }*/

  val res = for{
    i <- strlist
  }yield result + i

  println(res.mkString(""))
  if (result == str) println("String matched")
  else println("Not Matched")
}
