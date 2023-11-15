package edu.cmu.cs.cs214.rec09.plugin;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;

public class TicTacToePlugin implements GamePlugin<String> {

    private static final String GAME_NAME = "TicTacToe";

    private static final String X_MOVE = "It is X's turn.";
    private static final String O_MOVE = "It is O's turn.";
    private static final String O_WIN = "Player O won the game.";
    private static final String X_WIN = "Player X won the game.";
    private static final String TIE = "It is a tie.";

    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;

    private GameFramework framework;

    private TicTacToe game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework f) {
        this.framework = f;
    }

    @Override
    public void onNewGame() {
        this.game = new TicTacToe();
        framework.setFooterText(X_MOVE);
    }

    @Override
    public void onNewMove() {
        if (this.currentPlayer().equals("X")) {
            framework.setFooterText(X_MOVE);
        } else {
            framework.setFooterText(O_MOVE);
        }
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        framework.setSquare(x, y, this.currentPlayer());
        game.play(x, y);
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        if (game.winner() == null) {
            return TIE;
        } else if (game.winner() == TicTacToe.Player.X) {
            return X_WIN;
        } else {
            return O_WIN;
        }
    }

    @Override
    public void onGameClosed() {
    }

    @Override
    public String currentPlayer() {
        return game.currentPlayer() == TicTacToe.Player.X ? "X" : "O";
    }

}
