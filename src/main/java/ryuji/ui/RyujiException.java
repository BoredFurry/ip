package ryuji.ui;

public class RyujiException extends Exception {
    public RyujiException(String message) {
        super(message);
    }

    RyujiException() {
        super("");
    }

    public void showInputError() {
        System.out.println(this.getMessage());
    }

    public void showFileNotFound() {
        System.out.println(this.getMessage());
    }

    public void showInvalid() {
        System.out.println(this.getMessage());
    }
}
