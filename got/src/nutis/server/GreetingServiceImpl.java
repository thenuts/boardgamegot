package nutis.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nutis.client.GreetingService;
import nutis.model.commons.Game;
import nutis.model.commons.Player;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

  private static final EntityManagerFactory emfInstance = Persistence
      .createEntityManagerFactory("transactions-optional");

  public String greetServer(String input) {
    try {
      EntityManager em = emfInstance.createEntityManager();
      try {
        testGameRelation(em);
        //return counterLogic(em);
      } finally {
       // em.close();
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "";
  }

  private void testGameRelation(EntityManager em) {
    em.getTransaction().begin();
    
    Game game = new Game();
    game.setName("primeiro jogo");
    em.persist(game);
    Player player1 = new Player(); 
    player1.setEmail("player1");
    em.persist(player1);
//    Player player2 = new Player(); 
//    player2.setEmail("player2");
//    em.persist(player2);
//    GamePlayer aux = new GamePlayer();
//    aux.setGame(game);
//    aux.setPlayer(player1);
//    em.persist(aux);
//    aux = new GamePlayer();
//    aux.setGame(game);
//    aux.setPlayer(player2);
//    em.persist(aux);
    em.getTransaction().commit();
  }

//  private String counterLogic(EntityManager em) throws Exception {
//    List<?> result = em.createQuery("select from GreetingCounter").getResultList();
//    int size = result.size();
//    GreetingCounter counter;
//    em.getTransaction().begin();
//    if (size == 0) {
//      counter = new GreetingCounter();
//      counter.setCounter(1);
//      em.persist(counter);
//      
//      
//    } else {
//      counter = (GreetingCounter) result.get(0);
//      counter.setCounter(counter.getCounter() + 1);
//      em.flush();
//    }
//    em.getTransaction().commit();
//    UserService userService = UserServiceFactory.getUserService();
//    if (userService.getCurrentUser() != null) {
//      return ("Bem vindo " + userService.getCurrentUser().getNickname() + " - " + counter.getCounter() + "(" + size + ")");
//    } else {
//      throw new Exception("este serviço não deveria ter sido chamada sem que o usuário esteja logado");
//    }
//  }

//  /**
//   * Escape an html string. Escaping data received from the client helps to prevent cross-site script vulnerabilities.
//   * 
//   * @param html
//   *          the html string to escape
//   * @return the escaped string
//   */
//  private String escapeHtml(String html) {
//    if (html == null) {
//      return null;
//    }
//    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
//  }
}
