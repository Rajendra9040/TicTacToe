package controller;

import exception.BotCountException;
import exception.DuplicateSymbolAssignException;
import exception.WrongBoardSizeException;
import model.Game;
import model.GameState;
import model.Player;
import strategy.winningStrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies)
            throws DuplicateSymbolAssignException, WrongBoardSizeException, BotCountException {
        Game game = Game.getBuilder()
                        .setBoardSize(dimension)
                        .setPlayers(players)
                        .setWinningStrategies(winningStrategies).build();
        return game;

    }
    public void makeMove(Game game) {

    }

    public void displayBoard(Game game) {

    }

    public Player getWinner(Game game) {
        return null;
    }

    public GameState checkState(Game game) {
        return null;
    }

    public void undo() {

    }

}
