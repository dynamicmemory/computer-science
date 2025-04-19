package cosc250.tutorial4


/**
  * Part One of this week's tutorial - two demos and a challenge.
  *
  * This is written as extending App so you can run it (either from sbt - the run command, or from IntelliJ)
  */
object PartOne extends App {

  /*
   * Our first example is just a little example of how we can use the compiler to help us avoid mistakes.
   * In most applications that use a database, objects have IDs. The type of the IDs is normally the same, and
   * we could mis-assign an ID to one type of item with another.
   *
   * Here, we'll use an implicit conversion to turn IDs from Strings into a type that takes the kind of object it is
   * an ID to as part of the type signature.
   */

  case class IdStr[T](id:String)

  // Import implicit conversions if we want to do this promotion without a warning
  import scala.language.implicitConversions

  given id[T]:Conversion[String, IdStr[T]] with
    def apply(s:String) = IdStr[T](s)

  case class User(id:IdStr[User], name:String)
  case class Course(id:IdStr[Course], name:String)

  @main def implicitMain = {
    // We can create users and courses as normal
    val fred = User("123", "Fred")
    println(fred)

    // But see if you can accidentally assign a User ID to a course... (uncomment this and it shouldn't compile)
    //val course = Course(fred.id, "My course")
    //println(course)
  }

  // However, implicit conversions are seen as problematic because it's hard for a programmer to see where the conversion occurred
  // Instead, it might be better to define .asId as an extension method
  extension (s:String) def asId[T] = IdStr[T](s)

  @main def extensionIdMain = {
    // Though we're still inferring the type T, the place where the string becomes an Id is explicit in this version
    val fred = User("123".asId, "Fred")
    println(fred)

    // But see if you can accidentally assign a User ID to a course... (uncomment this and it shouldn't compile)
    //val course = Course(fred.id, "My course")
    //println(course)
  }



  /**
    * Let's wrap some extension methods.
    *
    * The example below is a contrived example as it's not something we'd really want to do. But it lets us demo
    * the principle on a simple example.
    *
    * Suppose we wanted to be able to write out binary strings, eg "1101011" and do binary operations on them such as
    * shift-right. "1101011" >> 2
    *
    * That the class extends AnyVal means that in the compiled code, the class will never have memory allocated to it -
    * is a "value class", that exists only to hold the extra methods we're adding to strings while it is in scope.
    */
  extension (str:String) {

    /**
      * This lets us say, for instance
      * "101".parseBinary
      */
    def parseBinary:Int = {
      Integer.parseInt(str, 2)
    }

    /**
      * We've defined an operator. Because Scala lets us omit dots and parenthesis,
      * we can now say
      * "110" - "10"
      */
    def -(other:String) = {
      (str.parseBinary - other.parseBinary).toBinaryString
    }

    /**
      * Zero-fill shift right.
      * Shifts the string n characters to the right, filling with '0' characters
      *
      * Try it and see what it does
      */
    def >>>(n:Int):String = {
      (str.parseBinary >>> n).toBinaryString
    }

    /**
      * Sign-preserving shift right.
      * Shifts the string n characters to the right, filling with '0' characters
      *
      * Try it and see what it does
      */
    def >>(n:Int):String = {
      (str.parseBinary >> n).toBinaryString
    }

    /**
      * Sign-preserving shift right.
      * Shifts the string n characters to the right, filling with '0' characters
      */
    def |(other:String):String = {
      (str.parseBinary | other.parseBinary).toBinaryString
    }

    /**
      * unary_ functions are special - they let us define operators that go
      * before a value. So, unary_~ means we can say
      * ~"110101"
      * to get its one's complement
      */
    def unary_~ : String = {
      val b = str.parseBinary
      (~b).toBinaryString
    }

  }


  // Now that these extension methods are in scope, try a few of these binary operations

  @main def binaryStringMain = {
    println("You need to uncomment the commands...")
    // println("1101".parseBinary)
    // println(~"10101010101010")
    // println("111" >> 2)
  }


  /*
   * Part Three - Vector arithmetic
   *
   * This will be useful for assignments two and three.
   * A location in two-dimensional space can be given as a pair of numbers (x, y). We could define this as a case
   * class or as a tuple. Let's use a tuple in this example as it lets us define extension methods.
   *
   * Velocities in 2D space can also be represented as (vx, vy), so let's just define it as a Vec2D rather than calling
   * it a position (that might be confusing if we then use it for a velocity).
   */
  type Vec2D = (Double, Double)

  /*
   * However, we'd like to be able to do things like vector addition.
   * (1, 2) + (3, 4) = (4, 6), etc.
   *
   * So to do this, we're going to define some extension methods to put onto tuples of Doubles
   */
  extension (vec:Vec2D) {

    def +(other:Vec2D):Vec2D = ???

    def -(other:Vec2D):Vec2D = ???

    def *(scalar:Double):Vec2D = ???

    def /(scalar:Double):Vec2D = ???

    def magnitude:Double = ???

    def angle:Double = ???

  }

  /*
   * There are some unit tests that will let you know if you've succeeded at implementing (some of) the extension
   * methods correctly. However, the main part of the tutorial is then to play with the vector operations you have
   * defined below...
   */

  @main def vectorMain = {
    println("You need to uncomment the examples, and then should play around with writing your own")
    //println( ((1.0, 2.0) * 5) + (4.2, 3.7) )
  }



}
