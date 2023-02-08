package lectures.part4pm

import lectures.GenericList
import lectures.part3FP.MyListFunction

object AllThePatterns extends App {
  // 1- Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "Found a number"
    case "Scala" => "The Scala"
    case true => "The truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match everything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I have found $something"
  }
  // 3 - Tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) =>
    case something => s"I have found $something"
  }

  val nestedTuple = (1, (2,3))
  val matchNestedTuple = nestedTuple match {
    case (_,(2,v)) =>
  }

  //PMs can be NESTED!!

  // 4 - Case classes - constructor patterns
  // PMs can be nested with case classes as well

/*
  val aList = MyListAgain[Int] = new Cons(2, new Cons(1, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead,subtail)) =>
    }
    */

  // 5 - list patterns
  val aStandardList = List(1,2,3,17)
  val standardListMatching = aStandardList match {
    case List(1,_, _, _, _) => //standard - extractor
    case List(1, _*) => //list of arbitrary length arguments
    case 1 :: List(_) => // infix patterns
    case List(1,2,3) :+ 17 => //infix patterns
  }

  //6 - type specifiers
  val unknown: Any = 2
  val unknownMatching = unknown match {
    case list: List[Int] => // explicit type specifiers
    case _ =>
  }

  // 7 - name Binding

/*
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => //  name binding => use the name later here
    case Cons(1, rest @ Cons(2,_)) => // name binding inside nested patterns
  }
  */

  // 8 - multi patterns
/*
  val multiPatterns = aList match {
    case Empty | Cons(0,_) => // compound pattern (multi pattern)
  }
  */

  // 9 - if guards
  val secondElementSpecial = aStandardList match {
    case List(x,_*) if x % 2 != 0 =>
  }

  val numbers = List(1,2,3)

  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of string"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)
  //JVM trick question

}
