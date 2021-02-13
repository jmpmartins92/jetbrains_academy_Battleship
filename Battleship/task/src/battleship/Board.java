package battleship;

public class Board {


    public Board() {
        String[][] board = new String[11][11];
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                if (row == 0 && col == 0) {
                    board[row][col] = " " + " ";
                } else if (row == 0 && col > 0) {
                    board[row][col] = col + " ";
                } else if (col == 0 && row > 0) {
                    board[row][col] = Character.toString((65 + row - 1)) + " ";
                } else {
                    board[row][col] = "~" + " ";
                }
            }
        }
    }




    public static void boardPrinter(Board board) {
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }


}

