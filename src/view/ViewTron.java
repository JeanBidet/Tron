package view;

import javax.swing.*;
import model.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ViewTron extends JFrame {
    
    /**
     * Notre plateau de jeu.
     */
    private JPanel plateau;

    private float height;
    private float length;

    private float overflowHeight;
    private float overflowLength;
    
    /**
     * Constructeur de la classe ViewTron. Initialise l'interface graphique du jeu.
     * @param tron est le modèle de notre jeu.
     */
    public ViewTron(Tron tron) {
        super("Jeu de Tron");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre
        setSize(800, 800); // On fixe une taille de fenêtre
        setResizable(false); // On empêche la modification de la taille pour éviter d'avoir des problèmes graphiques
        
        // Composant du plateau de jeu (à gauche)
        plateau = new Grid(tron);
        plateau.setBackground(new Color(214, 234, 248));
        plateau.setPreferredSize(new Dimension(800, 800));
        plateau.setLayout(new FlowLayout());
        getContentPane().add(plateau, BorderLayout.WEST);

        setLocationRelativeTo(null);
        setVisible(true);

    }

}