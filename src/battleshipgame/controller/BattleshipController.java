package battleshipgame.controller;

import battleshipgame.model.Battleship;
import battleshipgame.model.BattleshipFactory;
import battleshipgame.model.Board;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by yanair on 06.05.17.
 */
public class BattleshipController implements Initializable {

    public VBox enemyBoardArea;
    public VBox playerBoardArea;

    private Board enemyBoard;
    private Board playerBoard;

    private boolean gameRunning;
    private boolean enemyTurn;

    private Battleship currentPlayerShip;

    @FXML
    private Label gameResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNewGame();
    }

    @FXML
    private void startNewGame() {
        enemyBoardArea.getChildren().remove(enemyBoard);
        playerBoardArea.getChildren().remove(playerBoard);
        initializeNewGame();
    }

    @FXML
    private void rotateShip() {
        if (!gameRunning) {
            currentPlayerShip.rotate();
        }
    }

    @FXML
    private void placeShipsRandomly() {
        if (!gameRunning) {
            placeShipsRandomly(playerBoard);
            startGame();
        }
    }

    @FXML
    private void exitGame() {
        System.exit(0);
    }

    private void initializeNewGame() {
        BattleshipFactory battleshipFactory = new BattleshipFactory();
        gameResult.setText("");
        gameRunning = false;
        enemyTurn = false;
        currentPlayerShip = battleshipFactory.getNextShip();
        playerBoard = new Board(playerBoardClickHandler(battleshipFactory), playerBoardMouseEnteredHandler(),
                                playerBoardMouseExitedHandler(), true);
        enemyBoard = new Board(enemyBoardClickHandler(), false);
        enemyBoardArea.getChildren().add(enemyBoard);
        playerBoardArea.getChildren().add(playerBoard);
    }

    private EventHandler<MouseEvent> playerBoardClickHandler(BattleshipFactory battleshipFactory) {
        return event -> {
            if (gameRunning) {
                return;
            }

            Board.Field currentField = (Board.Field) event.getSource();

            if (event.getButton() == MouseButton.SECONDARY) {
                currentPlayerShip.rotate();
                return;
            }
            if (event.getButton() == MouseButton.PRIMARY) {
                boolean shipPlacementSuccessful = playerBoard.setShip(currentPlayerShip, currentField);
                if (shipPlacementSuccessful) {
                    currentPlayerShip = battleshipFactory.getNextShip();
                }
            }
            if (currentPlayerShip == null) {
                startGame();
            }
        };
    }

    private EventHandler<MouseEvent> playerBoardMouseEnteredHandler() {
        return event -> {
            if (gameRunning) {
                return;
            }
            Board.Field currentField = (Board.Field) event.getSource();
            playerBoard.highlightShipPlacement(currentPlayerShip, currentField);
        };
    }

    private EventHandler<MouseEvent> playerBoardMouseExitedHandler() {
        return event -> {
            if (gameRunning) {
                return;
            }
            playerBoard.removeShipPlacementHighlight();
        };
    }

    private EventHandler<MouseEvent> enemyBoardClickHandler() {
        return event -> {
            if (!gameRunning) {
                return;
            }

            Board.Field currentField = (Board.Field) event.getSource();

            if (currentField.wasShot()) {
                return;
            }

            boolean wasHit = enemyBoard.receiveShot(currentField);
            enemyTurn = !wasHit;

            if (enemyBoard.getShipsCount() == 0) {
                gameRunning = false;
                gameResult.setText("You won the game!");
            }

            if (enemyTurn && gameRunning) {
                enemyMove();
            }
        };
    }

    private void enemyMove() {
        Random random = new Random();
        Board.Field field;

        do {
            int row = random.nextInt(10);
            int col = random.nextInt(10);

            field = playerBoard.getField(row, col);
            enemyTurn = playerBoard.receiveShot(field);

            if (playerBoard.getShipsCount() == 0) {
                gameRunning = false;
                gameResult.setText("You lost :(");
            }

        } while (!field.wasShot() || enemyTurn);
    }

    private void startGame() {
        placeShipsRandomly(enemyBoard);
        gameRunning = true;
    }

    private void placeShipsRandomly(Board board) {
        BattleshipFactory battleshipFactory = new BattleshipFactory();

        for (Battleship ship = battleshipFactory.getNextShip(); ship != null; ship = battleshipFactory.getNextShip()) {
            board.placeShipOnBoardRandomly(ship);
        }
    }
}
