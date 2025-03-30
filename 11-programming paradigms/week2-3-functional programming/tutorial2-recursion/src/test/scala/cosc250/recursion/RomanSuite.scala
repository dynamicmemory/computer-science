package cosc250.recursion

import Roman._

class RomanSuite extends munit.FunSuite {
  test("Roman should calculate the next (numeral, value) pair for a number") {
    assertEquals(nextNumeral(1), ("I", 1))
    assertEquals(nextNumeral(3), ("I", 1))
    assertEquals(nextNumeral(4), ("IV", 4))
    assertEquals(nextNumeral(15), ("X", 10))
    assertEquals(nextNumeral(100), ("C", 100))
  }

  test("it should calculate Roman Numerals correctly") {
    assertEquals(roman(1), "I")
    assertEquals(roman(3), "III")
    assertEquals(roman(4), "IV")
    assertEquals(roman(10), "X")
    assertEquals(roman(104), "CIV")
    assertEquals(roman(1900), "MCM")
    assertEquals(roman(1988), "MCMLXXXVIII")
  }
}