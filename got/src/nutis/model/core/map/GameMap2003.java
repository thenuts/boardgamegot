package nutis.model.core.map;

import nutis.model.core.GameMap;
import nutis.model.core.House;
import nutis.model.core.HouseCard;
import nutis.model.core.Terrain;
import nutis.model.core.UnitType;

public class GameMap2003 extends GameMap {

  void init(){
       
    House stark = new House("Stark",2,3,3,2);
    House greyjoy = new House("Greyjoy",3,5,1,4);
    House lanister = new House("Lannister",3,2,5,1);
    House baratheon = new House("Baratheon",3,1,4,3);
    House tyrell = new House("Tyrell",3,4,2,5);    
    
    addLand(47, new Terrain("Sunspear",1,1,1,null));
    addLand(46, new Terrain("Yronwood",0,1,0,null),47);
    addLand(45, new Terrain("StarFail",1,0,0,null),46);
    addLand(44, new Terrain("The Arbor",0,1,0,null));
    addLand(43, new Terrain("Dornish Marches",1,0,0,null),45,46);
    addLand(42, new Terrain("Oldtown",0,0,1,null),43);
    addLand(41, new Terrain("Storms End",0,0,1,null),43);
    addLand(40, new Terrain("Kingswood",0,1,0,null),41,43);
    addLand(39, new Terrain("The Reach",0,0,1,null),40,43);
    addLand(38, new Terrain("Highgarden",2,0,2,tyrell),39,43,42);
    addLand(37, new Terrain("Kings Landing",0,2,2,null),40,39);
    addLand(36, new Terrain("Blackwater",2,0,0,null),37,39);
    addLand(35, new Terrain("Searoad Marches",1,0,0,null),38,39,36);
    addLand(34, new Terrain("Dragonstone",1,1,2,baratheon));
    addLand(33, new Terrain("Crackclaw Point",0,0,1,null),36,37);
    addLand(32, new Terrain("Harrenhal",0,1,1,null),33,36);
    addLand(31, new Terrain("Stoney Sept",0,1,0,null),32,36,35);
    addLand(30, new Terrain("The Eyrie",1,1,1,null));
    addLand(29, new Terrain("The Mountains of the moon",1,0,0,null),30,33);
    addLand(28, new Terrain("Riverrun",1,1,2,null),31,32);
    addLand(27, new Terrain("Lanisterport",2,0,2,lanister),28,31,35);
    addLand(26, new Terrain("The Fingers",1,0,0,null),29);
    addLand(25, new Terrain("The Twins",0,1,0,null),26,29);
    addLand(24, new Terrain("Seagard",1,1,2,null),28,25);
    addLand(23, new Terrain("Pike",1,1,2,greyjoy));
    addLand(22, new Terrain("Widow´s Watch",1,0,0,null));
    addLand(21, new Terrain("White Harbor",0,0,1,null),22);
    addLand(20, new Terrain("Moat Calin",0,0,1,null),21,25,24);
    addLand(19, new Terrain("Greywater Watch",1,0,0,null),20,24);
    addLand(18, new Terrain("Flint´s finger",0,0,1,null),19);
    addLand(17, new Terrain("Karhold",0,1,0,null));
    addLand(16, new Terrain("Winterfell",1,1,2,stark),21,20,17);
    addLand(15, new Terrain("The Stone Shore",1,0,0,null),16);
    addLand(14, new Terrain("Castle Black",0,1,0,null),17,16);

    addLand(12, new Terrain("The Shivering Sea"),22,21,16,17,14);
    addLand(11, new Terrain("The Narrow Sea"),30,29,26,25,20,21,22,12);
    addLand(10, new Terrain("Black Water Bay"),40,37,33);
    addLand(9, new Terrain("Shipbreaker Bay"),41,40,33,34,10,11);
    addLand(8, new Terrain("Sea of Dorne"),47,46,43,41,9);
    addLand(7, new Terrain("Summer Sea"),46,47,8,9);
    addLand(6, new Terrain("Redwyne Straits"),38,42,45,46,44,7);
    addLand(5, new Terrain("The Golden Sound"),28,27,35);
    addLand(4, new Terrain("Ironman´s Bay"),18,19,24,28,23,5);
    addLand(3, new Terrain("Sunset Sea"),18,35,38,4,5,6);
    addLand(2, new Terrain("Black Ice"),14,16,15,19,18,3);
    
    stark.getCards().add(new HouseCard(0,"Catelyn Stark",0,0,"your defense order bonus is doubled this battle"));
    stark.getCards().add(new HouseCard(0,"Maester Luwin",0,0,"If you win this battle, you mmay return one discaded house cards to your hand"));
    stark.getCards().add(new HouseCard(1,"Ser Rodrick Cassel",0,1,null));
    stark.getCards().add(new HouseCard(1,"House Mormont Lord",1,0,null));
    stark.getCards().add(new HouseCard(2,"Robb Stark",0,0,"Attacking knigths in your army add +3 to your combat strength intead of +2"));
    stark.getCards().add(new HouseCard(2,"Greatjon Umber",1,0,null));
    stark.getCards().add(new HouseCard(3,"Eddard Stark",0,2,null));
    
    greyjoy.getCards().add(new HouseCard(0,"Victorion Greyjoy",0,0,"attacking ships is your army add +2 to your combat strength instead of +1"));
    greyjoy.getCards().add(new HouseCard(0,"Barlon Greyjoin",0,0,"the combat strength of your opponents leader is 0"));
    greyjoy.getCards().add(new HouseCard(1,"Aeron Damphair",0,1,null));
    greyjoy.getCards().add(new HouseCard(1,"House Blacktyde Captain",0,1,null));
    greyjoy.getCards().add(new HouseCard(2,"Asha Greyjoyr",0,0,"if you win this battle, you may immediately remove support or consolidate power order in any sea or land area adjacent to the battle"));
    greyjoy.getCards().add(new HouseCard(2,"Theon GreyJoy",1,0,null));
    greyjoy.getCards().add(new HouseCard(3,"Euron Crow´s Eye",2,0,null));

    lanister.getCards().add(new HouseCard(0,"Vargo hoat",0,0,"attacking footman in your army add +2 to your combat strength instead of +1"));
    lanister.getCards().add(new HouseCard(0,"Cercei Lannister",0,0,"if you win this battle, remove any one of the losing opponent´s order tokens from the board"));
    lanister.getCards().add(new HouseCard(1,"Kevan Lannister",1,0,null));
    lanister.getCards().add(new HouseCard(1,"House Lannister Captaon",1,0,null));
    lanister.getCards().add(new HouseCard(2,"Ser Jaime Lannister",0,0,"if you win this battle, immediatly gain two power tokens"));
    lanister.getCards().add(new HouseCard(2,"Tyrion Lannister",0,1,null));
    lanister.getCards().add(new HouseCard(3,"Tywon Lannister",2,0,null));
    
    baratheon.getCards().add(new HouseCard(0,"Melisandre of Asshai",0,0,"if you win this battle, you may expend ono power token to choose and discard one house card from the loosing player´s hand."));
    baratheon.getCards().add(new HouseCard(0,"Sallanhor Saan",0,0,"The combat stregth of any supporting ships is reduced to 0"));
    baratheon.getCards().add(new HouseCard(1,"Ser Axell florent",1,0,null));
    baratheon.getCards().add(new HouseCard(1,"House florent lord",1,0,null));
    baratheon.getCards().add(new HouseCard(2,"Ser Davos Seaworth",0,0,"If you win this battle, tou may upgrade one of your footmen to a knight.That footman must have participated int the battle as either attacker or defender"));
    baratheon.getCards().add(new HouseCard(2,"Renly Baratheon",0,1,null));
    baratheon.getCards().add(new HouseCard(3,"Stannis Baratheon",1,1,null));
        
    tyrell.getCards().add(new HouseCard(0,"Queen of Thorns",0,0,"cancel the special ability of your opponent´s house card"));
    tyrell.getCards().add(new HouseCard(0,"Brienne of tarth",0,0,"immediately discar your opponent´s defense order in the area of the battle"));
    tyrell.getCards().add(new HouseCard(1,"House Tyrel Captain",1,0,null));
    tyrell.getCards().add(new HouseCard(1,"Margaery Tyrel",0,1,null));
    tyrell.getCards().add(new HouseCard(2,"Ser Loras Tyrell",0,0,"immediately kill one of your opponent´s attacking or defending footmen units."));
    tyrell.getCards().add(new HouseCard(2,"Ser Garlan Tyrell",1,0,null));
    tyrell.getCards().add(new HouseCard(3,"Mace Tyrell",2,0,null));
    
    UnitType footMan = new UnitType("Footman",1,false);//limit 10
    UnitType knigth = new UnitType("Knigth",1,false);//limit 4
    UnitType ship = new UnitType("Ship",1,true);//limit 5    
    
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
  }

}
