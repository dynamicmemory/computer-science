package cosc250.assignmentOne

import Challenge5._

class Challenge5Suite extends munit.FunSuite {
  test("it should determine if a cell is alive now correctly") {
    assertEquals(isAlive((2, 1), blinker1), true)
    assertEquals(isAlive((1, 2), blinker2), true)
    assertEquals(isAlive((-1, 1), blinker1), false)
    assertEquals(isAlive((1, 3), blinker2), false)
  }

  test("it should calclulate live neighbours") {
    assertEquals(liveNeighbours((2, 2), blinker1), 2)
    assertEquals(liveNeighbours((2, 1), blinker1), 1)
  }

  test("it should calculate the next liveness state of a cell") {
    assertEquals(aliveNextTurn((2, 1), blinker1), false)
    assertEquals(aliveNextTurn((2, 2), blinker1), true)
    assertEquals(aliveNextTurn((1, 2), blinker1), true)
  }

  test("it should calculate the next game state") {
    def justTheLiveOnes(c:ConwayState) = c.filter { case (k,v) => v }

    assertEquals(justTheLiveOnes(nextConwayState(blinker1)), blinker2)
    assertEquals(justTheLiveOnes(nextConwayState(blinker2)), blinker1)
  }

}