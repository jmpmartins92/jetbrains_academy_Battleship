package battleship;

import java.util.List;

public class Board {
    private String[][] board = new String[11][11];


    public Board() {

        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                if (row == 0 && col == 0) {
                    board[row][col] = " " + " ";
                } else if (row == 0) {
                    board[row][col] = col + " ";
                } else if (col == 0) {
                    board[row][col] = Character.toString((65 + row - 1)) + " ";
                } else {
                    board[row][col] = "~" + " ";
                }
            }
        }
    }

    public String pointAt(int row, int col) {
        return board[row][col];
    }

    public boolean pointIsBoat(int row, int col) {
        return board[row][col].equals("O ");
    }

    public void setBoat(int row, int col) {
        board[row][col] = "O ";
    }


    public void setHit(int row, int col) {
        board[row][col] = "X ";
    }

    public void setMiss(int row, int col) {
        board[row][col] = "M ";
    }


    public void boardPrinter() {
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    public void boatPlacementPlayer(List boatsList, Board board) {
        for (int i = 0; i < boatsList.size(); i++) {
            Boat boat = (Boat) boatsList.get(i);
            InputHandling input = new InputHandling();
            String userInput = input.coordsBoatRequest(boat);
            int  errorCode = input.boatCoordsCheck(userInput);
            if (errorCode == 0) {
                errorCode = input.boatPositionCheck(userInput, boat, board);
            }
            if (errorCode == 0) {
                input.boatPlacement(userInput, board);
                board.boardPrinter();
            } else {
                input.errorCalls(errorCode, boat);
                i--;
            }
        }
    }


}

