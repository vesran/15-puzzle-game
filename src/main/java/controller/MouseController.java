package controller;

import model.Board;
import model.Piece;
import view.GemBoard;
import view.Tile;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseController implements EventHandler<MouseEvent> {

    private Board model;
    private GemBoard view;

    public MouseController(Board model, GemBoard view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        for (Tile currentTile : this.view.getTiles()) {
            if (currentTile.contains(mouseEvent.getX(), mouseEvent.getY())) {
                this.model.move(currentTile.getPiece());
                this.model.update();
                return;
            }
        }
    }
}
