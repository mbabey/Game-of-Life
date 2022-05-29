
import java.util.LinkedList;
import java.util.List;

/**
 * Animal.
 * 
 * <p>
 * An Animal is an Organism with the following characteristics:
 * <ul>
 * <li>An Animal dies if it does not eat within a certain number of turns; this
 * is its lifeSpan,</li>
 * <li>An Animal can move,</li>
 * <li>An Animal can eat,</li>
 * <li>An Animal knows its children and its parents, and</li>
 * <li>An Animal reproduces with a partner that is neither its child nor its parent.</li>
 * </ul>
 * </p>
 * 
 * @author Maxwell Babey
 * @version 2022, 2.0
 */
public class Animal extends Organism {

    /** The maximum number of turns this Animal can exist. */
    protected int lifeSpan;

    /** The number of turns this Animal has existed. */
    protected int age;
    
    /**
     * Constructor. Creates an Animal.
     */
    public Animal() {
        this.age = 0;
    }

    /**
     * move. Moves this Animal to a Cell that does not contain an Animal already.
     */
    protected void move() {
        List<Cell> neighbours = this.currentCell.getNeighbours(this.sensorDistance);
        List<Cell> validNeighbours = new LinkedList<Cell>();
        for (Cell neighbour : neighbours) {
            if (isEdible(neighbour.getOrganism()) || (neighbour.getOrganism() == null)) {
                validNeighbours.add(neighbour);
            }
        }
        if (validNeighbours.size() > 0) {
            takeStep(validNeighbours);
        }
    }

    /**
     * takeStep. Helper method which moves the Omnivore to a new Cell. If the Cell
     * is occupied by something OmnivoreEdible, the Omnivore will eat.
     * 
     * @param validNeighbours - List containing valid neighbouring Cells
     */
    private void takeStep(List<Cell> validNeighbours) {
        int randNum = RandomGenerator.nextNumber(validNeighbours.size());
        Cell targetCell = validNeighbours.get(randNum);
        if (isEdible(targetCell.getOrganism())) {
            this.eat();
        }
        targetCell.setOrganism(this);
        this.currentCell.annihilate();
        this.currentCell = targetCell;
    }

    /**
     * eat. Eat food and reset age.
     */
    protected void eat() {
        // munch munch
        this.age = 0;
    }

    /**
     * reproduce. Calls on this Animal to check its neighbouring cells. If a certain
     * number of neighbours are also potential mate Animals and this Animal has a
     * certain number of empty neighbours, one those empty neighbours will be filled
     * with an Animal. Potentiality to be a mate is determined by nuclear family
     * ties.
     * 
     */
    @Override
    protected void reproduce() {
        if (!this.hasMated) {
            List<Cell> neighbours = this.currentCell.getNeighbours(this.sensorDistance);
            List<Cell> validNeighbours = new LinkedList<Cell>();
            int neighbourCount = 0;
            int foodCount = 0;
            Organism partner = null;
            for (Cell neighbour : neighbours) {
                if (isSpecies(neighbour.getOrganism()) && !isIncest(neighbour.getOrganism())) {
                    neighbourCount++;
                    if (partner == null) {
                        partner = neighbour.getOrganism();
                    }
                }
                if (isEdible(neighbour.getOrganism())) {
                    foodCount++;
                }
                if (neighbour.getOrganism() == null) {
                    validNeighbours.add(neighbour);
                }
            }
            if (neighbourCount >= this.likeNbToRep && foodCount >= this.foodNbToRep
                    && validNeighbours.size() >= this.emptyNbToRep) {
                createLife(validNeighbours, partner);
            }
        }
    }

    /**
     * isIncest. Helper method for Animal reproduction. Determines whether an
     * Organism is attempting to mate with a family member.
     * 
     * @param neighbour - the neighbour organism
     * @return true if mating would be incestuous; false otherwise
     */
    private boolean isIncest(Organism neighbour) {
        for (Organism parent : this.parents) {
            if (neighbour == parent)
                return true;
        }
        for (Organism child : this.children) {
            if (neighbour == child)
                return true;
        }
        return false;
    }

    /**
     * createLife. Creates a child Organism with this Animal and the partner Animal
     * as parents. Adds the baby to the List children of the parent Animals.
     * 
     * @param validNeighbours - list of empty neighbour cells
     * @param partner         - the partner to mate with
     */
    protected void createLife(List<Cell> validNeighbours, Organism partner) {
        Organism[] parentage = { this, partner };
        int randNum = RandomGenerator.nextNumber(validNeighbours.size());
        Organism baby = makeBaby(validNeighbours.get(randNum), parentage);
        partner.setHasMated(true);
        this.setHasMated(true);
        partner.addChild(baby);
        this.addChild(baby);
        validNeighbours.get(randNum).setOrganism(baby);
    }

    /**
     * makeBaby. Makes a new Organism in a specified Cell and with a Parentage.
     * Overridden in subclasses.
     * 
     * @param birthplace - the Cell the Organism will be born in
     * @param parentage  - the parents of the child
     * @return null Organism - default
     */
    protected Organism makeBaby(Cell birthplace, Organism[] parentage) {
        return new Organism();
    }

    /**
     * die. Kills this Animal if it is too old.
     */
    private void die() {
        if (this.age > this.lifeSpan) {
            this.currentCell.annihilate();
        }
    }

    /**
     * turnAction. Animal turn actions.
     * <ol>
     * <li>Increase age.</li>
     * <li>Reproduce, if possible.</li>
     * <li>If has not mated, move. If food is found, age will be set to 0. 4) If it
     * is time to die, then die.</li>
     * </ol>
     */
    @Override
    public void turnAction() {
        if (this.canAct) {
            this.age++;
            this.reproduce();
            if (!this.hasMated)
                this.move();
            this.die();
        }
        this.canAct = false;
    }
}
