
//Find last element of a list 
def p01[A](l: List[A]): A =
  l match {
    case h :: Nil => h 
    case _ :: tail => p01(tail)
    case _ => throw new NoSuchElementException
  }
// Find the second last element of a list 
def p02[A](l: List[A]): A = 
  // My solution using the libs
  // l(l.size - 2)

  l match {
    case h :: _ :: Nil => h 
    case _ :: tail => p02(tail)
    case _ => throw new NoSuchElementException
  }

// Find the nth element in the list 
def p03[A](n: Int, l: List[A]): A = 
  (n, l) match 
    case (0, h :: _ ) => h 
    case (n, _ :: tail) => p03(n - 1, tail)
    case (_, Nil ) => throw new NoSuchElementException

// Find the number of elements of a list 
def p04[A](ls: List[A]): Int = 
  // ls.size
  // def count(l: List[A], acc: Int): Int = 
  //   l match 
  //     case _ :: Nil => acc 
  //     case _ :: tail => count(tail, acc+1)
  //
  // count(l, 0)

  ls.foldLeft(0) { (c, _) => c + 1}

// Reverse a list 
def p05[A](ls: List[A]): List[A] = 
  // ls.reverse
  
  // def reverseTailRec(result: List[A], oldList: List[A]): List[A] = 
  //   oldList match 
  //     case Nil => result 
  //     case h :: tail => reverseTailRec(h :: result, tail)

  // reverseTailRec(List[A](), oldList)

  ls.foldLeft(List[A]()) { (accum, element) => element :: accum }

// Find out whether a list is a palindrome
// def p06[A](ls: List[A]): Bool =
//   ls == ls.reverse
  
// Flatten a nested list structure
// def p07[A](ls: List[A]): List[A] = 
//   ls flatMap {
//     case ms: List[_] => p07(ms)
//     case e => List(e)
//   }

// Eliminate consecutive duplicates of list elements 
def p08[A](ls: List[A]): List[A] = 
  ls.foldRight(List[A]()) {
    (element, accum) => 
      if (accum.isEmpty || accum.head != element) 
        element :: accum 
      else 
        accum 
  }

@main 
def run(): Unit = {
  val l = List(1,1,2,3,5,8)

  //Find last element of a list 
  println(p01(l))
  
  //Find the second last element 
  println(p02(l))

  //Find the kth element 
  println(p03(2, l))

  // Find the number of elements in the list 
  println(p04(l))

  // Reverse a list 
  // println(p05(l))

  // Find out whether a list is a palindrome
  // println(p06(List(1,2,3,2,1)))

  // Flatten a nested list structure
  // println(p07(List(List(1,1), 2, List(3, List(5, 8)))))

  // Eliminate consecutive duplicates of list elements 
  println(p08(List('a', 'a', 'b', 'c', 'c', 'c', 'd', 'e', 'e')))
}
