package lectures.part3FP

object HOFsCurries extends App {

  val superFunction: (Int ,(String,(Int => Boolean)) => Int) => (Int => Int) = null
  //Higher Order function

  //map,flatmap,filter in MyList

  /*
  A function that applies a function n times over a value x
  nTimes(f,n,x)
  nTimes(f,n,x) = f(f(f(x))) = nTimes(f,2,f(x)) =f(f(f(x)))
  nTimes(f,n,x) = f(......f(x)))) = nTimes(f,n-1,f(x))
  */

  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if(n <= 0) x
    else nTimes(f,n-1,f(x))
  }
  val plusOne = (x:Int) => x + 1

  println(nTimes(plusOne,10,1))

  /*
  nts(f,n) => f(f(f(......f(x)))))
  incrementer10 = nts(plusOne,10) = nts(plusOne(plusOne(.....(x)))
  val y = incrementer10(1)
   */
  def nTimesSupper(f: Int => Int, n: Int): (Int => Int) = {
    if(n <= 0) (x: Int) => x
    else (x:Int) => nTimesSupper(f,n-1)(f(x))
  }
  val superAdder = nTimesSupper(plusOne,10)
  println(superAdder(2))

  //Curried Function
  val adder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y

  val add3 = adder(2)
  println(add3(5))
  println(adder(5)(3))

  //Functions with multiple parameter list

  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  def toCurry(f: (Int,Int) => Int): (Int => Int => Int) =
    x => y => f(x,y)

  def fromCurry(f: (Int => Int => Int)): (Int,Int) => Int =
    (x,y) => f(x)(y)

  val multiply1 = toCurry((x,y) => x*y) // toCurry(_*_)
  val multiply2 = fromCurry(x => y => x*y)

  println(multiply1(2)(4))
  println(multiply2(3,2))

  def compose[A,B,T](f:A => B,g: T => A): T => B =
    x => f(g(x))
  def andThen[A,B,T](f:T => B,g: B => A): T => A =
    x => g(f(x))

  val add2 = (x:Int) => x+2
  val times3 = (x:Int) => x*3

  val composed = compose(add2,times3)
  val ordered = andThen(add2,times3)

  println(composed(4)) // f(g(x)) = (x*3)+2
  println(ordered(4)) // g(f(x)) = (x+2)*3

}
