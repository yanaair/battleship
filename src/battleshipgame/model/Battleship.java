package battleshipgame.model;

/**
 * Created by yanair on 05.05.17.
 */
public class Battleship {

    enum Ship {
        SINGLE_DECKER(SINGLE_DECKER_SIZE), TWO_DECKER(TWO_DECKER_SIZE),
        THREE_DECKER(THREE_DECKER_SIZE), FOUR_DECKER(FOUR_DECKER_SIZE);

        private int size;

        Ship(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    enum Deck {
        ONE, TWO, THREE, FOUR
    }

    private static final int FOUR_DECKER_SIZE           = 4;
    private static final int SINGLE_DECKER_SIZE         = 1;
    private static final int TWO_DECKER_SIZE            = 2;
    private static final int THREE_DECKER_SIZE          = 3;

    private int shipSize;
    private boolean horizontalDirection = false;
    private int health;

    Battleship(Ship ship) {
        this.shipSize = ship.getSize();
        this.health = ship.getSize();
    }

    int getShipSize() {
        return shipSize;
    }

    void receiveShot() {
        if (isAlive()) {
            health--;
        }
    }

    boolean isAlive() {
        return health > 0;
    }

    boolean hasHorizontalDirection() {
        return horizontalDirection;
    }

    public void rotate() {
        horizontalDirection = !horizontalDirection;
    }

    @Override
    public String toString() {
        return "(Size " + shipSize + " / Horizontal direction : " + horizontalDirection + ")";
    }

}
