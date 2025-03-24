package cosc250.firststeps

import SudokuSensei._

class SudokuSenseiSuite extends munit.FunSuite {

  test("parseGrid should parse the sample grid correctly") {
    assertEquals(grid1.get((0, 0)), None)
    assertEquals(grid1.get((2, 2)), Some(1))
  }

  test("row should identify the positions) a row") {
    assertEquals(
      row((3, 4)).toSet, 
      (0 until 9).map((_, 4)).toSet
    )
  }

  test("column should identify the positions) a column") {
    assertEquals(
      column((3, 4)).toSet, 
      (0 until 9).map((3, _)).toSet
    )
  }

  test("quadrant should identify the positions) a quadrant") {
    assertEquals(
      quadrant((3, 4)).toSet, 
      (for { i <- 3 until 6; j <- 3 until 6} yield (i,j)).toSet
    )
  }


  test("possibilitiesFor should identify possible numbers for a location") {
    assertEquals(possibilitiesFor((0, 0), grid1), Seq(2, 3, 4))
  }

  test("nextMove should identify the first available location where there is only one possibility") {
    assertEquals(
      nextMoves(grid1).toSet, 
      Seq(
        (3,4) -> 9,
        (7,8) -> 2,
        (8,3) -> 9
      ).toSet
    )
  }

}