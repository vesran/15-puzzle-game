package model;

public class Piece implements Comparable<Piece>, Cloneable {
    private final int id;
    private int x;
    private int y;

    public Piece(int x, int y, int id) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "p" + this.id + "(" + this.x + "," + this.y + ")";
    }

    @Override
    public int compareTo(Piece piece) {
        if (this.y != piece.y) {
            return this.y - piece.y;
        }
        return this.x - piece.x;
    }

    @Override
    public Piece clone() {
        return new Piece(this.x, this.y, this.id);
    }
}
