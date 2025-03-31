
case class DogName(name: String)

given Conversion[String, DogName] with 
  def apply(s: String): DogName = DogName(s)


extension(s: String)
  def evenletters = s.count(c => c.charValue % 2 == 0)

@main
def main(): Unit = {

  val dog: DogName = DogName("Archer")
  val dn: DogName = "Rosie"

  println(s"$dog, $dn")

  // Cool piece of code 
  val strings = Seq("zero", "one", "two", "three", "four", "five")

  extension(s: String)
    def value: Int = strings.indexOf(s)

    def plus(other: String): String = 
      strings(s.value + other.value)

    def multi(other: String): String = 
      strings(s.value * other.value)

    def minus(other: String): String = 
      strings(s.value - other.value)

  println(strings(1).plus(strings(1)))
  println("one".plus("one"))
  println("two" plus "three")
  println("five" minus "three")


  val list1 = List(1,2,3)
  val list2 = List("a","b","c")

  val nested = (for 
    a <- list1 
    b <- list2 
  yield(a, b))

  println(nested)

  println(list1.flatMap(a => list2.map(b => (a, b))))
}



// // calculates powers of two using recursion
// def powersOfTwo(num: Int): BigInt = 
//   if (num == 0) 
//     1 
//   else 
//     powersOfTwo(num - 1) * 2
//
//
// // Extends the string class to show even letters in the char set 
// extension (s: String)
//   def evenLetters = s.count(c => c.charValue % 2 == 0)
//
//
// @main
// def main(): Unit = {
//
//   // calculates a given number of powers of two
//   (0 to 8).map(x => println(s"$x bit number max val: ${powersOfTwo(x)}"))
//
//   // prints the number of even letters in the given string
//   println("hello".evenLetters)
//
// }


// trait Openable[A]:
//   extension (a: A) def open: String 
//
// class Wine(val name: String) 
//
// def pour[A : Openable](item: A) = 
//   val contents = item.open 
//   println(contents)
//
// given corkscrew:Openable[Wine] with
//   extension (w: Wine) def open = "Lovely " + w.name
//
// @main def main(): Unit = {
//
//   val one = "hello"
//   val two = "holpy"
//
//   val list = for 
//     x <- one.zipWithIndex
//     y <- two.zipWithIndex if (x._1 == y._1)
//   yield(x._2, y._2)
//
//   println(list)
//
//
//   // pour(Wine("pinoit noir"))
// }
