package seabattle;

/**
 * Created by yanair on 05.05.17.
 */
public class BattleshipFactory {

    public Battleship getBattleship(Battleship.Deck deck) {
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
                throw new IllegalArgumentException("Decks should be from 1 to 4."); // TODO catch exception ?
        }
    }

}
