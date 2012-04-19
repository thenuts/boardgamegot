package nutis.model.core.map;

import java.util.ArrayList;
import java.util.List;

import nutis.model.core.GameMap;
import nutis.model.core.House;
import nutis.model.core.HouseCard;
import nutis.model.core.HouseType;
import nutis.model.core.Order;
import nutis.model.core.Terrain;
import nutis.model.core.UnitType;

public class GameMap2003 extends GameMap {



  public GameMap2003(){ 
    supplyTrack = new int[][]{{2,2},{3,2},{3,2,2},{3,2,2,2},{3,3,2,2},{4,3,2,2},{4,3,2,2,2} };
    kingsCourt = new int[]{3,3,2,1,0,0};
    
    HouseType stark = new HouseType(1,"Stark","#808080");
    HouseType greyjoy = new HouseType(2,"Greyjoy","#000000");
    HouseType lanister = new HouseType(3,"Lannister","#a40000");
    HouseType baratheon = new HouseType(4,"Baratheon","#a4a400");
    HouseType tyrell = new HouseType(5,"Tyrell","#006000");   
    addHouseType(stark);   
    addHouseType(greyjoy);   
    addHouseType(lanister);   
    addHouseType(baratheon);   
    addHouseType(tyrell);
    
    //TODO migrar metodo para a key interna do terrain
    addLand( new Terrain(47,"Sunspear",1,1,1,null,670,1810));
    addLand( new Terrain(46,"Yronwood",0,1,0,null,500,1750),47);
    addLand( new Terrain(45,"StarFail",1,0,0,null,325,1810),46);
    addLand( new Terrain(44,"The Arbor",0,1,0,null,90,1850));
    addLand( new Terrain(43,"Dornish Marches",1,0,0,null,320,1630),45,46);
    addLand( new Terrain(42,"Oldtown",0,0,1,null,160,1640),43);
    addLand( new Terrain(41,"Storms End",0,0,1,null,620,1570),43);
    addLand( new Terrain(40,"Kingswood",0,1,0,null,600,1450),41,43);
    addLand( new Terrain(39,"The Reach",0,0,1,null,325,1500),40,43);
    addLand( new Terrain(38,"Highgarden",2,0,2,tyrell,170,1445),39,43,42);
    addLand( new Terrain(37,"Kings Landing",0,2,2,null,540,1290),40,39);
    addLand( new Terrain(36,"Blackwater",2,0,0,null,360,1340),37,39);
    addLand( new Terrain(35,"Searoad Marches",1,0,0,null,170,1375),38,39,36);
    addLand( new Terrain(34,"Dragonstone",1,1,2,baratheon,850,1190));
    addLand( new Terrain(33,"Crackclaw Point",0,0,1,null,575,1220),36,37);
    addLand( new Terrain(32,"Harrenhal",0,1,1,null,435,1130),33,36);
    addLand( new Terrain(31,"Stoney Sept",0,1,0,null,340,1250),32,36,35);
    addLand( new Terrain(30,"The Eyrie",1,1,1,null,630,1055));
    addLand( new Terrain(29,"The Mountains of the moon",1,0,0,null,580,960),30,33);
    addLand( new Terrain(28,"Riverrun",1,1,2,null,400,1080),31,32);
    addLand( new Terrain(27,"Lanisterport",2,0,2,lanister,220,1080),28,31,35);
    addLand( new Terrain(26,"The Fingers",1,0,0,null,590,880),29);
    addLand( new Terrain(25,"The Twins",0,1,0,null,440,870),26,29);
    addLand( new Terrain(24,"Seagard",1,1,2,null,315,955),28,25);
    addLand( new Terrain(23,"Pike",1,1,2,greyjoy,140,920));
    addLand( new Terrain(22,"Widow´s Watch",1,0,0,null,630,640));
    addLand( new Terrain(21,"White Harbor",0,0,1,null,565,555),22);
    addLand( new Terrain(20,"Moat Calin",0,0,1,null,380,740),21,25,24);
    addLand( new Terrain(19,"Greywater Watch",1,0,0,null,255,735),20,24);
    addLand( new Terrain(18,"Flint´s finger",0,0,1,null,135,775),19);
    addLand( new Terrain(17,"Karhold",0,1,0,null,655,275));
    addLand( new Terrain(16,"Winterfell",1,1,2,stark,325,360),21,20,17);
    addLand( new Terrain(15,"The Stone Shore",1,0,0,null,180,635),16);
    addLand( new Terrain(14,"Castle Black",0,1,0,null,530,200),17,16);
                            
    addLand( new Terrain(12,"The Shivering Sea",800,510),22,21,16,17,14);
    addLand( new Terrain(11,"The Narrow Sea",755,680),30,29,26,25,20,21,22,12);
    addLand( new Terrain(10,"Black Water Bay",680,1255),40,37,33);
    addLand( new Terrain(9, "Shipbreaker Bay",835,1380),41,40,33,34,10,11);
    addLand( new Terrain(8, "Sea of Dorne",580,1640),47,46,43,41,9);
    addLand( new Terrain(7, "Summer Sea",600,1930),46,47,8,9);
    addLand( new Terrain(6, "Redwyne Straits",200,1890),38,42,45,46,44,7);
    addLand( new Terrain(5, "The Golden Sound",60,1190),28,27,35);
    addLand( new Terrain(4, "Ironman´s Bay",40,1025),18,19,24,28,23,5);
    addLand( new Terrain(3, "Sunset Sea",15,1330),18,35,38,4,5,6);
    addLand( new Terrain(2, "Black Ice",25,630),14,16,15,19,18,3);
    
    stark.getCards().add(new HouseCard(1,0,"Catelyn Stark",0,0,"your defense order bonus is doubled this battle"));
    stark.getCards().add(new HouseCard(2,0,"Maester Luwin",0,0,"If you win this battle, you mmay return one discaded house cards to your hand"));
    stark.getCards().add(new HouseCard(3,1,"Ser Rodrick Cassel",0,1,null));
    stark.getCards().add(new HouseCard(4,1,"House Mormont Lord",1,0,null));
    stark.getCards().add(new HouseCard(5,2,"Robb Stark",0,0,"Attacking knigths in your army add +3 to your combat strength intead of +2"));
    stark.getCards().add(new HouseCard(6,2,"Greatjon Umber",1,0,null));
    stark.getCards().add(new HouseCard(7,3,"Eddard Stark",0,2,null));
    
    greyjoy.getCards().add(new HouseCard(8,0,"Victorion Greyjoy",0,0,"attacking ships is your army add +2 to your combat strength instead of +1"));
    greyjoy.getCards().add(new HouseCard(9,0,"Barlon Greyjoin",0,0,"the combat strength of your opponents leader is 0"));
    greyjoy.getCards().add(new HouseCard(10,1,"Aeron Damphair",0,1,null));
    greyjoy.getCards().add(new HouseCard(11,1,"House Blacktyde Captain",0,1,null));
    greyjoy.getCards().add(new HouseCard(12,2,"Asha Greyjoyr",0,0,"if you win this battle, you may immediately remove support or consolidate power order in any sea or land area adjacent to the battle"));
    greyjoy.getCards().add(new HouseCard(13,2,"Theon GreyJoy",1,0,null));
    greyjoy.getCards().add(new HouseCard(14,3,"Euron Crow´s Eye",2,0,null));

    lanister.getCards().add(new HouseCard(15,0,"Vargo hoat",0,0,"attacking footman in your army add +2 to your combat strength instead of +1"));
    lanister.getCards().add(new HouseCard(16,0,"Cercei Lannister",0,0,"if you win this battle, remove any one of the losing opponent´s order tokens from the board"));
    lanister.getCards().add(new HouseCard(17,1,"Kevan Lannister",1,0,null));
    lanister.getCards().add(new HouseCard(18,1,"House Lannister Captaon",1,0,null));
    lanister.getCards().add(new HouseCard(19,2,"Ser Jaime Lannister",0,0,"if you win this battle, immediatly gain two power tokens"));
    lanister.getCards().add(new HouseCard(20,2,"Tyrion Lannister",0,1,null));
    lanister.getCards().add(new HouseCard(21,3,"Tywon Lannister",2,0,null));
    
    baratheon.getCards().add(new HouseCard(22,0,"Melisandre of Asshai",0,0,"if you win this battle, you may expend ono power token to choose and discard one house card from the loosing player´s hand."));
    baratheon.getCards().add(new HouseCard(23,0,"Sallanhor Saan",0,0,"The combat stregth of any supporting ships is reduced to 0"));
    baratheon.getCards().add(new HouseCard(24,1,"Ser Axell florent",1,0,null));
    baratheon.getCards().add(new HouseCard(25,1,"House florent lord",1,0,null));
    baratheon.getCards().add(new HouseCard(26,2,"Ser Davos Seaworth",0,0,"If you win this battle, tou may upgrade one of your footmen to a knight.That footman must have participated int the battle as either attacker or defender"));
    baratheon.getCards().add(new HouseCard(27,2,"Renly Baratheon",0,1,null));
    baratheon.getCards().add(new HouseCard(28,3,"Stannis Baratheon",1,1,null));
        
    tyrell.getCards().add(new HouseCard(29,0,"Queen of Thorns",0,0,"cancel the special ability of your opponent´s house card"));
    tyrell.getCards().add(new HouseCard(30,0,"Brienne of tarth",0,0,"immediately discar your opponent´s defense order in the area of the battle"));
    tyrell.getCards().add(new HouseCard(31,1,"House Tyrel Captain",1,0,null));
    tyrell.getCards().add(new HouseCard(32,1,"Margaery Tyrel",0,1,null));
    tyrell.getCards().add(new HouseCard(33,2,"Ser Loras Tyrell",0,0,"immediately kill one of your opponent´s attacking or defending footmen units."));
    tyrell.getCards().add(new HouseCard(34,2,"Ser Garlan Tyrell",1,0,null));
    tyrell.getCards().add(new HouseCard(35,3,"Mace Tyrell",2,0,null));
    
    addUnitType( new UnitType(1,"Footman",1,false));//limit 10
    addUnitType( new UnitType(2,"Knigth",1,false));//limit 4
    addUnitType( new UnitType(3,"Ship",1,true));//limit 5    

    addUnitType( new UnitType(4,"Eyre",6,false));
    addUnitType( new UnitType(5,"Sunspear",5,false));
    addUnitType( new UnitType(6,"KingsLanding",5,false));
    
    addOrder(new Order(1,"March",-1,false));
    addOrder(new Order(2,"March",0,false));
    addOrder(new Order(3,"March",1,true));
    addOrder(new Order(4,"Defense",1,false));
    addOrder(new Order(5,"Defense",1,false));
    addOrder(new Order(6,"Defense",2,true));
    addOrder(new Order(7,"Raid",null,false));
    addOrder(new Order(8,"Raid",null,false));
    addOrder(new Order(9,"Raid",1,true));
    addOrder(new Order(10,"Support",null,false));
    addOrder(new Order(11,"Support",null,false));
    addOrder(new Order(12,"Support",1,true));
    addOrder(new Order(13,"Consolidate",null,false));
    addOrder(new Order(14,"Consolidate",null,false));
    addOrder(new Order(15,"Consolidate",null,true));
  }


  @Override
  public List<House> initializeHouses() {
    List<House> houses = new ArrayList<House>();
  House stark = new House(houseTypes.get(1),2,3,3,2);
  House greyjoy = new House(houseTypes.get(2),3,5,1,4);
  House lanister = new House(houseTypes.get(3),3,2,5,1);
  House baratheon = new House(houseTypes.get(4),3,1,4,3);
  House tyrell = new House(houseTypes.get(5),3,4,2,5);    
  houses.add(stark);
  houses.add(greyjoy);
  houses.add(lanister);
  houses.add(baratheon);
  houses.add(tyrell);
  
  

  //TODO Incluir pessas Neutras
//  lands.get(30).addUnit(eyre);
  
  UnitType footMan=unitTypes.get(1);
  UnitType knigth=unitTypes.get(2);
  UnitType ship=unitTypes.get(3);
  
  stark.addUnit(footMan,lands.get(21));
  stark.addUnit(footMan,lands.get(16));
  stark.addUnit(knigth,lands.get(16));
  stark.addUnit(ship,lands.get(12));

  greyjoy.addUnit(footMan,lands.get(19));
  greyjoy.addUnit(footMan,lands.get(23));
  greyjoy.addUnit(knigth,lands.get(23));
  greyjoy.addUnit(ship,lands.get(4));
  greyjoy.addUnit(ship,lands.get(4));

  lanister.addUnit(footMan,lands.get(31));
  lanister.addUnit(footMan,lands.get(27));
  lanister.addUnit(knigth,lands.get(27));
  lanister.addUnit(ship,lands.get(5));

  baratheon.addUnit(footMan,lands.get(40));
  baratheon.addUnit(footMan,lands.get(34));
  baratheon.addUnit(knigth,lands.get(34));
  baratheon.addUnit(ship,lands.get(9));
  baratheon.addUnit(ship,lands.get(9));

  tyrell.addUnit(footMan,lands.get(43));
  tyrell.addUnit(footMan,lands.get(38));
  tyrell.addUnit(knigth,lands.get(38));
  tyrell.addUnit(ship,lands.get(6));
  
    return houses;
  }


@Override
public int getNumberOfPlayers() {
  return 5;
}
  
  

}
