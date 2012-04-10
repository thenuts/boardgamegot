package nutis.model.core;

import java.util.HashMap;
import java.util.Map;


public class GameMap {
  protected Map<Integer,Terrain> lands = new HashMap<Integer, Terrain>();
  

  protected void addLand(int key, Terrain terrain,  int ...  neighbours) {
    lands.put(key, terrain);
    
    for(int neighbour:neighbours){
      terrain.addNeighbour(lands.get(neighbour));
    }
    
  }
}
