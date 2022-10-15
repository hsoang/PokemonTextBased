import java.util.Random;

public class Fire extends Pokemon{

  public Fire(String n, int h, int m){
    super(n, h, m);
  }

  @Override
  public String getAttackMenu(int atkType) {
    if(atkType==1){
      return "1. Slam \n2. Tackle \n3. Punch";
    }
    if(atkType == 2){
      return "1. Ember\n2. Fire Blast\n3. Fire Punch";
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
        return "EMBER";
      }
      if(move == 2){
        return "FIRE BLAST";
      }
      if(move == 3){
        return "FIRE PUNCH";
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
        int emberDMG = rand.nextInt((5-0)+1)+0;
        return emberDMG;
      }
      if(move == 2){
        Random rand = new Random();
        int fireBlastDMG = rand.nextInt((4-2)+1) + 2;
        return fireBlastDMG;
      }
      if(move == 3){
        Random rand = new Random();
        int firePunchDMG = rand.nextInt((4-1)+1) + 1;
        return firePunchDMG;
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