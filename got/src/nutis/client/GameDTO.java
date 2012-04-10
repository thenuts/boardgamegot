package nutis.client;

import java.io.Serializable;


public class GameDTO implements Serializable {
  private static final long serialVersionUID = -366123769759370155L;
  
  private KeyDTO id;
  private String name;
  
  public KeyDTO getId() {
    return id;
  }
  
  public void setId(KeyDTO id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
}
