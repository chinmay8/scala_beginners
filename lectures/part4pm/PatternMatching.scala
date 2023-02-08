package lectures.part4pm

import scala.util.Random

object PatternMatching extends App{

  val random = new Random
  val x = random.nextInt(10)

  //same as switch case in other language

  val description = x match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case _ => "something else" // wildcard or default
  }

  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi my name $n and I can't drink in US"
    case Person(n, a) => s"Hi my name is $n and I am $a years old."
    case _ => "I don't know who I am"
  }
  println(greeting)

  /*
  1. cases are matched in order
  2. what if no case matched ? MatchError
  3. type of PM expression? unified type of all the types in  all the cases
  4. PM works very well with case classes
   */

  //PM is sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")

  animal match {
    case Dog(someBreed) => println(s"Matched dog with $someBreed breed")
  }

  // match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  // WHY ?!! would you do that

  val isEvenCond = if(x % 2 == 0) true else false
  val isEvenNormal = x %2 == 0

  /*
  Exercise
  simple function uses PM
  takes an Expr => human readable form

  Sum(Number(1),Number(2)) = "2 + 3"
  Sum(Number(1), Number(2), Number(3)) = "2 + 3 + 4"
  Prod(Sum(Number(1), Number(2)), Number(3)) = "(2 + 1) * 3"
  Sum(Prod(Number(2), Number(1)), Number(3)) = "2 * 1 + 3"
   */

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr) : String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => {
      def maybeShowParenthesis(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }
      maybeShowParenthesis(e1) + " * " + maybeShowParenthesis(e2)
    }
  }

  println(show(Sum(Number(1),Number(2))))
  println(show(Sum(Sum(Number(1), Number(2)), Number(3))))
  println(show(Prod(Sum(Number(1), Number(2)), Number(3))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))

}
