package battleship;

import java.util.List;

import static battleship.InputHandling.playerTurnChange;

public class Main {

    static Board boardP1 = new Board();
    static Board fogOfWarP1 = new Board();
    static Board fogOfWarP2 = new Board();
    static Board boardP2 = new Board();
    static Boat aircraftCarrier = new AircraftCarrier();
    static Boat battleShip = new Battleship();
    static Boat submarine = new Submarine();
    static Boat cruiser = new Cruiser();
    static Boat destroyer = new Destroyer();
    static int currentPlayer = 1;

    public static List boatsList = List.of(aircraftCarrier, battleShip, submarine, cruiser, destroyer);

    public static void playerTurn() {
        if (currentPlayer == 1) {
            fogOfWarP1.boardPrinter();
            System.out.println("---------------------");
            boardP1.boardPrinter();
        } else if (currentPlayer == 2) {
            fogOfWarP2.boardPrinter();
            System.out.println("---------------------");
            boardP2.boardPrinter();
        }

    }


    public static void main(String[] args) {



        InputHandling.playerInitialization(currentPlayer);
        boardP1.boardPrinter();
        boardP1.boatPlacementPlayer(boatsList, boardP1);
        currentPlayer = playerTurnChange();
        InputHandling.promptEnterKey();

        InputHandling.playerInitialization(currentPlayer);
        boardP2.boardPrinter();
        boardP2.boatPlacementPlayer(boatsList, boardP2);
        System.out.println("The game starts!");
        currentPlayer = playerTurnChange();
        InputHandling.promptEnterKey();
        playerTurn();

        InputHandling shot = new InputHandling();

        while (!shot.gameEndCheck(boardP1) || !shot.gameEndCheck(boardP2)) {
            String shotInput = shot.shotCoordRequest();
            int shotTest = shot.shotCoordsCheck(shot.shotCoordInput(shotInput));

            if (shotTest != 0) {
                shot.errorCalls(shotTest, aircraftCarrier);
                while (shotTest != 0) {
                    shotInput = shot.shotCoordRequest();
                    shotTest = shot.shotCoordsCheck(shot.shotCoordInput(shotInput));
                    shot.errorCalls(shotTest, aircraftCarrier);
                }
            }
            if (currentPlayer == 1) {
                shot.shoot(shotInput, boardP2, fogOfWarP1);
                shot.gameEndCheck(boardP2);
            } else if (currentPlayer == 2) {
                shot.shoot(shotInput, boardP1, fogOfWarP2);
                shot.gameEndCheck(boardP1);
            }
            if (shot.gameEndCheck(boardP1) || shot.gameEndCheck(boardP2)) {
                break;
            }
            currentPlayer = playerTurnChange();
            InputHandling.promptEnterKey();
            playerTurn();
        }
        System.out.printf("\nPlayer %d, you sank the last ship. You won, congratulations!", currentPlayer);
    }
}
