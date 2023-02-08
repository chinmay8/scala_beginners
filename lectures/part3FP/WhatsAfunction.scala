package lectures.part3FP

object WhatsAfunction extends App{
  trait MyFunction[A, B]{
    def apply(element:A):B
  }

  val doubler = new MyFunction[Int,Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(5))

  /*
  ALL SCALA FUNCTIONS ARE OBJECTS
  there are scala specific FunctionTypes which are scala predefined objects
  ex . Function1,Function2
   */

  val stringToInt = new Function[String,Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToInt("3") + 4)

  val adder: (Int,Int) => Int = new Function2[Int,Int,Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(adder(5,3))

  /*
  Function types Function2[A,H,R] => (A,H) => R
   */

  val conCat:(String,String) => String = new Function2[String,String,String] {
    override def apply(v1: String, v2: String): String = v1 + " " + v2
  }
  println(conCat("Chinmay","Chaudhari"))
  /*
  for second question in GenericList
  step 1 : remove traits
  step 2 : replace MyTrnsformer[A,B] ==== A => B and MyPredicate[A] ==== A => Boolean (parameterType => returnType)
  step 3 : replace instantiating part with Function1 and functionName with apply method
   */

  /*
  for third question : High order functions
  Function1[Int,Function1[Int,Int]]
   */

  def superAdder : Function1[Int,Function1[Int,Int]] = new Function1[Int,Function1[Int,Int]]{
    override def apply(v1: Int): Int => Int = new Function[Int,Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }

  val adder1 = superAdder(3)
  println(adder1(4))
  println(superAdder(5)(3)) // Curried Functions

}
