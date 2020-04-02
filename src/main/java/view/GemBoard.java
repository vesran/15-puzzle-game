package view;

import model.Board;
import model.Piece;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GemBoard {

    private List<Tile> tiles;
    private Image imageSource;


    public GemBoard(Board board) {
        this.tiles = new ArrayList<>();
        this.imageSource = new Image(GemMainGUI.url, GemMainGUI.WIDTH, GemMainGUI.WIDTH, true, false);

        ImageView view = new ImageView();
        view.setImage(this.imageSource);
//        view.setFitWidth((double) GemMainGUI.WIDTH / board.getWidth());
//        view.setFitHeight((double) GemMainGUI.WIDTH / board.getHeight());

        double widthIv = (double)GemMainGUI.WIDTH / GemMainGUI.GRID_SIZE;
        double heightIv = (double)GemMainGUI.WIDTH / GemMainGUI.GRID_SIZE;
        double posX;
        double posY;

        for (Piece p : board.getPieces()) {
            posX = ((p.getId() - 1) % board.getWidth()) * widthIv;
            posY = ((p.getId() - 1) / board.getHeight()) * heightIv; // Division by int because we want the integer part
            view.setViewport(new Rectangle2D(posX, posY, widthIv, heightIv));
            tiles.add(new Tile(p, view.snapshot(null, null)));
        }
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }

    public void draw(GraphicsContext gc) {
        for (Tile tile : this.tiles) {
            tile.draw(gc);
        }
    }

}
