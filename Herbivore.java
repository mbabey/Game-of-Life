
import java.awt.Color;

/**
 * Herbivore.
 * 
 * <p>
 * A Herbivore is an Animal with the following characteristics:
 * <ul>
 * <li>A Herbivore can move to a nearby Cell if that Cell is empty or holds an
 * Organism a Herbivore can eat,</li>
 * <li>A Herbivore can eat an Organism that implements HerbivoreEdible, and</li>
 * <li>A Herbivore can mate with another Herbivore if that Herbivore is neither
 * its child nor its parent.</li>
 * </ul>
 * <p>
 * 
 * @author Maxwell Babey
 * @version 2022, 2.0
 */
public class Herbivore extends Animal implements CarnivoreEdible, OmnivoreEdible {

    /** The lifespan of a Herbivore in turns. */
    public static final int HERBIVORE_LIFESPAN = 5;

    /** The sensory distance of a Herbivore in Cells. */
    public static final int HERBIVORE_SENSORY_DISTANCE = 1;

    /** Herbivore neighbours for an Herbivore to reproduce. */
    public static final int HERBIVORE_NB_TO_REPRODUCE = 1;

    /** Food neighbours for an Herbivore to reproduce. */
    public static final int FOOD_HERBIVORE_NB_TO_REPRODUCE = 2;
    
    /** Empty neighbours for an Herbivore to reproduce. */
    public static final int EMPTY_HERBIVORE_NB_TO_REPRODUCE = 2;

    /** The colour of a Herbivore. */
    public static final Color HERBIVORE_COLOUR = Color.YELLOW;

    /**
     * Constructor. Creates a Herbivore.
     * 
     * @param location - the cell which this Herbivore occupies
     */
    public Herbivore(Cell location) {
        this.currentCell = location;
        this.lifeSpan = HERBIVORE_LIFESPAN;
        this.sensorDistance = HERBIVORE_SENSORY_DISTANCE;
        this.likeNbToRep = HERBIVORE_NB_TO_REPRODUCE; 
        this.foodNbToRep = FOOD_HERBIVORE_NB_TO_REPRODUCE;
        this.emptyNbToRep = EMPTY_HERBIVORE_NB_TO_REPRODUCE;
        this.orgColour = HERBIVORE_COLOUR;
        this.parents = NULL_PARENTS;
    }

    /**
     * Constructor. Creates a Herbivore with specified parents.
     * 
     * @param location  - the cell which this Herbivore occupies.
     * @param birthedBy - the Animals which created this Herbivore.
     */
    public Herbivore(Cell location, Organism[] birthedBy) {
        this.currentCell = location;
        this.lifeSpan = HERBIVORE_LIFESPAN;
        this.sensorDistance = HERBIVORE_SENSORY_DISTANCE;
        this.likeNbToRep = HERBIVORE_NB_TO_REPRODUCE; 
        this.foodNbToRep = FOOD_HERBIVORE_NB_TO_REPRODUCE;
        this.emptyNbToRep = EMPTY_HERBIVORE_NB_TO_REPRODUCE;
        this.orgColour = HERBIVORE_COLOUR;
        this.parents = birthedBy;
    }

    /**
     * Null constructor.
     */
    public Herbivore() {
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
     * @return true if Organism is HerbivoreEdible, false otherwise.
     */
    @Override
    protected boolean isEdible(Organism org) {
        return (org instanceof HerbivoreEdible);
    }

    /**
     * isSpecies. Determines species likeness.
     * 
     * @param org - Organism to check against.
     * @return true if Organism is a Herbivore, false otherwise.
     */
    @Override
    protected boolean isSpecies(Organism org) {
        return (org instanceof Herbivore);
    }

    /**
     * makeBaby. Makes a baby Herbivore.
     * 
     * @param birthplace - the Cell the Organism will be born in
     * @param parentage  - the parents of the child
     * @return a little baby Herbivore.
     */
    @Override
    protected Organism makeBaby(Cell birthplace, Organism[] parentage) {
        return new Herbivore(birthplace, parentage);
    }

}
