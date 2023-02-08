package lectures.part3FP

object MapFlatmapFilterFor extends App{

  val list = List(1,2,3,4)

  println(list)
  println(list.head)
  println(list.tail)

  //Map def map[B](f: A => B):List[B]
  println(list.map(_*2))
  println(list.map(_ + "is a number"))

  //filter def filter[B](f: A => Boolean):List[B]
  println(list.filter(_ % 2 == 0))

  //flatmap def flatmap[B](f: A => List[B]):List[B]
  val toPair = (x:Int) => List(x,x+1)
  println(list.flatMap(toPair))

  //print all combinations between two lists
  val chars = List('a','b','c','d')
  //Iterating
  println(list.flatMap(n => chars.map(c => "" + c + n)))

  val colors = List("black","white")
  println(list.flatMap(n => chars.flatMap(c => colors.map(clr => "" + n + c+ "-" + clr))))

  //foreach
  list.foreach(print)
  println()

  //for comprehension

  val forCombination = for {
    n <- list
    c <- chars
    clr <- colors
  } yield "" + n + c+ "-" + clr

  /*
  the above is equivalent to
  list.flatMap(n => chars.flatMap(c => colors.map(clr => "" + n + c+ "-" + clr)))
   */
  println(forCombination)

  val forCombination1 = for {
    n <- list if n % 2 == 0
    c <- chars
    clr <- colors
  } yield "" + n + c+ "-" + clr

  /*
  the above is equivalent to
  list.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(clr => "" + n + c+ "-" + clr)))
   */
  println(forCombination1)

  for {
    n <- list
  } print(n)
  /*
  the above is equivalent to
  list.foreach(print)
   */
  println()
  //syntax overload
  val test = list.map{ x =>
  x * 3
  }
  println(test)

}
