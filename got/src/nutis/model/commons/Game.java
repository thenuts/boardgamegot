package nutis.model.commons;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Key id;
  private String name;
  @OneToMany(mappedBy = "game")
  private List<GamePlayer> players;
  

  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<GamePlayer> getPlayers() {
    return players;
  }
  
  public void setPlayers(List<GamePlayer> players) {
    this.players = players;
  }

  
  public Key getId() {
    return id;
  }

  
  public void setId(Key id) {
    this.id = id;
  }

  
}
