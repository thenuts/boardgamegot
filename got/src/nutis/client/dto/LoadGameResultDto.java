package nutis.client.dto;

import java.io.Serializable;
import java.util.ArrayList;


public class LoadGameResultDto  implements Serializable{

  private static final long serialVersionUID = 2469241416432554204L;

  ArrayList<PieceDto> pieces = new ArrayList<PieceDto>();

  
  public ArrayList<PieceDto> getPieces() {
    return pieces;
  }
}
