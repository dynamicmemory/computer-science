package cosc250.assignmentOne

import Challenge6._

class Challenge6Suite extends munit.FunSuite {
  test("it should calculate scores correctly") {
    assertEquals(processHand(Seq()), 0)
    assertEquals(processHand(Seq(Add(3))), 3)
    assertEquals(processHand(Seq(Add(3), NegativeScore)), -3)
    assertEquals(processHand(Seq(Add(3), NegativeScore, Add(2))), -1)
    assertEquals(processHand(Seq(Add(3), NegativeScore, Add(2), DoubleScore)), -2)
    assertEquals(processHand(Seq(Add(3), NegativeScore, Add(2), DoubleScore, ZeroScore)), 0)
    assertEquals(processHand(Seq(Add(3), NegativeScore, Add(2), DoubleScore, ZeroScore, NegativeScore)), 0)
    assertEquals(processHand(Seq(Add(3), NegativeScore, Add(2), DoubleScore, ZeroScore, DoubleScore)), 0)    
  }

  test("it should remove cards from a hand") {
    assertEquals(removeCard(Seq(Add(5)), Add(5)), Seq.empty)
    assertEquals(removeCard(Seq(Add(5), Add(4)), Add(4)), Seq(Add(5)))
    assertEquals(removeCard(Seq(Add(5), DoubleScore, Add(4)), DoubleScore), Seq(Add(5), Add(4)))
    assertEquals(removeCard(Seq(Add(5), DoubleScore, Add(4)), Add(5)), Seq(DoubleScore, Add(4)))
  }

  test("it should calculate the difference) score from removing a card") {
    assertEquals(diffFromRemoving(Seq(Add(5)), Add(5)), -5)
    assertEquals(diffFromRemoving(Seq(DoubleScore), DoubleScore), 0)
    assertEquals(diffFromRemoving(Seq(NegativeScore, Add(5)), NegativeScore), 0)
    assertEquals(diffFromRemoving(Seq(Add(5), NegativeScore, Add(3)), NegativeScore), 10)
  }

  test("it should choose the best card for our oppenent to remove") {
    assertEquals(bestCardToRemove(Seq(Add(5), Add(3), Add(2))), Add(5))
    assertEquals(bestCardToRemove(Seq(Add(5), Add(3), Add(4), DoubleScore)), DoubleScore)
    assertEquals(bestCardToRemove(Seq(Add(5), Add(3), NegativeScore, ZeroScore)), ZeroScore)
    assertEquals(bestCardToRemove(Seq(Add(5), Add(3), DoubleScore)), Add(5))
  }

  test("it should choose the best order for our hand") {
    assertEquals(bestOrder(Seq(Add(5))), Seq(Add(5)))
    
    // A little internal utility for checking there is not a higher scoring order
    def isBest(h:Seq[Card]):Boolean = {
      def scoreHand(h:Seq[Card]) = processHand(removeCard(h, bestCardToRemove(h)))

      val hScore = scoreHand(h)
      h.permutations.find { alt => scoreHand(alt) > hScore } match {
        case Some(order) => println(s"Found a better order: $order"); false
        case _ => true
      }
    }

    assertEquals(isBest(bestOrder(Seq(Add(5), NegativeScore, Add(4)))), true)
    assertEquals(isBest(bestOrder(Seq(Add(5), NegativeScore, Add(4), DoubleScore))), true)
    assertEquals(isBest(bestOrder(Seq(Add(5), NegativeScore, Add(4), DoubleScore, ZeroScore, Add(4)))), true)
  }
}
