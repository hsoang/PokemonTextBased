import java.util.Random;

public class Water extends Pokemon{

  public Water(String n, int h, int m){
    super(n, h, m);
  }

  @Override
  public String getAttackMenu(int atkType) {
    if(atkType == 1){
      return "1. Slam \n2. Tackle \n3. Punch";
    }
    if(atkType == 2){
      return "1. Water gun\n2. Bubble Beam\n3. Waterfall";
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
        return "SLAMMED";
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
        return "WATER GUN";
      }
      if(move == 2){
        return "BUBBLE BEAM";
      }
      if(move == 3){
        return "WATER FALL";
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
        int waterGun = rand.nextInt((5-2)+1) + 2;
        return waterGun;
      }
      if(move == 2){
        Random rand = new Random();
        int bubbleBeam = rand.nextInt((3-1)+1) + 1;
        return bubbleBeam;
      }
      if(move == 3){
        Random rand = new Random();
        int waterfall = rand.nextInt((4-1)+1) + 1;
        return waterfall;
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