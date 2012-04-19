package nutis.client.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



public enum Phase implements Serializable{
  Planning(1),//all
  Raven(2),//one player
  Raid(3),//per player
  March(4),//per player
  Defense(5),//one player
  //Consolidade,
  //westross, supply, order limit, shufle,collect crowns,no suporting footman
  Mustering(6),//per player
  Clash(7),//all
  Wildling(8);//all
  
  private final int value;
  Phase(int value) {
    this.value= value;
}
  private static final Map<Integer, Phase> intToTypeMap = new HashMap<Integer, Phase>();
  static {
      for (Phase type : Phase.values()) {
          intToTypeMap.put(type.value, type);
      }
  }

  public static Phase fromInt(int i) {
    Phase type = intToTypeMap.get(Integer.valueOf(i));
      if (type == null) 
          throw new  IllegalArgumentException(i+"- is not a valid enum Phase value");
      return type;
  }

  public int getValue() {
    return value;
  }
};
