import java.util.Random;


abstract class Pokemon extends Entity{
	public static double [][] battleTable = {{1,.5,2},{2,1,.5},{.5,2,1}};

	/**
	 Initializes pokemon's name and Hp
	 */
	public Pokemon(String n, int h, int m) {
		super(n, h, m);
	}


	public String getAttackTypeMenu(){
		return "1. Basic Attack \n2. Special Attack";
	}

	public int getNumAttackTypeMenuItems() {
		return 2;
	}

	public String getAttackMenu(int atkType){
		if(atkType == 1){
			return "1. Slam \n2. Tackle \n3. Punch";
		}
		return null;
	}

	public int getNumAttackMenuItems(int atkType){
		if(atkType == 1){
			return 3;
		}
		return 0;
	}

	public String attack(Pokemon p, int atkType, int move){
		int totalDMG = (int) (getAttackDamage(atkType, move) * getAttackMultiplier(p, atkType) + getAttackBonus(atkType));
		p.takeDamage(totalDMG);
		return this.getName() +" " +getAttackString(atkType,move)+" " + p.getName() + "," + p.getName()+" took "+ totalDMG+" damage.";

	}

	public String getAttackString(int atkType, int move){
		if(atkType == 1){
			if(move == 1){
				return " SLAMED ";
			}
			if(move == 2){
				return " TACKLED ";
			}
			if(move == 3){
				return " PUNCHED ";
			}
		}
		return null;
	}

	public int getAttackDamage(int atkType, int move){

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

		return 0;
	}

	public double getAttackMultiplier(Pokemon p, int atkType){

		if(atkType == 1){
			return 1;
		}
		return 1;
	}

	public int getAttackBonus(int atkType){
		return 0;

	}

	/*
    @return an integer based on pokemon type
    */
	public int getType() {
		if( this instanceof Fire)
			return 0;
		else if( this instanceof Water)
			return 1;
		else if( this instanceof Grass)
			return 2;
		else
			return -1;
	}

}