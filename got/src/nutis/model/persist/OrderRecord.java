package nutis.model.persist;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;

@Entity
public class OrderRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Key id;
  private int terrain;
  private int order;
  @ManyToOne(fetch = FetchType.LAZY)
  private HouseRecord house;

  public int getTerrain() {
    return terrain;
  }

  public void setTerrain(int terrain) {
    this.terrain = terrain;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public HouseRecord getHouse() {
    return house;
  }

  public void setHouse(HouseRecord house) {
    this.house = house;
  }

  
  public Key getId() {
    return id;
  }

  
  public void setId(Key id) {
    this.id = id;
  }
}
