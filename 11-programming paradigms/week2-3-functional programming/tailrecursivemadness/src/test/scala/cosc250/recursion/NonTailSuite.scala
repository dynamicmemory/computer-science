// Don't delete the comment below -- it is a Scala-CLI "using" directive to instruct it to use the munit test framework
//> using test.dep org.scalameta::munit::1.1.0

package cosc250.recursion

import NonTail._

class NonTailSuite extends munit.FunSuite {

  test("it should calculate triangular numbers") {
    assertEquals(triangular(1), 1)
    assertEquals(triangular(2), 3)
    assertEquals(triangular(3), 6)
    assertEquals(triangular(4), 10)
  }

  test("it should calculate factorials") {
    assertEquals(factorial(1), 1)
    assertEquals(factorial(2), 2)
    assertEquals(factorial(3), 6)
    assertEquals(factorial(4), 24)
  }

  test("it should calculate Pascal's triangle") {
    assertEquals(pascal(0), List(1))
    assertEquals(pascal(1), List(1, 1))
    assertEquals(pascal(2), List(1, 2, 1))
    assertEquals(pascal(3), List(1, 3, 3, 1))
    assertEquals(pascal(4), List(1, 4, 6, 4, 1))
  }

  test("it should calculate the Fibonacci sequence") {
    assertEquals(fibonacci(0), BigInt(0))
    assertEquals(fibonacci(1), BigInt(1))
    assertEquals(fibonacci(2), BigInt(1))
    assertEquals(fibonacci(3), BigInt(2))
    assertEquals(fibonacci(4), BigInt(3))
    assertEquals(fibonacci(5), BigInt(5))
    assertEquals(fibonacci(6), BigInt(8))
    assertEquals(fibonacci(7), BigInt(13))
    assertEquals(fibonacci(8), BigInt(21))
    assertEquals(fibonacci(9), BigInt(34))
    assertEquals(fibonacci(10), BigInt(55))
  }

}