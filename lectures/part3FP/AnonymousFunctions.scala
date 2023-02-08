package lectures.part3FP

object AnonymousFunctions extends App {

  /*
  Anonymous Functions are also called as LAMBDAS
  val x:(int) => int = new Function1[Int,Int]{
  def apply(x:int):Int = x + 1
   */
  val doubler : Int => Int = (x: Int) => x * 2 // Lambdas

  // Multiple param lambdas
  val adder : (Int,Int) => Int = (a,b) => a + b
  /*
   Not necessary to mention parameter types after =
   But its necessary to mention before = to let compiler know the datatypes of parameters and return value
   */

  //No param lambdas
  val justHello:() => String = () => "Hello, Its anonymous function without parameters"

  println(doubler(2))
  println(adder(2,3))
  println(justHello) // Prints function hashvalue
  println(justHello()) // Actual function call

  //curly braces with lambdas - Suggested
  val stringToInt: String => Int = {
    (str:String) => str.toInt
  }

  //MOAR syntactic sugars
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder:(Int,Int) => Int = _ + _ // equivalent to (a,b) => a + b
  /*
  _ represents parameters seq wise and datatypes is must before =
   */

  //SpecialAdder in anonymous form (Curried Function)
  def superAdder = (x:Int) => (y:Int) => x + y
  println(superAdder(2)(3))
}
