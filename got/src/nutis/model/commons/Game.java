package nutis.model.commons;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import nutis.model.persist.Aliance;
import nutis.model.persist.House;

import com.google.appengine.api.datastore.Key;

@Entity
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Key id;
  private String name;
  
  private int turn;
  private int phase;
  private int wildlings;
  private int House;
  private int card1;
  private int card2;
  private int card3;
  private List<House> houses;
  private List<Aliance> aliances;  
  
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
    return House;
  }

  
  public void setHouse(int house) {
    House = house;
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

  
  public List<House> getHouses() {
    return houses;
  }

  
  public void setHouses(List<House> houses) {
    this.houses = houses;
  }

  
  public List<Aliance> getAliances() {
    return aliances;
  }

  
  public void setAliances(List<Aliance> aliances) {
    this.aliances = aliances;
  }

  
}
