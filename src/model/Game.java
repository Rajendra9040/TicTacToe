package model;

import exception.BotCountException;
import exception.DuplicateSymbolAssignException;
import exception.WrongBoardSizeException;
import strategy.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextMovePlayerIndex;

    public Game(Builder builder) {
        this.board = new Board(builder.getBoardSize());
        this.players = builder.getPlayers();
        this.winningStrategies = builder.getWinningStrategies();
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.nextModePlayerIndex = 0;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextModePlayerIndex() {
        return nextModePlayerIndex;
    }

    public void setNextModePlayerIndex(int nextModePlayerIndex) {
        this.nextModePlayerIndex = nextModePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    private int nextModePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public static class Builder {
        private int boardSize;
        private List<Player> players;
        private  List<WinningStrategy> winningStrategies;

        public int getBoardSize() {
            return boardSize;
        }

        public Builder setBoardSize(int boardSize) {
            this.boardSize = boardSize;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public void validate(Builder builder) throws DuplicateSymbolAssignException, WrongBoardSizeException, BotCountException {
            validatePlayers(builder);
            validateBoardSize(builder);

        }
        public void validateBoardSize(Builder builder) throws WrongBoardSizeException {
            if (builder.getBoardSize()<1) {
                throw new WrongBoardSizeException();
            }
        }

        public void validatePlayers(Builder builder) throws DuplicateSymbolAssignException, BotCountException {
            HashMap<Character, Integer> uniquePlayers = new HashMap<>();
            int botCount = 0;
            for (Player player: builder.getPlayers()) {
                int count = uniquePlayers.getOrDefault(player.getSymbol().getSymbol(), 0);

                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount += 1;
                }

                if (botCount>1) {
                    throw new BotCountException();
                }
                if (count==1) {
                    throw new DuplicateSymbolAssignException();
                }
                uniquePlayers.put(player.getSymbol().getSymbol(), 1);
            }
        }

        public Game build() throws DuplicateSymbolAssignException, WrongBoardSizeException, BotCountException {
            validate(this);
            Game game = new Game(this);
            return game;
        }
    }
}
