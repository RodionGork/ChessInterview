Шахматы
=======

`ChessInterview` is a console tool for tracking chess piece moves.

Given a number of moves in simplified notation it produces the resulting
position, printing out the board.

For example:

    $ java -jar target/Chess.jar e2-e4 e7-e5

    8 BR BN BB BQ BK BB BN BR
    7 BP BP BP BP -- BP BP BP
    6 -- -- -- -- -- -- -- --
    5 -- -- -- -- BP -- -- --
    4 -- -- -- -- WP -- -- --
    3 -- -- -- -- -- -- -- --
    2 WP WP WP WP -- WP WP WP
    1 WR WN WB WQ WK WB WN WR
      a  b  c  d  e  f  g  h

*It could be seen that pieces are written as a pairs of letters, first of them
denoting the side (White or Black) and second the rank (Pawn, kNight, Bishop, Rook, Queen or King).*

This version lacks the functionality of checking the moves for correctness.

### Problem Statement

Add checking functionality for signalizing errors of:

1. The move made by wrong side (i.e. first move done by black or any side making move two times in sequence).
2. An incorrect pawn move (no need to check "en passant" capture or promotion on the last line).

To build the project you can use [maven](http://maven.apache.org) or any IDE with maven support (NetBeans, Idea).
Sample build and run commands are in the `build_run.sh` file.
