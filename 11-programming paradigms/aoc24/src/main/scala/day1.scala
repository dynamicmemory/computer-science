
import scala.io.*

def day1() = {  
  // DAY 1
  val input = Source.fromFile("./src/main/scala/sample.txt").getLines().toSeq;

  val tuples = for 
      case s"$x  $y" <- input
  yield (x.toInt, y.toInt)

  val left = tuples.map((x,y) => x).sorted
  val right = tuples.map((x,y) => y).sorted 

  val zipped = left.zip(right)
  val difference = zipped.map((x,y) => math.abs(x - y))

  // val totals = difference.foldLeft(0)((acc, x) => acc + x)
  val totals = difference.sum

  val simScore: Seq[Long] = for 
      x <- left
  yield
      x * right.count(_ == x)
  
  println(simScore.sum)
  // println(difference)
  // println(totals)


}
