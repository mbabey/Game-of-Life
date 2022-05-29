
import java.awt.Color;

/**
 * Omnivore.
 * 
 * <p>
 * An Omnivore is an Animal with the following characteristics:
 * <ul>
 * <li>An Omnivore can move to a nearby Cell if that Cell is empty or holds an
 * Organism a Omnivore can eat,</li>
 * <li>An Omnivore can eat an Organism that implements OmnivoreEdible, and</li>
 * <li>An Omnivore can mate with another Omnivore if that Omnivore is neither
 * its child nor its parent.</li>
 * </ul>
 * <p>
 * 
 * @author Maxwell Babey
 * @version 2022, 1.0
 */
public class Omnivore extends Animal implements CarnivoreEdible {

    /** The lifespan of an Omnivore in turns. */
    public static final int OMNIVORE_LIFESPAN = 5;

    /** The sensory distance of an Omnivore in Cells. */
    public static final int OMNIVORE_SENSORY_DISTANCE = 1;

    /** Omnivore neighbours for an Omnivore to reproduce. */
    public static final int OMNIVORE_NB_TO_REPRODUCE = 1;

    /** Food neighbours for an Omnivore to reproduce. */
    public static final int FOOD_OMNIVORE_NB_TO_REPRODUCE = 1;
    
    /** Empty neighbours for an Omnivore to reproduce. */
    public static final int EMPTY_OMNIVORE_NB_TO_REPRODUCE = 3;

    /** The colour of an Omnivore. */
    public static final Color OMNIVORE_COLOUR = Color.BLUE;

    /**
     * Constructor. Creates a Omnivore.
     * 
     * @param location - the cell which this Omnivore occupies
     */
    public Omnivore(Cell location) {
        this.currentCell = location;
        this.lifeSpan = OMNIVORE_LIFESPAN;
        this.sensorDistance = OMNIVORE_SENSORY_DISTANCE;
        this.likeNbToRep = OMNIVORE_NB_TO_REPRODUCE; 
        this.foodNbToRep = FOOD_OMNIVORE_NB_TO_REPRODUCE;
        this.emptyNbToRep = EMPTY_OMNIVORE_NB_TO_REPRODUCE;
        this.orgColour = OMNIVORE_COLOUR;
        this.parents = NULL_PARENTS;
    }

    /**
     * Constructor. Creates a Omnivore with specified parents.
     * 
     * @param location  - the cell which this Omnivore occupies.
     * @param birthedBy - the Animals which created this Omnivore.
     */
    public Omnivore(Cell location, Organism[] birthedBy) {
        this.currentCell = location;
        this.lifeSpan = OMNIVORE_LIFESPAN;
        this.sensorDistance = OMNIVORE_SENSORY_DISTANCE;
        this.likeNbToRep = OMNIVORE_NB_TO_REPRODUCE; 
        this.foodNbToRep = FOOD_OMNIVORE_NB_TO_REPRODUCE;
        this.emptyNbToRep = EMPTY_OMNIVORE_NB_TO_REPRODUCE;
        this.orgColour = OMNIVORE_COLOUR;
        this.parents = birthedBy;
    }

    /**
     * Null constructor.
     */
    public Omnivore() {
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
     * isEdible. Determines Herbivore edibility.
     * 
     * @param org - Organism to check against.
     * @return true if Organism is OmnivoreEdible, false otherwise.
     */
    @Override
    protected boolean isEdible(Organism org) {
        return (org instanceof OmnivoreEdible);
    }
    
    /**
     * isSpecies. Determines species likeness.
     * 
     * @param org - Organism to check against.
     * @return true if Organism is an Omnivore, false otherwise.
     */
    @Override
    protected boolean isSpecies(Organism org) {
        return (org instanceof Omnivore);
    }
    
    /**
     * makeBaby. Makes a baby Omnivore.
     * 
     * @param birthplace - the Cell the Organism will be born in
     * @param parentage  - the parents of the child
     * @return a little baby Omnivore.
     */
    @Override
    protected Organism makeBaby(Cell birthplace, Organism[] parentage) {
        return new Omnivore(birthplace, parentage);
    }
}
