package battleship;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        Board fogOfWar = new Board();
        Boat aircraftCarrier = new AircraftCarrier();
        Boat battleShip = new Battleship();
        Boat submarine = new Submarine();
        Boat cruiser = new Cruiser();
        Boat destroyer = new Destroyer();


        List<Boat> boatsList = new ArrayList<>();
        boatsList.add(aircraftCarrier);
        boatsList.add(battleShip);
        boatsList.add(submarine);
        boatsList.add(cruiser);
        boatsList.add(destroyer);

        board.boardPrinter(board);
        int errorCode;


        for (int i = 0; i < boatsList.size(); i++) {
            Boat boat = boatsList.get(i);
            InputHandling input = new InputHandling(boat);
            String userInput = input.coordsBoatRequest(boat);
            errorCode = input.boatCoordsCheck(userInput);
            if (errorCode == 0) {
                errorCode = input.boatPositionCheck(userInput, boat, board);
            }
            if (errorCode == 0) {
                input.boatPlacement(userInput, board);
                board.boardPrinter(board);
            } else {
                input.errorCalls(errorCode, boat);
                i--;
            }
        }


        System.out.println("The game starts!");
        Board.boardPrinter(fogOfWar);

        InputHandling shot = new InputHandling(aircraftCarrier);


        String shotInput = shot.shotCoordRequest();
        int shotTest = shot.shotCoordsCheck(shotInput);

        if (shotTest != 0) {
            shot.errorCalls(shotTest, aircraftCarrier);
            while (shotTest != 0) {
                shotInput = shot.shotCoordRequest();
                shotTest = shot.shotCoordsCheck(shotInput);
                shot.errorCalls(shotTest, aircraftCarrier);
            }
        }
        shot.shoot(shotInput, board, fogOfWar);
        board.boardPrinter(board);


    }
}
