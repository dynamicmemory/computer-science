package assignmentTwo.reversi

class ReversiTests extends munit.FunSuite {

    // Test created by Will used in marking
    test("Walking to the edge of the board ") {
        assertEquals(walkToEdge((3, 2), Direction.North), Seq((3, 2), (3, 1), (3, 0)))
        assertEquals(walkToEdge((3, 2), Direction.South), Seq((3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7)))
        assertEquals(walkToEdge((3, 0), Direction.North), Seq((3, 0)))
        assertEquals(walkToEdge((3, 7), Direction.South), Seq((3, 7)))
        assertEquals(walkToEdge((3, -1), Direction.North), Seq.empty)
        assertEquals(walkToEdge((3, 8), Direction.South), Seq.empty)

        assertEquals(walkToEdge((3, 2), Direction.East), Seq((3, 2), (4, 2), (5, 2), (6, 2), (7, 2)))
        assertEquals(walkToEdge((3, 2), Direction.West), Seq((3, 2), (2, 2), (1, 2), (0, 2)))
        assertEquals(walkToEdge((0, 3), Direction.West), Seq((0, 3)))
        assertEquals(walkToEdge((7, 3), Direction.East), Seq((7, 3)))
        assertEquals(walkToEdge((-1, 3), Direction.East), Seq.empty)
        assertEquals(walkToEdge((8, 3), Direction.West), Seq.empty)

        assertEquals(walkToEdge((3, 2), Direction.NorthEast), Seq((3, 2), (4, 1), (5, 0)))
        assertEquals(walkToEdge((3, 2), Direction.SouthWest), Seq((3, 2), (2, 3), (1, 4), (0, 5)))
        assertEquals(walkToEdge((0, 3), Direction.SouthWest), Seq((0, 3)))
        assertEquals(walkToEdge((7, 3), Direction.NorthEast), Seq((7, 3)))
        assertEquals(walkToEdge((-1, 3), Direction.NorthEast), Seq.empty)
        assertEquals(walkToEdge((8, 3), Direction.SouthWest), Seq.empty)

        assertEquals(walkToEdge((3, 2), Direction.SouthEast), Seq((3, 2), (4, 3), (5, 4), (6, 5), (7, 6)))
        assertEquals(walkToEdge((3, 2), Direction.NorthWest), Seq((3, 2), (2, 1), (1, 0)))
        assertEquals(walkToEdge((3, 0), Direction.NorthWest), Seq((3, 0)))
        assertEquals(walkToEdge((3, 7), Direction.SouthEast), Seq((3, 7)))
        assertEquals(walkToEdge((3, -1), Direction.SouthEast), Seq.empty)
        assertEquals(walkToEdge((3, 8), Direction.NorthWest), Seq.empty)
    }


    // Test created by Will used in marking
    test("the count of pieces of each colour is returned correctly") {
        assertEquals(3, 
            Board.fromPrettyString("""|........
                                      |........
                                      |........
                                      |........
                                      |...OOO..
                                      |........
                                      |........
                                      |........""".stripMargin).pieces
        )

        assertEquals(3, 
            Board.fromPrettyString("""|........
                                      |........
                                      |........
                                      |........
                                      |...OOO..
                                      |........
                                      |........
                                      |........""".stripMargin).piecesFor(Player.White)
        )

        assertEquals(4, 
            Board.fromPrettyString("""|........
                                      |........
                                      |........
                                      |...*....
                                      |...OOO..
                                      |...***..
                                      |........
                                      |........""".stripMargin).piecesFor(Player.Black)
        )
    }    

    // Test created by Will used in marking
    test("isValidMove returns false if there's no opposing pieces flipped") {
        assertEquals(false, Board.fromPrettyString("""|........
                                                      |........
                                                      |........
                                                      |........
                                                      |...OOOO.
                                                      |........
                                                      |........
                                                      |........""".stripMargin).isValidMove((2, 4), Player.White))

        assertEquals(false, Board.fromPrettyString("""|........
                                                      |........
                                                      |........
                                                      |........
                                                      |...****.
                                                      |........
                                                      |........
                                                      |........""".stripMargin).isValidMove((2, 4), Player.White))                                  

        assertEquals(false, Board.fromPrettyString("""|........
                                                      |........
                                                      |........
                                                      |........
                                                      |...OO**.
                                                      |........
                                                      |........
                                                      |........""".stripMargin).isValidMove((2, 4), Player.White))                                                                                        

    }

    // Test created by Will used in marking
    test("isValidMove returns true if there are pieces flipped") {
        assertEquals(true, Board.fromPrettyString("""|........
                                                     |........
                                                     |........
                                                     |........
                                                     |...*OOO.
                                                     |........
                                                     |........
                                                     |........""".stripMargin).isValidMove((2, 4), Player.White))

        assertEquals(true, Board.fromPrettyString("""|........
                                                     |.....*..
                                                     |....O...
                                                     |...*....
                                                     |...***..
                                                     |........
                                                     |........
                                                     |........""".stripMargin).isValidMove((2, 4), Player.White))                                  

        assertEquals(true, Board.fromPrettyString("""|........
                                                     |........
                                                     |........
                                                     |........
                                                     |...*OOO.
                                                     |........
                                                     |........
                                                     |........""".stripMargin).isValidMove((2, 4), Player.White))                                                                                        

    }

    // Test created by Will used in marking
    test("at the start of the game, only central moves are valid") {

        assertEquals(true, Board.fromPrettyString("""|........
                                                     |........
                                                     |........
                                                     |...*....
                                                     |........
                                                     |........
                                                     |........
                                                     |........""".stripMargin).isValidMove((3, 4), Player.White)) 

        assertEquals(false, Board.fromPrettyString("""|........
                                                      |........
                                                      |........
                                                      |...*....
                                                      |........
                                                      |........
                                                      |........
                                                      |........""".stripMargin).isValidMove((3, 3), Player.White)) 

        assertEquals(false, Board.fromPrettyString("""|........
                                                      |........
                                                      |........
                                                      |...*....
                                                      |........
                                                      |........
                                                      |........
                                                      |........""".stripMargin).isValidMove((3, 5), Player.White)) 

    }

    
    // Feel free to add more tests of your own here
    

    

}
