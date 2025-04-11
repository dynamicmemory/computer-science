package assignmentTwo.queens

// The colours we can play with
enum Colour(val letter:Char):
    case Red extends Colour('r')
    case Orange extends Colour('o')
    case Yellow extends Colour('y')
    case Green extends Colour('g')
    case Blue extends Colour('b')
    case Pink extends Colour('p')
    case White extends Colour('w')
    case Dark extends Colour('d')
    case Cyan extends Colour('c')
    case Purple extends Colour('u')

/** all the different directions a straight line can go in*/
enum Direction(val dx:Int, val dy:Int):
    case North extends Direction(0, -1)
    case NorthEast extends Direction(1, -1)
    case East extends Direction(1, 0)
    case SouthEast extends Direction(1, 1)
    case South extends Direction(0, 1)
    case SouthWest extends Direction(-1, 1)
    case West extends Direction(-1, 0)
    case NorthWest extends Direction(-1, -1)

// A list of all the directions, North, NorthEast, etc.
def allDirections:Seq[Direction] = Direction.values.toSeq    

/** A location on the board. Zero-indexed. Location is a type alias for (Int, Int). e.g. (0, 0) is the top-left */
type Location = (Int, Int)

/** 
 * Defines some methods we can call on Locations. Note, not quite as many as we put into the Reversi game.
 */
extension (l:Location) {
    // Lets us add a direction to a location to step in that direction
    // e.g. (1, 1) + Direction.North == (1, 0)
    def +(dir:Direction):Location = 
        val (x, y) = l
        (x + dir.dx, y + dir.dy)
}

// Squares can contain a queen or a blank
enum Contents:
    case Queen
    case Blank

// A row of coloured squares
type Row = Seq[Colour]

// A sequence of rows makes up our coloured grid
type Grid = Seq[Row]

// To solve the grid, we keep track of what's possible
// i.e. for every location, we say "this could contain a Queen or a Blank"
// and for some squares, we'll get to eliminate "Queen" and for other squares we'll 
//  get to eliminate "Blank"
type PossibilityMap = Map[Location, Set[Contents]]

/** Methods we can call on Grids */
extension (grid:Grid) {

    /** implemented for you, the Set of colours present in the grid */
    def colours:Set[Colour] = 
        (for 
            row <- grid
            col <- row
        yield col).toSet

    /** Implemented for you, looks up what colour a square in the grid is, if it's on the board */
    def apply(location:Location):Option[Colour] = 
        val (x, y) = location
        if grid.allSquares.contains(location) then Some(grid(y)(x)) else None

    /** implemented for you, all the squares of a particular column */
    def column(x:Int):Seq[Location] = for y <- grid.indices yield (x, y)

    /** implemented for you, all the squares of a particular row */
    def row(y:Int):Seq[Location] = for x <- grid(y).indices yield (x, y)

    /** implemented for you, all the locations on the board */
    def allSquares:Seq[Location] = 
        for 
            y <- grid.indices
            x <- grid(y).indices 
        yield (x, y)

    /** implemented for you, all the squares of a particular colour */
    def squares(colour:Colour):Seq[Location] =
        for sq <- allSquares if grid(sq).contains(colour) yield sq    

    // The neighbouring squares that are in the grid
    def neighbours(loc:Location):Seq[Location] =
        for 
            d <- Direction.values.toSeq
            p = loc + d if grid.allSquares.contains(loc)
        yield p

    // Generates a possibility map that thinks any square could be a Queen or a Blank
    def allPossibilities:PossibilityMap = 
        (for 
            y <- grid.indices
            x <- grid(y).indices
        yield (x, y) -> Contents.values.toSet).toMap
}

object Grid {
    // Reads in a grid of colours from a string like
    //
    // wwrb
    // wrrb
    // rrrb
    // bbbb
    def fromPrettyString(s:String):Grid = 
        for line <- s.linesIterator.toSeq yield {
            // The .get here is slightly poor practice ("what if it didn't match?")
            // but as we're creating the pretty strings ourselves, we want it to fail if there's an unrecognised character, which it will
            line.map { (c) => 
                Colour.values.find(_.letter == c) match {
                    case Some(col) => col
                    case _ => throw RuntimeException(s"Grid contained $c which I don't have a colour for")
                }
            }
        } 
}

/** Some methods on possibility maps */
extension (map:PossibilityMap) {

    // A map is solved if we know whether every square is a Queen or a Blank
    def solved:Boolean = map.forall((loc, contents) => contents.size == 1)

    // All locations that could contain a queen, from what we know so far
    def queenLocations:Seq[Location] = 
        for (loc, contents) <- map.toSeq if contents.contains(Contents.Queen) yield loc

    /** Sets a square to be a Queen, also marking its neighbours and other squares 
     in the colour, row, and colum to be Blank */
    def setQueen(grid:Grid, loc:Location):PossibilityMap = 
        // If this square is a Queen, these can't be
        val thereforBlank:Seq[Location] =
            grid.column(loc._1).filter(_ != loc) ++  // The other squares in the column            
            grid.row(loc._2).filter(_ != loc) ++ // The other squares in the row
            {
                grid(loc) match {
                    case Some(c) => grid.squares(c) // The other squares of the same colour
                    case _ => throw RuntimeException(s"Looked up a square that had no colour")
                }   
            } ++ 
            grid.neighbours(loc) // The neighbouring squares

        map ++ thereforBlank.map(_ -> Set(Contents.Blank)) + (loc -> Set(Contents.Queen))

    /** Sets a square to be a Blank. Takes the grid as input for consistency of 
     API with setQueen */
    def setBlank(grid:Grid, loc:Location):PossibilityMap = 
        map + (loc -> Set(Contents.Blank))

    /** Returns true if there are two definite Queens in this map that are touching */
    def hasTwoQueensTouching(grid:Grid):Boolean = 
        grid.allSquares.exists((l) => map(l) == Set(Contents.Queen) && 
          grid.neighbours(l).exists((ll) => map(ll) == Set(Contents.Queen)))

    /** Implement this. Returns true if a row in this map definitely breaks a rule. 
     i.e. it has 2 definite queens or is all definite blanks */
    def rowFails(grid:Grid):Boolean = 

      // Getting the count of queens in a given row of the board 
      val queens = grid.indices.map(x => grid.row(x).count(q => map(q) == Set(Contents.Queen)))

      // returns true if all squares in a row are blank 
      val blanks = grid.indices.map(x => grid.row(x).forall(b => map(b) == Set(Contents.Blank)))

      // checking if queens are greater then 1 or all squares are blank
      queens.exists(rows => rows > 1) || blanks.exists(rows => rows)  

    def columnFails(grid:Grid):Boolean = 

      val queens = grid.indices.map(x => grid.column(x).count(q => map(q) == Set(Contents.Queen)))
      val blanks = grid.indices.map(x => grid.column(x).forall(b => map(b) == Set(Contents.Blank)))

      queens.exists(cols => cols > 1) || blanks.exists(cols => cols) 

    /** Implement this. Returns true if a colour in this map definitely breaks a rule. 
     i.e. it has 2 definite queens or is all definite blanks */        
    def colourFails(grid:Grid):Boolean = 

      val queens = grid.colours.map(x => grid.squares(x).count(q => map(q) == Set(Contents.Queen)))
      val blanks = grid.colours.map(x => grid.squares(x).forall(b => map(b) == Set(Contents.Blank)))

      queens.exists(col => col > 1) || blanks.exists(col => col) 

    /* My helper functions */

    // A function that contains both potential options a square could contain
    def hasBoth: Set[Contents] = Contents.values.toSet

    // Get all the combos of colours for the grid, filtering for 2 or more.
    def colourGroups(grid: Grid): Iterator[Set[Colour]] = grid.colours.subsets.filter(_.size >= 2)

 /** For the last task in the assignment. You can add another logical rule that 
     can invalidate a grid if it's impossible - 
     *  e.g. that if 2 colours only have the same 1 column free, it's invalid */
    def extraRuleFails(grid:Grid):Boolean = 

      def checkForInvalids(getIndex: Location => Int): Boolean = 

        // Map the colours to their position in the grid 
        val colourPositions: Map[Colour, Seq[Int]] = 
          grid.colours.map(c => c -> grid.squares(c).map(getIndex)).toMap  

        // Check is there exists a colourgroup that is only in 1 column 
        colourGroups(grid).exists(colourGroup => colourGroup.flatMap(colourPositions).size == 1)

      checkForInvalids(_._2) || checkForInvalids(_._1)


    // Returns true if this set of possibilities breaks a rule -- e.g. doesn't have 
    // queens in a row, column, or colour, has two queens touching,
    // or definitely has two queens in a row, column, or colour    
    def invalidFor(grid:Grid):Boolean = 
        hasTwoQueensTouching(grid) || rowFails(grid) || columnFails(grid) || 
          colourFails(grid) || extraRuleFails(grid)


    def makeAstep(grid: Grid): PossibilityMap =
      // val colourPositionsRow = grid.colours.map(c => c -> grid.squares(c).map(_._2)).toMap
      // val colourPositionsCol = grid.colours.map(c => c -> grid.squares(c).map(_._1)).toMap

      val snapShot = map

      val updated = grid.allSquares.foldLeft(map) { (currentMap, square) =>
        currentMap.get(square) match 

          // If the squares content is undefined yet
          case Some(possibilities) if possibilities == hasBoth =>
            val queensMap = currentMap.setQueen(grid, square)
            val blanksMap = currentMap.setBlank(grid, square)

            if (queensMap.invalidFor(grid))     
              currentMap.setBlank(grid, square)

            else if (blanksMap.invalidFor(grid))
              currentMap.setQueen(grid, square)

            // Neither were successful so leave the square alone for now, THIS CHECK IS KEY!!!
            else 
              currentMap

          case _ => currentMap
      }
      // Check if anything was solved, use the extraRule if not
      if (updated == snapShot) {

        // Get all the combos of colours for the grid, filtering for 2 or more.
        def colourGroups(grid: Grid): Iterator[Set[Colour]] = grid.colours.subsets.filter(_.size >= 2)

        def myExtraRule(map: PossibilityMap, getIndex: Location => Int): PossibilityMap =

          // Map: colour  -> grid number for each square depending on rows or columns being passed 
          val colourPositions: Map[Colour, Seq[Int]] = 
            grid.colours.map(c => c -> grid.squares(c).map(getIndex)).toMap  

          // Get the set of rows or columns that all the colours in a colour group fall on 
          colourGroups(grid).foldLeft(map) { (currentMap, colourGroup) =>
            val gridIndicies: Set[Int] = colourGroup.flatMap(colourPositions)

            // if the number of columns or rows is equal to the number of colours in the group 
            if (gridIndicies.size == colourGroup.size) 
              // get a set of only the colours not in our current group colour 
              val otherColours: Set[Colour] = grid.colours -- colourGroup

              // Get the locations of all the colours not in our group and filter 
              // for only the columns or rows our colour group is in, (this is the magic)
              otherColours.foldLeft(currentMap) { (updatedMap, otherColour) =>
                val matchLocations: Seq[Location] = grid.squares(otherColour).filter(loc => 
                    gridIndicies.contains(getIndex(loc)))

                // If there is a square with a colour that cant be a queen in our 
                // selected rows or cols, then set it to blank if it hasnt been 
                // assigned a value yet, otherwise let it be.
                matchLocations.foldLeft(updatedMap) {
                  case (finalMap, loc) if finalMap.get(loc).contains(hasBoth) => finalMap.setBlank(grid, loc)
                  case (finalMap, _) => finalMap
                }
              }
            else 
              currentMap      // colourGroups didnt match number of rows or cols 
          }
        // Pass the rows map into the columns map to check for both indicies possibilities
        val rowsMap = myExtraRule(updated, _._2)
        myExtraRule(rowsMap, _._1) 

      }
      else 
        updated // Normal rules worked  

}

val puzzles = Map(

    "#77" -> Grid.fromPrettyString("""|ppoopbbb
                                      |pgoopbbb
                                      |pwwppbbb
                                      |pwwppppp
                                      |pppuuupp
                                      |pppuuupp
                                      |ccpuuurp
                                      |ccpppppp""".stripMargin),

    "#170" -> Grid.fromPrettyString("""|byyydwww
                                       |bbbydddw
                                       |byyydwww
                                       |yyuyywrr
                                       |gguyowwr
                                       |guuuooor
                                       |grrrorrr
                                       |rrrrrrrr""".stripMargin),                                      

    "#332" -> Grid.fromPrettyString("""|pppppppp
                                       |poppppop
                                       |pobbggow
                                       |poobgoow
                                       |poooooow
                                       |prrrrrrw
                                       |ppyrrdww
                                       |ppyyddww""".stripMargin),

    "#151 (harder)" -> Grid.fromPrettyString("""|uuuuoobb
                                                |ugguowwb
                                                |ggggowwb
                                                |ggggggbb
                                                |rrgggggg
                                                |rygggggg
                                                |ryggdggd
                                                |rrggdddd""".stripMargin),
)

