package nutis.model.persist;

import java.util.List;

import com.google.appengine.api.datastore.Key;


public class GotMatch {
  private int turn;
  private int phase;
  private int wildlings;
  private int House;
  private int card1;
  private int card2;
  private int card3;
  private List<House> houses;
  private List<Aliance> aliances;
}
