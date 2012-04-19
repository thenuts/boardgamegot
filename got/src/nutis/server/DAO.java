package nutis.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import nutis.client.dto.GameDto;
import nutis.client.dto.KeyDto;
import nutis.client.dto.RetornoPadraoDTO;
import nutis.engine.Game;
import nutis.model.persist.GameRecord;
import nutis.model.persist.HouseRecord;
import nutis.model.persist.Player;


public abstract class DAO {
  final static Logger logger = LoggerFactory.getLogger(DAO.class);
  private static final EntityManagerFactory emfInstance = Persistence
      .createEntityManagerFactory("transactions-optional");
  protected RetornoPadraoDTO retorno;
  protected EntityManager em;
  
  public DAO(){
    this.em = emfInstance.createEntityManager();
  }

  public abstract RetornoPadraoDTO execute();
  
  /**
   * <p>
   * Método requisitados para operações com mensagem e outros dados que outros dados que forem necessários.
   * </p>
   * <p>
   * Para isso, uma nova classe deverá ser criado com essas propriedades, herdando de {@link RetornoPadraoDTO}
   * </p>
   * 
   * @param executavel
   *          - Classe anômica com o método que irá executar
   * @return {@link RetornoPadraoDTO}
   */
  //TODO estranho esse uncecked pois o RetornoPadraoDTO esta no extends do T 
  @SuppressWarnings("unchecked")
  public static <T extends RetornoPadraoDTO> T execute(DAO run) {
    try {
      run.execute();
    } catch (Throwable e) {
      if(e.getMessage()!=null){
        run.getRetorno().setMensagemDeErro(e.getMessage().split("\n"));
      }
      //TODO logger não esta funcionando no ambiente de desenvolvimento, analisar 
      System.out.println(e.getMessage());
      logger.error(e.getMessage(), e);
    } finally {
      if (run.getEntityManager() != null) {
        if (run.getEntityManager().getTransaction().isActive()) {
          run.getEntityManager().getTransaction().rollback();
        }
        run.getEntityManager().close();
      }
    }
    return (T) run.getRetorno();
  }

  public RetornoPadraoDTO getRetorno() {
    return retorno;
  }

  public Game readGame(KeyDto key) {
    GameRecord gameRecord = em.find(GameRecord.class, KeyFactory.createKey(key.getKind(), key.getId()));
    Game game = new Game(gameRecord);
    return game;
  }

  public GameRecord persistRandomGame(Game game) {
    Player guilherme = readPersistPlayer("oguilherme@gmail.com");
    Player divino = readPersistPlayer("divinonpassos@gmail.com");
    Player claudao = readPersistPlayer("claudao@gmail.com");
    Player rod = readPersistPlayer("rodmontero@gmail.com");
    Player tiago = readPersistPlayer("tiago.caux@gmail.com");
    GameRecord gameRecord = game.getRecord();
    gameRecord.setName("jogo " + Integer.toString((int) (Math.random() * 100)));
    for (HouseRecord house : gameRecord.getHouses()) {
      switch (house.getHouse()) {
        case 1:
          house.setPlayer(tiago.getId());
          break;
        case 2:
          house.setPlayer(rod.getId());
          break;
        case 3:
          house.setPlayer(divino.getId());
          break;
        case 4:
          house.setPlayer(claudao.getId());
          break;
        case 5:
          house.setPlayer(guilherme.getId());
          break;
        default:
          throw new IllegalStateException("house id unknow - " + house.getHouse());
      }
    }
    em.persist(gameRecord);
    return gameRecord;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<GameDto> readGames(Key key) {
    ArrayList<GameDto> result = null;
    List<?> queryResult = em.createQuery("select from " + HouseRecord.class.getName() + " where player=:player")
        .setParameter("player", key).getResultList();
    if (queryResult.size() > 0) {
      result = new ArrayList<GameDto>();
      for (HouseRecord houseRecord : (List<HouseRecord>) queryResult) {
        GameRecord game = em.find(GameRecord.class, houseRecord.getGame().getId());
        GameDto gameDTO = new GameDto();
        KeyDto keyDTO = new KeyDto();
        keyDTO.setId(game.getId().getId());
        keyDTO.setKind(game.getId().getKind());
        gameDTO.setId(keyDTO);
        gameDTO.setName(game.getName());
        result.add(gameDTO);
      }
    }
    return result;
  }

  public Player readPersistPlayer(String email) {
    Player player;
    player = null;
    List<?> queryResult = em.createQuery("select from " + Player.class.getName() + " where email=:email")
        .setParameter("email", email).getResultList();
    if (queryResult.size() == 0) {
      player = persistPlayer(email);
    } else if (queryResult.size() == 1) {
      player = (Player) queryResult.get(0);
    } else {
      throw new IllegalStateException("multiplos cadastros para o mesmo player");
    }
    return player;
  }

  public Player readPlayer(String email) {
    Player player;
    List<?> queryResult = em.createQuery("select from " + Player.class.getName() + " where email=:email")
        .setParameter("email", email).getResultList();
    if (queryResult.size() == 0) {
      player = null;
    } else if (queryResult.size() == 1) {
      player = (Player) queryResult.get(0);
    } else {
      throw new IllegalStateException("multiplos cadastros para o mesmo player");
    }
    return player;
  }

  public Player persistPlayer(String playerName) {
    Player player = new Player();
    player.setEmail(playerName);
    em.persist(player);
    em.clear();
    em = emfInstance.createEntityManager();
    return player;
  }

  public EntityManager getEntityManager() {
    return em;
  }    
}