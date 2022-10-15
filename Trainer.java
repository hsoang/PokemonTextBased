import java.util.ArrayList;
import java.util.Random;

public class Trainer extends Entity{
  private int money ;
  private int potions ;
  private int pokeballs;
  private Point loc;
  private ArrayList<Pokemon>pokemon = new ArrayList<Pokemon> ();

  
  /*Intializes trainer's name, pokemon, and map
   * @param n trainer's name
   * @param p trainer's pokemon
   * @param m map
   */
  public Trainer(String n, Pokemon p){
    super(n, 20, 20);
    this.pokemon.add(p);   
    loc = Map.getInstance().findStart();
    Map.getInstance().reveal(loc);
    this.money = 10;
    this.potions = 3;
    this.pokeballs = 3;
  }

  /*
   * Retrieves trainer's money
   * return trainer's money
   */
  public int getMoney(){
    return this.money;
  }

  /*
   * Trainer use money to spend on items
   * @param atm the spending amount
   * @return true if trainer has enough money
   */
  public boolean spendMoney(int amt){
    if (this.money-amt>=0) {
    	this.money -= amt;
    	return true;
    }else {
    	return false;
    }
  }

  /*
   * Add more money to trainer's money
   * @param atm is the amount add to trainer's money
   */
  public void receiveMoney(int amt){
    this.money += amt;
  }

  /*
   * Check if trainer has potions
   */
  public boolean hasPotion(){
    if(this.potions >0 ){
      return true;
    }else{
      return false;
    }
  }

  /*
   * add potion to trainer
   */
  public void receivePotion(){
     this.potions += 1;
  }

  /*
   * Heal chosen pokemon
   * @param pokeIndex index of the pokemon to use the potion
   */
  public void usePotions(int pokeIndex){
	  if(this.hasPotion()) {
		  this.pokemon.get(pokeIndex).heal();
		  this.potions -= 1;
          pokemon.set(pokeIndex,PokemonGenerator.getInstance().addRandomBuff(getPokemon(pokeIndex)));
	  }
  }
  
  /*
   * check if trainer has pokeball
   */
  public boolean hasPokeball(){
    if(this.pokeballs > 0 ){
      return true;
    }else{
      this.pokeballs = 0;
      return false;
    }
  }

  /*
   * Add pokeball to trainer
   */
  public void receivePokeball(){
     this.pokeballs += 1;
  }

  /*
   * Use pokeball to catch a pokemon
   * The chances to catch pokemon based on its health status
   * @param p catchable pokemon
   */
  public boolean catchPokemon(Pokemon p){
    Random rand = new Random();
    int chances = rand.nextInt((100-1)+1)+1;
    if(p.getHp()<5 && chances <= 75 && this.hasPokeball()){
        pokemon.add(p);
        this.pokeballs -= 1;
        return true;
    }
    else if(p.getHp()>=5 && p.getHp()<= 25 && chances <= 30 && hasPokeball() ){
        pokemon.add(p);
        this.pokeballs -= 1;
        return true;  
    }
    else if(p.getHp()> 25 && p.getHp()<= 30 && chances <= 5 && this.hasPokeball()){
        pokemon.add(p);
        this.pokeballs -= 1;
        return true;
    }else if(this.hasPokeball()==false){
      System.out.println("Sorry, you don't have any Pokeball");
      return false;
    }
    else {
      this.pokeballs -= 1;
      return false;
    }

  }

  /*
   * @return location of trainer on the map
   */
  public Point getLocation(){
    return this.loc;
  }

  /*
   * Trainer move to north direction by 1
   */
  public char goNorth(){
    char returnMap = Map.getInstance().getCharAtLoc(this.loc);
    if(this.loc.getX()-1 < 0){
      System.out.println("Cannot go that way");
    }else{
//      map.reveal(loc);
      this.loc.translate(-1, 0);
    }
    return returnMap;
  }

  /*
   * Trainer move to south direction by 1
   */
  public char goSouth(){
    char returnMap = Map.getInstance().getCharAtLoc(this.loc);
    if(this.loc.getX() +1  > 4){
      System.out.println("Cannot go that way");
    }else{
      this.loc.translate(1, 0);
    }
    return returnMap;
  }

  /*
   * Trainer move to east direction by 1
   */
  public char goEast(){
    char returnMap = Map.getInstance().getCharAtLoc(this.loc);
    if(this.loc.getY() +1 > 4){
      System.out.println("Cannot go that way");
    }else{
      this.loc.translate(0, 1);
    }
    return returnMap;
  }

  /*
   * Trainer move to west direction by 1
   */
  public char goWest(){
    char returnMap = Map.getInstance().getCharAtLoc(this.loc);
    if(this.loc.getY() - 1 < 0){
      System.out.println("Cannot go that way");
    }else{
      this.loc.translate(0, -1);
    }
    return returnMap;
  }

  /*
   * @return numbers of pokemon Trainer has.
   */
  public int getNumPokemon(){
    return this.pokemon.size();
  }

  /*
   * Heal all pokemon
   */
  public void healAllPokemon(){
    for(int i = 0; i < this.pokemon.size();i++){
      if(this.pokemon.get(i)!= null){
        this.pokemon.get(i).heal();
      }
    }
  }

  public void buffAllPokemon(){
    for(int i = 0; i < this.pokemon.size();i++){
      pokemon.set(i,PokemonGenerator.getInstance().addRandomBuff(getPokemon(i)));
    }
  }

  public void deBuffPokemon(int index){
    pokemon.set(index,PokemonGenerator.getInstance().addRandomDebuff(getPokemon(index)));
  }
  /*
   * Get pokemon @param index
   */
  public Pokemon getPokemon(int index){
    return this.pokemon.get(index);
  }
  
  /*
   * Display trainer's list of pokemon
   */
  public String getPokemonList(){
	 String list = "";
	 for(int i = 0; i < pokemon.size(); i++){
	     list += (i+1)+ "." +pokemon.get(i) + "\n";
	 }
	 return list;
	 
  }

  public Pokemon removePokemon(int index){


      System.out.println("You chose " + pokemon.get(index ).getName()+".");
      return pokemon.remove(index);

  }

  /*
   * Display trainer's information
   */
  @Override
  public String toString(){
	return super.toString() + "\nMoney: "+ this.money + "\nPotions: " + this.potions + "\nPoke Balls: "+this.pokeballs + "\nPokemon\n-------\n"+getPokemonList();
    
  }
  
}