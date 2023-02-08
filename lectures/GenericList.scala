package lectures

import sun.invoke.empty.Empty

abstract class MyListAgain[+A]{
  def head : A
  def tail: MyListAgain[A]
  def IsEmpty:Boolean
  def add[B >: A](n:B):MyListAgain[B]
  def printElements: String
  override def toString: String = s"[ $printElements ]"

  def map[B](transformer: MyTransformer[A,B]):MyListAgain[B]
  def filter(predicate:MyPredicate[A]):MyListAgain[A]
  def flatMap[B](transformer: MyTransformer[A,MyListAgain[B]]):MyListAgain[B]

  //Concatenation Function
  def ++[B >: A](list:MyListAgain[B]):MyListAgain[B]
}
// traits are like INTERFACE of JAVA and are inherited "with" keyword
trait MyPredicate[-T]{
  def test(n:T):Boolean
}

trait MyTransformer[-A,B]{
  def transform(a:A):B
}
/*
Case classes are powerful and are defined with keyword 'case'
1. class parameters are fields - can be used directly with class object
2. sensible toString method prints-> Person(JIM,34) instead of some hash value
    println(objectInstance) == println(objectInstance.toString) // Syntatic sugar
3. equals and hashcode are implemented OOTB
  jim == jim1 (2 different objects) returns true
4. CC have handy copy method
 val jim1 = jim.copy()
 or
 val jim1 = jim.copy(age = 24)
5. CC have companion objects
val mary = Person("Mary",24) // Calls apply method of case class
6. CC are serializable // used in AKKA
7. CCs have extractor patterns -- used in pattern matching
8. Case objects are similar to CC but they dont have comapnion objects

 */
case object Empty1 extends MyListAgain[Nothing]{
  def head:Nothing = ???
  def tail:MyListAgain[Nothing] = ???
  override def IsEmpty: Boolean = true
  override def add[B >: Nothing](n: B): MyListAgain[B] = new Cons1(n,Empty1)
  override def printElements: String = ""
  def map[B](transformer: MyTransformer[Nothing,B]):MyListAgain[B] = Empty1
  def filter(predicate:MyPredicate[Nothing]):MyListAgain[Nothing] = Empty1
  def flatMap[B](transformer: MyTransformer[Nothing,MyListAgain[B]]):MyListAgain[B] = Empty1

  override def ++[B >: Nothing](list: MyListAgain[B]): MyListAgain[B] = list
}

case class Cons1[+A](h:A,t:MyListAgain[A]) extends MyListAgain[A]{
  override def head: A = h
  override def tail: MyListAgain[A] = t
  override def IsEmpty: Boolean = false
  override def add[B >: A](n: B): MyListAgain[B] = new Cons1(n,this)
  override def printElements: String =
    if(t.IsEmpty) "" + h
    else
      h + " " + t.printElements

  def map[B](transformer: MyTransformer[A,B]):MyListAgain[B] =
    new Cons1(transformer.transform(h),t.map(transformer))

  def filter(predicate:MyPredicate[A]):MyListAgain[A] =
    if(predicate.test(h)) new Cons1(h,t.filter(predicate))
    else
      t.filter(predicate)

  override def ++[B >: A](list: MyListAgain[B]): MyListAgain[B] = new Cons1(h,t ++ list)

  override def flatMap[B](transformer: MyTransformer[A, MyListAgain[B]]): MyListAgain[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
}

object GenericList extends App{
  val listOfIntegers: MyListAgain[Int] = new Cons1(1,new Cons1(2,new Cons1(3, Empty1)))
  val anotherlistOfIntegers: MyListAgain[Int] = new Cons1(4,new Cons1(5, Empty1))
  val clonelistOfIntegers: MyListAgain[Int] = new Cons1(1,new Cons1(2,new Cons1(3, Empty1)))
  val listOfStrings: MyListAgain[String] = new Cons1[String]("Hello", new Cons1[String]("Scala",Empty1))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  //Anonymous Class examples with generics
  println(listOfIntegers.map(new MyTransformer[Int,Int] {
    override def transform(a: Int): Int = a * 2
  }).toString)
  println((listOfIntegers.filter(new MyPredicate[Int] {
    override def test(n: Int): Boolean = n%2==0
  }).toString))
  println((listOfIntegers ++ anotherlistOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformer[Int,MyListAgain[Int]] {
    override def transform(a: Int): MyListAgain[Int] = new Cons1[Int](a,new Cons1[Int](a + 1,Empty1))
  }).toString)

  println(listOfIntegers == clonelistOfIntegers)
}
