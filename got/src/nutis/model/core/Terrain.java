package nutis.model.core;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Terrain {
   private String name;
   private int mustering;
   private int crowns;
   private int barrels;
   private House capital; 
   private House owner; 
   private List<Unit> units;
   private List<Terrain> neighbourhood;
  
   public Terrain(String name, int mustering, int crowns, int barrels, House capital) {
     this(false,name,mustering,crowns,barrels,capital);
   }
   
   public Terrain(String name) {
     this(true,name,0,0,0,null);
   }
   
   public Terrain(boolean ocean,String name, int mustering, int crowns, int barrels, House capital) {
     // TODO Auto-generated constructor stub
   }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
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
  
  public void setUnits(List<Unit> units) {
    this.units = units;
  }
  
  public Collection<Terrain> getNeighbourhood() {    
    return Collections.unmodifiableCollection(neighbourhood);
  }
  
  public void addNeighbourhood(Terrain[] newNeighbours) {
    for (Terrain neighbour:newNeighbours){
      addNeighbour(neighbour);
    }
  }

  public void addNeighbour(Terrain neighbour) {
    if(!neighbourhood.contains(neighbour)){
      neighbourhood.add(neighbour);
      if(!neighbour.getNeighbourhood().contains(this)){
        neighbour.addNeighbourhood(new Terrain[] {neighbour });
      }
    }
  }

  
  public House getCapital() {
    return capital;
  }

  
  public void setCapital(House capital) {
    this.capital = capital;
  }
}
