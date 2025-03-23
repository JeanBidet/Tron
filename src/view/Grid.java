package view;

import javax.swing.*;
import model.*;
import java.awt.*;
import java.util.*;
import java.util.Map;

public class Grid extends JPanel {

    /**
     * Taille de nos cases sur le plateau de jeu de la vue.
     */
    public int cellHeight;
    public int cellLength;
    
    /**
     * Modèle de notre jeu.
     */
    private Tron tron;
    private int height;
    private int length;

    private static final Color[] COLORSBODY = {
        new Color(255,0,0, 75), 
        new Color(255,255,0, 75),
        new Color(0,0,255, 75),
        new Color(0,255,0, 75),
        new Color(255,0,255, 75),
        new Color(255, 255, 255, 75)
    };
    private static final Color[] COLORSHEAD = {
        new Color(255,0,0), 
        new Color(255,255,0),
        new Color(0,0,255),
        new Color(0,255,0),
        new Color(255,0,255),
        new Color(255, 255, 255)
    };

    /**
     * Classe qui va nous permmettre de créer le plateau de jeu dans la vue.
     * @param plateau est le modèle de notre jeu.
     */
    public Grid(Tron tron){
        this.tron = tron;
        this.height = tron.getGrid().length;
        this.length = tron.getGrid()[0].length;
        this.cellHeight = Math.floorDiv(770 , height);
        this.cellLength = Math.floorDiv(800 , length);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Graphics2D pour avoir plus de possibilités
        Graphics2D g2d = (Graphics2D) g;
        // On augmente l'épaisseur du trait
        g2d.setStroke(new BasicStroke(1.5f));

        // On crée une grille de la taille du plateau du modèle
        for (int i = 0; i < length*cellLength; i += this.cellLength) {
            for (int j = 0; j < height*cellHeight; j += this.cellHeight) {
                g.setColor(Color.BLACK);
                g.fillRect(i, j, this.cellLength, this.cellHeight);
                g.setColor(new Color(35, 23, 54));
                g.drawRect(i, j, this.cellLength, this.cellHeight);
            }
        }

        // Colorie toutes les cases occupé par la couleur du champion associé
        for(int i = 0; i < length; i ++){
            for(int j = 0; j < height; j ++){
                int cell = this.tron.getGrid()[j][i];
                for(int k=1; k<=tron.getChampionsList().size(); k++){
                    if(cell == k){
                        g.setColor(COLORSBODY[k-1]);
                        g.fillRect(i * this.cellLength, j * this.cellHeight, this.cellLength, this.cellHeight);

                    }
                }
            }
        }

        ArrayList<Champion> champions = tron.getChampionsList();

        for(int i=0; i<champions.size(); i++){
            Coord head = champions.get(i).getTrail().peek();
            g.setColor(COLORSHEAD[champions.get(i).getId()-1]);
            g.fillRect(head.getY() * this.cellLength, head.getX() * this.cellHeight, this.cellLength, this.cellHeight);
        }

        
    }

}