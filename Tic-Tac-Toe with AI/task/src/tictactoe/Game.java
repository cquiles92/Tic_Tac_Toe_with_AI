package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int GRID_ARRAY_SIZE = 3;
    private Symbol[][] gameStateAsGrid;
    private final Player player1;
    private final Player player2;


    Game(String[] parameters) {
        player1 = createPlayer(parameters[1], Symbol.X);
        player2 = createPlayer(parameters[2], Symbol.O);
    }

    public void start() {
        State currentState = initializeGame();
        Player currentPlayer = player1;

        while (currentState == State.UNDETERMINED) {
            System.out.println(currentPlayer.getEntryText());
            currentPlayer.playerMove(gameStateAsGrid);
            currentState = getCurrentState(gameStateAsGrid);
            drawGrid();
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }
        printState(currentState);
    }

    private State initializeGame() {
        gameStateAsGrid = createGameArray();
        drawGrid();
        return getCurrentState(gameStateAsGrid);
    }

    private Player createPlayer(String selection, Symbol playerSymbol) {
        Player_Choices selectedChoice = Player_Choices.valueOf(selection);
        Player player;
        switch (selectedChoice) {
            case EASY:
                player = new EasyCPU_Player(playerSymbol);
                break;
            case MEDIUM:
                player = new MediumCPU_Player(playerSymbol);
                break;
            case HARD:
                player = new HardCPU_Player(playerSymbol);
                break;
            case USER:
            default:
                player = new HumanPlayer(playerSymbol);
                break;
        }
        return player;
    }

    //methods to draw grid
    private void drawGrid() {
        System.out.println("---------");
        for (int i = 0; i < GRID_ARRAY_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < GRID_ARRAY_SIZE; j++) {
                System.out.print(gameStateAsGrid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private Symbol[][] createGameArray() {
        Symbol[][] gameState = new Symbol[GRID_ARRAY_SIZE][GRID_ARRAY_SIZE];
        for (int i = 0; i < GRID_ARRAY_SIZE; i++) {
            for (int j = 0; j < GRID_ARRAY_SIZE; j++) {
                gameState[i][j] = Symbol.EMPTY_SPACE;
            }
        }
        return gameState;
    }

    private void printState(State currentState) {
        switch (currentState) {
            case X_WINS:
                System.out.println("X wins");
                break;
            case O_WINS:
                System.out.println("O wins");
                break;
            case DRAW:
                System.out.println("Draw");
                break;
            case UNDETERMINED:
                System.out.println("Game not finished");
        }
    }

    public static State getCurrentState(Symbol[][] currentBoard) {
        boolean playerXWinner = winHorizontally(Symbol.X, currentBoard) || winVertically(Symbol.X, currentBoard) || winDiagonally(Symbol.X, currentBoard);
        boolean playerOWinner = winHorizontally(Symbol.O, currentBoard) || winVertically(Symbol.O, currentBoard) || winDiagonally(Symbol.O, currentBoard);
        boolean openSpace = isOpenSpaceAvailable(currentBoard);

        if (playerXWinner) {
            return State.X_WINS;
        } else if (playerOWinner) {
            return State.O_WINS;
        } else if (openSpace) {
            return State.UNDETERMINED;
        } else {
            return State.DRAW;
        }
    }

    private static boolean winHorizontally(Symbol currentSymbol, Symbol[][] board) {
        for (int row = 0; row < GRID_ARRAY_SIZE; row++) {
            for (int column = 0; true; column++) {
                if (board[row][column] != currentSymbol) {
                    break;
                }
                if (column == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean winVertically(Symbol currentSymbol, Symbol[][] board) {
        for (int column = 0; column < GRID_ARRAY_SIZE; column++) {
            for (int row = 0; true; row++) {
                if (board[row][column] != currentSymbol) {
                    break;
                }
                if (row == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean winDiagonally(Symbol currentSymbol, Symbol[][] board) {
        int center = GRID_ARRAY_SIZE / 2;

        if (board[center][center] == currentSymbol) {
            //Top Left Diagonal
            if (board[center - 1][center - 1] == currentSymbol &&
                    board[center + 1][center + 1] == currentSymbol) {
                return true;
            }
            //Bottom Left Diagonal
            else return board[center + 1][center - 1] == currentSymbol &&
                    board[center - 1][center + 1] == currentSymbol;
        } else {
            return false;
        }
    }

    public static boolean isOpenSpaceAvailable(Symbol[][] board) {
        return getEmptySpaces(board).size() > 0;
    }

    public static boolean isOccupied(int row, int column, Symbol[][] board) {
        return board[row][column] != Symbol.EMPTY_SPACE;
    }


    public static List<int[]> getEmptySpaces(Symbol[][] board) {
        List<int[]> emptySpaces = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!isOccupied(i, j, board)) {
                    int[] emptySpace = new int[2];
                    emptySpace[0] = i;
                    emptySpace[1] = j;
                    emptySpaces.add(emptySpace);
                }
            }
        }
        return emptySpaces;
    }
}
