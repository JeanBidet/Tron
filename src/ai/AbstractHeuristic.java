package ai;

import java.util.ArrayList;
import model.Coord;
import model.Map;

public abstract class AbstractHeuristic implements Heuristic {
    
    int[][] grid;
    ArrayList<Integer> emptyCases;
    int gridSize;

    public AbstractHeuristic(Map map) {
        this.grid = map.getGrid();
        this.emptyCases = map.getEmptyCases();
        this.gridSize = map.getSIZE();
    }

    public float estimate(Coord playerCoord) {
        return 1;
    }
}