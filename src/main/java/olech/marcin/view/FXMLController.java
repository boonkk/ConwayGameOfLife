package olech.marcin.view;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import olech.marcin.model.Model;
import olech.marcin.model.Pattern;

public class FXMLController implements Initializable {
    @FXML
    private Canvas cellCanvas;
    @FXML
    private Label currentPattern;

    /**
     * Updates label with given starting pattern
     * @param currentStartingPattern value of Pattern enum
     */
    public void updateCurrentPattern(Pattern currentStartingPattern) {
        currentPattern.setText(currentStartingPattern.name());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cellCanvas.setHeight(Model.BOARD_HEIGHT);
        cellCanvas.setWidth(Model.BOARD_WIDTH);
    }

    public Canvas getCellCanvas() {
        return cellCanvas;
    }

}
