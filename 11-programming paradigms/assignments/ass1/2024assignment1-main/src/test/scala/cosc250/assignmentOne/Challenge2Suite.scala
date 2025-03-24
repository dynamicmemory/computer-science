package cosc250.assignmentOne

import Challenge2._

class Challenge2Suite extends munit.FunSuite {

  test("Scrabble scoring should score individual letters correctly") { 
    assertEquals(letterScore('X'), 8) 
    assertEquals(letterScore('Q'), 10)
    assertEquals(letterScore('R'), 1)
    assertEquals(letterScore('S'), 1)
  }

  test("it should score letters on double and triple letter scores correctly") {
    assertEquals(letterAndSquareScore('X', DoubleLetterScore), 16)
    assertEquals(letterAndSquareScore('Q', TripleLetterScore), 30)
    assertEquals(letterAndSquareScore('R', DoubleLetterScore), 2)
    assertEquals(letterAndSquareScore('S', OrdinarySquare), 1)
    assertEquals(letterAndSquareScore('S', DoubleWordScore), 1)
    assertEquals(letterAndSquareScore('S', TripleWordScore), 1)
  }

  test("it should work out the letter scores for words correctly") {
    assertEquals(totalLetterScore("XYLOPHONE", "XYLOPHONE".map(_ => OrdinarySquare)), 24)
    assertEquals(totalLetterScore("HERRING", "HERRING".map(_ => OrdinarySquare)), 11)
    assertEquals(totalLetterScore("HERRING", "HERRING".map(_ => DoubleWordScore)), 11)
    assertEquals(totalLetterScore("HERRING", Seq(OrdinarySquare, OrdinarySquare, DoubleLetterScore, OrdinarySquare, OrdinarySquare, OrdinarySquare, DoubleLetterScore)), 14)
    assertEquals(totalLetterScore("HERRING", Seq(OrdinarySquare, OrdinarySquare, DoubleLetterScore, OrdinarySquare, OrdinarySquare, OrdinarySquare, TripleLetterScore)), 16)
  }

  test("it should score words, including double and triple word score squares, correctly") {
    assertEquals(scrabbleScore("HERRING", Seq(DoubleWordScore, OrdinarySquare, DoubleLetterScore, OrdinarySquare, OrdinarySquare, OrdinarySquare, TripleLetterScore)), 32)
    assertEquals(scrabbleScore("HERRING", Seq(DoubleWordScore, TripleWordScore, DoubleLetterScore, OrdinarySquare, OrdinarySquare, OrdinarySquare, TripleLetterScore)), 96)
  }

}
