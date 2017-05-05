package seabattle;

import java.util.Objects;

/**
 * Created by yanair on 05.05.17.
 */
public class Field {

    private int row;
    private int column;

    private boolean isShot = false;
    private boolean isOccupied = false;

    private Battleship battleship;

    public Field(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isShot() {
        return isShot;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setShip(Battleship battleship) {
        this.battleship = battleship;
        isOccupied = true;
    }

    public void removeShip() {
        this.battleship = null;
        isOccupied = false;
    }

    public void shoot() {
        isShot = true;
        if (battleship != null) {
            battleship.receiveShot(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Field)) {
            return false;
        }

        Field field = (Field) o;

        return field.getRow() == row && field.getColumn() == column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
