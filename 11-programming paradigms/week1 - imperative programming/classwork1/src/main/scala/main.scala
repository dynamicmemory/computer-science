import scala.compiletime.ops.boolean
def greet(name: String): String = {
  s"Hello, $name"
}

def square(x: Int): Int = 
  x * x 

def checkNumber(x:Int): String = x match
  case 1 => "one"
  case 2 => "Two"
  case 3 => "Three"
  case _ => "Other"

case class Person(name: String, age: Int)

def returnsum(list:List[Int]):Int = {
  var total: Int = 0

  for (num <- list)
    total += num

  total 
}

def returnseven(list:List[Int]):List[Int] = {
  var matches:List[Int] = Nil

  for (num <- list)
    if num % 2 == 0 then 
      matches = num :: matches 

  matches.reverse
}

def reverse_string(word:String):String = {
  val chars = word.toCharArray()
  var i:Int = 0
  var j:Int = chars.size - 1

  while (i < j)
    val temp = chars(i)
    chars(i) = chars(j)
    chars(j) = temp
    i += 1
    j -= 1

  String(chars)
}

def factorial(n:Int):Int = { 
  
  if n > 1 then 
    n * factorial(n-1)
  else 
    1
  }

def ispalindrome(word:String):Boolean = {
  val backwards:String = word.reverse
  var palindrome:Boolean = false

  if (word == backwards) 
    palindrome = true

  palindrome
}


@main def main() = {

  println(reverse_string("hello"))
  // println(ispalindrome("racecar"))
  // println(ispalindrome("poopman"))
  // println(factorial(5))
  
  // println(reverse_string("Lenny"))
  // val list:List[Int] = List(1,2,3,4,5,6,7,8,9)
  // println(returnseven(list))
  // val l = List(1,2,3,4,5)
  // println(returnsum(l))

  // val p1 = Person("Lenny", 25)
  // val p2 = p1.copy(age = 26)
  //
  // println(p1)
  // println(p2)


  // println(checkNumber(2))
  // println(checkNumber(99))

  // println(greet("Lenny"))
  // println(square(5))

  // val numbers = List(1,2,3,4,5)
  // println(numbers)
  // println(numbers.head)
  // println(numbers.tail)
  // println(numbers.map(_ * 2))

  // val nums = List(1,2,3,4,5)
  // val squaredNums = nums.map(square)
  // println(squaredNums)
  //
  // val add = (a: Int, b: Int) => a + b 
  // println(add(3, 7))
}



