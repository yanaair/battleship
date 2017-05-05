package seabattle;

import java.util.ArrayList;

/**
 * Created by yanair on 05.05.17.
 */
public class Player {

    private static final int SINGLE_DECKER_QUANTITY     = 4;
    private static final int TWO_DECKER_QUANTITY        = 3;
    private static final int THREE_DECKER_QUANTITY      = 2;
    private static final int FOUR_DECKER_QUANTITY       = 1;

    private ArrayList<Battleship> ships;
    private Player enemy;

    {
        BattleshipFactory battleshipFactory = new BattleshipFactory();
        ships = new ArrayList<>();

        addShips(Battleship.Deck.ONE);
        addShips(Battleship.Deck.TWO);
        addShips(Battleship.Deck.THREE);
        addShips(Battleship.Deck.FOUR);
    }

    public Player() {}

    public Player(Player enemy) {
        this.enemy = enemy;
    }

    private void addShips(Battleship.Deck deck) {
        BattleshipFactory battleshipFactory = new BattleshipFactory();
        int shipQuantity = getShipQuantity(deck);

        for (int i = 0; i < shipQuantity; i++) {
            ships.add(battleshipFactory.getBattleship(deck));
        }
    }

    private int getShipQuantity(Battleship.Deck deck) {
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
                throw new IllegalArgumentException("Decks should be from 1 to 4."); // TODO catch exception ?
        }
    }
}
