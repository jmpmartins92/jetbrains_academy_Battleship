package battleship;


import java.util.Scanner;


import static battleship.Main.*;


public class InputHandling {
    Scanner scanner = new Scanner(System.in);

    public static void playerInitialization(int currentPlayer) {
        System.out.printf("\nPlayer %d, place your ships on the game field\n\n", currentPlayer);
    }



    public static int playerTurnChange() {
        if (currentPlayer == 1) {
            currentPlayer = 2;

        } else if(currentPlayer == 2) {
            currentPlayer = 1;
        }
        System.out.printf("\nPlayer %d, it's your turn:\n", currentPlayer);
        return currentPlayer;
    }

    public static void promptEnterKey() {
        System.out.printf("\nPass the move to Player %d and press Enter:\n...\n", currentPlayer);
        Scanner enter = new Scanner(System.in);
        enter.nextLine();
    }






    public String coordsBoatRequest(Boat boat) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n", boat.boatName, boat.boatSize);
        return scanner.nextLine();
    }

    public int[] coordsInput(String userInput) {
        String[] userString = userInput.split(" ");
        String point1 = userString[0];
        String point2 = userString[1];
        int yPoint1;
        int xPoint1;
        int yPoint2;
        int xPoint2;



        yPoint1 = point1.charAt(0) - 64;
        if (point1.length() > 2) {
            xPoint1 = Character.getNumericValue(point1.charAt(1)) * 10 + Character.getNumericValue(point1.charAt(2));
        } else {
            xPoint1 = Character.getNumericValue(point1.charAt(1));
        }
        yPoint2 = point2.charAt(0) - 64;
        if (point2.length() > 2) {
            xPoint2 = Character.getNumericValue(point2.charAt(1)) * 10 + Character.getNumericValue(point2.charAt(2));
        } else {
            xPoint2 = Character.getNumericValue(point2.charAt(1));
        }
        return new int[]{yPoint1, xPoint1, yPoint2, xPoint2};
    }

    public int boatCoordsCheck (String userInput) {
        int errorCode = 0;
        int[] userCoords = coordsInput(userInput);
        int yPoint1 = userCoords[0];
        int xPoint1 = userCoords[1];
        int yPoint2 = userCoords[2];
        int xPoint2 = userCoords[3];


        if (yPoint1 < 1 || yPoint1 > 10) {
            return 101;
        } else if (xPoint1 < 1 || xPoint1 > 10) {
            return 102;
        } else if (yPoint2 < 1 || yPoint2 > 10) {
            return 103;
        } else if (xPoint2 < 1 || xPoint2 > 10) {
            return 104;
        } else if (userInput.length() < 5 || userInput.length() > 7) {
            return 105;
        }

        if (yPoint1 != yPoint2 && xPoint1 != xPoint2) {
            return 201;
        }
        return errorCode;
    }

    public int boatPositionCheck(String userInput, Boat boat, Board board) {
        int errorCode = 0;
        int[] userCoords = coordsInput(userInput);
        int yPoint1 = userCoords[0];
        int xPoint1 = userCoords[1];
        int yPoint2 = userCoords[2];
        int xPoint2 = userCoords[3];

        try {
            if (yPoint1 == yPoint2) {
                if (Math.max(xPoint1, xPoint2) - Math.min(xPoint1, xPoint2) + 1 != boat.boatSize) {
                    return 202;
                } else {
                    for (int boatSegment = Math.min(xPoint1, xPoint2) - 1; boatSegment < boat.boatSize + 1 + Math.min(xPoint1, xPoint2); boatSegment++) {
                        if (board.pointIsBoat(yPoint1, boatSegment) ||
                                board.pointIsBoat(yPoint1 + 1, boatSegment) ||
                                board.pointIsBoat(yPoint1 - 1, boatSegment)) {
                            return 203;
                        }

                    }
                }
            } else if (xPoint1 == xPoint2) {
                if (Math.max(yPoint1, yPoint2) - Math.min(yPoint1, yPoint2) + 1 != boat.boatSize) {
                    return 202;
                } else {
                    for (int boatSegment = Math.min(yPoint1, yPoint2) - 1; boatSegment < boat.boatSize + 1 + Math.min(yPoint1, yPoint2); boatSegment++) {
                        if (board.pointIsBoat(boatSegment, xPoint1) ||
                                board.pointIsBoat(boatSegment, xPoint1 + 1) ||
                                board.pointIsBoat(boatSegment, xPoint1 - 1)) {
                            return 203;
                        }
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return errorCode;
    }



    public void boatPlacement(String userInput, Board board) {

        int[] userCoords = coordsInput(userInput);
        int yPoint1 = userCoords[0];
        int xPoint1 = userCoords[1];
        int yPoint2 = userCoords[2];
        int xPoint2 = userCoords[3];

        if (yPoint1 == yPoint2) {
            for (int cell = Math.min(xPoint1, xPoint2); cell <= Math.max(xPoint1, xPoint2); cell++) {
                board.setBoat(yPoint1, cell);
            }
        }
        if (xPoint1 == xPoint2) {
            for (int cell = Math.min(yPoint1, yPoint2); cell <= Math.max(yPoint1, yPoint2); cell++) {
                board.setBoat(cell, xPoint1);
            }
        }
    }

    public String shotCoordRequest() {
        System.out.println("\nTake a shot!");
        return scanner.nextLine();
    }

    public int[] shotCoordInput(String userInput) {
        int yPoint;
        int xPoint;

        yPoint = userInput.charAt(0) - 64;
        if (userInput.length() > 2) {
            xPoint = Character.getNumericValue(userInput.charAt(1)) * 10 + Character.getNumericValue(userInput.charAt(2));
        } else {
            xPoint = Character.getNumericValue(userInput.charAt(1));
        }
        return new int[]{yPoint, xPoint};
    }

    public int shotCoordsCheck (int[] shotCoordInput) {
        int errorCode = 0;
        int yPoint = shotCoordInput[0];
        int xPoint = shotCoordInput[1];


        if (yPoint < 1 || yPoint > 10) {
            errorCode = 301;
        } else if (xPoint < 1 || xPoint > 10) {
            errorCode = 302;
        } else if (shotCoordInput.length < 2 || shotCoordInput.length > 3) {
            errorCode = 303;
        }

        return errorCode;
    }



    public boolean gameEndCheck (Board board) {
        boolean gameEndCheck = true;
        for (int row = 1; row < 11; row++) {
            for (int col = 1; col < 11; col++) {
                if (board.pointIsBoat(row, col)) {
                    gameEndCheck = false;
                }
            }
        }
        return gameEndCheck;
    }



    public void shoot (String userInput, Board board, Board fogOfWar) {
        int[] shotCoord = shotCoordInput(userInput);
        int yPoint = shotCoord[0];
        int xPoint = shotCoord[1];
        switch (board.pointAt(yPoint, xPoint)) {
            case "O ":
                board.setHit(yPoint, xPoint);
                fogOfWar.setHit(yPoint, xPoint);
                fogOfWar.boardPrinter();
                System.out.println("You hit a ship!");
                try {
                    if (board.pointAt(yPoint + 1, xPoint) != "O " &&
                            board.pointAt(yPoint - 1, xPoint) != "O " &&
                            board.pointAt(yPoint, xPoint + 1) != "O "&&
                            board.pointAt(yPoint, xPoint - 1) != "O ") {
                        System.out.println("You sank a ship!");
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                break;
            case "~ ":
                board.setMiss(yPoint, xPoint);
                fogOfWar.setMiss(yPoint, xPoint);
                fogOfWar.boardPrinter();
                System.out.println("You missed!");
                break;
            case "X ":
            case "M ":
                fogOfWar.boardPrinter();
                System.out.println("You already chose that coordinate. Choose another coordinate.");
                break;
        }

    }


    public void errorCalls(int errorCode, Boat boat) {
        switch (errorCode) {
            case 101: System.out.println("Error! Letter of first coordinate out of bounds of board.");
                break;
            case 102:
                System.out.println("Error! Number of first coordinate out of bounds of board.");
                break;
            case 103: System.out.println("Error! Letter of second coordinate out of bounds of board.");
                break;
            case 104:
                System.out.println("Error! Number of second coordinate out of bounds of board.");
                break;
            case 105: System.out.println("Error! Incorrect coordinate format. Should be of type X1 Y2");
                break;
            case 201:
                System.out.println("Error! Wrong ship location, must be placed vertically or horizontally on the board");
                break;
            case 202: System.out.printf("Error! Wrong length of the %s! Try again:\n", boat.boatName);
                break;
            case 203: System.out.println("Error! You placed it too close to another one. Try again:");
                break;
            case 301: System.out.println("Error! Letter of shot coordinate out of bounds of board.");
                break;
            case 302:
                System.out.println("Error! Number of shot coordinate out of bounds of board.");
                break;
            case 303: System.out.println("Error! Incorrect coordinate format. Should be of type X1");
                break;
            default:
                break;
        }
    }
}
