package cosc250.assignmentOne

import Challenge4._

class Challenge4Suite extends munit.FunSuite {

  test("it should produce the inverse of a Map") {
    assertEquals(inverse(Map.empty[Int,Char]), Map.empty)
    assertEquals(inverse(Map(1 -> 'A')), Map('A' -> 1))
    assertEquals(inverse(Map(1 -> 'A', 2 -> 'B')), Map('A' -> 1, 'B' -> 2))
  }

  test("it should produce a Vignere table") {
    assertEquals(vignereTable(1).mkString, "BCDEFGHIJKLMNOPQRSTUVWXYZA")
    assertEquals(vignereTable(2).mkString, "CDEFGHIJKLMNOPQRSTUVWXYZAB")
    assertEquals(vignereTable(13).mkString, "NOPQRSTUVWXYZABCDEFGHIJKLM")
    assertEquals(vignereTable.toSeq.length, 26)
  }

  test("it should convert a key string into a sequence of indices) the alphabet") {
    assertEquals(letterToNum("SCALA"), Seq(18, 2, 0, 11, 0))
    assertEquals(letterToNum("FUNCTIONALPROGRAMMING"), Seq(5, 20, 13, 2, 19, 8, 14, 13, 0, 11, 15, 17, 14, 6, 17, 0, 12, 12, 8, 13, 6))
    assertEquals(letterToNum(""), Seq.empty)
  }

  test("it should encode plaintext correctly") {
    assertEquals(encode("GREAT", "SCALA"), "YTELT")
    assertEquals(encode("FUNCTIONALPROGRAMMING", "SCALA"), "XWNNTAQNLLHTORRSOMTNY")
  }

  test("it should decode plaintext correctly") {
    assertEquals(decode("YTELT", "SCALA"), "GREAT")
    assertEquals(decode("XWNNTAQNLLHTORRSOMTNY", "SCALA"), "FUNCTIONALPROGRAMMING")
  }
}