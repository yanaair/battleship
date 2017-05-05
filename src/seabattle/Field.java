package seabattle;

/**
 * Created by yanair on 05.05.17.
 */
public class Field {

    private boolean isShot = false;
    private boolean isOccupied = false;

    public void setShip() {
        isOccupied = true;
    }

    public void removeShip() {
        isOccupied = false;
    }

    public void shot() {
        isShot = true;
    }

    public boolean isShot() {
        return isShot;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

}
