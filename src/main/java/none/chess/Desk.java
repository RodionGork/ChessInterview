package none.chess;

import java.util.regex.*;
import com.google.common.collect.*;

import static none.chess.Figure.Type.*;
import static none.chess.Figure.Side.*;

public class Desk {
    private Table<Integer, Character, Figure> figures = HashBasedTable.create();
    
    private int moves;
    
    public Desk() {
        initialSetup();
    }
    
    public void initialSetup() {
        Figure.Type[] types = new Figure.Type[] {ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK};
        for (int x = 0; x < 8; x++) {
            char column = (char) ('a' + x);
            figures.put(1, column, new Figure(types[x], WHITE));
            figures.put(2, column, new Figure(PAWN, WHITE));
            figures.put(7, column, new Figure(PAWN, BLACK));
            figures.put(8, column, new Figure(types[x], BLACK));
        }
        moves = 0;
    }

    public void move(String move) throws Exception {
        Pattern movePattern = Pattern.compile("^([a-h])(\\d)-([a-h])(\\d)$");
        Matcher moveMatcher = movePattern.matcher(move);
        if (!moveMatcher.matches()) {
            throw new Exception("Incorrect move");
        }

        Character xFrom = moveMatcher.group(1).charAt(0);
        Integer yFrom = Integer.parseInt(moveMatcher.group(2));
        Character xTo = moveMatcher.group(3).charAt(0);
        Integer yTo = Integer.parseInt(moveMatcher.group(4));
        if (!figures.contains(yFrom, xFrom)) {
            throw new Exception("No figure here!");
        }
        figures.put(yTo, xTo, figures.get(yFrom, xFrom));
        figures.remove(yFrom, xFrom);
        moves++;
    }
    
    @Override
    public String toString() {
        String crlf = System.getProperty("line.separator");
        StringBuilder res = new StringBuilder();
        for (int y = 8; y >= 1; y--) {
            res.append(y);
            for (char x = 'a'; x <= 'h'; x++) {
                res.append(' ');
                if (figures.contains(y, x)) {
                    res.append(figures.get(y, x));
                } else {
                    res.append("--");
                }
            }
            res.append(crlf);
        }
        res.append("  a  b  c  d  e  f  g  h");
        res.append(crlf);

        return res.toString();
    }
}
