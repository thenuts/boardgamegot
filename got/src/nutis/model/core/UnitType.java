package nutis.model.core;


public class UnitType {
  private String name;
  private int strength;
  private boolean seaUnit;
  
  public UnitType(String name, int strength, boolean seaUnit) {
    this.name=name;
    this.strength=strength;
    this.seaUnit=seaUnit;
  }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getStrength() {
    return strength;
  }
  
  public void setStrength(int strength) {
    this.strength = strength;
  }
  
  public boolean isSeaUnit() {
    return seaUnit;
  }
  
  public void setSeaUnit(boolean seaUnit) {
    this.seaUnit = seaUnit;
  }
}
