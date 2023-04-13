/**
 * @author George Gkonis
 * @version 1.0
 * <p>
 * Enumerates the possible relations of a person to another person.
 */
public enum Relation {
    FATHER, MOTHER,
    SON, DAUGHTER,
    BROTHER, SISTER,
    HUSBAND, WIFE,
    UNCLE, AUNT,
    COUSIN,
    GRANDFATHER, GRANDMOTHER,
    GRANDSON, GRANDDAUGHTER,
    NEPHEW, NIECE,
    UNRELATED;

    /**
     * Overrides the {@link Object#toString()} method, to return a string representation of the relation.
     *
     * @return the relation in lowercase.
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
