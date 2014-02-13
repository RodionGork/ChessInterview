package none.chess;

public class Figure {
    
    public static enum Type {
        PAWN('P'), KNIGHT('N'), BISHOP('B'), ROOK('R'), QUEEN('Q'), KING('K');
        
        private char letter;
        
        Type(char letter) {
            this.letter = letter;
        }
        
    }
    
    public static enum Side {
        WHITE('W'), BLACK('B');
        
        private char letter;
        
        Side(char letter) {
            this.letter = letter;
        }
    }
    
    private Type type;
    private Side side;
    
    public Figure(Type type, Side side) {
        this.type = type;
        this.side = side;
    }
    
    public Type getType() {
        return type;
    }
    
    public Side getSide() {
        return side;
    }
    
    @Override
    public String toString() {
        return side.letter + "" + type.letter;
    }
}
