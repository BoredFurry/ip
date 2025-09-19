package ryuji.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Ryuji ryuji = new Ryuji();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            // Add the CSS file to the scene
            scene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());

            stage.setTitle("RyujiCafe");
            Image icon = new Image(this.getClass()
                    .getResourceAsStream("/images/RyujiPFP.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setRyuji(ryuji);  // inject the ryuji instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
