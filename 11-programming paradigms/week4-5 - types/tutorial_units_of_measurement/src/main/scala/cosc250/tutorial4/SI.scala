package cosc250.tutorial4

import scala.language.{implicitConversions, postfixOps}

object SI extends App {

  /*
     In this tutorial, we'll play around with implicits to see if we can get
     an equation for Volts, Watts, Amps, and Ohms working.

     SI units is actually quite a hard problem, so we're not going to go very far.
     We're just going to get the equation from the lecture working.

     First, we'll do an example with prefixes (eg, "milli" for thousandths), where
     I've provided the class but not the implicit conversion function.

     Then we'll do one that sets up equations with units -- I've written the
     machinery for handling the units, but you'll need to define a class and an
     implicit conversion so that you can make things like
     3.pico.Watts
     compile

   */



  /*
      First, let's get prefixes working.
   */

  /** Let's declare a class that has various prefixes */
  class Prefix(d:Double) {
    def pico:Double = d / 1000000000000L
    def nano:Double = d / 1000000000
    def micro:Double = d / 1000000
    def milli:Double = d / 1000
    def centi:Double = d / 100
    def deci:Double = d / 10
    def deca:Double  = d * 10
    def hecta:Double = d * 100
    def kilo:Double = d * 1000
    def mega:Double = d * 1000000
    def giga:Double = d * 1000000000
    def tera:Double = d * 1000000000000L
  }

  // We can now say things like this:
  println("1 deci * 2 hecta is" + (new Prefix(1).deci * new Prefix(2).hecta))


  // YOUR TASK: Define an implicit conversion, so you can make these work:
  // println("1 deci * 2 hecta is" + (1.deci * 2.hecta))
  // println("Is 1 kilo * 1 giga == 1 tera? " + (1.kilo * 1.giga == 1.tera))


  /*
   * For the next part, we're going to need some classes to represent units.
   * And a class to represent a "unit equation" (eg V/A)
   * Let's create those for you.
   */


  /** SIUnit represents a raw unit. We'll just have two */
  trait SIUnit
  case object Volt extends SIUnit
  case object Amp extends SIUnit

  /**
    * A unit equation
    * eg, V / A  would have V -> 1 and A -> -1
    * the map contains each unit and its power
    *
    * eg, acceleration, m / s2  would be  m -> 1 and s -> -2
    * @param units
    */
  case class UnitEq(units:Map[SIUnit, Int]) {

    /** Multiplies this unit equation by another */
    def * (u:UnitEq):UnitEq = {

      // get the keySet from both maps, so we have all the units in both equations
      val keys = units.keySet ++ u.units.keySet

      // for all the keys, get the value out of both maps and sum them
      // filter out the ones where they sum to zero
      val newVals = for {
        k <- keys
        v1 = units.getOrElse(k, 0)
        v2 = u.units.getOrElse(k, 0) if v1 + v2 != 0
      } yield k -> (v1 + v2)

      UnitEq(newVals.toMap)
    }

    /** 1 divided by this equation */
    def inverse:UnitEq = {
      val newVals = for {
        (k,v) <- units
      } yield k -> -v

      UnitEq(newVals)
    }

    /** To divide by another equation, we multiply by its inverse */
    def / (u:UnitEq):UnitEq = this * u.inverse

    /**
      * Override toString to produce prettier output
      * Produces a String like m^1 s^-1
      */
    override def toString:String = {
      val seqStr = for {
        (k, v) <- units
      } yield s"$k^$v "

      seqStr.fold("")(_ concat _)
    }
  }

  /** Now we have a class that contains a number and its units */
  case class WithUnits(d:Double, u:UnitEq) {
    def *(wu:WithUnits) = WithUnits(d * wu.d, u * wu.u)
    def /(wu:WithUnits) = WithUnits(d / wu.d, u / wu.u)
    def +(wu:WithUnits):WithUnits = {
      if (u == wu.u) {
        WithUnits(d + wu.d, u)
      } else throw new IllegalArgumentException(s"Mismatched units -- $u + ${wu.u}")
    }

    override def toString = s"$d $u"
  }


  // So, now our problem is that to define 1 Volt, we have to say:
  val oneVolt = WithUnits(1, UnitEq(Map(Volt -> 1)))
  val oneOhm = WithUnits(1, UnitEq(Map(Volt -> 1, Amp -> -1)))

  // TASK: Your challenge is to write the implicit conversions so we can say:
  // println(3.Volts * (12.Volts / 6.Ohms + 2.Amps))
  // and get
  // 12.0 Volt^1 Amp^1


  /*
     Part 3: Here's one someone else made earlier.

     In our code, to keep things short, all unit measurements are of type WithUnits.
     So, 1.Volt + 2.Amps gives an error at runtime.

     But we could create types for Volts, Amps, etc, so this would be checked statically
     at compile time.

     That's probably too long an exercise for a tutorial -- there would be a lot of
     different unit combinations, so let's just demo one someone else made earlier

     This is scoped inside a block to prevent the import from leaking into the other
     work.
   */


  {
    // import squants.electro.ElectricPotentialConversions._
    // import squants.electro.ElectricalResistanceConversions._

    // val volts = 3 V
    // val resistance = 1 ohms

    // val current = volts / resistance
    // println(s"Current was $current")

    // This won't even compile
    // val current = volts + resistance


  }



}
