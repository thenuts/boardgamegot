package nutis.model.core;

import java.util.ArrayList;
import java.util.List;

import nutis.model.persist.HouseRecord;
import nutis.model.persist.UnitRecord;

import com.google.appengine.api.datastore.Key;


public class House {
  
  private int powerTokens; //limit 20
  private int supplyTrack;
  private int ironThrone;
  private int fifdoms;
  private int kingCourt;
  private HouseType type;
  private List<HouseCard> cards=new ArrayList<HouseCard>();
  private List<HouseCard> descartedCards=new ArrayList<HouseCard>();
  private List<Unit> units=new ArrayList<Unit>();
  private List<OrderIssued> orders=new ArrayList<OrderIssued>();
  private Key player;

  public House(HouseType type, int supplyTrack, int ironThrone, int fifdoms, int kingCourt) {
    this.type=type;
    this.supplyTrack=supplyTrack;
    this.ironThrone=ironThrone;
    this.fifdoms=fifdoms;
    this.kingCourt=kingCourt;
    
    powerTokens=5;
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

  public List<HouseCard> getDescartedCards() {
    return descartedCards;
  }
    
  public List<Unit> getUnits() {
    return units;
  }

  public void addUnit(UnitType unitType, Terrain terrain) {
    new Unit(unitType,terrain,this);
  }
  
  public HouseRecord getRecord() {
    HouseRecord result = new HouseRecord();
    result.setPowerTokens(this.getPowerTokens());
    result.setIronThrone(this.getIronThrone());
    result.setFifdoms(this.getFifdoms());
    result.setKingCourt(this.getKingCourt());
    result.setSupplyTrack(this.getSupplyTrack());
    result.setHouse(this.getType().getId());
    for(HouseCard card:this.getCards()){
      result.getCards().add(card.getId());
    }
    for(HouseCard card:this.getDescartedCards()){
      result.getDescartedCards().add(card.getId());
    }
    for(Unit unit:this.getUnits()){
      result.getUnits().add(unit.getRecord());
    }
    for(OrderIssued order:this.getOrders()){
      result.getOrders().add(order.getRecord());
    }
    
    return result;
  }

  
  public List<OrderIssued> getOrders() {
    return orders;
  }

  public static House fromRecord(HouseRecord house,GameMap map) {
    House result = new House(map.getHouseTypes().get(house.getHouse()), house.getSupplyTrack(), 
        house.getIronThrone(), house.getFifdoms(), house.getKingCourt());
    result.setPlayer(house.getPlayer());
    for(UnitRecord unit:house.getUnits()){
      result.addUnit(map.getUnitTypes().get(unit.getType()), map.getLands().get(unit.getTerrain()));
    }
    return result;
  }

  
  public HouseType getType() {
    return type;
  }

  
  public Key getPlayer() {
    return player;
  }

  
  public void setPlayer(Key player) {
    this.player = player;
  }

}
