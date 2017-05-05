package seabattle;

/**
 * Created by yanair on 05.05.17.
 */
public class Field {

    private int row;
    private int column;

    private boolean isShot = false;
    private boolean isOccupied = false;

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

    public void setShip() {
        isOccupied = true;
    }

    public void removeShip() {
        isOccupied = false;
    }

    public void shot() {
        isShot = true;
    }

}
