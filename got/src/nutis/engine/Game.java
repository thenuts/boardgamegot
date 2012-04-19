package nutis.engine;

import java.util.ArrayList;
import java.util.List;

import nutis.client.dto.Phase;
import nutis.model.core.Aliance;
import nutis.model.core.GameMap;
import nutis.model.core.House;
import nutis.model.core.WestrosCard;
import nutis.model.core.map.GameMap2003;
import nutis.model.persist.GameRecord;
import nutis.model.persist.HouseRecord;

import com.google.appengine.api.datastore.Key;


public class Game {
  
  
  Phase phase;
  House house;
  
  List<House> houses;
  GameMap map;
  
  private String name;
  
  private int turn;
  private int wildlings;
  private WestrosCard card1;
  private WestrosCard card2;
  private WestrosCard card3;
  private List<Aliance> aliances = new ArrayList<Aliance>();  
  
  public Game(GameMap map){
    this.map=map;
    phase = Phase.Planning;
  }
  
  public Game(GameRecord gameRecord) {
    //TODO aceitar varios mapas no record
    map = new GameMap2003();
    this.name = gameRecord.getName();
    this.turn = gameRecord.getTurn();
    this.phase = Phase.fromInt(gameRecord.getPhase());
    this.wildlings = gameRecord.getWildlings();
    houses = new ArrayList<House>();
    for(HouseRecord house:gameRecord.getHouses()){
      houses.add(House.fromRecord(house,map));
    }
  }

  public void initialize(){
    houses = map.initializeHouses();    
  }
  
  void begin(){
    //definir e-mail de cada jogador
    //colocar as peças iniciais de cada jogador
    //atribuir as cartas de cada jogador
    //atribuir o supply, iron throne, sword e corvo de cada jogador
    //colocar o jogo na fase de planejamento
  }
  
  void planningPhase(){
    //ver restrições de ordens
    //atribuir ordens a jogadores
    //enviar e-mail a todos os jogadores
  }
  
  void planningOrder(){
    //params: player , ordem, land
    //verificar se o player possui a ordem
    //remover a ordem do player
    //atribuir a terra
  }
  
  void undoPlanningOrder(){
    //params: player , land
    //remover a ordem da terra
    //incluir ordem ao player
  }
  
  void finishplanningOrder(){
    // params: player
    //initCrowOrder
  }
  
  void initCrowOrder(){
    //se foi o ultimo jogador, enviar email a todos, com link de troca ao corvo
    //colocar o jogo na fase de planejamento corvo
  }
  
  void crowOrder(){
    //params: player , ordem, la
    //verificar se player já fez a crown order
    //verificar se o player possui a ordem    
    //remover a ordem do player
    //se a terra possui uma ordem, remover e voltar ao player
    // armazenar a terra e ordem original
    //atribuir a terra
  }
  
  void undoCrowOrder(){
    //params: player , 
    //desfazer croworder
  }  

  void finishCrowOrder(){
    // params: 
    //initRaidPhase
     
  }
  
  void initRaidPhase(){
  //colocar o jogo na fase de raid
    //se não possui nenhuma ordem de raid
    //iniBattlePhase
    //por ordem do iron throne envie email para o primeiro player com raid
  }
  
  void decideRaid(){
    //param:land land
    //valida raid na land de origem
    //valida neibor na terra de destino
    //remove ordem da terra de destino
    //inclua ordem na lista do player
    //removem a ordem da terra de orgem
    //inclua ordem na lista do player    
  }
  
  void finishRaidPhase(){
    //se não possui nenhuma ordem de raid
    //iniBattlePhase
    //por ordem do iron throne envie email para o proximo player com raid
  }  
  
  void initBattlePhase(){
    //colocar o jogo na fase de battle
    //se não possui nenhuma ordem de battle
    //iniWestrosPhase
    //por ordem do iron throne envie email para o primeiro player com battle
    //coloque o jogo em status de combate
  }
  
  void initDecideBattle(){
    //param:units,land, retreatland, card
    //valida se nenhuma udidade esta defeated
    //valida se todas as unidades estao na mesma terra
    //valida se todas as unidades sao do player
    //valida se a land é neibour
    //valida se o retread é neibour do neibour
    //avise caso a retreat land viole limite de unidades ou esteja ocupoada
    //se for combate 
    // valida se o player ja fez um combate a partir dessa terra
    // some os pontos de unidade cada player
    // some o ponto do ataque espada
    // some os pontos da defesa
    // some os pontos do ataque
    // some os pontos do suporte
    // se adversario não neutro
    // envie e-mail ao adversario
    // status aguardando defense
    //senao
    // mova as unidades para o terreno
    // remova power token se ouver
    //  se ouver proxima jogada e for outro player envie e-mail
    //  senao init westros
  }
  
  void initdefenseBattle(){
    //param:card
    //some carta aos pontos
    //chame poderes das cartas
    //contes espada do vencedor
    //conte torre do perdedor
    //remova unidade do perdedor
    //mova unidade do perdedor
    //se ouver proxima jogada envie e-mail
    //senao init westros
  } 
  
  void initWestrosPhase(){
    
  }

  
  public List<House> getHouses() {
    return houses;
  }

  public House getHouse(Key key) {
    House result=null;
    for(House house:houses){
      if(house.getPlayer().equals(key)){
        result = house;
        break;
      }
    }
    return result;
  }

  
  public GameMap getMap() {
    return map;
  }

  public GameRecord getRecord() {
    GameRecord result = new GameRecord();
    result.setName(name);
    result.setTurn(turn);
    result.setPhase(phase.getValue());
    result.setWildlings(wildlings);
    result.setMapType(map.getClass().getName());
    for (House house : getHouses()) {
      result.getHouses().add(house.getRecord());
    }
    return result;
  }

  
  public Phase getPhase() {
    return phase;
  }

  
  public void setPhase(Phase phase) {
    this.phase = phase;
  }
  
}
