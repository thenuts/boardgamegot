package nutis.client;

import java.io.Serializable;
import java.util.ArrayList;


public class InitializeResultDTO  implements Serializable {
  
  private static final long serialVersionUID = -4207667867127807432L;
  
  private ArrayList<GameDTO> games;
  private String player;
  
  public ArrayList<GameDTO> getGames() {
    return games;
  }
  
  public void setGames(ArrayList<GameDTO> games) {
    this.games = games;
  }
  
  public String getPlayer() {
    return player;
  }
  
  public void setPlayer(String player) {
    this.player = player;
  }
}
