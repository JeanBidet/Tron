package ai;

import java.nio.file.DirectoryIteratorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import model.Champion;
import model.Coord;
import model.Map;
import model.Vector;

public class Dumbmind implements Mindset{
    
    private int maxDepth;
    private Heuristic heuristic;
    private Map map;
    private ArrayList<Champion> championsList;
    private final int MAXPLAYERS = 2;
    private Coord result;
    private int id;
    private int directionCount = 4;
    private Vector vector;
    private String polarDirection;

    private int prevDirection = -1;

    public Dumbmind(Map map, int maxDepth, Heuristic heuristic, ArrayList<Champion> championsList, int id,Vector vector){
        this.map = map;
        this.maxDepth = maxDepth;
        this.heuristic = heuristic;
        this.championsList = championsList;
        this.result = null;
        this.id = id;
        this.vector=vector;
        this.polarDirection="vide";
        
    }

public Vector dumbthinking(Coord playerCoord, int deep, int alpha, int beta) {
        Random random = new Random();
        ArrayList<Coord> nextCoords = new ArrayList<>();
        
        int x = playerCoord.getX();
        int y = playerCoord.getY();
        
        final int[] currentDirection = {-1}; // Utiliser un tableau unidimensionnel pour stocker la direction actuelle
        
        Coord prevCoord = playerCoord;
        
        // Liste des directions possibles (droite, bas, gauche, haut)
        ArrayList<Integer> possibleDirections = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        
        // Si l'IA a déjà bougé, retirez la direction opposée de la liste des directions possibles
        if (this.prevDirection != -1) {
            possibleDirections.removeIf(dir -> dir == (this.prevDirection + 2) % 4);
        }
        
        // Si l'IA a déjà bougé, retirez la dernière direction de la liste des directions possibles
        if (currentDirection[0] != -1) {
            possibleDirections.removeIf(dir -> dir == currentDirection[0]);
        }
        
        // Choisissez une direction parmi les directions possibles restantes
        int randomIndex = random.nextInt(possibleDirections.size());
        int randomNumber = possibleDirections.get(randomIndex);
        currentDirection[0] = randomNumber; // Mettez à jour la direction actuelle

        // Mettez à jour la direction précédente
        this.prevDirection = currentDirection[0];

        // Sélectionner la prochaine coordonnée en fonction de la direction choisie
        Coord nextCoord;
        switch (randomNumber) {
            case 0:
                nextCoord = new Coord(x + 1, y); // Déplacer vers la droite
                break;
            case 1:
                nextCoord = new Coord(x, y + 1); // Déplacer vers le bas
                break;
            case 2:
                nextCoord = new Coord(x - 1, y); // Déplacer vers la gauche
                break;
            case 3:
                nextCoord = new Coord(x, y - 1); // Déplacer vers le haut
                break;
            default:
                // Ce cas ne doit normalement pas se produire, mais au cas où, choisissez une valeur par défaut
                nextCoord = new Coord(x, y);
                break;
        }
        
        return new Vector(playerCoord, nextCoord, this.id);
    }
    

    @Override
    public void launch(Coord playerCoord, int deep, int alpha, int beta) {

        this.vector = dumbthinking(playerCoord,0,0,0);

    }
    
    @Override
    public Vector getVector() {
        return this.vector;
    }

}