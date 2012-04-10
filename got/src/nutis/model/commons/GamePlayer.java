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
  private Game game;
  private Player player;
  
  public Game getGame() {
    return game;
  }
  
  public void setGame(Game game) {
    this.game = game;
  }
  
  public Player getPlayer() {
    return player;
  }
  
  public void setPlayer(Player player) {
    this.player = player;
  }

  
  public Key getId() {
    return id;
  }

  
  public void setId(Key id) {
    this.id = id;
  }

  
}
