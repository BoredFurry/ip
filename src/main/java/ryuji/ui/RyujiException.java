package ryuji.ui;

/**
 * The {@code RyujiException} class represents exceptions specific to the Ryuji chatbot application.
 * It extends the built-in {@link Exception} class and is used to signal application-specific errors,
 * such as invalid input or issues loading files.
 */
public class RyujiException extends Exception {

    /**
     * Constructs a {@code RyujiException} with the specified error message.
     *
     * @param message the detailed error message
     */
    public RyujiException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code RyujiException} with an empty message.
     */
    RyujiException() {
        super("");
    }

    /**
     * Displays the stored error message, typically used for input-related errors.
     */
    public void showInputError() {
        System.out.println(this.getMessage());
    }

    /**
     * Displays the stored error message, typically used when the task file is not found.
     */
    public void showFileNotFound() {
        System.out.println(this.getMessage());
    }

    /**
     * Displays the stored error message, typically used for invalid commands or data.
     */
    public void showInvalid() {
        System.out.println(this.getMessage());
    }
}
