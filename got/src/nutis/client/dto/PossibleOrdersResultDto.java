package nutis.client.dto;

import java.io.Serializable;
import java.util.ArrayList;



public class PossibleOrdersResultDto extends RetornoPadraoDTO implements Serializable {

  private static final long serialVersionUID = 9190346318951479927L;
  
  private int starOrders;
  private ArrayList<PieceDto> pieces = new ArrayList<PieceDto>();
  private ArrayList<OrderDto> orders = new ArrayList<OrderDto>();

  
  public ArrayList<PieceDto> getPieces() {
    return pieces;
  }


  
  public ArrayList<OrderDto> getOrders() {
    return orders;
  }



  
  public int getStarOrders() {
    return starOrders;
  }



  
  public void setStarOrders(int starOrders) {
    this.starOrders = starOrders;
  }
}
