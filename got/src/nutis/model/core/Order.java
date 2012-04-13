package nutis.model.core;

import nutis.client.dto.OrderDto;


public class Order {
  private int id;
  private String name;
  private Integer value;
  private boolean star;
  
  public Order(int id, String name, Integer value, boolean star) {
    this.id=id;
    this.name=name;
    this.value=value;
    this.star=star;
  }

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
  
  public Integer getValue() {
    return value;
  }
  
  public void setValue(Integer value) {
    this.value = value;
  }
  
  public boolean isStar() {
    return star;
  }
  
  public void setStar(boolean star) {
    this.star = star;
  }

  public OrderDto getRecord() {
    OrderDto result = new OrderDto();
    result.setId(this.getId());
    result.setStar(this.isStar());
    result.setName(this.getName()+(this.getValue()!=null?" "+this.getValue():""));
    return result;
  }
}
