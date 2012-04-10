package nutis.model.core;

import java.util.ArrayList;
import java.util.List;


public class Match {
  enum Phase{
    
  };
  Phase phase;
  House house;
  
  
  void inicializar(){
    //define players
    //define mapa
    //define cartas
    //define westross
    
    List<Terrain> lands = new ArrayList<Terrain>();
    //TODO inicializar terrenos
   // lands.add(new Land("teste",1,0,0,null)); //,new Land[] {new Land(),new Land()}));
    
    UnitType eyre = new UnitType("Eyre",6,false);
    UnitType sunspear = new UnitType("Sunspear",5,false);
    UnitType kingsLanding = new UnitType("KingsLanding",5,false);
    
    //TODO essas variaveis são alteradas quando muda o numero de jogadores
    int[][] supplyTrack = new int[][]{{2,2},{3,2},{3,2,2},{3,2,2,2},{3,3,2,2},{4,3,2,2},{4,3,2,2,2} };
    int[] kingsCourt = new int[]{3,3,2,1,0,0};
    
    //TODO rever esses valores iniciais
    House stark = new House("Stark",2,3,3,2);
    House greyjoy = new House("Greyjoy",3,5,1,4);
    House lanister = new House("Lannister",3,2,5,1);
    House baratheon = new House("Baratheon",3,1,4,3);
    House tyrell = new House("Tyrell",3,4,2,5);
    //House martell = new House("Martell",3,6,6,6);
    
    //TODO os sets de carta variam pelo numero de jogadores e a expansão sendo usada, implementar essas variacoes
    List<HouseCard> cardsStark; 
    List<HouseCard> cardsGreyJoy;
    List<HouseCard> cardsLanister; 
    List<HouseCard> cardsBaratheon; 
    List<HouseCard> cardsTyrell;
    List<HouseCard> cardsMartell;
//    List<Integer[]> supplyTrack = new ArrayList<Integer[]>();
//    supplyTrack.add(new ArrayList<Integer>(new int[] {1,2));
    
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
  
}
