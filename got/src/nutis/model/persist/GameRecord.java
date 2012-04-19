package nutis.model.persist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class GameRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Key id;
  private String name;
  private String mapType;
  private int turn;
  private int phase;
  private int wildlings;
  private Integer house;
  private Integer card1;
  private Integer card2;
  private Integer card3;
  @OneToMany(cascade={CascadeType.PERSIST}, mappedBy="game")
  private List<HouseRecord> houses = new ArrayList<HouseRecord>();
  @OneToMany(cascade=CascadeType.PERSIST, mappedBy="game")
  private List<AlianceRecord> aliances = new ArrayList<AlianceRecord>();
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Key getId() {
    return id;
  }

  
  public void setId(Key id) {
    this.id = id;
  }

  
  public int getTurn() {
    return turn;
  }

  
  public void setTurn(int turn) {
    this.turn = turn;
  }

  
  public int getPhase() {
    return phase;
  }

  
  public void setPhase(int phase) {
    this.phase = phase;
  }

  
  public int getWildlings() {
    return wildlings;
  }

  
  public void setWildlings(int wildlings) {
    this.wildlings = wildlings;
  }

  
  public int getHouse() {
    return house;
  }

  
  public void setHouse(int house) {
    this.house = house;
  }

  
  public int getCard1() {
    return card1;
  }

  
  public void setCard1(int card1) {
    this.card1 = card1;
  }

  
  public int getCard2() {
    return card2;
  }

  
  public void setCard2(int card2) {
    this.card2 = card2;
  }

  
  public int getCard3() {
    return card3;
  }

  
  public void setCard3(int card3) {
    this.card3 = card3;
  }

  
  public List<HouseRecord> getHouses() {
    return houses;
  }

  
  public void setHouses(List<HouseRecord> houses) {
    this.houses = houses;
  }

  
  public List<AlianceRecord> getAliances() {
    return aliances;
  }

  
  public void setAliances(List<AlianceRecord> aliances) {
    this.aliances = aliances;
  }

  
  public String getMapType() {
    return mapType;
  }

  
  public void setMapType(String mapType) {
    this.mapType = mapType;
  }

  
}
