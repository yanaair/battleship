package seabattle;

import java.util.HashSet;
import java.util.Set;

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
        ONE(1), TWO(2), THREE(3), FOUR(4);

        private int deck;
        Deck(int deck){
            this.deck = deck;
        }
    }

    private static final int FOUR_DECKER_SIZE           = 4;
    private static final int SINGLE_DECKER_SIZE         = 1;
    private static final int TWO_DECKER_SIZE            = 2;
    private static final int THREE_DECKER_SIZE          = 3;

    private int shipSize;
    private Set<Field> occupiedFields = new HashSet<>();

    public Battleship(Ship ship) {
        this.shipSize = ship.getSize();
    }

    public int getShipSize() {
        return shipSize;
    }

    public void addField(Field field) {
        occupiedFields.add(field);
    }

    public void removeField(Field field) {
        occupiedFields.remove(field);
    }

    public void receiveShot(Field field) {
        for (Field f : occupiedFields) {
            if (f.equals(field)) {
                f.shoot();
            }
        }
    }

}
