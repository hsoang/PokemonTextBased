public abstract class Entity{
  private String name;
  private int hp;
  private int maxHP;

  /**
  Stores the name and max hp of the entity
  */
  public Entity(String n,int h, int mHp){
    name = n;
    hp = h;
    maxHP = mHp;
  }
  
  /**
  @returns the entity's Hp
  */
  public int getHp(){
    return hp;
  }

  /**
  @returns the entity's Max Hp
  */
  public int getMaxHp(){
    return maxHP;
  }

  /**
  @param the damage taken
  Calculates the damage entity takes
  */
  public void takeDamage(int d){
    if (d < 0){
      d = 0;
    }
    hp -= d;
    if(hp<0){
      hp = 0;
    }
  }

  /**
  heals the entity for full Hp
  */
  public void heal(){
    hp = maxHP;
  }

  /**
  @return the name of entity as a string
  */
  public String getName(){
    return name;
  }


  public String toString(){
    String rString = (getName() +" HP: " + getHp() + "/" + getMaxHp());
    return rString;
  }


}