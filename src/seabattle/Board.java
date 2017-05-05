package seabattle;

/**
 * Created by yanair on 05.05.17.
 */
public class Board {

    enum Direction {
        VERTICAL, HORIZONTAL
    }

    private static final int ROW = 10;
    private static final int COL = 10;
    private Field[][] fields;

    public Board() {
        fields = new Field[ROW][COL];

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                fields[row][col] = new Field(row, col);
            }
        }
    }

    public void setShip(Battleship ship, Field startField, Direction direction) {
        int shipSize = ship.getShipSize();

        for (int i = 0; i < shipSize; i++) {
            if (direction == Direction.VERTICAL) {
                Field field = getField(startField.getRow() + i, startField.getColumn());
                field.setShip(ship);
                ship.addField(field);
            } else {
                Field field = getField(startField.getRow(), startField.getColumn() + i);
                field.setShip(ship);
                ship.addField(field);
            }
        }
    }

    public void removeShip(Battleship ship, Field startField, Direction direction) {
        int shipSize = ship.getShipSize();

        for (int i = 0; i < shipSize; i++) {
            if (direction == Direction.VERTICAL) {
                Field field = getField(startField.getRow() + i, startField.getColumn());
                field.removeShip();
                ship.removeField(field);
            } else {
                Field field = getField(startField.getRow(), startField.getColumn() + i);
                field.removeShip();
                ship.removeField(field);
            }
        }
    }

    private Field getField(int row, int col) {
        return fields[row][col];
    }
}
