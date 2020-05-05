package olech.marcin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import olech.marcin.controller.Controller;
import olech.marcin.view.FXMLController;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        FXMLController fxmlController = loader.getController();
        Controller controller = new Controller(fxmlController);

        scene.getStylesheets().add("/styles/Styles.css");
        scene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER)
                controller.startNextPattern();
                controller.startSimulation();
        });
        stage.setTitle("Conway's Game of Life");
        stage.setScene(scene);
        stage.show();
        controller.startSimulation();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
