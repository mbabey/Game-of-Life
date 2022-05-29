
import java.awt.Color;

/**
 * Carnivore.
 * 
 * <p>
 * A Carnivore is an Animal with the following characteristics:
 * <ul>
 * <li>A Carnivore can move to a nearby Cell if that Cell is empty or holds an
 * Organism a Carnivore can eat.</li>
 * <li>A Carnivore can eat an Organism that implements CarnivoreEdible</li>
 * <li>An Carnivore can mate with another Carnivore if that Carnivore is neither
 * its child nor its parent.</li>
 * </ul>
 * <p>
 * 
 * @author Maxwell Babey
 * @version 2022, 1.0
 */
public class Carnivore extends Animal implements OmnivoreEdible {

    /** The lifespan of a Carnivore in turns. */
    public static final int CARNIVORE_LIFESPAN = 5;

    /** The sensory distance of a Carnivore in Cells. */
    public static final int CARNIVORE_SENSORY_DISTANCE = 1;

    /** Carnivore neighbours for a Carnivore to reproduce. */
    public static final int CARNIVORE_NB_TO_REPRODUCE = 1;

    /** Empty neighbours for an Carnivore to reproduce. */
    public static final int FOOD_CARNIVORE_NB_TO_REPRODUCE = 2;
    
    /** Empty neighbours for an Carnivore to reproduce. */
    public static final int EMPTY_CARNIVORE_NB_TO_REPRODUCE = 3;

    /** The colour of a Carnivore. */
    public static final Color CARNIVORE_COLOUR = Color.RED;

    /**
     * Constructor. Creates a Carnivore.
     * 
     * @param location - the cell which this Carnivore occupies
     */
    public Carnivore(Cell location) {
        this.currentCell = location;
        this.lifeSpan = CARNIVORE_LIFESPAN;
        this.sensorDistance = CARNIVORE_SENSORY_DISTANCE;
        this.likeNbToRep = CARNIVORE_NB_TO_REPRODUCE; 
        this.foodNbToRep = FOOD_CARNIVORE_NB_TO_REPRODUCE;
        this.emptyNbToRep = EMPTY_CARNIVORE_NB_TO_REPRODUCE;
        this.orgColour = CARNIVORE_COLOUR;
        this.parents = NULL_PARENTS;
    }

    /**
     * Constructor. Creates a Carnivore with specified parents.
     * 
     * @param location  - the cell which this Carnivore occupies.
     * @param birthedBy - the Animals which created this Carnivore.
     */
    public Carnivore(Cell location, Organism[] birthedBy) {
        this.currentCell = location;
        this.lifeSpan = CARNIVORE_LIFESPAN;
        this.sensorDistance = CARNIVORE_SENSORY_DISTANCE;
        this.likeNbToRep = CARNIVORE_NB_TO_REPRODUCE; 
        this.foodNbToRep = FOOD_CARNIVORE_NB_TO_REPRODUCE;
        this.emptyNbToRep = EMPTY_CARNIVORE_NB_TO_REPRODUCE;
        this.orgColour = CARNIVORE_COLOUR;
        this.parents = birthedBy;
    }

    /**
     * Null constructor.
     */
    public Carnivore() {
        this.currentCell = null;
        this.lifeSpan = 0;
        this.sensorDistance = 0;
        this.likeNbToRep = 0; 
        this.foodNbToRep = 0;
        this.emptyNbToRep = 0;
        this.orgColour = null;
        this.parents = null;
    }

    /**
     * isEdible. Determines Carnivore edibility.
     * 
     * @param org - Organism to check against.
     * @return true if Organism is CarnivoreEdible, false otherwise.
     */
    @Override
    protected boolean isEdible(Organism org) {
        return (org instanceof CarnivoreEdible);
    }

    /**
     * isSpecies. Determines species likeness.
     * 
     * @param org - Organism to check against.
     * @return true if Organism is a Carnivore, false otherwise.
     */
    @Override
    protected boolean isSpecies(Organism org) {
        return (org instanceof Carnivore);
    }

    /**
     * makeBaby. Makes a baby Carnivore.
     * 
     * @param birthplace - the Cell the Organism will be born in
     * @param parentage  - the parents of the child
     * @return a little baby Carnivore.
     */
    @Override
    protected Organism makeBaby(Cell birthplace, Organism[] parentage) {
        return new Carnivore(birthplace, parentage);
    }

}
