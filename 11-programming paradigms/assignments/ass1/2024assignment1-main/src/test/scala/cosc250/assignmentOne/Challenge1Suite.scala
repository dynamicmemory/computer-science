// Don't delete the comment below -- it is a Scala-CLI "using" directive to instruct it to use the munit test framework
//> using test.dep org.scalameta::munit::1.1.0

package cosc250.assignmentOne

import Challenge1._

/**
 * The tests for Challenge 1. You may not remove any of these.
 */
class Challenge1Suite extends munit.FunSuite {

  test("isPalindrome should determine palindromes correctly") {
    assert(isPalindrome(List(1, 2, 2, 1)))
    assert(isPalindrome(List(1, 2, 3, 2, 1)))
    assert(isPalindrome(List()))
    assert(isPalindrome(List(1)))
    assert(isPalindrome(List(1, 1)))
    assert(!isPalindrome(List(1, 2)))
    assert(!isPalindrome(List(1, 2, 1, 1)))
    assert(isPalindrome((1 to 50).toList ++ (1 to 50).reverse.toList))
  }

  test("entriesBiggerThanIndex should determine if list entries are bigger than their index") {
    assert(entriesBiggerThanIndex(List(1, 2, 3)))
    assert(entriesBiggerThanIndex(List()))
    assert(!entriesBiggerThanIndex(List(0, 1, 2)))
    assert(!entriesBiggerThanIndex(List(0, 0, 3)))
    assert(!entriesBiggerThanIndex((1 to 50).toList ++ (1 to 50).reverse.toList))
  }

  test("secondPalindrome should determine if every second entry in a list forms a palindrome") {
    assert(secondPalindrome(List()))
    assert(secondPalindrome(List(1)))
    assert(secondPalindrome(List(1, 2)))
    assert(secondPalindrome(List(1, 1, 99, 2, -99, 2, 103, 1)))
    assert(secondPalindrome(List(1, 1, 99, 2, -99, 2, 103, 1, 567)))
    assert(secondPalindrome(List(1, 1, 99, 2, 8, 3, -99, 2, 103, 1, 567)))
    assert(!secondPalindrome((1 to 100).toList))
  }


}
