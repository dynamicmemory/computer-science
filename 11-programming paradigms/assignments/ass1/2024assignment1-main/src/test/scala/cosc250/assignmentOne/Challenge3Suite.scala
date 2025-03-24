package cosc250.assignmentOne

import Challenge3._

class Challenge3Suite extends munit.FunSuite {

  test("Eight queens should understand rows") {
    assertEquals(sameRow((0, 1), (7, 1)), true)
    assertEquals(sameRow((0, 2), (1, 2)), true)
    assertEquals(sameRow((0, 3), (0, 1)), false)
    assertEquals(sameRow((0, 1), (4, 5)), false)
  }

  test("it should understand columns") {
    assertEquals(sameCol((0, 1), (0, 6)), true)
    assertEquals(sameCol((2, 2), (2, 1)), true)
    assertEquals(sameCol((4, 3), (3, 3)), false)
    assertEquals(sameCol((0, 1), (4, 5)), false)
  }

  test("it should understand diagonals") {
    assertEquals(sameDiagonal((0, 1), (1, 2)), true)
    assertEquals(sameDiagonal((0, 1), (1, 0)), true)
    assertEquals(sameDiagonal((0, 1), (5, 6)), true)
    assertEquals(sameDiagonal((0, 1), (6, 5)), false)
  }

  test("it should understand attacks") {
    assertEquals(attackingEachOther((7, 1), (7, 1)), false)
    assertEquals(attackingEachOther((7, 1), (5, 2)), false)
    assertEquals(attackingEachOther((7, 1), (2, 2)), false)
    assertEquals(attackingEachOther((7, 1), (1, 1)), true)
    assertEquals(attackingEachOther((7, 7), (1, 1)), true)
  }

  test("it should find attacks) a Sequence") {
    assertEquals(seqContainsAttack(Seq(0 -> 1, 1 -> 1)), true)
    assertEquals(seqContainsAttack(Seq(0 -> 1, 1 -> 3, 2 -> 2, 3 -> 4)), true)
    assertEquals(seqContainsAttack(Seq(0 -> 1, 1 -> 3, 2 -> 2)), true)
    assertEquals(seqContainsAttack(Seq.empty), false)
    assertEquals(seqContainsAttack(Seq(0 -> 1)), false)
    assertEquals(seqContainsAttack(Seq(0 -> 1, 1 -> 3, 2 -> 5)), false)
  }

  test("it should understand valid solutions") {
    assertEquals(isValid(Seq((0,0), (1,4), (2,7), (3,5), (4,2), (5,6), (6,1), (7,3))), true)
    assertEquals(isValid(Seq((0,0), (1,5), (2,7), (3,2), (4,6), (5,3), (6,1), (7,4))), true)
    assertEquals(isValid(Seq(0 -> 7, 2 -> 0, 3 -> 2, 4 -> 5, 5 -> 1, 6 -> 6, 7 -> 5)), false)
    assertEquals(isValid(Seq(0 -> 7, 1 -> 4, 2 -> 2, 3 -> 0, 4 -> 5, 5 -> 1, 6 -> 6, 7 -> 5)), false)
  }

  test("it should expand shorthand") {
    assertEquals(expandShortHand(Seq()), Seq())
    assertEquals(expandShortHand(Seq(1, 2, 3)), Seq((0, 1), (1, 2), (2, 3)))
  }

  test("it should find 92 solutions") {
    assertEquals(eightQueens.count(_ => true), 92)
  }
}