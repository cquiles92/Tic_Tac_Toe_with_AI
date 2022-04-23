package tictactoe;

import java.util.List;

public class HardCPU_Player extends Player {
    public HardCPU_Player(Symbol playerSymbol) {
        super(playerSymbol);
        String cpuLevel = "hard";
        entryText = String.format("Making move level \"%s\"", cpuLevel);
    }

    @Override
    public void playerMove(Symbol[][] currentBoard) {
        List<int[]> emptySpaces = Game.getEmptySpaces(currentBoard);
        int bestScore = Integer.MIN_VALUE;
        int[] defaultMove = emptySpaces.get(0);
        int row = defaultMove[0];
        int column = defaultMove[1];

        //option to prefer the middle space if available
        //middle space = 4 win conditions leaving only border available to win 3 in a row (Perimeter or 4 lines)
        if (!Game.isOccupied(1, 1, currentBoard)) {
            row = 1;
            column = 1;
        }
        //determine and execute the best move with the minimax algorithm
        else {
            for (int[] emptySpace : emptySpaces) {
                currentBoard[emptySpace[0]][emptySpace[1]] = getPlayerSymbol();
                int score = minimax(currentBoard, 0, false);
                if (score > bestScore) {
                    bestScore = score;
                    row = emptySpace[0];
                    column = emptySpace[1];
                }
                currentBoard[emptySpace[0]][emptySpace[1]] = Symbol.EMPTY_SPACE;
            }
        }
        currentBoard[row][column] = getPlayerSymbol();
    }

    //helper function to assign a score to a game state.
    //+100 for a win
    //0 for a draw
    //-100 for a loss
    //each turn or depth subtracts from score to create optimal route
    private int calculateScore(State currentState) {
        int score;
        switch (currentState) {
            case O_WINS:
                score = this.getPlayerSymbol() == Symbol.O ? 100 : -100;
                break;
            case X_WINS:
                score = this.getPlayerSymbol() == Symbol.X ? 100 : -100;
                break;
            case DRAW:
            case UNDETERMINED:
            default:
                score = 0;
        }
        return score;
    }

    private int minimax(Symbol[][] currentBoard, int depth, boolean isMaximizing) {
        //test if the game is over
        State currentState = Game.getCurrentState(currentBoard);
        if (currentState != State.UNDETERMINED) {
            int turns = isMaximizing ? -depth : depth;
            return calculateScore(currentState) - turns;
        }

        //determine the current player and how many available spaces left on the board
        Symbol turnPlayer = isMaximizing ? getPlayerSymbol() : (getPlayerSymbol() == Symbol.X ? Symbol.O : Symbol.X);
        List<int[]> availableMoves = Game.getEmptySpaces(currentBoard);

        int bestScore;

        //if maximizing (the computer turn that wants to win)
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int[] availableMove : availableMoves) {
                currentBoard[availableMove[0]][availableMove[1]] = turnPlayer;
                int score = minimax(currentBoard, depth + 1, false);
                currentBoard[availableMove[0]][availableMove[1]] = Symbol.EMPTY_SPACE;
                bestScore = Math.max(score, bestScore);
            }
        }
        //if minimizing (the computer assuming the optimal play and score for the opponent)
        else {
            bestScore = Integer.MAX_VALUE;
            for (int[] availableMove : availableMoves) {
                currentBoard[availableMove[0]][availableMove[1]] = turnPlayer;
                int score = minimax(currentBoard, depth + 1, true);
                currentBoard[availableMove[0]][availableMove[1]] = Symbol.EMPTY_SPACE;
                bestScore = Math.min(score, bestScore);
            }
        }
        return bestScore;
    }

    @Override
    public String getEntryText() {
        return super.getEntryText();
    }
}
