package nutis.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import nutis.client.CommonService;
import nutis.client.dto.GameDto;
import nutis.client.dto.InitializeResultDto;
import nutis.client.dto.KeyDto;
import nutis.client.dto.LoadGameResultDto;
import nutis.client.dto.PieceDto;
import nutis.client.dto.PossibleOrdersResultDto;
import nutis.client.dto.RetornoPadraoDTO;
import nutis.engine.Game;
import nutis.model.core.Order;
import nutis.model.core.Terrain;
import nutis.model.core.Unit;
import nutis.model.core.map.GameMap2003;
import nutis.model.persist.GameRecord;
import nutis.model.persist.HouseRecord;
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

  @Override
  public InitializeResultDto initialize() {
    return Business.<InitializeResultDto> execute(new ExecutavelComRetorno() {

      @Override
      public InitializeResultDto execute() {
        InitializeResultDto result = new InitializeResultDto();
        retorno = result;
        result.setPlayer(getPlayer());
        Player player = readPersistPlayer(result.getPlayer(), em);
        result.setGames(readGames(player.getId(), em));
        return result;
      }
    });
  }

  @Override
  public RetornoPadraoDTO createGame() {
    return Business.<RetornoPadraoDTO> execute(new ExecutavelComRetorno() {

      @Override
      public RetornoPadraoDTO execute() {
        Game game = new Game(new GameMap2003());
        game.initialize();
        persistRandomGame(game, em);
        return null;
      }
    });
  }

  @Override
  public LoadGameResultDto loadGame(final KeyDto key) {
    return Business.<LoadGameResultDto> execute(new ExecutavelComRetorno() {

      @Override
      public LoadGameResultDto execute() {
        LoadGameResultDto result = new LoadGameResultDto();
        retorno = result;
        Player player = readPersistPlayer(getPlayer(), em);
        Game game = readGame(key, em);
        result.setPieceKindCount(20);
        createPieces(result.getPieces(), game,null);
        return result;
      }
    });
  }

  @Override
  public PossibleOrdersResultDto getPossibleOrders(final KeyDto gameKey) {
    return Business.<PossibleOrdersResultDto> execute(new ExecutavelComRetorno() {

      @Override
      public PossibleOrdersResultDto execute() {
        PossibleOrdersResultDto result = new PossibleOrdersResultDto();
        retorno = result;
        Player player = readPersistPlayer(getPlayer(), em);
        Game game = readGame(gameKey, em);
        createPieces(result.getPieces(), game,game.getHouse(player.getId()).getType().getId());
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
  public RetornoPadraoDTO sendOrders(KeyDto gameKey) {
    // TODO Auto-generated method stub
    return null;
  }

  private void createPieces(ArrayList<PieceDto> arrayList, Game game, Integer house) {
    for (Terrain terrain : game.getMap().getLands().values()) {
      if (terrain.getUnits().size() > 0) {        
        if(house == null || terrain.getUnits().get(0).getHouse().getType().getId() == house)
        {
          PieceDto piece = createPieceDto(game, terrain);
          arrayList.add(piece);          
        }
      }
    }
  }

  private PieceDto createPieceDto(Game game, Terrain terrain) {
    PieceDto piece = new PieceDto();
    piece.setTerrainId(terrain.getId());
    piece.setX(terrain.getX());
    piece.setY(terrain.getY());
    // TODO adaptar código para o combate
    piece.setHouse(terrain.getUnits().get(0).getHouse().getType().getName());
    int houseId = terrain.getUnits().get(0).getHouse().getType().getId();
    int[] units = new int[3];
    for (Unit unit : terrain.getUnits()) {
      units[unit.getType().getId() - 1]++;
    }
    String unitsString = "";
    for (int i = 0; i < units.length; i++) {
      if (units[i] > 0) {
        unitsString += units[i] + game.getMap().getUnitTypes().get(i + 1).getInitials() + " ";
        piece.getPieces().put((houseId - 1) * 4 + i , units[i]);
      }
    }
    piece.setPiecesText(terrain.getName() + " - " + unitsString);
    return piece;
  }

  private Game readGame(KeyDto key, EntityManager em) {
    GameRecord gameRecord = em.find(GameRecord.class, KeyFactory.createKey(key.getKind(), key.getId()));
    Game game = new Game(gameRecord);
    return game;
  }

  private GameRecord persistRandomGame(Game game, EntityManager em) {
    Player guilherme = readPersistPlayer("oguilherme@gmail.com", em);
    Player divino = readPersistPlayer("divinonpassos@gmail.com", em);
    Player claudao = readPersistPlayer("claudao@gmail.com", em);
    Player rod = readPersistPlayer("rodmontero@gmail.com", em);
    Player tiago = readPersistPlayer("tiago.caux@gmail.com", em);
    em.getTransaction().begin();
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
    em.getTransaction().commit();
    return gameRecord;
  }

  @SuppressWarnings("unchecked")
  private ArrayList<GameDto> readGames(Key key, EntityManager em) {
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

  private Player readPersistPlayer(String email, EntityManager em) {
    Player player;
    player = null;
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
}
