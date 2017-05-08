package battleshipgame.model;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by yanair on 05.05.17.
 */
public class BattleshipFactory {

    private static final int SINGLE_DECKER_QUANTITY     = 4;
    private static final int TWO_DECKER_QUANTITY        = 3;
    private static final int THREE_DECKER_QUANTITY      = 2;
    private static final int FOUR_DECKER_QUANTITY       = 1;

    private LinkedList<Battleship> ships = new LinkedList<>();

    {
        addShips(Battleship.Deck.ONE);
        addShips(Battleship.Deck.TWO);
        addShips(Battleship.Deck.THREE);
        addShips(Battleship.Deck.FOUR);
    }

    private void addShips(Battleship.Deck deck) {
        int shipQuantity = getShipQuantity(deck);

        for (int i = 0; i < shipQuantity; i++) {
            ships.add(getBattleship(deck));
        }
    }

    private static int getShipQuantity(Battleship.Deck deck) {
        switch (deck) {
            case ONE:
                return SINGLE_DECKER_QUANTITY;
            case TWO:
                return TWO_DECKER_QUANTITY;
            case THREE:
                return THREE_DECKER_QUANTITY;
            case FOUR:
                return FOUR_DECKER_QUANTITY;
            default:
                return 0;
        }
    }

    static int getTotalShipsCount() {
        return Arrays.stream(new int []{getShipQuantity(Battleship.Deck.ONE), getShipQuantity(Battleship.Deck.TWO),
                getShipQuantity(Battleship.Deck.THREE), getShipQuantity(Battleship.Deck.FOUR)}, 0, 4).sum();
    }

    private Battleship getBattleship(Battleship.Deck deck) {
        switch (deck) {
            case ONE:
                return new Battleship(Battleship.Ship.SINGLE_DECKER);
            case TWO:
                return new Battleship(Battleship.Ship.TWO_DECKER);
            case THREE:
                return new Battleship(Battleship.Ship.THREE_DECKER);
            case FOUR:
                return new Battleship(Battleship.Ship.FOUR_DECKER);
            default:
                return null;
        }
    }

    public Battleship getNextShip() {
        return ships.pollFirst(); // returns null for empty list
    }

}
