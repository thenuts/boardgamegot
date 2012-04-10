package nutis.model.commons;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class GamePlayer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Key  id;
  private Key game;
  private Key player;
  
  public Key getId() {
    return id;
  }

  
  public void setId(Key id) {
    this.id = id;
  }


  
  public Key getGame() {
    return game;
  }


  
  public void setGame(Key game) {
    this.game = game;
  }


  
  public Key getPlayer() {
    return player;
  }


  
  public void setPlayer(Key player) {
    this.player = player;
  }

  
}
