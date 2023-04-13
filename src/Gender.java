/**
 * @author George Gkonis
 * @version 1.0
 * <p>
 * Enumerates the genders of a person.
 */
public enum Gender {
    MAN, WOMAN;

    /**
     * Overrides the {@link Object#toString()} method, to return a string representation of the gender.
     *
     * @return the gender in lowercase.
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
