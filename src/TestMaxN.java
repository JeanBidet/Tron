import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import model.Tron;
import view.ViewTron;

public class TestMaxN {


    public static void main(String[] args) {
        
        int nbPlayersNegamaxAlphaBeta = 0;
        int nbPlayersMAXN = 3;
        int nbPlayersParanoid = 0;
        int nbPlayersMAXNAlphaBeta = 0;
        int nbPlayersSOS = 0;
        int gridSize = 10;

        Properties prop = new Properties();
        InputStream input = null;

        
        try {
            // Charger le fichier config.properties depuis le classpath
            input = Main.class.getClassLoader().getResourceAsStream("configMaxN.properties");
            prop.load(input);
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
