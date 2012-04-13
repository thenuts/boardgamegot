package nutis.model.persist;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;


public class AlianceRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Key id;
  private Key supporter;
  private Key supported;
  
  public Key getId() {
    return id;
  }
  
  public void setId(Key id) {
    this.id = id;
  }
  
  public Key getSupporter() {
    return supporter;
  }
  
  public void setSupporter(Key supporter) {
    this.supporter = supporter;
  }
  
  public Key getSupported() {
    return supported;
  }
  
  public void setSupported(Key supported) {
    this.supported = supported;
  }
}
