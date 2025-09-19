package ryuji.ui;

import ryuji.storage.Storage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ryuji ryuji;

    private final Image userImage = new Image(this
            .getClass().getResourceAsStream("/images/UserPFP.png"));
    private final Image ryujiImage = new Image(this.getClass()
            .getResourceAsStream("/images/RyujiPFP.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getRyujiDialog(Ui.showWelcome(), ryujiImage));
        dialogContainer.getChildren().addAll(DialogBox.getRyujiDialog(Ui.showWelcome(), ryujiImage));
    }

    /**
     * Injects the Ryuji instance
     */
    public void setRyuji(Ryuji ryuji) {
        this.ryuji = ryuji;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ryuji.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRyujiDialog(response, ryujiImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(1);
                    timer.cancel();
                }
            }, 5000);
        }
    }

}

