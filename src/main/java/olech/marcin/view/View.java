package olech.marcin.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import olech.marcin.model.Model;

public class View {
    private Model model;
    private Canvas canvas;
    private GraphicsContext graphics;

    public View(Model model, Canvas canvas) {
        this.model = model;
        this.canvas = canvas;
        graphics = canvas.getGraphicsContext2D();
    }

    public void drawBoard() {
        boolean[][] cellArray = model.getCellArray();
        graphics.setFill(Color.LIGHTGREY);
        graphics.fillRect(0, 0, Model.BOARD_WIDTH, Model.BOARD_HEIGHT);

        for (int y = 0; y < cellArray.length; y++) {
            for (int x = 0; x < cellArray[y].length; x++) {
                graphics.setFill(Color.LIGHTSLATEGREY);
                graphics.fillOval(x * Model.CELL_SIZE, y * Model.CELL_SIZE, Model.CELL_SIZE, Model.CELL_SIZE);

                if( cellArray[y][x] ) {
                    graphics.setFill(Color.RED);
                } else {
                    graphics.setFill(Color.LIGHTGRAY);
                }
                graphics.fillOval((x * Model.CELL_SIZE) + 1, (y * Model.CELL_SIZE) + 1, Model.CELL_SIZE - 2, Model.CELL_SIZE - 2);
            }
        }
    }
}
