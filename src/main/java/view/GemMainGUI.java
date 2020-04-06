package view;

import controller.MouseController;
import model.Board;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GemMainGUI extends Application {

    public static final int WIDTH = 400;
    public static final int GRID_SIZE = 2;
    public static final String url = "https://66.media.tumblr.com/009b6f44bd6b2876c97c8afa2955ae6f/tumblr_pkyp42fG3L1v46ab0o1_400.jpg";

    private GemBoard container;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, WIDTH);

        Canvas canvas = new Canvas(WIDTH, WIDTH);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Board model = new Board(GRID_SIZE, GRID_SIZE);
        this.container = new GemBoard(model);
        scene.setOnMouseClicked(new MouseController(model, this.container));

        stage.setTitle("15-puzzle game");
        stage.setScene(scene);
        this.run(gc);
        stage.show();
    }

    private void run(final GraphicsContext gc) {
        new AnimationTimer() {

            @Override
            public void handle(long l) {
                // Erase former drawings
                gc.clearRect(0, 0, WIDTH, WIDTH);

                // Drawing tiles
                GemMainGUI.this.container.draw(gc);

            }
        }.start();
    }

}
