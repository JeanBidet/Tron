package model;

import java.util.ArrayList;

public class Map {
    
    private int[][] grid;
    private ArrayList<Integer> emptyCases = new ArrayList<>();
    private final int SIZE;

    public Map(int size) {
        this.SIZE = size;
        this.grid = new int[SIZE][SIZE];
        this.emptyCases.add(0);
    }

    public Map(int[][] grid, ArrayList<Integer> emptyCases) {
        this.SIZE = grid.length;
        this.grid = new int[SIZE][SIZE];
        this.emptyCases = emptyCases;
        copyGrid(grid);
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getSIZE() {
        return SIZE;
    }

    public ArrayList<Integer> getEmptyCases() {
        return emptyCases;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setCase(Coord playerCoord, int id) {
        grid[playerCoord.getY()][playerCoord.getX()] = id; 
    }

    private void copyGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public void addUselessTrail(Champion champion) {
        emptyCases.add(champion.getId());
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < getSIZE(); i ++){
            for(int j = 0; j < getSIZE(); j ++){
                res += grid[i][j];
            }
            res += "\n";
        }
        return res;
    }

}