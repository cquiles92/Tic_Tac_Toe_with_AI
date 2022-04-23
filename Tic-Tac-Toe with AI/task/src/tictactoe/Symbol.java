package tictactoe;

public enum Symbol {
    X, O, EMPTY_SPACE {
        @Override
        public String toString() {
            return " ";
        }
    }
}
