package cosc250.tutorial4

import cosc250.tutorial4.SI._


/**
  *
  */
class TutorialSuite extends munit.FunSuite {

  test("you should write some tests for your code and delete this") {
    assertEquals(true, true)
  }


  test("VecOps should add two Vec2D") {
    import PartOne._

    assertEquals((1.0, 2.0) + (3.0, 4.0), (4.0, 6.0))
  }

  test("it should subtract one Vec2D from another") {
    import PartOne._

    assertEquals((1.0, 2.0) - (3.0, 4.0), (-2.0, -2.0))
  }

  test("it should multiply a Vec2D by a scalar") {
    import PartOne._

    assertEquals((1.0, 2.0) * 3, (3.0, -6.0))
  }

  test("it should take the magnitude of a Vec2D") {
    import PartOne._

    assert(Math.abs((3.0, 4.0).magnitude - 5.0) < 0.1)
  }
}
