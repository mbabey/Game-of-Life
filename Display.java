
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * Display.
 * 
 * <p>
 * GUI for Game Of Life. Displays the 2D Cell array in a World as a square grid.
 * Each Cell is displayed as a coloured square. <br>
 * Colour Key:
 * <ul>
 * <li>White : Empty</li>
 * <li>Green : Plant</li>
 * <li>Yellow : Herbivore</li>
 * </ul>
 * </p>
 * 
 * @author Maxwell Babey
 * @version 2022, 1.0
 */
public class Display extends JFrame {

    /** The world assigned to this Display. */
    private final World world;

    /**
     * Constructor. Creates a Display, and assigns it a world to display.
     * 
     * @param w - the world to display
     */
    public Display(final World w) {
        this.world = w;
    }

    /**
     * displayWorld. Displays the world assigned to this Display as a grid.
     */
    public void displayWorld() {
        this.setLayout(new GridLayout(this.world.getRows(), this.world.getCols()));
        for (int row = 0; row < this.world.getRows(); ++row) {
            for (int col = 0; col < this.world.getCols(); ++col) {
                this.add((Component) this.world.getCell(col, row));
            }
        }
    }
}