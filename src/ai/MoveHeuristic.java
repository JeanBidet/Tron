package ai;

import model.Vector;

public class MoveHeuristic {
    
    private Vector v;
    private int[] heuristic;

    public MoveHeuristic(){
        this.v = new Vector();
    }

    public MoveHeuristic(int[] heuristic){
        this.v = new Vector();
        this.heuristic = heuristic;
    }

    public MoveHeuristic(Vector v, int[] heuristic){
        this.v = v;
        this.heuristic = heuristic;
    }

    public void setV(Vector v) {
        this.v = v;
    }

    public void setHeuristic(int[] heuristic) {
        this.heuristic = heuristic;
    }

    public Vector getV() {
        return v;
    }

    public int[] getHeuristic() {
        return heuristic;
    }

    @Override
    public String toString() {
        return ("Vecteur: " + this.v + ", heuristique: " + this.heuristic);
    }

}
