
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 * GameLogic.
 * 
 * Controls the logic of the Game of Life. Creates a World of a certain size and
 * assigns it to a Display, then listens for mouse clicks on the Display to
 * iterate game turns.
 * 
 * @author Dennis Richards - original
 * @author Maxwell Babey - update
 * @version 2022, 2.1
 */
public class GameLogic {

    private static final Toolkit TOOLKIT;

    /** The Display assigned to this GameLogic. */
    public final Display gameScreen;

    /** The World assigned to this GameLogic. */
    public final World world;

    /** The X-dimension of the World grid. */
    public static final int WORLD_GRID_X = 25;

    /** The Y-dimension of the World grid. */
    public static final int WORLD_GRID_Y = 25;

    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * Constructor. Creates a GameLogic. Initializes the World and the Display.
     */
    public GameLogic() {
        RandomGenerator.reset();
        this.world = new World(WORLD_GRID_X, WORLD_GRID_Y);
        this.gameScreen = new Display(this.world);
    }

    /**
     * startGameOfLife. Starts the Game of Life.
     */
    public void startGameOfLife() {
        this.world.letThereBeLight();
        position(this.gameScreen);
        this.gameScreen.displayWorld();
        this.gameScreen.addMouseListener(new DisplayClickListener(this));
        this.gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameScreen.setVisible(true);
    }

    /**
     * position. Sets the size and position of the Display.
     * 
     * @param display - the frame to size and position
     */
    private static void position(final Display display) {
        final Dimension size;
        size = calculateScreenArea(0.80f, 0.80f);
        display.setSize(size);
        display.setLocationRelativeTo(null);
    }

    /**
     * calculateScreenArea. Calculates the size of the system screen.
     * 
     * @param widthPercent  - the percentage width of the system screen the display
     *                      will take up.
     * @param heightPercent - the percentage height of the system screen the display
     *                      will take up.
     * @return area - the dimensions for the game display.
     */
    public static Dimension calculateScreenArea(final float widthPercent, final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > 100.0f)) {
            throw new IllegalArgumentException("widthPercent cannot be " + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > 100.0f)) {
            throw new IllegalArgumentException("heightPercent cannot be " + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }

    /**
     * gameTurn. Runs the methods which iterate the game by one turn (day).
     */
    public void gameTurn() {
        this.world.twentyThreeHoursAndFiftySixMinutes();
        this.gameScreen.repaint();
    }

    /**
     * DisplayClickListener. Listener class for mouse actions on a Display.
     */
    private class DisplayClickListener implements MouseListener {

        /** The GameLogic this DisplayClickListener will trigger actions within. */
        private final GameLogic game;

        /**
         * Constructor. Creates a DisplayClickListener.
         * 
         * @param game - the GameLogic this DisplayClickLostener will act upon.
         */
        public DisplayClickListener(GameLogic game) {
            this.game = game;
        }

        /**
         * mouseClicked. On mouse click, calls this DisplayClickListener's GameLogic
         * turn method.
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            this.game.gameTurn();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }
}
