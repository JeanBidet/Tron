import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import model.Tron;
import view.ViewTron;

public class Main {
    
    public static void main(String[] args) {
        
        int nbPlayersDumbmind = 2;
        int nbPlayersNegamaxAlphaBeta = 0;
        int nbPlayersMAXN = 0;
        int nbPlayersParanoid = 0;
        int nbPlayersMAXNAlphaBeta = 0;
        int nbPlayersSOS = 0;
        int gridSize = 31;

        Properties prop = new Properties();
        InputStream input = null;

        try {
            // Charger le fichier config.properties depuis le classpath
            input = Main.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);

            nbPlayersDumbmind = Integer.valueOf(prop.getProperty("nb.players.Dumbmind"));
            nbPlayersNegamaxAlphaBeta = Integer.valueOf(prop.getProperty("nb.players.Negamax.alphabeta"));
            nbPlayersMAXN = Integer.valueOf(prop.getProperty("nb.players.MAXN"));
            nbPlayersParanoid = Integer.valueOf(prop.getProperty("nb.players.Paranoid"));
            nbPlayersMAXNAlphaBeta = Integer.valueOf(prop.getProperty("nb.players.MAXN.alphabeta"));
            nbPlayersSOS = Integer.valueOf(prop.getProperty("nb.players.SOS"));
            gridSize = Integer.valueOf(prop.getProperty("grid.size"));

        } catch (Exception e) {
            System.out.println("Une erreur est survenue");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    System.out.println("Une erreur est survenue");
                }
            }
            HashMap<String, Integer> map = new HashMap<>();
            map.put("Dumbmind", nbPlayersDumbmind);
            map.put("NegamaxAlphaBeta", nbPlayersNegamaxAlphaBeta);
            map.put("MAXN", nbPlayersMAXN);
            map.put("Paranoid", nbPlayersParanoid);
            map.put("MAXNAlphaBeta", nbPlayersMAXNAlphaBeta);
            map.put("SOS", nbPlayersSOS);
            Tron tron = new Tron(map, gridSize);
            ViewTron viewTron = new ViewTron(tron);
            tron.play(viewTron);
        }
    }
}
