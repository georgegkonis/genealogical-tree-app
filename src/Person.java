import java.util.*;

/**
 * This class represents a person.
 *
 * @author George Gkonis
 * @version 1.0
 */
public class Person {

    // ####################### ATTRIBUTES #######################

    /**
     * The person's name.
     */
    private final String name;

    /**
     * The person's gender.
     */
    private final Gender gender;

    /**
     * The person's father.
     */
    private Person father;

    /**
     * The person's mother.
     */
    private Person mother;

    /**
     * The person's spouse.
     */
    private Person spouse;

    /**
     * The person's children.
     */
    private final Set<Person> children;

    // ####################### CONSTRUCTORS #######################

    /**
     * Constructor method for the {@link Person} class.
     *
     * @param name   the person's name.
     * @param gender the person's gender.
     */
    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.children = new HashSet<>();
    }

    // ####################### METHODS #######################

    /**
     * Finds the person's siblings.
     *
     * @return the person's siblings.
     */
    public Set<Person> findSiblings() {
        Set<Person> siblings = new HashSet<>();
        if (father != null) siblings.addAll(father.getChildren());
        if (mother != null) siblings.addAll(mother.getChildren());
        siblings.remove(this);
        return siblings;
    }

    /**
     * Finds the person's grandparents.
     *
     * @return the person's grandparents.
     */
    public Set<Person> findGrandparents() {
        Set<Person> grandparents = new HashSet<>();
        if (father != null) {
            if (father.getFather() != null) grandparents.add(father.getFather());
            if (father.getMother() != null) grandparents.add(father.getMother());
        }
        if (mother != null) {
            if (mother.getFather() != null) grandparents.add(mother.getFather());
            if (mother.getMother() != null) grandparents.add(mother.getMother());
        }
        return grandparents;
    }

    /**
     * Finds the person's grandchildren.
     *
     * @return the person's grandchildren.
     */
    public Set<Person> findGrandchildren() {
        Set<Person> grandchildren = new HashSet<>();
        for (Person child : children) {
            grandchildren.addAll(child.getChildren());
        }
        return grandchildren;
    }

    /**
     * Finds the person's uncles and aunts.
     *
     * @return the person's uncles and aunts.
     */
    public Set<Person> findUnclesAndAunts() {
        Set<Person> unclesAndAunts = new HashSet<>();
        if (father != null) unclesAndAunts.addAll(father.findSiblings());
        if (mother != null) unclesAndAunts.addAll(mother.findSiblings());
        return unclesAndAunts;
    }

    /**
     * Finds the person's cousins.
     *
     * @return the person's cousins.
     */
    public Set<Person> findCousins() {
        Set<Person> cousins = new HashSet<>();
        for (Person uncleOrAunt : findUnclesAndAunts()) {
            cousins.addAll(uncleOrAunt.getChildren());
        }
        return cousins;
    }

    /**
     * Finds the person's nephews and nieces.
     *
     * @return the person's nephews and nieces.
     */
    public Set<Person> findNephewsAndNieces() {
        Set<Person> nephewsAndNieces = new HashSet<>();
        for (Person sibling : findSiblings()) {
            nephewsAndNieces.addAll(sibling.getChildren());
        }
        return nephewsAndNieces;
    }

    /**
     * Overrides the {@link Object#toString()} method, to return a string representation of the person.
     *
     * @return the person's name and gender separated by a comma.
     */
    @Override
    public String toString() {
        return name + "," + gender;
    }

    // ####################### GETTERS #######################

    /**
     * Getter method for the {@link Person#name} attribute.
     *
     * @return the person's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the {@link Person#gender} attribute.
     *
     * @return the person's gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Getter method for the {@link Person#father} attribute.
     *
     * @return the person's father.
     */
    public Person getFather() {
        return father;
    }

    /**
     * Getter method for the {@link Person#mother} attribute.
     *
     * @return the person's mother.
     */
    public Person getMother() {
        return mother;
    }

    /**
     * Getter method for the {@link Person#spouse} attribute.
     *
     * @return the person's spouse.
     */
    public Person getSpouse() {
        return spouse;
    }

    /**
     * Getter method for the {@link Person#children} attribute.
     *
     * @return the person's children.
     */
    public Set<Person> getChildren() {
        return children;
    }

    // ####################### SETTERS #######################

    /**
     * Setter method for the {@link Person#father} attribute.
     *
     * @param person the person to set as the father.
     */
    public void setFather(Person person) {
        this.father = person;
    }

    /**
     * Setter method for the {@link Person#mother} attribute.
     *
     * @param person the person to set as the mother.
     */
    public void setMother(Person person) {
        this.mother = person;
    }

    /**
     * Setter method for the {@link Person#spouse} attribute.
     *
     * @param person the person to set as the spouse.
     */
    public void setSpouse(Person person) {
        this.spouse = person;
    }
}
