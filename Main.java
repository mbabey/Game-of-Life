
/**
 * Main.
 * 
 * Drives the game.
 * 
 * @author Maxwell Babey
 * @version 2022, 1.0
 */
public final class Main {

    /**
     * Drives the program.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        GameLogic GameOfLife = new GameLogic();
        GameOfLife.startGameOfLife();
    }
}
