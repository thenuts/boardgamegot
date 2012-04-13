package nutis.model.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class GameMap {
  protected Map<Integer,Terrain> lands = new HashMap<Integer, Terrain>();
  protected Map<Integer,HouseType> houseTypes = new HashMap<Integer, HouseType>();
  protected Map<Integer,UnitType> unitTypes = new HashMap<Integer, UnitType>();
//TODO migrar para collections
  protected int[][] supplyTrack;
  protected int[] kingsCourt;
  private Map<Integer, Order> orders = new HashMap<Integer, Order>();;

  protected void addLand(Terrain terrain,  int ...  neighbours) {
    lands.put(terrain.getId(), terrain);
    
    for(int neighbour:neighbours){
      terrain.addNeighbour(lands.get(neighbour));
    }
    
  }  

  protected void addHouseType(HouseType houseType) {
    houseTypes.put(houseType.getId(), houseType);
  }
  
  protected void addUnitType(UnitType unitType) {
    unitTypes.put(unitType.getId(), unitType);
  }

  protected void addOrder(Order order) {
    orders.put(order.getId(), order);
  }
  
  public Map<Integer, Terrain> getLands() {
    return lands;
  }

  
  public Map<Integer,HouseType> getHouseTypes() {
    return houseTypes;
  }
  
  public Map<Integer,UnitType> getUnitTypes() {
    return unitTypes;
  }

  
  public Map<Integer,Order> getOrderTypes() {
    return orders;
  }

  public int[][] getSupplyTrack(){
    return supplyTrack;
  }
  
  public int[] getKingsCourt(){
    return kingsCourt;
  }
  
  public abstract List<House> initializeHouses();
}
