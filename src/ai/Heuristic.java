package ai;

import model.Coord;

public interface Heuristic {
    
    public float estimate(Coord playerCoord);

}