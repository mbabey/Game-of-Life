
/**
 * World.
 * 
 * A World is primarily a 2D array storing Cells. The 2D array is displayed as a
 * grid by Display. World is responsible for handling a game turn.
 * 
 * @author Maxwell Babey
 * @version 2022, 1.0
 */
public class World { // Added to turn order, wakeUpWorld

    /** The order in which turns are executed during the game. */
    public static final Organism[] TURN_ORDER = { new Plant(), new Herbivore(), new Carnivore(), new Omnivore() };

    /** 2D array storing the Cells which make up this world. */
    private Cell[][] landMass;

    /**
     * Constructor. Creates a World object with a landMass of a certain size.
     * 
     * @param xLength - the x-dimension of the landMass
     * @param yLength - the y-dimension of the landMass
     */
    public World(int xLength, int yLength) {
        this.landMass = new Cell[yLength][xLength];
    }

    /**
     * letThereBeLight. For each cell in the world, generate a random number and if
     * that number is a certain value, create life in that cell. The type of life is
     * determined by the number generated.
     */
    public void letThereBeLight() {
        for (int row = 0; row < this.landMass.length; row++) {
            for (int col = 0; col < this.landMass[row].length; col++) {
                this.landMass[row][col] = new Cell(this, row, col);
                int randNum = RandomGenerator.nextNumber(99);
                if (randNum >= 80) {
                    this.landMass[row][col].setOrganism(new Herbivore(this.landMass[row][col]));
                } else if (randNum >= 60) {
                    this.landMass[row][col].setOrganism(new Plant(this.landMass[row][col]));
                } else if (randNum >= 50) {
                    this.landMass[row][col].setOrganism(new Carnivore(this.landMass[row][col]));
                } else if (randNum >= 45) {
                    this.landMass[row][col].setOrganism(new Omnivore(this.landMass[row][col]));
                }
            }
        }
    }

    /**
     * getCell. Gets the cell at a location in the world array.
     * 
     * @param xpos - x position of the cell
     * @param ypos - y position of the cell
     * @return Cell at (xpos, ypos)
     */
    public Cell getCell(int xpos, int ypos) {
        return this.landMass[ypos][xpos];
    }

    /**
     * getRows. Get the number of rows in this world's landMass array.
     * 
     * @return number of rows - an int
     */
    public int getRows() {
        return this.landMass.length;
    }

    /**
     * getCols. Get the number of columns in this world's landMass array.
     * 
     * @return number of cols - an int
     */
    public int getCols() {
        return this.landMass[0].length;
    }

    /**
     * twentyThreeHoursAndFiftySixMinutes. Runs the turns for the Organisms in the
     * world in order based on TURN_ORDER.
     */
    public void twentyThreeHoursAndFiftySixMinutes() {
        this.wakeUpWorld();
        for (Organism org : TURN_ORDER) {
            for (int row = 0; row < this.landMass.length; row++) {
                for (int col = 0; col < this.landMass[row].length; col++) {
                    if ((this.landMass[row][col].getOrganism() != null)
                            && ((this.landMass[row][col].getOrganism()).getClass().isInstance(org))) {
                        this.landMass[row][col].getOrganism().turnAction();
                    }
                }
            }
        }
    }

    /**
     * wakeUpWorld. Sets the canAct flag of each organism to true and the hasMated
     * flag of each Animal to false.
     */
    private void wakeUpWorld() {
        for (int row = 0; row < this.landMass.length; row++) {
            for (int col = 0; col < this.landMass[row].length; col++) {
                if (this.landMass[row][col].getOrganism() != null) {
                    this.landMass[row][col].getOrganism().setCanAct(true);
                }
                if (this.landMass[row][col].getOrganism() instanceof Animal) {
                    ((Animal) this.landMass[row][col].getOrganism()).setHasMated(false);
                }
            }
        }
    }

}
