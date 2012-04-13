package nutis.model.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Terrain {
   int id;
   private String name;
   private int mustering;
   private int crowns;
   private int barrels;
   private HouseType capital; 
   private House owner; 
   private boolean ocean;
   private List<Unit> units = new ArrayList<Unit>();
   private List<Terrain> neighbourhood = new ArrayList<Terrain>();
   private int x;
   private int y;
  
   public Terrain(int id,String name, int mustering, int crowns, int barrels, HouseType capital,int x, int y) {
     this(id,false,name,mustering,crowns,barrels,capital,x,y);
   }
   
   public Terrain(int id,String name,int x, int y) {
     this(id,true,name,0,0,0,null,x,y);
   }
   
   public Terrain(int id,boolean ocean,String name, int mustering, int crowns, int barrels, HouseType capital,int x, int y) {
     this.id=id;
     this.ocean=ocean;
     this.name=name;
     this.mustering=mustering;
     this.crowns=crowns;
     this.barrels=barrels;
     this.capital=capital;
     this.x=x;
     this.y=y;
   }

  public String getName() {
    return name;
  }
  
  public int getMustering() {
    return mustering;
  }
  
  public void setMustering(int mustering) {
    this.mustering = mustering;
  }
  
  public int getCrowns() {
    return crowns;
  }
  
  public void setCrowns(int crowns) {
    this.crowns = crowns;
  }
  
  public int getBarrels() {
    return barrels;
  }
  
  public void setBarrels(int barrels) {
    this.barrels = barrels;
  }
  
  public House getOwner() {
    return owner;
  }
  
  public void setOwner(House owner) {
    this.owner = owner;
  }
  
  public List<Unit> getUnits() {
    return units;
  }
  
  public Collection<Terrain> getNeighbourhood() {    
    return Collections.unmodifiableCollection(neighbourhood);
  }
  
//  public void addNeighbourhood(Terrain[] newNeighbours) {
//    for (Terrain neighbour:newNeighbours){
//      addNeighbour(neighbour);
//    }
//  }

  public void addNeighbour(Terrain neighbour) {
    if(!neighbourhood.contains(neighbour)){
      neighbourhood.add(neighbour);
      if(!neighbour.getNeighbourhood().contains(this)){
        neighbour.addNeighbour(neighbour );
      }
    }
  }
  
  public HouseType getCapital() {
    return capital;
  }

  
  public int getId() {
    return id;
  }

  
  public boolean isOcean() {
    return ocean;
  }

  public void setId(int id) {
    this.id = id;
  }

  
  public int getX() {
    return x;
  }

  
  public int getY() {
    return y;
  }

}
