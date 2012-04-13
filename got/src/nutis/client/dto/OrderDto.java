package nutis.client.dto;

import java.io.Serializable;


public class OrderDto implements Serializable {
  
  private static final long serialVersionUID = 4179905128731579717L;
  
  private int id;
  private String name;
  private boolean star;
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public boolean isStar() {
    return star;
  }
  
  public void setStar(boolean star) {
    this.star = star;
  }
}
