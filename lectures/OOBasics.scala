package lectures

object OOBasics extends App{

  val author = new Writer("Chinmay","Chaudhari",24)

  val novel = new Novel("Mahabharat",1996,author)
  novel.WrittenBy()

  val counter = new Counter(5)
  counter.current_count()
  val new_counter = counter.incr_decr()
  new_counter.current_count()
  val anotherNewCounter = counter.incr_decr(10)
  anotherNewCounter.current_count()
}
class Writer(val fname: String,val sname: String,val age: Int){
  def fullname():Unit=println(s"$fname $sname")
}
class Novel(name: String,yor: Int,author: Writer){
  val authorAge = author.age
  def WrittenBy()=author.fullname()
  // failed for this step
  def copy(newyear: Int) = new Novel(name,newyear,author)
}
class Counter(val n:Int){
  def current_count()=println(n)
  def incr_decr():Counter=new Counter(n+1)
  def incr_decr(amt:Int):Counter=new Counter(n+amt)
}