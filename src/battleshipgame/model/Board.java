package battleshipgame.model;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static battleshipgame.model.BattleshipFactory.getTotalShipsCount;

/**
 * Created by yanair on 05.05.17.
 */
public class Board extends Parent {

    private static final int ROW = 10;
    private static final int COL = 10;

    private int shipsCount = getTotalShipsCount();
    private boolean playerBoard;
    private VBox rows = new VBox();
    private List<Field> highlightedFields = new ArrayList<>();

    public Board(EventHandler<? super MouseEvent> clickHandler, boolean playerBoard) {
        this.playerBoard = playerBoard;
        for (int row = 0; row < ROW; row++) {
            HBox currentRow = new HBox();
            for (int col = 0; col < COL; col++) {
                Field field = new Field(row, col);
                field.setOnMouseClicked(clickHandler);
                currentRow.getChildren().add(field);
            }
            rows.getChildren().add(currentRow);
        }
        getChildren().add(rows);
    }

    public Board(EventHandler<? super MouseEvent> clickHandler, EventHandler<? super MouseEvent> mouseEnteredHandler,
                 EventHandler<? super MouseEvent> mouseExitedHandler, boolean playerBoard) {
        this.playerBoard = playerBoard;
        for (int row = 0; row < ROW; row++) {
            HBox currentRow = new HBox();
            for (int col = 0; col < COL; col++) {
                Field field = new Field(row, col);
                field.setOnMouseClicked(clickHandler);
                field.setOnMouseEntered(mouseEnteredHandler);
                field.setOnMouseExited(mouseExitedHandler);
                currentRow.getChildren().add(field);
            }
            rows.getChildren().add(currentRow);
        }
        getChildren().add(rows);
    }

    public boolean setShip(Battleship ship, Field startField) {
        int shipSize = ship.getShipSize();

        if (!canSetShip(ship, startField)) {
            return false;
        }

        for (int i = 0; i < shipSize; i++) {
            if (ship.hasHorizontalDirection()) {
                Field occupiedField = getField(startField.getRow(), startField.getColumn() + i);
                occupiedField.setShip(ship);
            } else {
                Field occupiedField = getField(startField.getRow() + i, startField.getColumn());
                occupiedField.setShip(ship);
            }
        }
        return true;
    }

    private boolean canSetShip(Battleship ship, Field startField) { // todo add logic for adjacent fields
        int shipSize = ship.getShipSize();

        // check that there's enough space for ship
        if (ship.hasHorizontalDirection()) {
            if (startField.getColumn() + shipSize > COL) {
                return false;
            }
        } else {
            if (startField.getRow() + shipSize > COL) {
                return false;
            }
        }

        // check that fields are not occupied
        for (int i = 0; i < shipSize; i++) {
            if (ship.hasHorizontalDirection()) {
                Field currentField = getField(startField.getRow(), startField.getColumn() + i);
                if (currentField.isOccupied()) {
                    return false;
                }
            } else {
                Field currentField = getField(startField.getRow() + i, startField.getColumn());
                if (currentField.isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void highlightShipPlacement(Battleship ship, Field startField) {
        if (ship == null || !canSetShip(ship, startField)) {
            return;
        }

        int shipSize = ship.getShipSize();

        for (int i = 0; i < shipSize; i++) {
            if (ship.hasHorizontalDirection()) {
                Field occupiedField = getField(startField.getRow(), startField.getColumn() + i);
                occupiedField.highlight();
                highlightedFields.add(occupiedField);
            } else {
                Field occupiedField = getField(startField.getRow() + i, startField.getColumn());
                occupiedField.highlight();
                highlightedFields.add(occupiedField);
            }
        }
    }

    public void removeShipPlacementHighlight() {
        for (Field f : highlightedFields) {
            f.removeHighlight();
        }
    }

    public void placeShipOnBoardRandomly(Battleship battleship) {
        boolean shipPlaced;
        Random random = new Random();

        do {
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            Field start = new Field(row, col);

            if (random.nextBoolean()) {
                battleship.rotate();
            }

            shipPlaced = setShip(battleship, start);
        } while (!shipPlaced);
    }

    public Field getField(int row, int col) {
        HBox hBox = (HBox) rows.getChildren().get(row);
        return (Field) hBox.getChildren().get(col);
    }

    public boolean receiveShot(Field field) {
        if (!playerBoard) {
            sleep();
        }
        return field.shoot();
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getShipsCount() {
        return shipsCount;
    }

    public class Field extends Rectangle {

        private static final int FIELD_SIZE = 30;

        private int row;
        private int column;

        private boolean wasShot = false;
        private boolean isOccupied = false;

        private Battleship battleship;

        private Field(int row, int column) {
            super(FIELD_SIZE, FIELD_SIZE);
            this.row = row;
            this.column = column;
            setFill(Color.WHITE);
            setStroke(Color.DARKGRAY);
        }

        private int getRow() {
            return row;
        }

        private int getColumn() {
            return column;
        }

        public boolean wasShot() {
            return wasShot;
        }

        private boolean isOccupied() {
            return isOccupied;
        }

        private void highlight() {
            if (!isOccupied()) {
                setFill(Color.LIGHTBLUE);
            }
        }

        private void removeHighlight() {
            if (!isOccupied()) {
                setFill(Color.WHITE);
            }
        }

        private void setShip(Battleship battleship) {
            this.battleship = battleship;
            isOccupied = true;
            if (playerBoard) {
                setFill(Color.ROYALBLUE);
            }
        }

        private boolean shoot() {
            wasShot = true;

            if (battleship != null) {
                battleship.receiveShot();
                setFill(Color.ORANGERED);

                if (!battleship.isAlive()) {
                    shipsCount--;
                }
                return true;
            }

            setFill(Color.GRAY);
            return false;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + column + ")";
        }
    }

}
