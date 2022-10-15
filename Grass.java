import java.util.Random;

public class Grass extends Pokemon{

  public Grass(String n, int h, int m){
    super(n, h, m);
  }

  @Override
  public String getAttackMenu(int atkType) {
    if(atkType == 1){
      return "1. Slam \n2. Tackle \n3. Punch";
    }
    if(atkType == 2){
      return "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";
    }
    return null;
  }

  @Override
  public int getNumAttackMenuItems(int atkType) {
    if(atkType == 2) {
      return 3;
    }
    return 0;
  }
  @Override
  public String getAttackString(int atkType, int move){
    if(atkType == 1){
      if(move == 1){
        return "SLAMED";
      }
      if(move == 2){
        return "TACKLED";
      }
      if(move == 3){
        return "PUNCHED";
      }
    }
    else if(atkType == 2){
      if(move == 1){
        return "Vine Whip";
      }
      if(move == 2){
        return "Razor Leaf";
      }
      if(move == 3){
        return "Solar Beam";
      }
    }
    return null;
  }
  @Override
  public int getAttackDamage(int atkType, int move){
    if(atkType == 1){
      if(move == 1){
        Random rand = new Random();
        int slam = rand.nextInt((5-0)+1)+0;
        return slam;
      }
      if(move == 2){
        Random rand = new Random();
        int tackle = rand.nextInt((3-2)+1)+2;
        return tackle;
      }
      if(move == 3){
        Random rand = new Random();
        int punch = rand.nextInt((4-1)+1)+1;
        return punch;
      }
    }
    if(atkType == 2){
      if(move == 1){
        Random rand = new Random();
        int vineWhip = rand.nextInt((3-1)+1) + 1;
        return vineWhip;
      }
      if(move == 2){
        Random rand = new Random();
        int razorLeaf = rand.nextInt((4-2)+1) + 2;
        return razorLeaf;
      }
      if(move == 3){
        Random rand = new Random();
        int solarBeam = rand.nextInt((5-0)+1) + 0;
        return solarBeam;
      }
    }
    return 0;
  }
  @Override
  public double getAttackMultiplier(Pokemon p , int atkType){
    if(atkType == 2){
      return Pokemon.battleTable[this.getType()][p.getType()];
    }
    return 1;
  }
}