package nutis.model.core;

import java.util.ArrayList;
import java.util.List;


public class House {
  private String name;
  private int powerTokens; //limit 20
  private int supplyTrack;
  private int ironThrone;
  private int fifdoms;
  private int kingCourt;
  private List<HouseCard> cards=new ArrayList<HouseCard>();
  private List<Unit> units=new ArrayList<Unit>();

  public House(String name, int supplyTrack, int ironThrone, int fifdoms, int kingCourt) {
    this.name=name;
    this.supplyTrack=supplyTrack;
    this.ironThrone=ironThrone;
    this.fifdoms=fifdoms;
    this.kingCourt=kingCourt;
    
    powerTokens=5;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getPowerTokens() {
    return powerTokens;
  }
  
  public void setPowerTokens(int powerTokens) {
    this.powerTokens = powerTokens;
  }
  
  public int getSupplyTrack() {
    return supplyTrack;
  }
  
  public void setSupplyTrack(int supplyTrack) {
    this.supplyTrack = supplyTrack;
  }
  
  public int getIronThrone() {
    return ironThrone;
  }
  
  public void setIronThrone(int ironThrone) {
    this.ironThrone = ironThrone;
  }
  
  public int getFifdoms() {
    return fifdoms;
  }
  
  public void setFifdoms(int fifdoms) {
    this.fifdoms = fifdoms;
  }
  
  public int getKingCourt() {
    return kingCourt;
  }
  
  public void setKingCourt(int kingCourt) {
    this.kingCourt = kingCourt;
  }
  
  public List<HouseCard> getCards() {
    return cards;
  }
  
  public void setCards(List<HouseCard> cards) {
    this.cards = cards;
  }

  
  public List<Unit> getUnits() {
    return units;
  }

  
  public void setUnits(List<Unit> units) {
    this.units = units;
  }

  public void addUnit(UnitType unitType, Terrain terrain) {
    new Unit(unitType,terrain,this);
    
  } 
}
