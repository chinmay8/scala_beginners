package lectures.part4pm

object PatternsEverywhere extends App {

  //big idea #1

  try {
    //code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  //catches are matches
/*
  try {
    //code
  } catch (e) {
    e match {
      case e: RuntimeException => "runtime"
      case npe: NullPointerException => "npe"
      case _ => "something else"
    }
  }
*/

  //big idea #2
  val list = List(1,2,3,4)
  val evenOnes = for{
    x <- list if x %2 ==0
  } yield x * 10

  //generators are also based on pattern matching
  val tuples = List((1,2),(3,4))
  val filterTuples = for {
    (x,y) <- tuples
  } yield x * y
  // case classes, :: operators, ....

  //big idea #3
  val tuple = (1,2,3)
  val (a,b,c) = tuple
  println(b)
  // multiple value definitions based pattern matching
  // ALL THE POWER

  val head :: tail = list
  println(head)
  println(tail)

  // big idea #4 - New
  // partial function based pattern matching
  val mappedList = list.map{
    case x if x %2 == 0 => x + " is even"
    case 1 => "the one"
    case _ => "something else"
  } // partial function literal

  val mappedList2 = list.map{x => x match {
    case v if x %2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  }
  }
  println(mappedList)

}
