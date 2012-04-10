package nutis.model.commons;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;


@Entity
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Key id;
  private String email;
//  @OneToMany(mappedBy = "player")
//  private List<GamePlayer> games;
  
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }

  
  public Key getId() {
    return id;
  }

  
  public void setId(Key id) {
    this.id = id;
  }

  
//  public List<GamePlayer> getGames() {
//    return games;
//  }
//  
//  public void setGames(List<GamePlayer> games) {
//    this.games = games;
//  }
}
