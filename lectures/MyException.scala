package lectures

object MyException extends App {
  def getInt(x:Boolean)={
    if (x) throw new RuntimeException("No int for you")
    else 42
  }
  try{
    getInt(true)
  }catch {
    case e:Exception => println("Excption occurred")
  }finally {
    println("Finally block.")
  }

  /*
Exercise Question
 */
  // OUtofMemory Error
//  val array = Array.ofDim(Int.MaxValue)

  //Stack Overflow error
//  def infinite:Int = 1 + infinite
//  val noLimit = infinite

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationError extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x:Int,y:Int) = {
      val res = x + y
      if (x>0 && y>0 && res<0) throw new OverflowException
      else if (x<0 && y<0 && res>0) throw new UnderflowException
      else res
    }

    def subtract(x:Int,y:Int) = {
      val res = x - y
      if (x>0 && y>0 && res<0) throw new OverflowException
      else if (x<0 && y<0 && res>0) throw new UnderflowException
      else res
    }

    def multiply(x:Int,y:Int) = {
      val res = x * y
      if (x>0 && y>0 && res<0) throw new OverflowException
      else if (x<0 && y<0 && res<0) throw new OverflowException
      else if (x>0 && y<0 && res>0) throw new UnderflowException
      else if (x<0 && y>0 && res>0) throw new UnderflowException
      else res
    }

    def divide(x:Int,y:Int) = {
      if (y == 0) throw new MathCalculationError
      else x / y
    }
  }
  println(PocketCalculator.add(10,2))
  //    println(PocketCalculator.add(Int.MaxValue,2)) //throws OverflowExecption
  println(PocketCalculator.divide(2,0))

}
