package olech.marcin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import olech.marcin.controller.Controller;
import olech.marcin.view.FXMLController;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));

        Parent root = loader.load();
        FXMLController fxmlController = loader.getController();
        Controller controller = new Controller(fxmlController, root, stage);

        controller.startSimulation();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
