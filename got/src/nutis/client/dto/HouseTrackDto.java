package nutis.client.dto;

import java.io.Serializable;


public class HouseTrackDto implements Serializable{
  private static final long serialVersionUID = 892394754373322350L;
  
  String houseColor;
  int ironThrone;
  int fiefdoms;
  int court;
  int supply;
  int stars;
  
  public HouseTrackDto(int ironThrone, int fiefdoms, int court,int supply, int stars, String color) {
    this.ironThrone=ironThrone;
    this.fiefdoms=fiefdoms;
    this.court=court;
    this.supply=supply;
    this.stars=stars;
    this.houseColor=color;
  }

  public String getHouseColor() {
    return houseColor;
  }
  
  public int getIronThrone() {
    return ironThrone;
  }
  
  public int getFiefdoms() {
    return fiefdoms;
  }
  
  public int getCourt() {
    return court;
  }
  
  public int getSupply() {
    return supply;
  }
  
  public int getStars() {
    return stars;
  }
}