
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Cell.
 * 
 * <p>
 * A Cell is a position in a World 2D landMass array. A Cell either holds an
 * Organism or is empty (represented by organism = null). A Cell knows its own
 * position and is able to recognize its neighbouring Cells. <br>
 * A Cell is displayed on the Display as a coloured square. <br>
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
public class Cell extends JPanel {

    /** The World this cell belongs to. Allows the cell to look around itself. */
    private final World world;

    /** The Cell's x-position and y-position */
    private final int xpos;
    private final int ypos;

    /** The Organism in this Cell. Null if there is no Organism. */
    private Organism organism;

    /** The colour of an empty cell. */
    public static final Color EMPTY_CELL_COLOUR = Color.WHITE;

    /**
     * Constructor. Creates a Cell object. Initializes Organism to null (empty).
     * 
     * @param x - the Cell's x-position in the world
     * @param y - the Cell's y-position in the world
     */
    public Cell(World w, int y, int x) {
        this.world = w;
        this.xpos = x;
        this.ypos = y;
        this.organism = null;
        this.colourCell(this.organism);
    }

    /**
     * getOrganism. Returns the Organism of this Cell.
     * 
     * @return the Organism of this Cell
     */
    public Organism getOrganism() {
        return this.organism;
    }

    /**
     * setOrganism. Puts an Organism in this Cell and sets the colour.
     * 
     * @param org - the Organism to put in this Cell.
     */
    public void setOrganism(Organism org) {
        this.organism = org;
        this.colourCell(this.organism);
    }

    /**
     * annihilate. Removes the Organism in this Cell and resets the colour.
     */
    public void annihilate() {
        this.organism = null;
        this.colourCell(this.organism);
    }

    /**
     * colourCell. Assigns a colour to this Cell based on the Organism within.
     * 
     * @param org - the Organism in this Cell.
     */
    private void colourCell(Organism org) {
        if (org == null) {
            this.setBackground(EMPTY_CELL_COLOUR);
        } else {
            this.setBackground(org.getColour());
        }
    }

    /**
     * getNeighbours. Gets the cells which are sensorDistance around this cell and
     * stores them in an array.
     * 
     * @param sensorDistance - the sensory distance of the organism in this cell
     *                       getting neigbours
     * @return an array of Cells near this cell
     */
    public List<Cell> getNeighbours(int sensorDistance) {
        List<Cell> neighbours = new LinkedList<Cell>();
        for (int row = -1 * sensorDistance; row <= sensorDistance; row++) {
            for (int col = -1 * sensorDistance; col <= sensorDistance; col++) {
                if (inBounds(row, col) && this.world.getCell(this.xpos + col, this.ypos + row) != this) {
                    neighbours.add(this.world.getCell(this.xpos + col, this.ypos + row));
                }
            }
        }
        return neighbours;
    }

    /**
     * inBounds. Helper method for checking the array bounds during getNeighbours.
     * 
     * @param row - the row integer in the current iteration
     * @param col - the col integer in the current integer
     * @return true if the Cell being checked falls within the 2D landMass array of
     *         this Cell's world, false otherwise.
     */
    private boolean inBounds(int row, int col) {
        return ((this.ypos + row) >= 0 && (this.xpos + col) >= 0 && (this.ypos + row) < this.world.getRows()
                && (this.xpos + col) < this.world.getCols());
    }

    /**
     * paint. Draws this Cell on the display.
     */
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        g.drawRect(0, 0, this.getWidth(), this.getHeight());
    }
}
