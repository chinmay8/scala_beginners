package lectures.part3FP

abstract class MyListAgain[+A]{
  def head : A
  def tail: MyListAgain[A]
  def IsEmpty:Boolean
  def add[B >: A](n:B):MyListAgain[B]
  def printElements: String
  override def toString: String = s"[ $printElements ]"

  def map[B](transformer: A => B):MyListAgain[B]
  def filter(predicate: A => Boolean):MyListAgain[A]
  def flatMap[B](transformer: A => MyListAgain[B]):MyListAgain[B]

  //Concatenation Function
  def ++[B >: A](list:MyListAgain[B]):MyListAgain[B]

  //HOFs

  def foreach(f: A => Unit):Unit
  def sort(compare: (A,A) => Int): MyListAgain[A]
  def zipWith[B,C](list: MyListAgain[B],zip: (A,B) => C):MyListAgain[C]
  def fold[B](start:B)(operator: (B,A) => B): B
}
// traits are like INTERFACE of JAVA and are inherited "with" keyword

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
  def map[B](transformer: Nothing => B):MyListAgain[B] = Empty1
  def filter(predicate:Nothing => Boolean):MyListAgain[Nothing] = Empty1
  def flatMap[B](transformer: Nothing => MyListAgain[B]):MyListAgain[B] = Empty1

  override def ++[B >: Nothing](list: MyListAgain[B]): MyListAgain[B] = list

  //HOFs

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): MyListAgain[Nothing] = Empty1

  override def zipWith[B, C](list: MyListAgain[B], zip: (Nothing, B) => C): MyListAgain[C] = {
    if(!list.IsEmpty) throw new RuntimeException("Lists do not have same lengths")
    else Empty1
  }

  override def fold[B](start: B)(operator: (B,Nothing) => B): B = start
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

  def map[B](transformer: A => B):MyListAgain[B] =
    new Cons1(transformer(h),t.map(transformer))

  def filter(predicate:A => Boolean):MyListAgain[A] =
    if(predicate(h)) new Cons1(h,t.filter(predicate))
    else
      t.filter(predicate)

  override def ++[B >: A](list: MyListAgain[B]): MyListAgain[B] = new Cons1(h,t ++ list)

  override def flatMap[B](transformer: A => MyListAgain[B]): MyListAgain[B] =
    transformer(h) ++ t.flatMap(transformer)

  //HOFs

  override def foreach(f: A => Unit): Unit = {
    f(h)
    tail.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyListAgain[A] = {
    def insert(x: A, sortedList: MyListAgain[A]): MyListAgain[A] = {
      if(sortedList.IsEmpty) new Cons1(x,Empty1)
      else if(compare(x,sortedList.head) <= 0) new Cons1(x, sortedList)
      else new Cons1(sortedList.head,insert(x, sortedList.tail))
    }
    val sortedTail = t.sort(compare)
    insert(h,sortedTail)
  }

  override def zipWith[B, C](list: MyListAgain[B], zip: (A, B) => C): MyListAgain[C] = {
    if(list.IsEmpty) throw new RuntimeException("Lists do not have same lengths")
    else new Cons1[C](zip(h,list.head),t.zipWith(list.tail,zip))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

object MyListFunction extends App {
  val listOfIntegers: MyListAgain[Int] = new Cons1(1,new Cons1(2,new Cons1(3, Empty1)))
  val anotherlistOfIntegers: MyListAgain[Int] = new Cons1(4,new Cons1(5, Empty1))
  val clonelistOfIntegers: MyListAgain[Int] = new Cons1(1,new Cons1(2,new Cons1(3, Empty1)))
  val listOfStrings: MyListAgain[String] = new Cons1[String]("Hello", new Cons1[String]("Scala",Empty1))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  //Anonymous Class examples with generics
  println(listOfIntegers.map((a:Int) => a * 2).toString) // can be written as map(_*2)
  println((listOfIntegers.filter((n: Int) => n%2==0).toString)) // can be written as filter(_%2==0)
  println((listOfIntegers ++ anotherlistOfIntegers).toString)
  println(listOfIntegers.flatMap((a: Int) => new Cons1[Int](a,new Cons1[Int](a + 1,Empty1))).toString)

  println(listOfIntegers == clonelistOfIntegers)

  //HOFs

  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x,y) => y - x))
  println(anotherlistOfIntegers.zipWith[String,String](listOfStrings, _ +"-"+ _))
  //alternative representation for calling zipWith
  println(anotherlistOfIntegers.zipWith[String,String](listOfStrings, (x:Int,y:String) => x + "-" + y))

  println(listOfIntegers.fold(0)(_ + _))
}
