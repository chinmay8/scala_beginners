package lectures

abstract class MyList{
  def head : Int
  def tail: MyList
  def IsEmpty:Boolean
  def add(n:Int):MyList
  def printElements: String
  override def toString: String = s"[ $printElements ]"
}

object Empty extends MyList{
  def head:Int = ???
  def tail:MyList = ???
  override def IsEmpty: Boolean = true
  override def add(n: Int): MyList = new Cons(n,Empty.this)
  override def printElements: String = ""
}

class Cons(h:Int,t:MyList) extends MyList{
  override def head: Int = h
  override def tail: MyList = t
  override def IsEmpty: Boolean = false
  override def add(n: Int): MyList = new Cons(n,this)
  override def printElements: String =
    if(t.IsEmpty) "" + h
    else
      t.printElements + " " + h
}
object Inheritance extends App {

  val empty = Empty
  val mylist = new Cons(1,empty)
  println(mylist.head)
  println(mylist.tail.IsEmpty)
  println(mylist.add(2).toString)
}
