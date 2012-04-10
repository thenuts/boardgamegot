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
      return userService.getCurrentUser().getNickname();
    } else {
      throw new IllegalStateException("este serviço não deveria ter sido chamada sem que o usuário esteja logado");
    }
  }
  // private void testGameRelation(EntityManager em) {
  // em.getTransaction().begin();
  // Game game = new Game();
  // game.setName("primeiro jogo");
  // em.persist(game);
  // em.getTransaction().commit();
  // em.getTransaction().begin();
  // Player player1 = new Player();
  // player1.setEmail("player1");
  // em.persist(player1);
  // em.getTransaction().commit();
  // em.getTransaction().begin();
  // Player player2 = new Player();
  // player2.setEmail("player2");
  // em.persist(player2);
  // em.getTransaction().commit();
  // em.getTransaction().begin();
  // GamePlayer aux = new GamePlayer();
  // aux.setGame(game.getId());
  // aux.setPlayer(player1.getId());
  // em.persist(aux);
  // em.getTransaction().commit();
  // // aux = new GamePlayer();
  // // aux.setGame(game);
  // // aux.setPlayer(player2);
  // // em.persist(aux);
  // // em.getTransaction().commit();
  // }
  // private String counterLogic(EntityManager em) throws Exception {
  // List<?> result = em.createQuery("select from GreetingCounter").getResultList();
  // int size = result.size();
  // GreetingCounter counter;
  // em.getTransaction().begin();
  // if (size == 0) {
  // counter = new GreetingCounter();
  // counter.setCounter(1);
  // em.persist(counter);
  //
  //
  // } else {
  // counter = (GreetingCounter) result.get(0);
  // counter.setCounter(counter.getCounter() + 1);
  // em.flush();
  // }
  // em.getTransaction().commit();
  // UserService userService = UserServiceFactory.getUserService();
  // if (userService.getCurrentUser() != null) {
  // return ("Bem vindo " + userService.getCurrentUser().getNickname() + " - " + counter.getCounter() + "(" + size +
  // ")");
  // } else {
  // throw new Exception("este serviço não deveria ter sido chamada sem que o usuário esteja logado");
  // }
  // }
  // /**
  // * Escape an html string. Escaping data received from the client helps to prevent cross-site script vulnerabilities.
  // *
  // * @param html
  // * the html string to escape
  // * @return the escaped string
  // */
  // private String escapeHtml(String html) {
  // if (html == null) {
  // return null;
  // }
  // return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
  // }

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
        addPlayerToGame(em, game, "test@example.com");
        addPlayerToGame(em, game, "test2@example.com");
        addPlayerToGame(em, game, "test3@example.com");
      } finally {
        em.close();
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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
