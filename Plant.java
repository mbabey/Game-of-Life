
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Plant.
 * 
 * <p>
 * A Plant is an Organism with the following characteristics:
 * <ul>
 * <li>A Plant cannot move,</li>
 * <li>A Plant cannot die, and</li>
 * <li>A Plant can seed a neighbouring empty cell.</li>
 * </ul>
 * </p>
 * 
 * @author Maxwell Babey
 * @version 2022, 1.1
 */
public class Plant extends Organism implements HerbivoreEdible, OmnivoreEdible {

    /** The sensory distance of a Plant in Cells. */
    public static final int PLANT_SENSORY_DISTANCE = 1;

    /**
     * The number of neighbours for this plant to seed an empty cell nearby.
     */
    public static final int PLANT_NB_TO_PROPAGATE = 2;

    /**
     * The number of empty neighbours for this plant to seed an empty cell nearby.
     */
    public static final int EMPTY_PLANT_NB_TO_PROPAGATE = 3;

    /** The percent chance a Plant has to seed an empty cell. */
    public static final int PERCENT_CHANCE_TO_PROPAGATE = 100;

    /** The colour of a Plant. */
    public static final Color PLANT_COLOUR = Color.GREEN;

    /**
     * Constructor. Creates a plant at the specified Cell.
     * 
     * @param currentCell - the Cell to create a Plant at.
     */
    public Plant(Cell location) {
        this.currentCell = location;
        this.sensorDistance = PLANT_SENSORY_DISTANCE;
        this.likeNbToRep = PLANT_NB_TO_PROPAGATE;
        this.foodNbToRep = 0;
        this.emptyNbToRep = EMPTY_PLANT_NB_TO_PROPAGATE;
        this.orgColour = PLANT_COLOUR;
    }
    
    /**
     * Null constructor.
     */
    public Plant() {
        this.currentCell = null;
        this.sensorDistance = 0;
        this.likeNbToRep = 0;
        this.foodNbToRep = 0;
        this.emptyNbToRep = 0;
        this.orgColour = null;
    }

    /**
     * reproduce. Calls on this Plant to check its neighbouring cells. If a certain
     * number of neighbours are also Plants and this Plant has a certain number of
     * empty neighbours, those empty neighbours have a chance to become Plants.
     */
    @Override
    protected void reproduce() {
        List<Cell> neighbours = this.currentCell.getNeighbours(this.sensorDistance);
        List<Cell> validNeighbours = new LinkedList<Cell>();
        int plantNeighbourCount = 0;
        for (Cell neighbour : neighbours) {
            if (isSpecies(neighbour.getOrganism())) {
                plantNeighbourCount++;
            }
            if (neighbour.getOrganism() == null) {
                validNeighbours.add(neighbour);
            }
        }
        if (plantNeighbourCount >= likeNbToRep && validNeighbours.size() >= emptyNbToRep) {
            seed(validNeighbours);
        }
    }

    /**
     * isSpecies.
     * 
     * @param org - Organism to check against.
     * @return true if Organism is a Plant, false otherwise.
     */
    @Override
    protected boolean isSpecies(Organism org) {
        return (org instanceof Plant);
    }

    /**
     * seed. Helper method for reproduce. Seeds a random valid neighbouring Cell.
     * 
     * @param validNeighbours - List of valid neighbour Cells
     */
    private void seed(List<Cell> validNeighbours) {
        int randNum = RandomGenerator.nextNumber(99);
        if (randNum <= PERCENT_CHANCE_TO_PROPAGATE) {
            randNum = RandomGenerator.nextNumber(validNeighbours.size());
            validNeighbours.get(randNum).setOrganism(new Plant(validNeighbours.get(randNum)));
        }
    }

    /**
     * turnAction. Plant turn actions.
     */
    @Override
    public void turnAction() {
        if (this.canAct) {
            this.reproduce();
        }
        this.canAct = false;
    }
}
