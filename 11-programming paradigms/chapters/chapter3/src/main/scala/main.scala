import scala.io.Source

def squaresNumber(x: Int): Int = x * x

def printArgs(args: Array[String]): Unit = 
  // One way of functionally doing this 
  args.foreach(arg => println(arg))

  // found out you can just do this
  args.foreach(println)

  // another way 
  for ( arg <- args)
    println(arg)

// So apparently the above isnt functional programming really as it has side effects...
// This is how you go balls to the wall functional... apparently 
def formatArgs(args: Array[String]) = args.mkString("\n")

@main
def main(): Unit = {

  if (args.length > 0) {

    for (line <- Source.fromFile(args(0)).getLines())
      println(line.length + " " + line)
  }
  else 
    Console.err.println("Please enter filename")


























  
  val greetStrings = new Array[String](3)

  greetStrings(0) = "Hello"
  greetStrings(1) = "you"
  greetStrings(2) = "bitch"
  
  // God like function for looping
  // for 
  //   i <- greetStrings
  // yield(println(i))


  val numNames = Array("zero", "one", "two", "three")

  // Maps 
  val romanNumerals = Map(1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V")
  // println(romanNumerals(4))

  val argsArray = Array("Hello", "there", "people")
  // printArgs(argsArray)

  // println(formatArgs(numNames))
  // testings quick example 
  val test = formatArgs(numNames)

  assert(test == "zero\none\ntwo\nthree")

  assert(squaresNumber(2) == 4)
  println("Passed 1st test")
  assert(squaresNumber(0) == 0) 
  println("Passed 2st test")
  assert(squaresNumber(4) == 16) 
  println("Passed 1st test")
  println("Passed all test")
}
