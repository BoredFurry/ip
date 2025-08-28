package ryuji.ui;

public class RyujiException extends Exception {
    RyujiException(String message) {
        super(message);
    }

    RyujiException() {
        super("");
    }

    public void showInputError() {
        System.out.println(this.getMessage());
    }

    public void showFileNotFound() {

    }

    public void showInvalid() {

    }
}
