import controller.GameController;
import exception.BotCountException;
import exception.DuplicateSymbolAssignException;
import exception.WrongBoardSizeException;
import model.Game;
import model.GameState;
import model.Player;
import strategy.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DuplicateSymbolAssignException,
            WrongBoardSizeException, BotCountException {
        GameController gameController = new GameController();
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        List<WinningStrategy> winningStrategies = new ArrayList<>();

        Game game = gameController.startGame(dimension, players, winningStrategies);
        System.out.println("Game started");
        while (gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
            gameController.displayBoard(game);
            gameController.makeMove(game);

            if(gameController.checkState(game).equals(GameState.SUCCESS)) {
                System.out.println("Some player is the winner");
            } else if (gameController.checkState(game).equals(GameState.DRAW)) {
                System.out.println("Game is drawn");
            }

        }
    }
}