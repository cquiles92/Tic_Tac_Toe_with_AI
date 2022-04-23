package tictactoe;

public abstract class Player {
    private final Symbol playerSymbol;
    protected String entryText;

    Player(Symbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public abstract void playerMove(Symbol[][] currentBoard);

    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }

    public String getEntryText() {
        return entryText;
    }
}
