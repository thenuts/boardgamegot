package nutis.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nutis.client.CommonService;
import nutis.client.dto.GameDto;
import nutis.client.dto.InitializeResultDto;
import nutis.client.dto.KeyDto;
import nutis.client.dto.LoadGameResultDto;
import nutis.client.dto.PieceDto;
import nutis.client.dto.PossibleOrdersResultDto;
import nutis.engine.Game;
import nutis.model.core.HouseType;
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

  private static final EntityManagerFactory emfInstance = Persistence
      .createEntityManagerFactory("transactions-optional");

  @Override
  public InitializeResultDto initialize() {
    InitializeResultDto result = new InitializeResultDto();
    try {
      result.setPlayer(getPlayer());
      EntityManager em = emfInstance.createEntityManager();
      try {
        Player player = readPersistPlayer(result.getPlayer(), em);
        result.setGames(readGames(em, player.getId()));
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
      Game game = new Game(new GameMap2003());
      game.initialize();
      EntityManager em = emfInstance.createEntityManager();
      try {
        GameRecord gameRecord = persistRandomGame(game, em);
        addPlayerToGame(em, gameRecord, "oguilherme@gmail.com",game.getMap().getHouseTypes().get(5));
        addPlayerToGame(em, gameRecord, "divinonpassos@gmail.com",game.getMap().getHouseTypes().get(3));//divino.passos@ilinkbr.com
        addPlayerToGame(em, gameRecord, "claudao@gmail.com",game.getMap().getHouseTypes().get(4));
        addPlayerToGame(em, gameRecord, "rodmontero@gmail.com",game.getMap().getHouseTypes().get(2));//wconrad@terra.com.br
        addPlayerToGame(em, gameRecord, "tiago.caux@gmail.com",game.getMap().getHouseTypes().get(1));   
        //TODO remover gam,biarra abaixo por algum motivo ele so esta salvando player ao alterar uma segunda entidade
        addPlayerToGame(em, gameRecord, "tiago.caux@gmail.com2",game.getMap().getHouseTypes().get(2));    
      } catch (Throwable e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } finally {
        if (em.getTransaction().isActive()) {
          em.getTransaction().rollback();
        }
        em.close();
      }
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  @Override
  public LoadGameResultDto loadGame(KeyDto key) {
    LoadGameResultDto result = new LoadGameResultDto();
    EntityManager em = emfInstance.createEntityManager();
    Player player = readPersistPlayer(getPlayer(), em);
    Game game = readGame(key, em);
    for(Terrain terrain:game.getMap().getLands().values()){
      if(terrain.getUnits().size()>0)
      {
        PieceDto piece=new PieceDto();
        piece.setTerrain(terrain.getName());
        piece.setX(terrain.getX());
        piece.setY(terrain.getY());
        //TODO adaptar código para o combate
        piece.setHouse(terrain.getUnits().get(0).getHouse().getType().getName());
        int[] units = new int[3];
        for(Unit unit:terrain.getUnits()){
          units[unit.getType().getId()-1]++;
        }
        String unitsString="";
        for (int i = 0; i < units.length; i++) {
          if(units[i]>0){
            unitsString+= units[i]+game.getMap().getUnitTypes().get(i+1).getInitials()+" ";
            piece.getPieces().put(i+1, units[i]);
          }
        }
        piece.setPiecesText(unitsString);
        result.getPieces().add(piece);
      }
    }
    em.close();
    return result;
  }

  private Game readGame(KeyDto key, EntityManager em) {
    GameRecord gameRecord = em.find(GameRecord.class, KeyFactory.createKey(key.getKind(), key.getId()));
    Game game = new Game(gameRecord);
    return game;
  }
  

  @Override
  public PossibleOrdersResultDto getPossibleOrders(KeyDto gameKey) {
    PossibleOrdersResultDto result = new PossibleOrdersResultDto();
    EntityManager em = emfInstance.createEntityManager();
    Player player = readPersistPlayer(getPlayer(), em);
    Game game = readGame(gameKey, em);
    for(Terrain terrain:game.getMap().getLands().values()){
      if(terrain.getUnits().size()>0)  
      {
        if(terrain.getUnits().get(0).getHouse().getPlayer().equals(player.getId())){
          PieceDto piece=new PieceDto();
          piece.setTerrain(terrain.getName());
          piece.setTerrainId(terrain.getId());
          //TODO adaptar código para o combate
          piece.setHouse(terrain.getUnits().get(0).getHouse().getType().getName());
          int[] units = new int[3];
          for(Unit unit:terrain.getUnits()){
            units[unit.getType().getId()-1]++;
          }
          String unitsString="";
          for (int i = 0; i < units.length; i++) {
            if(units[i]>0){
              unitsString+= units[i]+game.getMap().getUnitTypes().get(i+1).getInitials()+" ";
            }
          }
          piece.setPiecesText(unitsString);
          result.getPieces().add(piece);
        }
      }
    }
    for(Order order:game.getMap().getOrderTypes().values()){
      result.getOrders().add(order.getRecord());
    }
    em.close();
    return result;
  }  

  private GameRecord persistRandomGame(Game game, EntityManager em) {
    em.getTransaction().begin();
    GameRecord gameRecord = game.getRecord();
    gameRecord.setName("jogo " + Integer.toString((int) (Math.random() * 100)));
    em.persist(gameRecord);
    em.getTransaction().commit();
    return gameRecord;
  }


  @SuppressWarnings("unchecked")
  private ArrayList<GameDto> readGames(EntityManager em, Key key) {
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

  private void addPlayerToGame(EntityManager em, GameRecord gameRecord, String email, HouseType houseType) {
    Player player = readPersistPlayer(email, em);
//    em.getTransaction().begin();
//    GamePlayer gamePlayer = new GamePlayer();
//    gamePlayer.setPlayer(player.getId());
//    gamePlayer.setGame(game.getId());
//    em.persist(gamePlayer);
//    em.getTransaction().commit();
    em.getTransaction().begin();
    for(HouseRecord house:gameRecord.getHouses()){
      if(house.getHouse()==houseType.getId()){
        house.setPlayer(player.getId());
        em.merge(house);
        break;
      }
    }
    em.getTransaction().commit();
  }

  @Override
  public void sendOrders(KeyDto gameKey) {
    // TODO Auto-generated method stub
    
  }

}
