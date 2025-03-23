package model;

public class Coord {

    private int x;
    private int y;
    private int GRIDSIZE = 0;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord(int x, int y, int gridSize) {
        this(x,y);
        this.GRIDSIZE = gridSize;
    }

    /*Methods : */
    public void addVector(Vector vector){
        setX(getX()+vector.getXDirection());
        setY(getY()+vector.getYDirection());
    }

    /*Getters : */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*Setters : */
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coord) {
            Coord object = (Coord) obj;
            return (this.x == object.getX() && this.y == object.getY());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x*GRIDSIZE + y;
    }
}
