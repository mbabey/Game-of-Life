
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * Organism.
 * 
 * Organism is a superclass of Plant and Animal. An Organism occupies a Cell.
 * Every turn, an Organism takes an action specified by its sub-type.
 * 
 * @author Maxwell Babey
 * @version 2022, 1.0
 */
public class Organism {

    /** Defines the sensory distance of an organism. */
    protected int sensorDistance;

    /** Holds the current cell of this organism. */
    protected Cell currentCell;

    /** Array containing the progenitors of all Organisms. */
    public static final Organism[] NULL_PARENTS = { new Organism(), new Organism() };

    /** The parents of this Organism. */
    protected Organism[] parents;

    /** The children of this Organism. */
    protected List<Organism> children = new LinkedList<Organism>();

    /** The number of like-species neighbours for this Organism to reproduce. */
    protected int likeNbToRep;
    
    /** The number of -Edible implementing neighbours for this Organism to reproduce. */
    protected int foodNbToRep;
    
    /** The number of empty neighbours for this Organism to reproduce. */
    protected int emptyNbToRep;
    
    /** Flags whether this Organism has mated in a turn. */
    protected boolean hasMated = false;

    /** Switch for whether this Organism can act in a turn. */
    protected boolean canAct = false;

    /** Holds the colour of the Organism. */
    protected Color orgColour;

    /**
     * setHasMated. Setter for hasMated.
     * 
     * @param hasMatedState - the state to which hasMated will be set.
     */
    public void setHasMated(boolean hasMatedState) {
        this.hasMated = hasMatedState;
    }

    /**
     * setCanAct. Setter for canAct.
     * 
     * @param canActState - state to which canAct will be set.
     */
    public void setCanAct(boolean canActState) {
        this.canAct = canActState;
    }

    /**
     * Get the colour of this Organism.
     * 
     * @return - orgColour
     */
    public Color getColour() {
        return this.orgColour;
    }

    /**
     * isEdible. Determines whether an Organism is edible by another Organism.
     * Overridden by subclasses.
     * 
     * @param org - the organism to be tested against.
     * @return false - default
     */
    protected boolean isEdible(Organism org) {
        return false;
    }

    /**
     * isSpecies. Determines whether an Organism is the same species as another
     * Organism. Overridden by subclasses.
     * 
     * @param org - the organism to be tested against.
     * @return false - default
     */
    protected boolean isSpecies(Organism org) {
        return false;
    }

    /**
     * reproduce. Overridden by subclasses.
     */
    protected void reproduce() {
    }

    /**
     * addChild. Adds a child to this Organism's list of children.
     * 
     * @param child - the child to add
     */
    protected void addChild(Organism child) {
        this.children.add(child);
    }

    /**
     * turnAction. Calls the actions of this Organism. Actions depend on the
     * Organism sub-type.
     */
    public void turnAction() {
    }

}
