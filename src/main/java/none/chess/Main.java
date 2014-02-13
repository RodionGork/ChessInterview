package none.chess;

public class Main {

    public static void main(String[] args) {
        Desk desk = new Desk();
        for (String arg: args) {
            try {
                desk.move(arg);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println(desk);
    }
}
