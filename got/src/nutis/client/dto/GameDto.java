package nutis.client.dto;

import java.io.Serializable;


public class GameDto implements Serializable {
  private static final long serialVersionUID = -366123769759370155L;
  
  private KeyDto id;
  private String name;
  
  public KeyDto getId() {
    return id;
  }
  
  public void setId(KeyDto id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
}
