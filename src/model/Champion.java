package model;

import java.sql.Ref;
import java.util.Stack;
import ai.Mindset;

public class Champion{
    
    private Mindset mindset;
    private Stack<Coord> trail = new Stack<>();
    private int id;
    private Vector vector;

    public Champion(Mindset mindset,Referee referee, Coord coord, int id, Vector vector) {
        this.mindset = mindset;
        this.trail.push(coord);
        this.id = id;
        this.vector= vector;
        this.vector.setID(this.id);
    }

    /* Getters: */
    public Vector getVector() {
        return vector;
    }

    public Mindset getMindset() {
        return mindset;
    }

    public Stack<Coord> getTrail() {
        return trail;
    }

    public int getId() {
        return id;
    }

    /* Setters: */

    public void setVector(Vector vector) {
        this.vector = vector;
    }

    public void setMindset(Mindset mindset) {
        this.mindset = mindset;
    }

    /* Methods: */
    public void addCoord(Coord coord) {
        this.trail.push(coord);
    }

    
    public void run(){
        Coord newCoord = this.trail.peek();
        newCoord.addVector(mindset.getVector());
        this.trail.add(newCoord);
        System.out.println(this.id+" go to "+this.trail.peek());
    }
    
}
