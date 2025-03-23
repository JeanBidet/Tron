package ai;

import java.util.ArrayList;

import model.*;

public class MaxN implements Mindset{

    private Map map;
    private int maxDepth;
    private Heuristic heuristic;
    private ArrayList<Champion> championsList;
    private int id;
    private Vector vector;
    private int nbPlayers;

    public MaxN(Map map, int maxDepth, Heuristic heuristic, ArrayList<Champion> championsList, int id, Vector vector){
        this.map = map;
        this.maxDepth = maxDepth;
        this.heuristic = heuristic;
        this.championsList = championsList;
        this.id = id;
        this.nbPlayers = championsList.size();
        this.vector = vector;
    }

    private MoveHeuristic maxN2(int deep, int idChampion){

        // Initialisation de la liste de choix qui permet de répertorier les choix
        // de directions avec leurs heuristiques associées
        MoveHeuristic best = new MoveHeuristic();

        // Les coordonnées possibles pour le champion courant lors de son prochain coup
        ArrayList<Coord> possibleCoords = possibleCoords(idChampion); 
        
        if (possibleCoords.size() == 0 || deep == maxDepth) {
            
            return new MoveHeuristic(heuristicMultiPlayer(idChampion));

        }

        // Boucle pour créer tous les arbres de possibilités
        for(Coord nextCoord : possibleCoords){

            // On "joue" le coup
            this.map.setCase(nextCoord, idChampion);

            // Récupère l'heuristique "max" du prochain Champion
            int[] heuristic = maxN2(deep+1, getNextId(idChampion)).getHeuristic();
            
            // Reset de la case "joué"
            this.map.setCase(nextCoord, 0); 

            // Récupération de la coordonnée du joueur courant
            Champion champion = championsList.get(idChampion-1);

            //System.out.println("MAIS VAS Y :"+Referee.referedChampions.toString());

            Coord coordPlayer = champion.getTrail().lastElement();

            // Ajoute un choix de direction avec son heuristique associée
            Vector v = new Vector(coordPlayer, nextCoord, idChampion);
            //System.out.println(v + " c1 = (" + coordPlayer.getX() + ", " + coordPlayer.getY() + ") c2 = (" + nextCoord.getX() + ", " + nextCoord.getY() + ")");

            //System.out.println(championsList.size());
            if(best.getHeuristic() == null){
                best.setV(v);
                best.setHeuristic(heuristic);
            }
            else{
                for(int i = 0; i < championsList.size(); i++){
                    if(championsList.get(i).getId() == idChampion){
                        //System.out.println(heuristic.length + " i: " + i);
                        if(best.getHeuristic()[i] < heuristic[i]){
                            best.setV(v);
                            best.setHeuristic(heuristic);
                        }
                    }
                }
            }
            

        }
        if(idChampion == this.id){
            String res = this.id + best.getV().toString() + ": heuristic :";
            for(int i : best.getHeuristic()){
                res += i + ", ";
            }
            //System.out.println(res);
        }
        //System.out.println(best.getV());
        return best;

    }

    private int getNextId(int idChampion){
        int idNextChampion = idChampion;
        if(idChampion >= championsList.size()){
            idNextChampion = championsList.get(1).getId();
        }
        else{
            for(int i = 0; i < championsList.size(); i++){
                //System.out.println(nbPlayers);
                if(championsList.get(i).getId() == idChampion){
                    idNextChampion = championsList.get(i+1).getId();
                }
            }
        }
        return idNextChampion;
    }

    //Problème: Si un joueur meurt -> championList -- -> pos Champ heuristic plus bonne par rapport à son id
    private int[] heuristicMultiPlayer(int idChampion){
        int[] heuristic = new int[this.championsList.size()];

        // Calcule l'heuristique de chacun des champions pour la position du joueur courant
        for(int i = 0; i < this.championsList.size(); i++){

            // Récupération de la position du champion i
            Champion champion = this.championsList.get(i);
            Coord coordPlayer = champion.getTrail().lastElement();

            // Calcul de l'heuristique du Champion i
            heuristic[champion.getId()-1] = (int) this.heuristic.estimate(coordPlayer);

        }

        return heuristic;
    }

    private ArrayList<Coord> possibleCoords(int idChampion){
        // Récupération de la position du champion i
        Champion champion = championsList.get(idChampion-1);
        Coord coordPlayer = champion.getTrail().lastElement();

        // Récupération de la map et des entiers correspondants aux cases vides
        int[][] grid = map.getGrid();
        ArrayList<Integer> emptyCases = map.getEmptyCases();

        ArrayList<Coord> nextCoords = new ArrayList<>();

        int x = coordPlayer.getX();
        int y = coordPlayer.getY();

        Coord nextCoord = new Coord(x-1, y);
        if (x-1 > 0 && emptyCases.contains(grid[x-1][y])) {
            //System.out.println(idChampion+  " idChampion");
            nextCoords.add(nextCoord);
        }
        
        nextCoord = new Coord(x, y+1);
        if (y+1 < grid.length && emptyCases.contains(grid[x][y+1])) {
            nextCoords.add(nextCoord);            
        }
        
        nextCoord = new Coord(x+1, y);
        if (x+1 < grid.length && emptyCases.contains(grid[x+1][y])) {
            nextCoords.add(nextCoord);
        }
        
        nextCoord = new Coord(x, y-1);
        if (y-1 > 0 && emptyCases.contains(grid[x][y-1])) {
            nextCoords.add(nextCoord);
        }

        System.out.println(idChampion + ": Coord Init : " + coordPlayer + " Coord Finales : " + nextCoords);

        return nextCoords;

    }

    @Override
    public void launch(Coord playerCoord, int deep, int alpha, int beta) {
        Map originalMap = map;        
        map = new Map(map.getGrid(), map.getEmptyCases());
        //long startTime = System.currentTimeMillis();
        Vector newVector = maxN2(deep, this.id).getV();
        if(newVector.getPolaDirection() != "NULL"){
            this.vector = newVector;
        }
        //long endTime = System.currentTimeMillis();
        //long executionTime = endTime - startTime;
        //System.out.println("Temps d'exécution: " + executionTime + " ms");
        //System.out.println(this.id + ": " + this.vector + "\nCoord " + this.id + ": " + playerCoord + " to : (" + (this.vector.getXDirection() + playerCoord.getX()) + ", " + (this.vector.getYDirection() + playerCoord.getY()) + "\n" + possibleCoords(this.id));
        map = originalMap;
        //System.out.println(this.id + ": " + this.getVector());
        /*for(Champion c : this.championsList){
            System.out.println(c);
        }*/
    }
    
    @Override
    public Vector getVector() {
        return this.vector;
    }
    
}
