/**
 * @author George Gkonis
 * @version 1.0
 * <p>
 * This class represents a custom exception that is thrown when an invalid input is provided.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructor method for the {@link InvalidInputException} class.
     */
    public InvalidInputException() {
    }

    /**
     * Constructor method for the {@link InvalidInputException} class.
     *
     * @param message the exception message.
     */
    public InvalidInputException(String message) {
        super("Invalid Input! " + message);
    }
}
