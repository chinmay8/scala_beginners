package lectures.part3FP

import scala.util.Random

object Sequences extends App{

  //Seq
  val aSequence = Seq(1,3,2,5,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ List(6,8,7))
  println(aSequence.sorted)

  //Ranges
  val aRange: Seq[Int] = 1 to 10 //also can be wriiten as -- 1 until 10
  aRange.foreach(println)
  (1 to 10).foreach(x => println("Hello"))

  //Lists
  val aList = List(1,2,3)
  val prepended = 42 :: aList // Add element at start
  val addElement = 41 +: aList :+ 43 //Add element at start and end

  println(prepended)
  println(addElement)

  val apples = List.fill(5)("apple") //create a list with5 elements apple
  println(apples)
  println(aList.mkString("-|-")) // convert a list into string with mentioned seperator

  //arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements) // prints dump value
  threeElements.foreach(println)

  //mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  //arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  //vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  //vectors vs list

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]):Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    }yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity),r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numberList = (1 to maxCapacity).toList
  val numberVector = (1 to maxCapacity).toVector

  /*
  ADV: keeps the reference to tail
  DISADV: updating element in the middle takes long
   */
  println(getWriteTime(numberList))

  /*
  ADV: depth of the tree is small
  DISADV: needs to replace an entire 32 element chunk
   */
  println(getWriteTime(numberVector))

}
