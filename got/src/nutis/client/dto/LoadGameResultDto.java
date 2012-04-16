package nutis.client.dto;

import java.io.Serializable;
import java.util.ArrayList;



public class LoadGameResultDto  extends RetornoPadraoDTO implements Serializable{

  private static final long serialVersionUID = 2469241416432554204L;

  ArrayList<PieceDto> pieces = new ArrayList<PieceDto>();

  private int pieceKindCount;

  
  public ArrayList<PieceDto> getPieces() {
    return pieces;
  }


  public int getPieceKindCount() {
    return pieceKindCount;
  }
  
  public void setPieceKindCount(int pieceKindCount) {
    this.pieceKindCount = pieceKindCount;
  }
}
