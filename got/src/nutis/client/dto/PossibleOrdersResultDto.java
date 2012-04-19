package nutis.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;



public class PossibleOrdersResultDto extends RetornoPadraoDTO implements Serializable {

  private static final long serialVersionUID = 9190346318951479927L;
  
  private int starOrders;
  private HashMap<Integer,String> lands = new HashMap<Integer,String>();
  private ArrayList<OrderDto> orders = new ArrayList<OrderDto>();
  
  public ArrayList<OrderDto> getOrders() {
    return orders;
  }
  
  public int getStarOrders() {
    return starOrders;
  }
  
  public void setStarOrders(int starOrders) {
    this.starOrders = starOrders;
  }
  
  public HashMap<Integer, String> getLands() {
    return lands;
  }
}
