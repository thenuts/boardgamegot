package nutis.model.core;


public class UnitType {
  private int id;
  private String name;
  private String initials;
  private int strength;
  private boolean seaUnit;
  
  public UnitType(int id,String name, int strength, boolean seaUnit) {
    this.id=id;
    this.name=name;
    this.strength=strength;
    this.initials= name.substring(0,1);
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

  
  public int getId() {
    return id;
  }

  
  public String getInitials() {
    return initials;
  }
}
