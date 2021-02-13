package battleship;

import java.util.Scanner;

import static battleship.Boat.*;


public class InputHandling {
    Scanner scanner = new Scanner(System.in);

    Boat boat;

    public InputHandling(Boat boat) {

        this.boat = boat;
    }



    public String coordsBoatRequest(Boat boat) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n", boat.boatName, boat.boatSize);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public int[] coordsInput(String userInput) {
        String[] userString = userInput.split(" ");
        String point1 = userString[0];
        String point2 = userString[1];
        int yPoint1 = 0;
        int xPoint1 = 0;
        int yPoint2 = 0;
        int xPoint2 = 0;



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
        int[] coordsInput = {yPoint1, xPoint1, yPoint2, xPoint2};
        return coordsInput;
    }

    public int boatCoordsCheck (String userInput) {
        int errorCode = 0;
        int[] userCoords = coordsInput(userInput);
        int yPoint1 = userCoords[0];
        int xPoint1 = userCoords[1];
        int yPoint2 = userCoords[2];
        int xPoint2 = userCoords[3];


        if (yPoint1 < 1 || yPoint1 > 10) {
            return errorCode = 101;
        } else if (xPoint1 < 1 || xPoint1 > 10) {
            return errorCode = 102;
        } else if (yPoint2 < 1 || yPoint2 > 10) {
            return errorCode = 103;
        } else if (xPoint2 < 1 || xPoint2 > 10) {
            return errorCode = 104;
        } else if (userInput.length() < 5 || userInput.length() > 7) {
            return errorCode = 105;
        }

        if (yPoint1 != yPoint2 && xPoint1 != xPoint2) {
            return errorCode = 201;
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
                    return errorCode = 202;
                } else {
                    for (int boatSegment = Math.min(xPoint1, xPoint2) - 1; boatSegment < boat.boatSize + 1 + Math.min(xPoint1, xPoint2); boatSegment++) {
                        System.out.println(boatSegment);
                        if (board[yPoint1][boatSegment].equals("O ") ||
                                board[yPoint1 + 1][boatSegment].equals("O ") ||
                                board[yPoint1 - 1][boatSegment].equals("O ")) {
                            return errorCode = 203;
                        }

                    }
                }
            } else if (xPoint1 == xPoint2) {
                if (Math.max(yPoint1, yPoint2) - Math.min(yPoint1, yPoint2) + 1 != boat.boatSize) {
                    return errorCode = 202;
                } else {
                    for (int boatSegment = Math.min(yPoint1, yPoint2) - 1; boatSegment < boat.boatSize + 1 + Math.min(yPoint1, yPoint2); boatSegment++) {
                        System.out.println(boatSegment);
                        if (board[boatSegment][xPoint1].equals("O ") ||
                                board[boatSegment][xPoint1 + 1].equals("O ") ||
                                board[boatSegment][xPoint1 - 1].equals("O ")) {
                            return errorCode = 203;
                        }

                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
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
                board[yPoint1][cell] = "O ";
            }
        }
        if (xPoint1 == xPoint2) {
            for (int cell = Math.min(yPoint1, yPoint2); cell <= Math.max(yPoint1, yPoint2); cell++) {
                board[cell][xPoint1] = "O ";
            }
        }
    }

    public String shotCoordRequest() {
        System.out.println("Take a shot!");
        String userInput = scanner.nextLine();
        return userInput;
    }

    public int[] shotCoordInput(String userInput) {
        int yPoint = 0;
        int xPoint = 0;

        yPoint = userInput.charAt(0) - 64;
        if (userInput.length() > 2) {
            xPoint = Character.getNumericValue(userInput.charAt(1)) * 10 + Character.getNumericValue(userInput.charAt(2));
        } else {
            xPoint = Character.getNumericValue(userInput.charAt(1));
        }
        int[] shotCoord = {yPoint, xPoint};
        return shotCoord;
    }

    public int shotCoordsCheck (String userInput) {
        int errorCode = 0;
        int[] shotCoord = shotCoordInput(userInput);
        int yPoint = shotCoord[0];
        int xPoint = shotCoord[1];


        if (yPoint < 1 || yPoint > 10) {
            return errorCode = 301;
        } else if (xPoint < 1 || xPoint > 10) {
            return errorCode = 302;
        } else if (userInput.length() < 2 || userInput.length() > 3) {
            return errorCode = 303;
        }

        return errorCode;
    }



    public void shoot (String userInput, Board board, Board fogOfWar) {
        int[] shotCoord = shotCoordInput(userInput);
        int yPoint = shotCoord[0];
        int xPoint = shotCoord[1];
        if (board[yPoint][xPoint].equals("O ")) {
            board[yPoint][xPoint] = "X ";
            fogOfWar[yPoint][xPoint] = "X ";
            Board.boardPrinter(fogOfWar);
            System.out.println("You hit a ship!");

        } else if (board[yPoint][xPoint].equals("~ ")) {
            board[yPoint][xPoint] = "M ";
            fogOfWar[yPoint][xPoint] = "M ";
            Board.boardPrinter(fogOfWar);
            System.out.println("You missed!");
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
            case 201: System.out.printf("Error! Wrong ship location, must be placed vertically or horizontally on the board\n");
                break;
            case 202: System.out.printf("Error! Wrong length of the %s! Try again:\n", boat.boatName);
                break;
            case 203: System.out.printf("Error! You placed it too close to another one. Try again:\n", boat.boatName);
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
