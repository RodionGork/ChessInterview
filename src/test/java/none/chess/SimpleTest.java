package none.chess;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class SimpleTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testNoMoves() {
        runFile("001-no-moves.test");
    }

    @Test
    public void testSimpleError() {
        runFile("011-simple-error.test");
    }

    @Test
    public void testSimple() {
        runFile("012-simple-move.test");
    }

    @Test
    public void testColorRotationError() {
        runFile("013-color-rotation-error.test");
    }

    @Test
    public void testColorRotationCorrect() {
        runFile("014-color-rotation-correct.test");
    }

    @Test
    public void testPawnMovesOneSquareVertically() {
        runFile("021-pawn-moves-one-square-vertically.test");
    }

    @Test
    public void testPawnCanMoveTwoSquaresOnFirstMove() {
        runFile("022-pawn-can-move-two-squares-on-first-move.test");
    }

    @Test
    public void testPawnCanNotMoveDiagonally() {
        runFile("023-pawn-can-not-move-diagonally.test");
    }

    @Test
    public void testPawnCapturesDiagonally() {
        runFile("024-pawn-captures-diagonally.test");
    }

    @Test
    public void testPawnCanNotCaptureVertically() {
        runFile("025-pawn-can-not-capture-vertically.test");
    }

    @Test
    public void testPawnCanNotMoveFartherOneSquare() {
        runFile("026-pawn-can-not-move-farther-one-square.test");
    }

    @Test
    public void testPawnCanNotMoveAcrossFigure() {
        runFile("027-pawn-can-not-move-across-figure.test");
    }


    private void runFile(String filename) {
        URL url = Resources.getResource(filename);
        String text = "";
        try {
            text = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        List<String> lines = new ArrayList<>(Arrays.asList(text.split("\n")));

        String moves = lines.get(0).trim();
        String movesDesc = moves.isEmpty() ? "(no moves)" : moves;
        lines.remove(0);

        boolean isCorrect = !lines.get(0).equals("error");
        lines.remove(0);

        String[] empty = {};
        Main.main(moves.isEmpty() ? empty : moves.split(" "));
        if (isCorrect) {
            Assert.assertEquals("\n\n\033[31mMoves are correct, but chess thinks there is an error:\n" + movesDesc + "\033[m\n\n", "", errContent.toString());
            //Assert.assertEquals(StringUtils.join(lines, "\n") + "\n\n", outContent.toString());
        } else {
            Assert.assertNotEquals("\n\n\033[31mMoves are invalid, but chess does not detect that:\n" + movesDesc + "\033[m\n\n", "", errContent.toString());
        }
    }
}
