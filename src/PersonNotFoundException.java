/**
 * This class represents a custom exception that is thrown when a person is not found.
 *
 * @author George Gkonis
 * @version 1.0
 */
public class PersonNotFoundException extends Exception {

    /**
     * Constructor method for the {@link PersonNotFoundException} class.
     *
     * @param message the exception message.
     */
    public PersonNotFoundException(String message) {
        super(message);
    }
}
