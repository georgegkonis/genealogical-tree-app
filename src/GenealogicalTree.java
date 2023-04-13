import java.util.*;

/**
 * This class represents a genealogical tree.
 *
 * @author George Gkonis
 * @version 1.0
 */
public class GenealogicalTree {

    // ####################### ATTRIBUTES #######################

    /**
     * The people in the genealogical tree. Maps the name of the person to the person for easy access.
     */
    private final Map<String, Person> people = new HashMap<>();

    // ######################### METHODS #########################

    /**
     * Adds a person to the tree.
     *
     * @param person the person to add to the tree.
     */
    public void addPerson(Person person) {
        people.put(person.getName(), person);
    }

    /**
     * Finds and returns the person with the specified name from the tree. If the person cannot be found, an exception
     * with a relevant message is thrown.
     *
     * @param name the name of the person to find.
     * @return the person that was found.
     * @throws PersonNotFoundException if the person could not be found.
     */
    public Person findPerson(String name) throws PersonNotFoundException {
        Person person = people.get(name);
        if (person == null) throw new PersonNotFoundException("Person not found: " + name);
        return person;
    }

    /**
     * Adds the specified relation between the first and second person.
     * <ul>
     * <li>If the relation is {@link Relation#FATHER}, the first person is set as the father of the second person, and
     * the second person is added to the first person's list of children.
     * <li>If the relation is {@link Relation#MOTHER}, the first person is set as the mother of the second person, and
     * the second person is added to the first person's list of children.
     * <li>If the relation is {@link Relation#HUSBAND} or {@link Relation#WIFE}, the first person is set as the spouse of
     * the second person, and the second person is set as the spouse of the first person.
     * </ul>
     * The above relations are the only ones that are saved directly for each person. All other relations are calculated
     * using the tree's structure.
     * <p>
     * If either the first or second person cannot be found in the tree, an exception with a relevant message is thrown.
     *
     * @param name1    the name of the first person.
     * @param name2    the name of the second person.
     * @param relation the relation the first person has to the second person.
     * @throws PersonNotFoundException if either of the people are not in the tree.
     */
    public void addRelation(String name1, String name2, Relation relation) throws PersonNotFoundException {
        Person person1 = this.findPerson(name1);
        Person person2 = this.findPerson(name2);

        switch (relation) {
            case FATHER -> {
                person1.getChildren().add(person2);
                person2.setFather(person1);
            }
            case MOTHER -> {
                person1.getChildren().add(person2);
                person2.setMother(person1);
            }
            case HUSBAND, WIFE -> {
                person1.setSpouse(person2);
                person2.setSpouse(person1);
            }
        }
    }

    /**
     * Finds the relation the first person has to the second person.
     *
     * @param name1 the name of the first person.
     * @param name2 the name of the second person.
     * @return the relation the first person has to the second person.
     * @throws PersonNotFoundException if either of the people are not in the tree.
     */
    public Relation findRelation(String name1, String name2) throws PersonNotFoundException {
        Person person1 = this.findPerson(name1);
        Person person2 = this.findPerson(name2);

        if (isParent(person1, person2)) {
            return person1.getGender() == Gender.MAN ? Relation.FATHER : Relation.MOTHER;
        }
        if (isChild(person1, person2)) {
            return person1.getGender() == Gender.MAN ? Relation.SON : Relation.DAUGHTER;
        }
        if (isSibling(person1, person2)) {
            return person1.getGender() == Gender.MAN ? Relation.BROTHER : Relation.SISTER;
        }
        if (isCousin(person1, person2)) {
            return Relation.COUSIN;
        }
        if (isSpouse(person1, person2)) {
            return person1.getGender() == Gender.MAN ? Relation.HUSBAND : Relation.WIFE;
        }
        if (isGrandparent(person1, person2)) {
            return person1.getGender() == Gender.MAN ? Relation.GRANDFATHER : Relation.GRANDMOTHER;
        }
        if (isGrandchild(person1, person2)) {
            return person1.getGender() == Gender.MAN ? Relation.GRANDSON : Relation.GRANDDAUGHTER;
        }
        if (isUncleOrAunt(person1, person2)) {
            return person1.getGender() == Gender.MAN ? Relation.UNCLE : Relation.AUNT;
        }
        if (isNephewOrNiece(person1, person2)) {
            return person1.getGender() == Gender.MAN ? Relation.NEPHEW : Relation.NIECE;
        }
        return Relation.UNRELATED;
    }

    /**
     * Checks if the first person is the father or mother of the second person.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return {@code true} if the first person is the father of the second person, {@code false} otherwise.
     */
    private boolean isParent(Person person1, Person person2) {
        return person1.getChildren().contains(person2);
    }

    /**
     * Checks if the first person is a son or daughter of the second person.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return {@code true} if the first person is a son or daughter of the second person, {@code false} otherwise.
     */
    private boolean isChild(Person person1, Person person2) {
        return person1.getFather() == person2 || person1.getMother() == person2;
    }

    /**
     * Checks if the first person is a grandfather or grandmother of the second person.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return {@code true} if the first person is a grandfather or grandmother of the second person, {@code false}
     * otherwise.
     */
    private boolean isGrandparent(Person person1, Person person2) {
        return person1.findGrandchildren().contains(person2);
    }

    /**
     * Checks if the first person is a grandson or granddaughter of the second person.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return {@code true} if the first person is a grandson or granddaughter of the second person, {@code false}
     * otherwise.
     */
    private boolean isGrandchild(Person person1, Person person2) {
        return person1.findGrandparents().contains(person2);
    }

    /**
     * Checks if the first person is a brother or sister of the second person.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return {@code true} if the first person is a brother or sister of the second person, {@code false} otherwise.
     */
    private boolean isSibling(Person person1, Person person2) {
        return person1.findSiblings().contains(person2);
    }

    /**
     * Checks if the first person is a cousin of the second person.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return {@code true} if the first person is a cousin of the second person, {@code false} otherwise.
     */
    private boolean isCousin(Person person1, Person person2) {
        return person1.findCousins().contains(person2);
    }

    /**
     * Checks if the first person is the husband or wife of the second person.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return {@code true} if the first person is the husband or wife of the second person, {@code false} otherwise.
     */
    private boolean isSpouse(Person person1, Person person2) {
        return person1.getSpouse() == person2;
    }

    /**
     * Checks if the first person is an uncle or aunt of the second person.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return {@code true} if the first person is an uncle or aunt of the second person, {@code false} otherwise.
     */
    private boolean isUncleOrAunt(Person person1, Person person2) {
        return person1.findNephewsAndNieces().contains(person2);
    }

    /**
     * Checks if the first person is a nephew or niece of the second person.
     *
     * @param person1 the first person.
     * @param person2 the second person.
     * @return {@code true} if the first person is a nephew or niece of the second person, {@code false} otherwise.
     */
    private boolean isNephewOrNiece(Person person1, Person person2) {
        return person1.findUnclesAndAunts().contains(person2);
    }

    /**
     * Sorts the people in the genealogical tree by name and returns them in a list.
     *
     * @return the people sorted by name.
     */
    public List<Person> getPeopleInOrder() {
        return people.values().stream().sorted(Comparator.comparing(Person::getName)).toList();
    }
}
