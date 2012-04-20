package nutis.model.persist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class HouseRecord {  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Key id;
  private Key player;
  private int house;
  private int powerTokens; 
  private int supplyTrack;
  private int ironThrone;
  private int fiefdoms;
  private int kingCourt;
  private Integer bid;
  private Integer combatCard;
  @OneToMany(cascade=CascadeType.PERSIST, mappedBy="house")
  private List<Integer> cards= new ArrayList<Integer>();
  @OneToMany(cascade=CascadeType.PERSIST, mappedBy="house")
  private List<Integer> descartedCards= new ArrayList<Integer>();
  @OneToMany(cascade=CascadeType.PERSIST, mappedBy="house")
  private List<UnitRecord> units= new ArrayList<UnitRecord>();
  @OneToMany(cascade=CascadeType.PERSIST, mappedBy="house")
  private List<OrderRecord> orders= new ArrayList<OrderRecord>();
  @ManyToOne(fetch = FetchType.LAZY)
  private GameRecord game;
  
  public Key getPlayer() {
    return player;
  }
  
  public void setPlayer(Key player) {
    this.player = player;
  }
  
  public int getHouse() {
    return house;
  }
  
  public void setHouse(int house) {
    this.house = house;
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
  
  public int getFiefdoms() {
    return fiefdoms;
  }
  
  public void setFiefdoms(int fiefdoms) {
    this.fiefdoms = fiefdoms;
  }
  
  public int getKingCourt() {
    return kingCourt;
  }
  
  public void setKingCourt(int kingCourt) {
    this.kingCourt = kingCourt;
  }
  
  public int getBid() {
    return bid;
  }
  
  public void setBid(int bid) {
    this.bid = bid;
  }
  
  public int getCombatCard() {
    return combatCard;
  }
  
  public void setCombatCard(int combatCard) {
    this.combatCard = combatCard;
  }
  
  public List<Integer> getCards() {
    return cards;
  }
  
  public void setCards(List<Integer> cards) {
    this.cards = cards;
  }
  
  public List<UnitRecord> getUnits() {
    return units;
  }
  
  public void setUnits(List<UnitRecord> units) {
    this.units = units;
  }
  
  public List<OrderRecord> getOrders() {
    return orders;
  }
  
  public void setOrders(List<OrderRecord> orders) {
    this.orders = orders;
  }

  
  public List<Integer> getDescartedCards() {
    return descartedCards;
  }

  
  public Key getId() {
    return id;
  }

  
  public void setId(Key id) {
    this.id = id;
  }

  
  public GameRecord getGame() {
    return game;
  }

  
  public void setGame(GameRecord game) {
    this.game = game;
  }
}
