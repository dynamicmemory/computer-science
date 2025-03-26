@main def main() = {
  val args = List(1,2,3,4,5)
  var i = 0; 
  
  while (i < args.length)
    if (i != 0)
      print(" ")
    print(args(i))
    i += 1

  println()

  args.foreach(arg => println(arg))
}
