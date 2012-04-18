package nutis.model.core;

import nutis.model.persist.OrderRecord;


public class OrderIssued {
  Order type;
  Terrain terrain;
  House house;
  public OrderIssued(Order order, Terrain terrain, House house) {
    this.type=order;
    this.house=house;
    this.terrain=terrain;
    
    terrain.getOrders().add(this);
    house.getOrders().add(this);
  }

  public OrderRecord getRecord() {
    OrderRecord result = new OrderRecord();
    result.setOrder(this.getType().getId());
    result.setTerrain(this.getTerrain().getId());
    return result;
  }
  
  public Order getType() {
    return type;
  }
  
  public void setType(Order type) {
    this.type = type;
  }
  
  public Terrain getTerrain() {
    return terrain;
  }
  
  public void setTerrain(Terrain terrain) {
    this.terrain = terrain;
  }

  
  public House getHouse() {
    return house;
  }

  
  public void setHouse(House house) {
    this.house = house;
  }
}
