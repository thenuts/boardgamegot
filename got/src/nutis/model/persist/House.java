package nutis.model.persist;

import java.util.List;

import com.google.appengine.api.datastore.Key;


public class House {
  private Key player;
  private int house;
  private int powerTokens; 
  private int supplyTrack;
  private int ironThrone;
  private int fifdoms;
  private int kingCourt;
  private int bid;
  private int combatCard;
  private List<Integer> cards;
  private List<Unit> units;
  private List<Order> orders;
}
