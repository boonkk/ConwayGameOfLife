package olech.marcin.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import olech.marcin.model.Model;
import olech.marcin.view.FXMLController;


public class Controller {
    private FXMLController fxmlController;
    private Model model;
    private AnimationTimer animationTimer;
    private Canvas canvas;
    private final long tickLengthMillis=(long)((double)1/30*1e9); // 1 / 30 (ticks per seconds) * 1e9 (converting to nanosecs)

    /**
     * @param fxmlController FXMLController object needs to be passed to constructor
     *                       (can be obtained from FXMLLoader)
     */
    public Controller(FXMLController fxmlController) {
        this.fxmlController = fxmlController;
        this.canvas = fxmlController.getCellCanvas();
        initModel();
    }

    private void initModel() {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        model = new Model(graphics);
        graphics.setFill(Color.LAVENDER);
        startNextPattern();
        animationTimer = new AnimationTimer() {
            private long previous = 0;
            @Override
            public void handle(long current) {
                if ((current - previous) >= tickLengthMillis) {
                    model.tick();
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
