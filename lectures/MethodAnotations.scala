package lectures
import scala.language.postfixOps

object MethodAnotations extends App {
val mary = new Person("mary",24)
  println((mary+"the rockstar").name) // infix notations
  println((+mary).age) // prefix notations
  println(mary learnsScala) // postfix notations
  println(mary(2)) // apply method to make object as functions
}

class Person(val name: String, val age: Int){
  def +(x: String):Person= new Person(name + " (" + x + ")", age)
  def unary_+():Person = new Person(name,age + 1)
  def learns(x: String) = name + " learns " + x
  def apply(x: Int)= s"$name watched Inception $x times"
  def learnsScala = this learns "Scala"
}
