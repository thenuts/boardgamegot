package nutis.model.core;


public class HouseCard {
   private House house;
   private int power;
   private String name;
   private int fortification;
   private int sword;
   private String text;
  
  public HouseCard(int power, String name, int sword, int fortification, String text) {
    this.power=power;
    this.name=name;
    this.sword=sword;
    this.fortification=fortification;
    this.text=text;
  }

  public House getHouse() {
    return house;
  }
  
  public void setHouse(House house) {
    this.house = house;
  }
  
  public int getFortification() {
    return fortification;
  }
  
  public void setFortification(int fortification) {
    this.fortification = fortification;
  }
  
  public int getSword() {
    return sword;
  }
  
  public void setSword(int sword) {
    this.sword = sword;
  }
  
  public String getText() {
    return text;
  }
  
  public void setText(String text) {
    this.text = text;
  }

  
  public int getPower() {
    return power;
  }

  
  public void setPower(int power) {
    this.power = power;
  }

  
  public String getName() {
    return name;
  }

  
  public void setName(String name) {
    this.name = name;
  }
   
   //TODO implementar algum mecanimos generico do poder, provavelmente um metodo com acesso ao math e battle 
}
