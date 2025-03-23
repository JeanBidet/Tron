package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ai.ThinkingThread;

public class Referee {
    
    private HashMap<Integer,Champion> champMap;

    //L'arbitre à trois états : celui ou il constate un gagnant, celui ou ca joue et celui ou il remarque qu'il n'y a plus aucun joueur sur la map (égalité)
    private String state;
    private Map map;
    ArrayList<Champion> champions;
    private ArrayList<Champion> referedChampions;

    public Referee(ArrayList<Champion> champions, Map map){

        this.state="IN PROGRESS";
        this.champions=champions;

        /*
        this.champMap = new HashMap<Integer,Champion>();
        for (Champion champion : champions) {
            this.champMap.put(champion.getId(),champion);
        }
        */
        this.map = map;

        this.referedChampions = new ArrayList<>(this.champions);
    }

    /* Methods : */

    public Boolean isADeadOrEmptyCell(int cellContent){
        if(cellContent != 0 || ! map.getEmptyCases().contains(cellContent)){
            return false;
        }
        return true;
    }

    public void referChamp(){

        //Attention les Champs' sont en threads.
        //List<Thread> threads = new ArrayList<>();
        /*
        Cette boucle est une manière opti d'ordre n de traiter le placement de chaque joueur afin de savoir si ils se touchent ou non.
        Elle vérifie si la cellule sur laquel se déplace le joueur est libre ou non si il s'est déplac" sur un cellule prise alors le joueur est mort.
        */
            for (int i = 0; i < this.champions.size(); i++) {
                //System.out.println(this.map.getGrid().length);
                if (!isADeadOrEmptyCell(this.map.getGrid()[this.champions.get(i).getTrail().peek().getX()][this.champions.get(i).getTrail().peek().getY()])==true) {
                    map.addUselessTrail(this.champions.get(i));
                    referedChampions.remove(this.champions.get(i));
                    ThinkingThread champThread = Tron.threadsClass.get(i);
                    champThread.stopIt();
                    
                    }
                }

        //this.champions = referedChampions;//La liste des Champions est mise à jour

        
        for(int i=0; i<this.champions.size(); i++){
            map.getGrid()[champions.get(i).getTrail().peek().getX()][champions.get(i).getTrail().peek().getY()] = champions.get(i).getId();
        }

        if(this.champions.size() == 0){
            this.state="DRAW";
        }

        if(this.champions.size() == 1){
            this.state="WINNER";
        }
    }

    public ArrayList<Champion> getChampList() {
        return this.champions;
    }

    public String getWinState() {
        return this.state;
    }
    
}