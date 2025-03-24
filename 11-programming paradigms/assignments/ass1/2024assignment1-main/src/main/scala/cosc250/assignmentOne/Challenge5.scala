package cosc250.assignmentOne

/**
 
  Challenge 5 is John Conway most famous creation: Conway's Game of Life.
  https://en.wikipedia.org/wiki/Conway's_Game_of_Life
  
  Suppose we have a grid of squares, say 20 by 20
  And a square can be filled in (alive) or not filled in (dead).
  
  And at each "time step", we generate a new grid using the following rules:
  Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
  Any live cell with two or three live neighbours lives on to the next generation.
  Any live cell with more than three live neighbours dies, as if by overpopulation.
  Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
  (Each cell has eight neighbours)

  */
object Challenge5 {

  /**
    We're going to define the game using an immutable Map.
    Here, I've used Scala's "type alias" syntax to say that a ConwayState is a map from a tuple of ints to a boolean
    
    The tuple is going to contain (x, y) coordinates, and the Boolean is going to contain the values.
    */
  type Position = (Int, Int)
  type ConwayState = Map[Position, Boolean]

  /**
    If an element in the map is missing, assume it to be false (dead). You can use getOrElse for this. This also
    has the advantage that we *can* ask about negative indices -- getOrElse((-1, -1), false) will be false

    Hint: Map has a getOrElse method
    */
  def isAlive(p:Position, s:ConwayState):Boolean = {
    s.getOrElse(p, false)
  }


  /**
    * Blinkers have a habit of toggling -- to help you test your code, I've included their definition.
    * If you have a blinker1, and you move forward one tick in the game state, you should get blinker2.
    * See the wikipedia page for more on this.
    */
  val blinker1:ConwayState = Map(
    (2, 1) -> true, (2, 2) -> true, (2, 3) -> true
  )
  val blinker2:ConwayState = Map(
    (1, 2) -> true, (2, 2) -> true, (3, 2) -> true
  )

  /**
    First, define a function that given a tuple and a ConwayState will count the number of live neighbours
    */
  def liveNeighbours(pos:Position, state:ConwayState):Int = {

    // take the x,y coords of living squares and count the number of comparisons with a given 
    // position that returns true if they arnt the same square or are one square away
    state.keys.count((x, y) => (x != pos._1 || y != pos._2) && 
      (math.abs(x - pos._1) <= 1 && math.abs(y - pos._2) <= 1))

    // This has to be a line crossed with functional programming? is this normal or too obscure? i wouldnt know...
  }

  /**
    * Next, define a function that determines whether a position should be alive or dead
    */
  def aliveNextTurn(pos:(Int, Int), state:ConwayState):Boolean = {
    // More checks for equality against the games rules
    (isAlive(pos, state) && liveNeighbours(pos, state) > 1 && liveNeighbours(pos, state) < 4) || 
    (!isAlive(pos, state) && liveNeighbours(pos, state) == 3)
  }

  /**
    * Next, define a function that will compute the next state of the game of life, for a given maximum X and Y
    */
  def nextConwayState(state:ConwayState, maxSize:(Int, Int) = (20, 20)):ConwayState = {
     
    // Go through each int in maxSize (0 to 20) for both x and y coords and check against 
    // game rules to get the next game state.
    (for {
      x <- 0 to maxSize._1
      y <- 0 to maxSize._2 if (aliveNextTurn((x, y), state))
    } yield (x, y) -> true).toMap
  }
}
