package cosc250.assignmentOne

/**
  Challenge 6 

  We have a little card game that uses an unusual deck.
  There are "Add" cards from 1 to 9, a Double card, a Negative card, and a Zero card.

  The way the game is played is you are dealt eight random cards, and you have to put them
  in the order that will give you the highest score.

  The way the score is calculated is this:
  You start with a score of zero and look at the first card.

  If the card is ZeroScore, you reset your running total to zero
  If the card is Add(number), you add the number to your running total
  If the card is NegativeScore, you mutliply the running total by -1.
    (NB: We only multiple the running total by -1. It does not affect how later cards are processed.
      So, Seq(Add(2), NegativeScore) would produce -2, but
          Seq(Add(2), NegativeScore, Add(3)) would produce 1 (the result of adding 3 to -2)
  If the card is DoubleScore, you double the running total  
    (Again, we only double the running total. It does not affect how later cards are processed.
      This isn't Scrabble.)

  You then move on to the next card and do the same, and so on until you have processed
  all the cards in your hand.

  There are NEVER duplicate cards in a hand.
  
  */
object Challenge6 {

  sealed trait Card
  case object DoubleScore extends Card
  case object NegativeScore extends Card
  case class Add(n:Int) extends Card
  case object ZeroScore extends Card

  val allCards = List(DoubleScore, NegativeScore, ZeroScore) ++ (1 to 9).toList.map(Add.apply)
  
  val rng = new scala.util.Random(99)
  def deal:Seq[Card] = rng.shuffle(allCards).take(8)
  
  /**
   Use foldLeft to work out the score for a hand
    */
  def processHand(cards:Seq[Card]):Int = {
    cards.foldLeft(0) { 
      case (score, DoubleScore) => score * 2
      case (score, NegativeScore) => score * -1
      case (score, ZeroScore) => score * 0
      case (score, Add(n)) => score + n
    }
  }

  /**
   We're going to introduce an extra rule.

    Once you've placed your hand on the table, your opponent must remove
    one card from your hand. The other cards remain in the order they were placed,
    but that card is removed.

    Write a function that will remove one card from a hand, leaving the rest in order.

    Hint: There are no duplicate cards in a hand. You might find the filter method
    on Seq useful.
    */
   def removeCard(hand:Seq[Card], card:Card):Seq[Card] = hand.filter(c => c !=card)

  /**
   Write a function that calculates the change in score that is made to a hand by 
    removing a particular card. Eg, if it reduces it by 2 return -2
    */
  def diffFromRemoving(hand:Seq[Card], card:Card):Int = {
    processHand(removeCard(hand, card)) - processHand(hand)
  }

  /**
   Write a function that will work out the best card for them to remove 
    (the one that reduces your score the most). 

    Hint: A hand is a short sequence. Try them all and pick the best one.

    Hint: you might find the minBy function on Seq[Int] useful
    */
  def bestCardToRemove(hand:Seq[Card]):Card = {
    hand.minBy(card => diffFromRemoving(hand, card))
  }

  /**
   So now we're going to do something tricky.

    Given that our opponent will remove a card from our deck, what is the best order
    to place our cards down in?

    Let us assume that they will always pick the best card to remove from our hand, and
    work out what permutation of our cards will give us the maximum score after they've 
    removed it

    There is a "permutations" function that will give us all the permutations of our deck,
    so we can do this using maxBy

    NOTE: The tests for this function use your other functions. So if processHand or
    bestCardToRemove are wrong, this might appear to pass by mistake.

    Hint: There might be more than one "best order" that would result in the same score.
    In which case any of those will do.
    */
  def bestOrder(hand:Seq[Card]):Seq[Card] = {
    hand.permutations.maxBy(hands => processHand(removeCard(hands, bestCardToRemove(hands))))

    // This list of challenges felt alot easier then challenge 5 or 4 tbh, nvim btw.
  }


}
