package nutis.client.dto;

import java.io.Serializable;
import java.util.HashMap;


public class PieceDto implements Serializable{
  private static final long serialVersionUID = 1156718942165162099L;
  
  //TODO no futuro esta classe representa´ra peças individuais, hoje elas estao agrupadas por terreno e house
  
//  private String terrain;
  private int terrainId;
  private String house;
  private String piecesText;
  private HashMap<Integer,Integer> pieces = new HashMap<Integer, Integer>();
  private int x;
  private int y;
  
//  public String getTerrain() {
//    return terrain;
//  }
//  
//  public void setTerrain(String terrain) {
//    this.terrain = terrain;
//  }
  
  public String getHouse() {
    return house;
  }
  
  public void setHouse(String house) {
    this.house = house;
  }
  
  public String getPiecesText() {
    return piecesText;
  }
  
  public void setPiecesText(String piecesText) {
    this.piecesText = piecesText;
  }

  
  public int getTerrainId() {
    return terrainId;
  }

  
  public void setTerrainId(int terrainId) {
    this.terrainId = terrainId;
  }

  
  public HashMap<Integer, Integer> getPieces() {
    return pieces;
  }

  
  public int getX() {
    return x;
  }

  
  public void setX(int x) {
    this.x = x;
  }

  
  public int getY() {
    return y;
  }

  
  public void setY(int y) {
    this.y = y;
  }
}
