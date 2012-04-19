package nutis.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nutis.client.CommonService;
import nutis.client.dto.GameDto;
import nutis.client.dto.InitializeResultDto;
import nutis.client.dto.KeyDto;
import nutis.client.dto.LoadGameResultDto;
import nutis.client.dto.PieceDto;
import nutis.client.dto.PossibleOrdersResultDto;
import nutis.client.dto.RetornoPadraoDTO;
import nutis.engine.Game;
import nutis.model.core.House;
import nutis.model.core.Order;
import nutis.model.core.OrderIssued;
import nutis.model.core.Terrain;
import nutis.model.core.Unit;
import nutis.model.core.map.GameMap2003;
import nutis.model.persist.GameRecord;
import nutis.model.persist.HouseRecord;
import nutis.model.persist.OrderRecord;
import nutis.model.persist.Player;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CommonServiceImpl extends RemoteServiceServlet implements CommonService {

  final static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
  private static final EntityManagerFactory emfInstance = Persistence
      .createEntityManagerFactory("transactions-optional");

  @Override
  public InitializeResultDto initialize() {
    return execute(new ExecutavelComRetorno() {

      @Override
      public InitializeResultDto execute() {
        InitializeResultDto result = new InitializeResultDto();
        retorno = result;
        result.setPlayer(getPlayer());
        Player player = readPersistPlayer(result.getPlayer());
        result.setGames(readGames(player.getId()));
        return result;
      }
    });
  }

  @Override
  public RetornoPadraoDTO createGame() {
    return execute(new ExecutavelComRetorno() {

      @Override
      public RetornoPadraoDTO execute() {
        RetornoPadraoDTO result = new RetornoPadraoDTO();
        retorno = result;
        Game game = new Game(new GameMap2003());
        game.initialize();
        persistRandomGame(game);
        return result;
      }
    });
  }

  @Override
  public LoadGameResultDto loadGame(final KeyDto key) {
    return execute(new ExecutavelComRetorno() {

      @Override
      public LoadGameResultDto execute() {
        LoadGameResultDto result = new LoadGameResultDto();
        retorno = result;
        // Player player = readPlayer(getPlayer(), em);
        Game game = readGame(key);
        result.setPieceKindCount(44);
        createPieces(result.getPieces(), game, null);
        return result;
      }
    });
  }

  @Override
  public PossibleOrdersResultDto getPossibleOrders(final KeyDto gameKey) {
    return execute(new ExecutavelComRetorno() {

      @Override
      public PossibleOrdersResultDto execute() {
        PossibleOrdersResultDto result = new PossibleOrdersResultDto();
        retorno = result;
        Player player = readPlayer(getPlayer());
        Game game = readGame(gameKey);
        createPieces(result.getPieces(), game, game.getHouse(player.getId()).getType().getId());
        int kingCourt = game.getHouse(player.getId()).getKingCourt();
        int starCount = game.getMap().getKingsCourt()[kingCourt];
        result.setStarOrders(starCount);
        for (Order order : game.getMap().getOrderTypes().values()) {
          if (starCount > 0) {
            result.getOrders().add(order.getRecord());
          } else if (!order.isStar()) {
            result.getOrders().add(order.getRecord());
          }
        }
        return result;
      }
    });
  }

  @Override
  public RetornoPadraoDTO sendOrders(final KeyDto key, final HashMap<Integer, Integer> internalOrders) {
    return execute(new ExecutavelComRetorno() {

      @Override
      public RetornoPadraoDTO execute() {
        RetornoPadraoDTO result = new RetornoPadraoDTO();
        retorno = result;
        // TODO melhorar eficiencia da persistencia acessando a house diretamente atrvés de pk
        GameRecord gameRecord = em.find(GameRecord.class, KeyFactory.createKey(key.getKind(), key.getId()));
        Player player = readPlayer(getPlayer());
        int houseCount = 0;
        for (HouseRecord house : gameRecord.getHouses()) {
          if (house.getOrders().size() > 0) {
            houseCount++;
          }
          if (house.getPlayer().equals(player.getId())) {
            for (OrderRecord order : house.getOrders()) {
              // em.getTransaction().begin();
              em.remove(order);
              // em.getTransaction().commit();
            }
            for (Map.Entry<Integer, Integer> entry : internalOrders.entrySet()) {
              // em.getTransaction().begin();
              OrderRecord order = new OrderRecord();
              // TODO revisar relação abaixo
              order.setTerrain(entry.getKey());
              order.setOrder(entry.getValue());
              order.setHouse(house);
              em.persist(order);
              // em.getTransaction().commit();
            }
            break;
          }
        }
        Game game = new Game(gameRecord);
        if (houseCount == game.getMap().getNumberOfPlayers()) {
        }
        return result;
      }
    });
  }

  private void createPieces(ArrayList<PieceDto> arrayList, Game game, Integer houseId) {
    for (Terrain terrain : game.getMap().getLands().values()) {
      if (terrain.getUnits().size() > 0) {
        if (houseId == null || terrain.getUnits().get(0).getHouse().getType().getId() == houseId) {
          HashMap<Integer, HashMap<Integer, Integer>> units = new HashMap<Integer, HashMap<Integer, Integer>>();
          // house,unit, count
          for (Unit unit : terrain.getUnits()) {
            int houseid = unit.getHouse().getType().getId();
            if (!units.containsKey(houseid)) {
              units.put(houseid, new HashMap<Integer, Integer>());
            }
            int unitid = unit.getType().getId();
            if (units.get(houseid).containsKey(unitid)) {
              int count = units.get(houseid).get(unitid);
              units.get(houseid).put(unitid, count++);
            } else {
              units.get(houseid).put(unitid, 1);
            }
          }
          for (Map.Entry<Integer, HashMap<Integer, Integer>> houseUnits : units.entrySet()) {
            PieceDto piece = new PieceDto();
            piece.setTerrainId(terrain.getId());
            piece.setX(terrain.getX());
            piece.setY(terrain.getY());
            piece.setHouse(game.getMap().getHouseTypes().get(houseUnits.getKey()).getName());
            String unitsString = "";
            for (Map.Entry<Integer, Integer> unit : houseUnits.getValue().entrySet()) {
              unitsString += unit.getValue() + game.getMap().getUnitTypes().get(unit.getKey()).getInitials() + " ";
              piece.getPieces().put((houseUnits.getKey() - 1) * 4 + unit.getKey(), unit.getValue());
            }
            piece.setPiecesText(terrain.getName() + " - " + unitsString);
            House house = terrain.getUnits().get(0).getHouse();
            if (house.getOrders() != null) {
              for (OrderIssued order : house.getOrders()) {
                if (order.getTerrain().getId() == terrain.getId()) {
                  piece.getPieces().put(24 + houseUnits.getKey(), 1);
                  break;
                }
              }
            }
            arrayList.add(piece);
          }
        }
      }
    }
  }

  private Game readGame(KeyDto key) {
    GameRecord gameRecord = em.find(GameRecord.class, KeyFactory.createKey(key.getKind(), key.getId()));
    Game game = new Game(gameRecord);
    return game;
  }

  private GameRecord persistRandomGame(Game game) {
    Player guilherme = readPersistPlayer("oguilherme@gmail.com");
    Player divino = readPersistPlayer("divinonpassos@gmail.com");
    Player claudao = readPersistPlayer("claudao@gmail.com");
    Player rod = readPersistPlayer("rodmontero@gmail.com");
    Player tiago = readPersistPlayer("tiago.caux@gmail.com");
    // em.getTransaction().begin();
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
    // em.getTransaction().commit();
    return gameRecord;
  }

  @SuppressWarnings("unchecked")
  private ArrayList<GameDto> readGames(Key key) {
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

  private Player readPersistPlayer(String email) {
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

  private Player readPlayer(String email) {
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

  private Player persistPlayer(String playerName) {
    // em.getTransaction().begin();
    Player player = new Player();
    player.setEmail(playerName);
    em.persist(player);
    em.close();
    em = emfInstance.createEntityManager();
    // em.getTransaction().commit();
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

  static EntityManager em = null;

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
  // TODO estranho esse uncecked pois o RetornoPadraoDTO esta no extends do T
  @SuppressWarnings("unchecked")
  public static <T extends RetornoPadraoDTO> T execute(ExecutavelComRetorno run) {
    try {
      em = emfInstance.createEntityManager();
      run.setEntityManager(em);
      run.execute();
    } catch (Throwable e) {
      if (e.getMessage() != null) {
        run.getRetorno().setMensagemDeErro(e.getMessage().split("\n"));
      }
      // TODO logger não esta funcionando no ambiente de desenvolvimento, analisar
      System.out.println(e.getMessage());
      logger.error(e.getMessage(), e);
    } finally {
      if (em != null) {
        if (em.isOpen()) {
          if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
          }
          em.close();
        }
      }
    }
    return (T) run.getRetorno();
  }
}
