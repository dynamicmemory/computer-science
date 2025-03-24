package cosc250.assignmentOne

/*
 * To avoid name-clashes in any functions, but without having to separate files into different
 * directories, the challenges are wrapped in objects. 
 * 
 * This is Challenge1. In this challenge, you will write some functions that work with Lists
 * 
 * Where you see ??? you need to write the code that will implement the function.
 */
object Challenge1 {

  /** 
    A palindrome is a list that is the same forwards as backwards. eg, List(1, 2, 2, 1)
    Make this function return true if a list is a palindrome. Note, you ARE permitted to
    use the inbuilt reverse method on Lists.
   */
  def isPalindrome[T](list:List[T]):Boolean = if (list == list.reverse) true else false

  /** 
    Make this function return true if every entry is bigger than its position in the list.
    So, List(1, 2, 3) would return true but List(1, 2, 2) would return false because the
    last entry is at position 2 (remember, lists are zero-indexed) but its value (exactly 2) 
    is not bigger than 2.

    You will find list.zipWithIndex and list.forall useful
   */
  def entriesBiggerThanIndex(list:List[Int]):Boolean = 
    
    list.zipWithIndex.forall(tuple => tuple._1 > tuple._2)

  /**
    A little tricker. Make this function return true if every second entry in a list
    forms a palindrome. eg, List(99, 1, 98, 2, 97, 2, 96, 2, 95, 1) is a palindrome because
    every second element forms List(1, 2, 2, 1) which is a palindrome

    You might find zipWithIndex and filter useful to extract every second element.
    You might also find it helpful to print out an intermediate result
  */
  def secondPalindrome[T](list:List[T]):Boolean = 

    // Reusing isPalindrome from above "cus we functional like dat"
    isPalindrome(list.zipWithIndex.filter(tuple => tuple._2 % 2 != 0).map(_._1))

}

