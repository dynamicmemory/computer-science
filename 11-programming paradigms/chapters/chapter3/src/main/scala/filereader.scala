import scala.io.Source 

def widthOfLength(s: String): Int = s.length.toString.length

@main
def main(file: String): Unit = {

  if (file.length > 0) 
    val lines = Source.fromFile(file).getLines().toList

    val maxWidth = widthOfLength(lines.reduceLeft((a, b) => 
        if (a.length > b.length) a else b))

    for (line <- lines) 
      val numSpaces = maxWidth - widthOfLength(line)
      val padding = " " * numSpaces
      println(padding + line.length + " | " + line)
  else
    Console.err.println("Please enter filename")

}
