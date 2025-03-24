import scala.io.*

type Level = Int 
type Report = Seq[Level]

def day2() = {

  val input  = Source.fromFile("./src/main/scala/day2_sample.txt").getLines();


  def pasrseReport(line: String): Report = {
    line.split(' ').map(_.toInt)
  }

  val reports = input.map(x => pasrseReport(x))


  def thisReportSafe(r: Report) = {
    val zipped = r.zip(r.tail)

    val ascendingSafe = zipped.forall((a, b) => b > a && Math.abs(a - b) <= 3)

    val decendingSafe= zipped.forall((a, b) => a > b && Math.abs(a - b) <= 3)
  
    ascendingSafe || decendingSafe 
  }


  def safe(r: Report): Boolean = {
    thisReportSafe(r) || possibilities(r).exists(thisReportSafe(_)) 
  }


  def possibilities(r :Report): Seq[Report] = {

    for
      omit <- r.indices
      ommitted = for 
        (l, i) <- r.zipWithIndex if i != omit
      yield l
    yield ommitted
  }

  reports.foreach((x) => println(safe(x)))
  println(reports.count(x => safe(x)))
}
