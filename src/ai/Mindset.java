package ai;

import model.Vector;
import model.Coord;

public interface Mindset {
    
    public final static int INFINITE = Integer.MAX_VALUE;

    public void launch(Coord playerCoord, int deep, int alpha, int beta );//Attribue une nouvelle Coordonnée ou aller

    public Vector getVector();
    
}
 