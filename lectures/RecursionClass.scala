package lectures

import scala.annotation.tailrec
//import scala.jdk.Accumulator

object RecursionClass extends App{
  // MyFunction - Wrong
  @tailrec
  def concatString(n:Int,aString:String):String={
    if (n<=1) aString
    else
      concatString(n-1,aString + aString)
  }

  println(concatString(2,"Chinmay"))

  //Tutor Function
  @tailrec
  def ConcatString(aString: String,n: Int, accumulator: String): String = {
    if (n<=0) accumulator
    else
      ConcatString(aString,n-1,accumulator + aString)
  }

  println(ConcatString("Chinmay",5,""))

  // My isprime function

  def IsPrime(n:Int):Boolean={
    @tailrec
    def IsPrimetUntil(t:Int):Boolean={
      if (t<=1) true
      else if(n%t!=0)
        IsPrimetUntil(t-1)
      else false
    }
    IsPrimetUntil(n/2)
  }
  println(if (IsPrime(10)) "Is a prime number" else "Is not a prime number")

  //Tutor function

  def isprime(n:Int):Boolean={
    @tailrec
    def isStillprime(t:Int,acc:Boolean):Boolean={
      if (!acc) false
      else if (t<=1) true
      else
        isStillprime(t-1,n%t!=0 && acc)
    }
    isStillprime(n/2, true)
  }
  println(if (isprime(13)) "Is a prime number" else "Is not a prime number")

// My Fibo function-- Wrong
  def FiboSeries(n:Int):Int={
    def FiboHelper(t:Int,acc:Int):Int={
      if (t<=2) acc
      else
        FiboHelper(t-1,acc+(t-1))
    }
    FiboHelper(n,0)
  }

  println(FiboSeries(5))

  //Tutor function
  def fibonacci(n:Int):Int={
    @tailrec
    def fiboseries(i:Int,last:Int,nexttoLast:Int):Int={
      if (i>=n) last
      else
        fiboseries(i+1,last + nexttoLast,last)
    }

    if (n<=2) 1
    else
      fiboseries(2,1,1)
  }
  println(fibonacci(5))
}
