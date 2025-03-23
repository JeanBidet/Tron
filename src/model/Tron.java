package model;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ai.CasesAvailable;
import ai.Dumbmind;
import ai.MaxN;
import ai.Mindset;
import ai.Paranoid;
//import ai.NegaAlphaBeta;
import ai.ThinkingThread;
import view.ViewTron;

import java.util.ArrayList;

public class Tron {
    
    private ArrayList<Champion> championsList = new ArrayList<>(); //Liste des champions
    private ArrayList<Thread> threadsList = new ArrayList<>();//Liste des Threads lancé
    public static ArrayList<ThinkingThread> threadsClass = new ArrayList<>();//Liste des classe de Thread
    private Map map;//Map du jeu
    private Referee referee;//Arbitre du jeu
    private int id = 1;

    public Tron(HashMap<String, Integer> players, int gridSize) {
        this.map = new Map(gridSize);
        createChampionsAndThreadsList(players);
        this.referee = new Referee(championsList, map);
        
    }

    public void play(ViewTron viewTron) {
        //WIP
        //THREADS STARTED

        /*Chaque champions analyse la situation à l'aide de son threads de mindset attribué*/
        for(int i=0;i<threadsList.size();i++){
            threadsList.get(i).start();
        }

        /*Processus principal de jeu*/
        while (referee.getWinState().equals("IN PROGRESS")) {
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){}

            
            /*Chaque champion cours en ligne droite*/
            for(int i=0;i<championsList.size();i++){
                championsList.get(i).run();
            }

            /*L'arbitre check la position des joueurs*/
            referee.referChamp();

            this.championsList = referee.getChampList();
            //System.out.println(championsList.toString());
            //Actualisation de la vue
            viewTron.repaint();
        }
    }

    public Coord[] coordStart(int nbChampion){
        Coord[] coordStart = new Coord[nbChampion];
        int[] nbSliceXY = this.nbSliceXY(nbChampion);
        int xGap = (this.map.getSIZE() / (nbSliceXY[1]));
        int yGap = (this.map.getSIZE() / (nbSliceXY[0]));
        int x = xGap / 2;
        int y = yGap / 2;
        coordStart[0] = new Coord(x, y);
        for(int i = 1; i < nbChampion; i++){
            if(i % nbSliceXY[1] == 0){
                x = xGap / 2;
                y += yGap;
            }
            else{
                x += xGap;
            }
            coordStart[i] = new Coord(x, y);
        }
        return coordStart;
    }
    

    public int[] nbSliceXY(int nbChampion){
        int[] res = new int[2];
        for(int i = nbChampion-1; i > 1; i--){
            if(nbChampion % i == 0){
                res[0] = i;
                res[1] = nbChampion/i;
                return res;
            }
        }
        res[0] = nbChampion;
        res[1] = 1; 
        return res;
    }

    private void createChampionsAndThreadsList(HashMap<String, Integer> players) {
        int nbChampion = players.get("MAXN");
        Coord[] coordStart = coordStart(nbChampion);
        for (int i = 0; i < players.get("MAXN"); i++) {
            map.getGrid()[coordStart[i].getX()][coordStart[i].getY()] = id;

            Vector vector = new Vector();
            Mindset mindset = new MaxN(map, 8, new CasesAvailable(map), championsList, id, vector);
            Champion champion = new Champion(mindset,referee, coordStart[i], id,vector) ;

            championsList.add(champion);
            
            ThinkingThread thinkingThread = new ThinkingThread(champion,i);
            threadsClass.add(thinkingThread);

            System.out.println(i);
            Thread launchedThread = new Thread(thinkingThread::run);
            threadsList.add(launchedThread);

            id++;
        }
    }

    public int[][] getGrid() {
        return map.getGrid();
    }

    public ArrayList<Champion> getChampionsList() {
        return referee.getChampList();
    }

}