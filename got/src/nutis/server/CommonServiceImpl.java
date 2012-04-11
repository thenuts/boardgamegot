package nutis.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nutis.client.CommonService;
import nutis.client.GameDTO;
import nutis.client.InitializeResultDTO;
import nutis.client.KeyDTO;
import nutis.model.commons.Game;
import nutis.model.commons.GamePlayer;
import nutis.model.commons.Player;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CommonServiceImpl extends RemoteServiceServlet implements CommonService {

  private static final EntityManagerFactory emfInstance = Persistence
      .createEntityManagerFactory("transactions-optional");

  
  @Override
  public InitializeResultDTO initialize() {
    InitializeResultDTO result = new InitializeResultDTO();
    try {
      result.setPlayer(getPlayer());
      EntityManager em = emfInstance.createEntityManager();
      try {
        Player player = readPlayer(result.getPlayer(), em);
        result.setGames(readGames( em, player.getId()));
      } finally {
        em.close();
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return result;
  }
  
  @Override
  public void createGame() {
    try {
      EntityManager em = emfInstance.createEntityManager();
      try {
        em.getTransaction().begin();
        Game game = new Game();
        game.setName("jogo "+Integer.toString((int)(Math.random()*100)));
        em.persist(game);
        em.getTransaction().commit();
        addPlayerToGame(em, game, "oguilherme@gmail.com");
        addPlayerToGame(em, game, "divino.passos@ilinkbr.com");
        addPlayerToGame(em, game, "claudao@gmail.com");
        addPlayerToGame(em, game, "wconrad@terra.com.br");
        addPlayerToGame(em, game, "tiago.caux@gmail.com");
        //wconrad@terra.com.br
      } finally {
        em.close();
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private ArrayList<GameDTO> readGames(EntityManager em, Key key) {
    ArrayList<GameDTO> result = null;
    List<?> queryResult = em.createQuery("select from " + GamePlayer.class.getName() + " where player=:player")
        .setParameter("player", key).getResultList();
    if(queryResult.size()>0){
      result = new ArrayList<GameDTO>();
      for(GamePlayer gamePlayer:(List<GamePlayer>)queryResult){
        Game game = em.find(Game.class, gamePlayer.getGame());
        GameDTO gameDTO = new GameDTO();
        KeyDTO keyDTO = new KeyDTO();
        keyDTO.setId(game.getId().getId());
        keyDTO.setKind(game.getId().getKind());
        gameDTO.setId(keyDTO);
        gameDTO.setName(game.getName());
        result.add(gameDTO);
      }
    }
    return result;
  }

  private Player readPlayer(String email, EntityManager em) {
    Player player;
    player=null;
    List<?> queryResult = em.createQuery("select from " + Player.class.getName() + " where email=:email")
        .setParameter("email", email).getResultList();
    if (queryResult.size() == 0) {
      player = persistPlayer(email, em);
    } else if (queryResult.size() == 1) {
      player = (Player) queryResult.get(0);
    } else {
      throw new IllegalStateException("multiplos cadastros para o mesmo player");
    }
    return player;
  }

  private Player persistPlayer(String playerName, EntityManager em) {
    em.getTransaction().begin();
    Player player = new Player();
    player.setEmail(playerName);
    em.persist(player);
    em.getTransaction().commit();
    return player;
  }

  private String getPlayer() {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.getCurrentUser() != null) {
      return userService.getCurrentUser().getEmail();
    } else {
      throw new IllegalStateException("este serviço não deveria ter sido chamada sem que o usuário esteja logado");
    }
  }



  private void addPlayerToGame(EntityManager em, Game game, String email) {
    Player player = readPlayer(email, em);
    em.getTransaction().begin();
    GamePlayer gamePlayer = new GamePlayer();
    gamePlayer.setPlayer(player.getId());
    gamePlayer.setGame(game.getId());
    em.persist(gamePlayer);
    em.getTransaction().commit();
  }
}
