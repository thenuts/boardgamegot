package nutis.model.persist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class UnitRecord {  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Key id;
  private int terrain;
  private int type;
  private boolean defeated;
  
  public int getTerrain() {
    return terrain;
  }
  
  public void setTerrain(int terrain) {
    this.terrain = terrain;
  }
  
  public boolean isDefeated() {
    return defeated;
  }
  
  public void setDefeated(boolean defeated) {
    this.defeated = defeated;
  }

  
  public int getType() {
    return type;
  }

  
  public void setType(int type) {
    this.type = type;
  }

  
  public Key getId() {
    return id;
  }

  
  public void setId(Key id) {
    this.id = id;
  }
}
