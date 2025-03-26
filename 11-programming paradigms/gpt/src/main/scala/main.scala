// After for weeks of this course... I have to start again, i simply dont understand 
import scala.annotation.tailrec

def factorial(n: Int): BigInt = {
  @tailrec
  def loop(acc: BigInt, n: Int): BigInt = 
    if (n <= 1) acc else loop(acc * n, n - 1)

  loop(1, n)
}

@main def main(): Unit = {

  def operateOnList(list: List[Int], op: Int => Int): List[Int] = 
    list.map(op)

  def evenSquares(list: List[Int]): Int = 
    list.map(x => x * x).filter(_ % 2 == 0).sum

  val doubled = operateOnList(List(1,2,3), x => x * 2)

  // println(doubled)
  // println(evenSquares(List(1,2,3,4,5)))

  // Somehow made tail recursion fib 
  def fibonacci(n: Int): Int = {
    @tailrec
    def loop(n: Int, a: Int, b: Int): Int = 
      if (n < 1)
        b
      else 
        loop(n - 1, b, a+b)

    loop(n, 0, 1)
  }

  // println(fibonacci(5)) 

  def add(a: Int)(b: Int): Int = a+b
  def multi(a: Int)(b: Int): Int = a * b 
  val add5 = add(5)
  println(add5(10))

  val doublex = multi(2)
  println(doublex(15))

  // Very confusing example of what this is meant to be... dont even know the name 
  def safeDivide(a: Double, b: Double): Option[Double] = 
    if (b == 0) None else Some(a / b)

  def safeSqrt(a: Double): Option[Double] = 
    if (a < 0) None else Some(Math.sqrt(a))

  def sqrtDivide(a: Double, b: Double): Option[Double] = 
    safeSqrt(a).flatMap(sqrt => safeDivide(sqrt, b))

  println(sqrtDivide(-1, 0))


  // Safe quadratic
  
}
