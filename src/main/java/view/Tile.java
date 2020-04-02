package view;

import model.Piece;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Tile {

    private Piece piece;
    private static int width = GemMainGUI.WIDTH / GemMainGUI.GRID_SIZE;
    private WritableImage croppedImage;

    public Tile(Piece piece, WritableImage image) {
        this.piece = piece;
        this.croppedImage = image;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public boolean contains(double x, double y) {
        boolean betweenX = this.piece.getX() * width <= x && x <= this.piece.getX() * width + width;
        boolean betweenY = this.piece.getY() * width <= y && y <= this.piece.getY() * width + width;
        return betweenX && betweenY;
    }

    public void draw(GraphicsContext gc) {
        if (this.piece == null) return;

        int x = this.piece.getX() * width;
        int y = this.piece.getY() * width;

        if (this.croppedImage != null) {
            gc.drawImage(this.croppedImage, x, y);
        } else {
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, width, width);
        }

        gc.setStroke(Color.BLACK);
        gc.strokeRect(x, y, width, width);


        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(this.piece.getId()), x + width / 2.0, y + width / 2.0);
    }

}
