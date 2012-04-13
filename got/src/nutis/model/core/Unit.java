package nutis.model.core;

import nutis.model.persist.UnitRecord;


public class Unit {
   private House house;
   private Terrain terrain;
   private boolean defeated=false;
   private UnitType type;
  

  public Unit(UnitType type, Terrain terrain, House house) {
    this.type=type;
    this.house=house;
    this.terrain=terrain;
    
    terrain.getUnits().add(this);
    house.getUnits().add(this);
  }

  public House getHouse() {
    return house;
  }
  
  public void setHouse(House house) {
    this.house = house;
  }
  
  
  public boolean isDefeated() {
    return defeated;
  }
  
  public void setDefeated(boolean defeated) {
    this.defeated = defeated;
  }
  
  public UnitType getType() {
    return type;
  }
  
  public void setType(UnitType type) {
    this.type = type;
  }

  
  public Terrain getTerrain() {
    return terrain;
  }

  
  public void setTerrain(Terrain terrain) {
    this.terrain = terrain;
  }

  public UnitRecord getRecord() {
    UnitRecord result = new UnitRecord();
    result.setDefeated(this.isDefeated());
    result.setType(this.getType().getId());
    result.setTerrain(this.getTerrain().getId());
    return result;
  }
}
