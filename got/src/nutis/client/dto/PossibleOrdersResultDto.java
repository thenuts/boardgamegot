package nutis.client.dto;

import java.io.Serializable;
import java.util.ArrayList;


public class PossibleOrdersResultDto implements Serializable {

  private static final long serialVersionUID = 9190346318951479927L;
  

  ArrayList<PieceDto> pieces = new ArrayList<PieceDto>();
  ArrayList<OrderDto> orders = new ArrayList<OrderDto>();

  
  public ArrayList<PieceDto> getPieces() {
    return pieces;
  }


  
  public ArrayList<OrderDto> getOrders() {
    return orders;
  }
}
