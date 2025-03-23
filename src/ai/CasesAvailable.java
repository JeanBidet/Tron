package ai;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import model.Coord;
import model.Map;

public class CasesAvailable extends AbstractHeuristic {

    public CasesAvailable(Map map) {
        super(map);
    }

    public float estimate(Coord playerCoord) {
        int score = 0;
        LinkedList<Coord> open = new LinkedList<>();
        Set<Coord> closed = new LinkedHashSet<>();
        open.add(playerCoord);
        
        while (!open.isEmpty()) {
            Coord currentCase = open.pollFirst();
            int x = currentCase.getX();
            int y = currentCase.getY();
            closed.add(new Coord(x,y,gridSize));
            
            Coord nextCoord = new Coord(x-1, y, gridSize);
            if (x-1 >= 0 && emptyCases.contains(grid[x-1][y]) && !closed.contains(nextCoord)) {
                open.add(nextCoord);
                closed.add(nextCoord);
            }
            
            nextCoord = new Coord(x, y+1, gridSize);
            if (y+1 < grid.length && emptyCases.contains(grid[x][y+1]) && !closed.contains(nextCoord)) {
                open.add(nextCoord);
                closed.add(nextCoord);
            }
            
            nextCoord = new Coord(x+1, y, gridSize);
            if (x+1 < grid.length && emptyCases.contains(grid[x+1][y]) && !closed.contains(nextCoord)) {
                open.add(nextCoord);
                closed.add(nextCoord);
            }
            
            nextCoord = new Coord(x, y-1,gridSize);
            if (y-1 >= 0 && emptyCases.contains(grid[x][y-1]) && !closed.contains(nextCoord)) {
                open.add(nextCoord);
                closed.add(nextCoord);
            }
        }

        score += closed.size() - 1; //On enlève la case sur laquelle se trouve le joueur
        return score;
    }
}