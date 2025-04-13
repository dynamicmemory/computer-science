
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
  ls.size
  def count(l: List[A], acc: Int): Int = 
    l match 
      case _ :: Nil => acc 
      case _ :: tail => count(tail, acc+1)

  count(ls, 0)

  ls.foldLeft(0) { (c, _) => c + 1}

// Reverse a list 
def p05[A](ls: List[A]): List[A] = 
  ls.reverse
  
  def reverseTailRec(result: List[A], oldList: List[A]): List[A] = 
    oldList match 
      case Nil => result 
      case h :: tail => reverseTailRec(h :: result, tail)

  reverseTailRec(List[A](), ls)

  ls.foldLeft(List[A]()) { (accum, element) => element :: accum }

// Find out whether a list is a palindrome
def p06[A](ls: List[A]): Boolean =
  (ls == ls.reverse)
  
// Flatten a nested list structure
def p07[A](ls: List[Any]): List[Any] = 
  ls flatMap {
    case ms: List[_] => p07(ms)
    case e => List(e)
  }

// Eliminate consecutive duplicates of list elements 
def p08[A](ls: List[A]): List[A] = 
  ls.foldRight(List[A]()) {
    (element, accum) => 
      if (accum.isEmpty || accum.head != element) 
        element :: accum 
      else 
        accum 
  }

// Pack consecutive duplicates of list elements into sublists
def p09[A](ls: List[A]): List[List[A]] = 
  ls match {
    case Nil => Nil 
    case head :: _ =>
      val packed = ls.takeWhile(_ == head)
      val rest = ls.dropWhile(_ == head)
      packed :: p09(rest)
  }

// Run length encoding of a list 
def p10[A](ls: List[A]): List[(Int, A)] = 
  p09(ls).map(e => (e.size -> e.head))

// Run length encoding of a list 
def p11[A](ls: List[A]): List[Any]= 
  // first solution
  // p10(ls).map { e => 
  //  if (e.size > 1)
  //    (e.size -> e.head) 
  //  else 
  //    e.head
  // }
  p10(ls) map { t => if (t._1 == 1) t._2 else t }

// Decompress p10 
def p12[A](ls: List[A]): List[Any] = 
  val values = p10(ls)
  for 
    x <- values
    y <- (0 until x._1)
  yield (x._2)

// Skipping this one as its all of the last 5 in one function... stupid.
def p13[A](ls: List[A]): List[Any] = 
  List[Int]()

// Duplicate every element in the list 
def p14[A](ls: List[A]): List[Any] = 
  ls flatMap { e => List(e, e) }

// Duplicate every element in the list by the given n
def p15[A](n: Int, ls: List[A]): List[A] = 
  {for 
    y <- ls 
    x <- (0 to n)
  yield(y)}.toList

// Drop every Nth element from a list 
def p16[A](n: Int, ls: List[A]): List[Any] = 
  ls.zipWithIndex.map(e => (e._1, (e._2+1))).filter(e => e._2 % n != 0).map(e => e._1)
  // ls.zipWithIndex filter { e => (e._2 + 1) % n != 0 } map { _._1 }

// Split a list into two parts, the length of the first part is given 
def p17[A](n: Int, ls: List[A]): Tuple= 
  ls.splitAt(n)  

// Extract a slice from a list 
def p18[A](i: Int, k: Int, ls: List[Any]): List[Any] = 

  ls.zipWithIndex.foldLeft(List[Any]()) { (accum, element) =>
    if (element._2 >= i && element._2 < k)
      accum :+ element._1
    else 
      accum 
    }

  // ls.zipWithIndex filter { case (_, idx) => idx >= i && <= k } map (_._1)
  ls drop i take (k - (i max 0))

// Rotate a list N places to the left 
def p19[A](n: Int, ls: List[A]): List[A] = 

  (ls drop n) ++ (ls take n)

// Remove the Kth element from a list 
def p20[A](n: Int, ls: List[A]): Tuple = 
  // ((ls take n) ++ (ls drop n+1), (ls take n+1).last) // Apparently this is terrible
  ls.splitAt(n) match 
    case (Nil, _) if n < 0 => throw new NoSuchElementException
    case (pre, e :: post) => (pre ::: post, e)
    case (pre, Nil) => throw new NoSuchElementException

// Insert an element at a given position into a list 
def p21[A](element: A, n: Int, ls: List[A]): List[A] = 
  ls.splitAt(n) match 
    case (Nil, _) if n < 0 => throw new NoSuchElementException
    case (pre, post) => (pre ::: element :: post)
    case (pre, Nil) => throw new NoSuchElementException

// Create a list containing all intergers withoin a given range
def p22[Int](n: Int, k: Int) = 
  (n to k)
  

@main 
def run(): Unit = {
  val l = List(1,1,2,3,5,8)

  //Find last element of a list 
  // println(p01(l))
  
  //Find the second last element 
  // println(p02(l))

  //Find the kth element 
  // println(p03(2, l))

  // Find the number of elements in the list 
  // println(p04(l))

  // Reverse a list 
  // println(p05(l))

  // Find out whether a list is a palindrome
  // println(p06(List(1,2,3,2,1)))

  // Flatten a nested list structure
  // println(p07(List(List(1,1), 2, List(3, List(5, 8)))))

  // Eliminate consecutive duplicates of list elements 
  // println(p08(List('a', 'a', 'b', 'c', 'c', 'c', 'd', 'e', 'e')))

  // Pack consecutuve duplicates of list elements into subjects 
  // println(p09(List('a', 'a', 'b', 'c', 'c', 'c', 'd', 'e', 'e')))

  // println(p10(List('a', 'a', 'b', 'c', 'c', 'c', 'd', 'e', 'e')))
  // println(p11(List('a', 'a', 'b', 'c', 'c', 'c', 'd', 'e', 'e')))
  // println(p12(List('a', 'a', 'b', 'c', 'c', 'c', 'd', 'e', 'e')))
  // println(p14(List('a', 'a', 'b', 'c', 'c', 'c', 'd', 'e', 'e')))
  // println(p15(3, List('a', 'a', 'b', 'c', 'c', 'c', 'd', 'e', 'e')))
  // println(p16(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')))
  // println(p17(4, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))
  // println(p18(3, 7, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))
  // println(p19(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))
  println(p20(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))
  println(p21("new", 3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')))
  println(p22(4, 9))
  
  
}

