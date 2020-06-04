package olech.marcin.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import olech.marcin.model.Model;
import olech.marcin.view.FXMLController;
import olech.marcin.view.View;


public class Controller {
    private FXMLController fxmlController;
    private Model model;
    private View view;
    private AnimationTimer animationTimer;
    private Canvas canvas;
    private final long tickLengthNanos =(long)((double)1/30*1e9); // 1 / 30 (ticks per seconds) * 1e9 (converting to nanosecs)

    /**
     * @param fxmlController FXMLController object needs to be passed to constructor
     *                       (can be obtained from FXMLLoader)
     */
    public Controller(FXMLController fxmlController, Parent root, Stage stage) {
        this.fxmlController = fxmlController;
        this.canvas = fxmlController.getCellCanvas(); // ->
        initModel();

        Scene scene = getInitScene(root);
        view = new View(model, canvas);
        view.drawBoard();

        stage.setTitle("Conway's Game of Life");
        stage.setScene(scene);
        stage.show();

    }

    private Scene getInitScene(Parent root) {
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER)
                startNextPattern();
            startSimulation();
        });
        scene.getStylesheets().add("/styles/Styles.css");
        return scene;
    }

    private void initModel() {
        model = new Model();
        startNextPattern();

        animationTimer = new AnimationTimer() {
            private long previous = 0;
            @Override
            public void handle(long current) {
                if ((current - previous) >= tickLengthNanos ) {
                    model.tick();
                    view.drawBoard();
                    previous = current;
                }
            }
        };
    }

    /**
     * Starts simulation (animation)
     */
    public void startSimulation() {
        animationTimer.start();
    }

    /**
     * Sets next pattern for the simulation (next value of Pattern enum)
     */
    public void startNextPattern() {
        model.nextPattern();
        fxmlController.updateCurrentPattern(model.getPattern());

    }



}
