package nutis.client.dto;

import java.io.Serializable;
import java.util.ArrayList;




public class LoadGameResultDto  extends RetornoPadraoDTO implements Serializable{

  private static final long serialVersionUID = 2469241416432554204L;

  ArrayList<PieceDto> pieces = new ArrayList<PieceDto>();
  private int pieceKindCount;
  private Phase phase;
  HouseTrackDto[] houses;
  int[][] supplyTrackValues;
  
  public ArrayList<PieceDto> getPieces() {
    return pieces;
  }

  public int getPieceKindCount() {
    return pieceKindCount;
  }
  
  public void setPieceKindCount(int pieceKindCount) {
    this.pieceKindCount = pieceKindCount;
  }

  
  public Phase getPhase() {
    return phase;
  }

  
  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  
  public HouseTrackDto[] getHouses() {
    return houses;
  }

  
  public int[][] getSupplyTrackValues() {
    return supplyTrackValues;
  }

  
  public void setHouses(HouseTrackDto[] houses) {
    this.houses = houses;
  }

  
  public void setSupplyTrackValues(int[][] supplyTrackValues) {
    this.supplyTrackValues = supplyTrackValues;
  }
}
