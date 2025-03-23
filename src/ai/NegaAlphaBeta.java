package ai;

import java.util.ArrayList;
import java.util.HashMap;
import model.Champion;
import model.Coord;
import model.Map;
/* 
        
public class NegaAlphaBeta implements Mindset{
    
    private int maxDepth;
    private Heuristic heuristic;
    private Map map;
    private ArrayList<Champion> championsList;
    private final int MAXPLAYERS = 2;
    private Coord result;
    private int id;
    private HashMap<Coord, Integer> test = new HashMap<>();

    public NegaAlphaBeta(Map map, int maxDepth, Heuristic heuristic, ArrayList<Champion> championsList, int id){
        this.map = map;
        this.maxDepth = maxDepth;
        this.heuristic = heuristic;
        this.championsList = championsList;
        this.result = null;
        this.id = id;
    }

    //La map est modifiée en simultanée avec les threads -> fausse les calculs
    //Si jamais des mouvements renvoient le même résultat en choisir un aléatoirement
    //Sortir les bons mouvements -> HashMap ?
    public int negaAlphaBeta(Coord playerCoord, int deep, int alpha, int beta) {
        ArrayList<Coord> nextCoords = new ArrayList<>();
        int possibleMoves = possibleMoves(nextCoords);
        if (possibleMoves == 0 || deep == maxDepth) {
            return (int) heuristic.estimate(playerCoord);
        }
        
        int best = -INFINITE;
        
        for (int i = 0; i < possibleMoves; i += MAXPLAYERS) {
            for (int j = 0; j < MAXPLAYERS; j++) {
                map.setCase(nextCoords.get(i+j), -1);
                championsList.get(j).getTrail().add(nextCoords.get(i+j));
            }
            best = Math.max(best, -negaAlphaBeta(nextCoords.get(i), deep+1, -beta, -alpha));
            //System.out.println(best);
            for (int j = 0; j < MAXPLAYERS; j++) {
                map.setCase(nextCoords.get(i+j), 0);
                championsList.get(j).getTrail().pop();
            }

            if (best >= beta) {
                if (deep == 0) {
                    System.out.println("First");
                    System.out.println(id + " ->");
                    test.put(result, best);
                }
                return best;
            }
            
            alpha = Math.max(alpha, best);

        }
        result = nextCoords.get(id-1);

        return best;
    }

    private int possibleMoves(ArrayList<Coord> nextCoords) {
        int[][] grid = map.getGrid();
        ArrayList<Integer> emptyCases = map.getEmptyCases();
        HashMap<Integer, ArrayList<Coord>> possibleMovesForEachChampion = new HashMap<>();

        for (int i = 0; i < MAXPLAYERS; i++) {
            
            ArrayList<Coord> possibleMoves = new ArrayList<>();
            Champion champion = championsList.get(i);
            int x = champion.getTrail().lastElement().getX();
            int y = champion.getTrail().lastElement().getY();

            Coord nextCoord = new Coord(x-1, y);
            if (x-1 >= 0 && emptyCases.contains(grid[x-1][y])) {
                possibleMoves.add(nextCoord);
            }
            
            nextCoord = new Coord(x, y+1);
            if (y+1 < grid.length && emptyCases.contains(grid[x][y+1])) {
                possibleMoves.add(nextCoord);
            }
            
            nextCoord = new Coord(x+1, y);
            if (x+1 < grid.length && emptyCases.contains(grid[x+1][y])) {
                possibleMoves.add(nextCoord);
            }
            
            nextCoord = new Coord(x, y-1);
            if (y-1 >= 0 && emptyCases.contains(grid[x][y-1])) {
                possibleMoves.add(nextCoord);
            }

            possibleMovesForEachChampion.put(i, possibleMoves);
        }

        ArrayList<Coord> firstChampionMoves = possibleMovesForEachChampion.get(0);
        ArrayList<Coord> secondChampionMoves = possibleMovesForEachChampion.get(1);

        for (int i = 0; i < firstChampionMoves.size(); i++) {
            for (int j = 0; j < secondChampionMoves.size(); j++) {
                nextCoords.add(firstChampionMoves.get(i));
                nextCoords.add(secondChampionMoves.get(j));
            }
        }

        return nextCoords.size();
    }

    @Override
    public Coord launch(Coord playerCoord, int deep, int alpha, int beta) {
        Map originalMap = map;
        map = new Map(map.getGrid(), map.getEmptyCases());
        negaAlphaBeta(playerCoord, deep, alpha, beta);
        System.out.println(test);
        map = originalMap;
        return result;
    }

}
*/