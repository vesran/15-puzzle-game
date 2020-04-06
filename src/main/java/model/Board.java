package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Board implements Cloneable {

    private int boundX;
    private int boundY;
    private List<Piece> pieces;
    private int emptyPositionX;
    private int emptyPositionY;
    private Piece triggered;

    private Board() {
        this.pieces = new ArrayList<>();
    }

    public Board(int x, int y) {
        this();
        this.boundX = x;
        this.boundY = y;

        // Create each pieces with their position in the board
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i < this.boundX * this.boundY; i++) {
            ids.add(i);
        }
        Iterator<Integer> it = ids.iterator();

        for (int j = 0; j < this.boundY; j++) {
            for (int i = 0; i < this.boundY; i++) {
                if (i != this.boundX - 1 || j != this.boundY - 1) this.pieces.add(new Piece(i, j, it.next()));
            }
        }

        this.emptyPositionX = this.boundX - 1;
        this.emptyPositionY = this.boundY - 1;

        this.shuffle();
    }

    public void setTriggered(Piece p) {
        this.triggered = p;
    }

    public Piece getTriggered() {
        return this.triggered;
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public int getWidth() {
        return this.boundX;
    }

    public int getHeight() {
        return this.boundY;
    }

    public int getEmptyPositionX() {
        return this.emptyPositionX;
    }

    public int getEmptyPositionY() {
        return this.emptyPositionY;
    }

    public void move(Piece currentPiece) {
        if (((Math.abs(currentPiece.getX() - this.emptyPositionX) <= 1) && !(Math.abs(currentPiece.getY() - this.emptyPositionY) >= 1)) ||
                (!(Math.abs(currentPiece.getX() - this.emptyPositionX) >= 1) && (Math.abs(currentPiece.getY() - this.emptyPositionY) <= 1))) {
            int saveX = currentPiece.getX();
            int saveY = currentPiece.getY();

            currentPiece.setX(this.emptyPositionX);
            currentPiece.setY(this.emptyPositionY);

            this.emptyPositionX = saveX;
            this.emptyPositionY = saveY;
            this.pieces.sort(Piece::compareTo);

        } else {
            System.out.println("Error !");
        }
    }

    public List<Piece> moveablePieces() {
        List<Piece> moveable = new ArrayList<>();
        if (this.emptyPositionX != 0) {
            moveable.add(this.pieceAt(this.emptyPositionX - 1, this.emptyPositionY));
        }
        if (this.emptyPositionX != this.boundX - 1) {
            moveable.add(this.pieceAt(this.emptyPositionX + 1, this.emptyPositionY));
        }
        if (this.emptyPositionY != 0) {
            moveable.add(this.pieceAt(this.emptyPositionX, this.emptyPositionY - 1));
        }
        if (this.emptyPositionY != this.boundY - 1) {
            moveable.add(this.pieceAt(this.emptyPositionX, this.emptyPositionY + 1));
        }
        return moveable;
    }

    public Piece pieceAt(int x, int y) {
        for (Piece piece : this.pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }
        throw new IllegalArgumentException("No piece at " + x + " " + y + " in the board.");
    }

    private boolean isSolvable() {
        // Count the number of inversion
        Piece currentPiece;
        int inversion;
        int totalInversion = 0;

        for (int i = 0; i < this.pieces.size() - 1; i++) {
            currentPiece = this.pieces.get(i);
            inversion = 0;
            for (int j = i + 1; j < this.pieces.size(); j++) {
                if (currentPiece.getId() > this.pieces.get(j).getId()) {
                    inversion++;
                }
            }
            totalInversion += inversion;
        }

        // Examine the total number of inversion
        if (this.boundX % 2 != 0) {
            return totalInversion % 2 == 0;
        } else {
            if ((this.boundY - this.emptyPositionY) % 2 == 0) {
                return totalInversion % 2 != 0;
            } else {
                return totalInversion % 2 == 0;
            }
        }
    }

    private void shuffle() {
        Collections.shuffle(this.pieces);
        if (!this.isSolvable()) {
            Collections.swap(this.pieces, 0, 1);
        }

        // Synchronize with pieces's position in the grid
        Iterator<Piece> it = this.pieces.iterator();
        Piece currentPiece;

        for (int j = 0; j < this.boundY; j++) {
            for (int i = 0; i < this.boundX; i++) {
                if (it.hasNext()) {
                    currentPiece = it.next();
                    currentPiece.setX(i);
                    currentPiece.setY(j);
                }
            }
        }
//        System.out.println(this.pieces);
    }

    public boolean isSolved() {
        for (int i = 0; i < this.pieces.size() - 1; i++) {
            if (this.pieces.get(i).getId() > this.pieces.get(i + 1).getId()) {
                return false;
            }
        }
        return (this.emptyPositionX == this.boundX - 1 && this.emptyPositionY == this.boundY - 1);
    }

    public void update() {
        this.pieces.sort(Piece::compareTo);
        System.out.println(this.pieces);

        if (this.isSolved()) {
            System.out.println("SOLVED !");
        }
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Board)) return false;
        Board another = (Board) o;
        if (this.emptyPositionX != another.emptyPositionX || this.emptyPositionY != another.emptyPositionY) {
            return false;
        }
        Iterator<Piece> itThis = this.pieces.iterator();
        Iterator<Piece> itAnother = another.pieces.iterator();
        Piece pthis, pano;
        while (itThis.hasNext() && itAnother.hasNext()) {
            pthis = itThis.next();
            pano = itAnother.next();
            if (pthis.getId() != pano.getId()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < this.pieces.size(); i++) {
            strb.append(this.pieces.get(i));
            strb.append(", ");
        }
        strb.append(this.emptyPositionX + " " + this.emptyPositionY);
        return strb.toString();
    }

    @Override
    public Board clone() {
        Board another = new Board();
        another.boundX = this.boundX;
        another.boundY = this.boundY;
        another.emptyPositionX = this.emptyPositionX;
        another.emptyPositionY = this.emptyPositionY;
        for (Piece piece : this.pieces) {
            another.pieces.add(piece.clone());
        }
        return another;
    }
}
