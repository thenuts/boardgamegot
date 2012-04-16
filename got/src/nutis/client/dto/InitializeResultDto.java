package nutis.client.dto;

import java.io.Serializable;
import java.util.ArrayList;



public class InitializeResultDto extends RetornoPadraoDTO implements Serializable {
  
  private static final long serialVersionUID = -4207667867127807432L;
  
  private ArrayList<GameDto> games;
  private String player;
  
  public ArrayList<GameDto> getGames() {
    return games;
  }
  
  public void setGames(ArrayList<GameDto> games) {
    this.games = games;
  }
  
  public String getPlayer() {
    return player;
  }
  
  public void setPlayer(String player) {
    this.player = player;
  }
}
