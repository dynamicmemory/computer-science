package cosc250.recursion

import Puzzle._

class PuzzleSuite extends munit.FunSuite {

  test("Puzzle should calculate the next line of the puzzle sequence") {
    assertEquals(nextLine(List(1)), List(1, 1))
    assertEquals(nextLine(List(1, 1)), List(2, 1))
    assertEquals(nextLine(List(2, 1)), List(1, 2, 1, 1))
    assertEquals(nextLine(List(1, 2, 1, 1)), List(1, 1, 1, 2, 2, 1))
  }

  test("it should work out the nth line of the puzzle") {
    assertEquals(puzzle(0), List(1))
    assertEquals(puzzle(1), List(1, 1))
    assertEquals(puzzle(2), List(2, 1))
    assertEquals(puzzle(3), List(1, 2, 1, 1))
    assertEquals(puzzle(4), List(1, 1, 1, 2, 2, 1))
  }

}