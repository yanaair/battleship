package seabattle;

/**
 * Created by yanair on 05.05.17.
 */
public class Board {

    private static final int SIZE = 10;
    private Field[][] fields;

    public Board() {
        fields = new Field[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                fields[row][col] = new Field(row, col);
            }
        }
    }



}
