package nutis.model.core;

import java.util.ArrayList;
import java.util.List;


public class HouseType {
  private int id;
  private String name;
  private List<HouseCard> cards=new ArrayList<HouseCard>();
  private String color;
  
  public HouseType(int id, String name, String color) {
    this.id=id;
    this.name=name;
    this.color=color;
  }

  public int getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }
  
  public List<HouseCard> getCards() {
    return cards;
  }

  
  public String getColor() {
    return color;
  }
}
