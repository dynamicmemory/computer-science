// functional programming
import java.awt.Rectangle
import scala.compiletime.ops.double
def doubleList(list: List[Int]): List[Int] = 
  list.map(x => x * 2)


def countElements(list: List[Int]): Int = 
  if list.isEmpty then 0
  else 1 + countElements(list.tail) 


def sumElements(list :List[Int]): Int = 
  if list.isEmpty then 0
  else list.head + sumElements(list.tail)


def factorial(n: Int): Int = 
  if n == 0 then 1 else n * factorial(n - 1)


def describe(x: Any): String = x match 
  case 0 => "zero"
  case 1 => "one"
  case "hello" => "a greeting"
  case _ => "something else"


case class Person(name: String, age: Int)

def greet(person: Person): String = person match
  case Person("Alice", _) => "Hello alice"
  case Person(name, age) if age < 18 => s"Hello, young $name"
  case Person(name, age) => s"Hello, $name, age $age"


sealed trait Shape

case class Circle(radius: Double) extends Shape 
case class Rectangle(width: Double, height: Double) extends Shape 

def area(shape: Shape): Double = shape match
  case Circle(r) => Math.PI * r * r
  case Rectangle(w, h) => w * h 


// sealed trait MyList
//
// case object Empty extends MyList 
//
// case class Cons(head: Int, tail: MyList) extends MyList
//
// def sum(list: MyList): Int = list match 
//   case Empty => 0
//   case Cons(h, t) => h + sum(t)
//
// def count(list: MyList): Int = list match
//   case Empty => 0
//   case Cons(h, t) => 1 + count(t)

def twice(x: Int): Int = x * 2 

sealed trait IntList:
  def map(f: Int => Int): IntList = this match
    case Empty => Empty
    case Cons(head, tail) => Cons(f(head), tail.map(f))

case object Empty extends IntList
case class Cons(head: Int, tail: IntList) extends IntList








@main
def run() = {

  val list = List(1,2,1,2)

  println(list.zipWithIndex)


  def isPalindrome[T](list:List[T]):Boolean = {

    if (list == list.reverse) true else false 

  }

  println(

    isPalindrome(list.zipWithIndex.filter(t => t._2 % 2 != 0).map(_._1)) 
  )


  def letterScore(char:Char):Int = { char match
    case 'AEIOULNSTR' => 1
    case char.contain("DG") => 2 
    case char.contain("BCMP") => 3
    case char.contain("FHVWY") => 4
    case char.contain("K") => 5
    case char.contain("JX") => 8
    case char.contain("QZ") => 10
  }

  println(letterScore("F")

  
  // val list = Cons(1, Cons(2, Cons(3, Empty)))
  // println(list.map(twice))
  
  // val names = List("a", "b", "c", "d", "e", "f")
  // println(names.zipWithIndex)
  //
  // def timesPostion(arr: Array[Int]): Array[Int] = 
  //   arr.zipWithIndex.map((a, i) => a * i)
  //
  // println(timesPostion(Array(5,5,5)).toList)
  //
  // def multiplyBy(x: Int): Int => Int = (y: Int) => x * y 
  //
  //
  // val double = multiplyBy(2)
  // twice(7)
  // double(7)
  //







  // val myList = Cons(1, Cons(2, Cons(3, Empty)))

  // val c = Circle(5)
  // val r = Rectangle(2, 3)
  //
  // println(area(c))
  // println(area(r))
  //
  //
  // val list = List(1,2,3,4,5)
  //
  //
  // // val list2 = doubleList(list)
  // val list2 = sumElements(list)
  // println(list2)
  //
  // println(factorial(5))
  //
  // println(describe(0))
  // println(describe(1))
  // println(describe("hello"))
  // println(describe("purple"))
  //
  // println(greet(Person("Alice", 18)))
  // println(greet(Person("Bob", 17)))
  // println(greet(Person("Poop", 18)))

} 


// def repeat[T](list:List[T]):List[T] = 
//   list match
//     case Nil => Nil 
//     case h :: tail => h :: h :: repeat(tail)
//
// def alternate[T](list:List[T]):List[T] = 
//   list match 
//     case Nil => Nil
//     case h :: Nil => Nil 
//     case a :: b :: tail => b :: alternate(tail)
//
// @main
// def main() = {
//
//   println(repeat(List(1, 2, 3)))
//   println(alternate(List(1,2,3,4,5,6)))
// }
//
// import scala.annotation.tailrec
//
// sealed trait IntList:
//   def head:Int
//   def tail:IntList
//
//   final def length:Int = this match 
//     case Empty => 0
//     case Cons(_, tail) => 1 + tail.length
//
//   @tailrec
//   final def apply(index:Int):Int = this match 
//     case Empty => throw new IndexOutOfBoundsException()
//     case Cons(h, tail) =>
//       if index == 0 then h else tail.apply(index - 1)
//
// case object Empty extends IntList: 
//   def head:Int = throw new NoSuchElementException()
//   def tail:IntList = throw new NoSuchElementException()
//
// case class Cons(head:Int, tail:IntList) extends IntList
//
// val myList:IntList = Cons(1, Cons(2, Cons(3, Empty)))
//
//
// @main
// def main() = {
//
//   val myList = Cons(1, Cons(2, Cons(3, Empty)))
//
//   myList(2)
//   myList.length
// }
//
// def describe(card:Card):String = card match 
//   case Card(1, Spade) => "Old Frizzle"
//   case Card(1, _) => "An ace"
//   case Card(11, Spade) | Card(11, Hearts) => "A one-eyed Jack"
//   case Card(x, _) if x > 10 => "A picture card"
//   case Card(x, y) => s"Just a boring old $x of $y"
//
//
// def describePosition(pos:(Int, Int)):String = 
//   pos match
//     case (x,y) if x > 0 && y > 0 => "top right"
//     case (x,y) if x < 0 && y > 0 => "top left"
//     case (x,y) if x > 0 && y < 0 => "bottom right"
//     case (x,y) if x < 0 && y < 0 => "bottom left"
//     case _ => "Youre on an axis"    
//
// //---------------------------------------------------
// enum Suit:
//     case Clubs, Diamonds, Hearts, Spades
//
// import Suit.*
//
// def colour(s:Suit):String = s match 
//   case Clubs | Spaces => "black"
//   case Diamonds | Hearts => "red"
//
//
// case class Card(value:Int, suit:Suit)
//
// def playOrPass(opt:Option[Card]):String = opt match
//   case Some(card) => S"Your played the  ${card.value} of ${card.suit}"
//   case None => "You passed"
//
// @main
// def run() = {
//
//   val aceOfSpades = Card(1, Spades)
//   val Card(value, suit) = aceOfSpades //deconstructing assignment 
//
//
//   val map = Map[Card, String](Card(1, Spades) -> "Old Frizzle")
//
//   describe(Card(11, Clubs))
// }
//
