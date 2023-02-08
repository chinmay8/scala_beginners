package lectures

object MyFunctions extends App {
  def GreetingFunction(name:String, age:Int)={
    "Hi! my name is " + name + " and I'm " + age + " years old."
  }

  def FactorialNumber(n:Int):Int = {
    if(n<=1) 1
    else
      n*FactorialNumber(n-1)
  }

  def FibonacciSeries(n:Int):Int={
    if (n<=2) 1
    else
      FibonacciSeries(n-1)+FibonacciSeries(n-2)
  }

  def IsPrime(n:Int):Boolean={
    if (n <= 1) false
    else  if (n == 2) true
    else !(2 to n-1).exists(i => n%i == 0)
  }

  def Isprime(n:Int):Boolean={
    def IsprimeUntil(t:Int):Boolean={
      if (t<=1) true
      else n%t != 0 && IsprimeUntil(t-1)
    }
    IsprimeUntil(n/2)
  }

  def CallByValue(x:Long)={
    println("By value :" + x)
    println("By value :" + x)
  }

  def CallByName(x: => Long){
    println("By Name :" + x)
    println("By Name :" + x)
  }

  println(GreetingFunction("Chinmay",24))
  println(FactorialNumber(4))
  println(FibonacciSeries(4))
  println(if (IsPrime(13)) "Is prime number" else "Is not a prime number")
  println(if (Isprime(13)) "Is prime number" else "Is not a prime number")
  CallByName(System.nanoTime())
  CallByValue(System.nanoTime())

}
