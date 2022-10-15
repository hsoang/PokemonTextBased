import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    
    PokemonGenerator g = PokemonGenerator.getInstance();
    Random rand = new Random();

    int currMap = 1;
    int level = 1;


    //Create object map and it will automatically load area 1
	  Map map = Map.getInstance();
	  
    
	  System.out.println("Prof. Oak: Hello there new trainer, what is you name?");
	    String trainerName = CheckInput.getString();
	    System.out.println("Great to meet you, " + trainerName);
	    System.out.println("Choose your first pokemon:");
	    System.out.println("1. Charmander");
	    System.out.println("2. Bulbasaur");
	    System.out.println("3. Squirtle");
	    
      /**
      Create a player and then get player's input which Pokemon they want at starter pokemon. After that assign chosen pokemon to player.
      */
	    Trainer player = null;
        Pokemon playerPoke = null;
	    int firstPokemon = CheckInput.getIntRange(1, 3);
	    if(firstPokemon == 1) {
	    	playerPoke = g.getPokemon("Charmander");
	    }
        else if(firstPokemon == 2) {
            playerPoke = g.getPokemon("Bulbasaur");
	    }
	    else if(firstPokemon == 3) {
            playerPoke =g.getPokemon("Squirtle");
	    }

        player = new Trainer(trainerName,playerPoke);




    // GAME WHILE LOOP ///
    //Game over when player's hp down to 0 or when the player want to quit
    while(player.getHp() > 0){
	    System.out.println(player.toString());
	    System.out.println(map.mapToString(player.getLocation()));
	    
      int direction = mainMenu();
      switch(direction){
            case 1:
              player.goNorth();
              break;
            case 2:
              player.goSouth();
              break;
            case 3:
              player.goEast();
              break;
            case 4:
              player.goWest();
              break;
            case 5:
              System.out.println("Quit");
              System.exit(0);
              break;               
      }
      //********//
      System.out.println();
      if(map.getCharAtLoc(player.getLocation())== 'w'){
        
        Pokemon wild = g.generateRandomPokemon(level);
        System.out.println();
        System.out.println("A wild "+ wild.getName() + " has appeared.");
        System.out.println("Wild " + wild.getName() + " HP: " + wild.getHp()+"/"+wild.getMaxHp());
        System.out.println();

        //WHILE LOOP FOR WILD POKEMON///
        int options = 0;
        while(options != 4 ){

          System.out.println("What do you want to do?");
          System.out.println("1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away");
          options = CheckInput.getIntRange(1,4); 

          //Fight option  
          if(options==1){

            int count = 0;
            boolean allDead = false;
            for(int i = 0; i < player.getNumPokemon(); i++ ){            
              if(player.getPokemon(i).getHp() <= 0){
                // allDead = true; 
                count += 1;
                if(count == player.getNumPokemon()){
                  allDead = true;
                }                
              }
              else{
                allDead = false;
              }
            }

            if(allDead == true){
              int d = rand.nextInt(4) + 1;
              player.takeDamage(d);
              System.out.println("All of your pokemons are dead!");
              break; 
            }else {
              trainerAttack(player, wild);
            }

            if (wild.getHp() <= 0){
              System.out.println("\nYou defeat wild "+wild.getName());
              map.reveal(player.getLocation());
              map.removeCharAtLoc(player.getLocation());
              break;
            }

          //Use potion option
          }else if(options == 2) {
              if (player.hasPotion() == true) {
                  System.out.println(player.getPokemonList());
                  System.out.println("Which Pokemon do you want to heal?");
                  int healPokemon = CheckInput.getIntRange(1, player.getNumPokemon());
                  player.usePotions(healPokemon - 1);
              } else {
                  System.out.println("You have no more potions!");
              }
          }

          //Catch pokemon option
          else if(options == 3){
              if(player.catchPokemon(wild)){
                      System.out.println("Great job, you caught another Pokemon!");
                      System.out.println(player.getPokemonList());
                      if(player.getNumPokemon()==7){
                          System.out.println("Your party is full, need to release a pokemon.");
                          System.out.println(player.getPokemonList());
                          int release = CheckInput.getIntRange(1,7);
                          player.removePokemon(release-1);
                      }
                      map.reveal(player.getLocation());
                      map.removeCharAtLoc(player.getLocation());
                      options = 4;
              }
              else{
                  System.out.println("The wild Pokemon escaped!?");
              }
          }


          //Run away option
          else {
            options = 4;
            map.reveal(player.getLocation());
            //PLAYER MOVES RANDOM DIRECTION IF THEY RUN
            int runAway  = rand.nextInt((4-1)+1)+1;
            switch(runAway){
              case 1:
                player.goNorth();
                break;
              case 2:
                player.goSouth();
                break;
              case 3:
                player.goEast();
                break;
              case 4:
                player.goWest();
                break;              
            }
          }           
        }// end of Encounter wild Pokemon while loop       
      }   
      //WHEN TRAINER REACHES THE END OF THE MAP
      if (map.getCharAtLoc(player.getLocation()) == 'f'){

        System.out.println("Congratz!! You made it to the Gym, now you have to defeat me to get to new area.");
        System.out.println("Don't think about running away or catching my pokemon.");

        Pokemon boss = g.generateRandomPokemon(level+3);

        System.out.println("Gym leader chose "+ boss.getName()+".");

      while(boss.getHp()>=0) {
        System.out.println("What do you want to do?");
        System.out.println("1. Fight.\n2. Use Potion.");
        int options = CheckInput.getIntRange(1, 2);
        if (options == 1) {
          int count = 0;
          boolean allDead = false;
          for (int i = 0; i < player.getNumPokemon(); i++) {
            if (player.getPokemon(i).getHp() <= 0) {
              count += 1;
              if (count == player.getNumPokemon()) {
                allDead = true;
              }
            }
            else {
              allDead = false;
            }
          }
          if (allDead == true) {
            int d = rand.nextInt(4) + 1;
            player.takeDamage(d);
            System.out.println("All of your pokemons are dead!");
            System.out.println("Try again, after you're stronger.");
            level = 1;
            // currMap = 1;
            
            break;
          } else {
            trainerAttack(player, boss);
          }
          if (boss.getHp() <= 0) {
            System.out.println("\nYou defeat gym leader's pokemon " + boss.getName());
            System.out.println("Move on to the next area.");
            player.buffAllPokemon();
            level += 1;
            currMap += 1;
            map.loadMap(currMap);
            break;
          }
          //Use potion option
        } else if (options == 2) {
          if (player.hasPotion() == true) {
            System.out.println(player.getPokemonList());
            System.out.println("Which Pokemon do you want to heal?");
            int healPokemon = CheckInput.getIntRange(1, player.getNumPokemon());
            player.usePotions(healPokemon - 1);
          } else {
            System.out.println("You have no more potions!");
          }
        }
      }
        //System.out.println(currMap);        
        map.reveal(player.getLocation());
      }
      //WHEN TRAINER ENCOUNTERS A PERSON
      if (map.getCharAtLoc(player.getLocation()) == 'p'){
        int randPerson = rand.nextInt(4) + 1;
        switch(randPerson) {
          case 1:
            System.out.println("You've encountered Chansey!\nChansey throws you a potion and runs.");
            player.receivePotion();
            break;
          case 2:
            System.out.println("You are ambushed. As you open your eyes, you see a Gastly flying away. Gastley AMBUSHED you for 3 damage.");
            player.takeDamage(3);
            break;
          case 3:
            System.out.println("You run into Misty.\nMisty: " + player.getName() + ", you're going to need this!\nMisty gives you 4 dollars.");
            player.receiveMoney(4);
            break;
          case 4:
            System.out.println("Something drops on your head.\n...\nTurns out it is a PokeBall.\nYou receive a PokeBall and take 1 damage.");
            player.receivePokeball();
            player.takeDamage(1);
            break;         
        }
        map.reveal(player.getLocation());
        map.removeCharAtLoc(player.getLocation());
      }
      //WHEN TRAINER ENCOUNTERS AN ITEM
      if (map.getCharAtLoc(player.getLocation()) == 'i'){
        int randomItem = rand.nextInt(2) + 1;
        switch(randomItem){
          case 1:
            player.receivePotion();
            System.out.println("You've found a potion!\n");
            break;
          case 2:
            player.receivePokeball();
            System.out.println("You've found a PokeBall!\n");
            break;
        }
        map.reveal(player.getLocation());
        map.removeCharAtLoc(player.getLocation());
      }
      //WHEN TRAINER ENCOUNTERS A CITY
      if (map.getCharAtLoc(player.getLocation()) == 'c'){
        System.out.println("You've entered the city.\nWhere would you like to go?");
        System.out.println("1. Store\n2. Pokemon Hospital");
        int cityInput = CheckInput.getIntRange(1,2);
        if (cityInput == 1){
          store(player);
        }else if (cityInput == 2){
          System.out.println("Hello! Welcome to the Pokemon Hospital.\nI'll fix your poor pokemon up in a jiffy!\n...\n....\nThere you go! See you again soon.\n");
          player.healAllPokemon();
        }
        map.reveal(player.getLocation());
        
      }
      //WHEN TRAINER ENCOUNTERS NOTHING
      if (map.getCharAtLoc(player.getLocation()) == 'n'){
        map.reveal(player.getLocation());
      }
    }
    //********//
    //END OF GAME LOOP//
    //********//  
  }

  //Display the main menu
  public static int mainMenu(){
    System.out.println("\nMain Menu:");
      System.out.println("1. Go North");
      System.out.println("2. Go South");
      System.out.println("3. Go East");
      System.out.println("4. Go West");
      System.out.println("5. Quit");
      int directions = CheckInput.getIntRange(1,5);
      return directions;
  }

  /**
  Display and interact with the store.
  @param Trainer t the player
  */
  public static void store(Trainer t){
      int userInput = 0;
	    do{
	      System.out.println("Hello! What Can I help you with?");
	      System.out.println("1. Buy Potions - $5\n2. Buy Poke Balls - $3\n3. Exit");
	      userInput = CheckInput.getIntRange(1,3);
	      
	      if (userInput == 1){
	        if (t.spendMoney(5)){
	          t.receivePotion();
	        }else {
	          System.out.println("Not enough money for a potion!");
	        }
	      }else if (userInput == 2){
	        if (t.spendMoney(3)){
	          t.receivePokeball();
	        }else {
	          System.out.println("Not enough money for a PokeBall!");
	        }
	      }else {
	        System.out.println("Thank you, come again soon!");
	      }
	      
	    }while (userInput != 3);
	  }

  /**
  The fight between trainer's Pokemon and wild Pokemon
  @param Trainer t the player
  @param Pokemon wild construct a wild Pokemon
  */
  static void trainerAttack(Trainer t, Pokemon wild){
    PokemonGenerator g = PokemonGenerator.getInstance();
    Pokemon playerPoke = null; 
    System.out.println();
    
    System.out.println("Choose a Pokemon: ");
    System.out.println(t.getPokemonList());
  
    int chosenPoke ;
    System.out.println();

    boolean alive = true;
    do{
       chosenPoke = CheckInput.getIntRange(1,t.getNumPokemon());
      playerPoke = t.getPokemon(chosenPoke-1);
      if(playerPoke.getHp()<=0){
        System.out.println(playerPoke.getName()+" cant fight");
        System.out.println("Choose another Pokemon: ");
        System.out.println(t.getPokemonList());
      }else{
        alive = false;
      }
    }while(alive);
    System.out.println(playerPoke.getName()+ " I choose you!!");

  
    System.out.println(playerPoke.getAttackTypeMenu());
    /*
    1. Basic Attack
    2. Special Attack
    */
    int attackoptions = CheckInput.getIntRange(1,2);
    if(attackoptions==1){    
      System.out.println(playerPoke.getAttackMenu(1));
      /*
      1. Slam \n2. Tackle \n3. Punch
      */
      int basicAttackOptions = CheckInput.getIntRange(1,3);
      System.out.println(playerPoke.attack(wild,1,basicAttackOptions));
      System.out.println("Wild " + wild.getName()+" HP: "+wild.getHp() + "/" + wild.getMaxHp());      
    }
    else if(attackoptions==2){
      System.out.println(playerPoke.getAttackMenu(2));
      /*
      Depend on which pokemon to get its special menu
      */
      int specialAttackOptions = CheckInput.getIntRange(1,3);
      System.out.println(playerPoke.attack(wild,2,specialAttackOptions));
      System.out.println("Wild " + wild.getName()+" HP: "+wild.getHp() + "/" + wild.getMaxHp());
    }

    //Debuff wild pokemon during fight
    Random rand = new Random();
    int chances = rand.nextInt((100-1)+1)+1;
    if( chances<= 25){
      wild = g.addRandomDebuff(wild);
      System.out.println("Wild Pokemon got debuff.");
      System.out.println(wild.getName());
      System.out.println();
    }

    //Wild Pokemon Attack
    int fightBack = rand.nextInt(2) + 1 ;
    int wildBasicAttack = rand.nextInt(3) + 1 ;
    switch(fightBack){
        case 1:
          System.out.println(wild.attack(playerPoke,1,wildBasicAttack));
          break;
        case 2:
          System.out.println(wild.attack(playerPoke,2,wildBasicAttack));
          break;
    }
    //Debuff player's pokemon during fight
    if( chances<= 10){
      t.deBuffPokemon(chosenPoke-1);
      System.out.println("Your Pokemon got debuff.");
      System.out.println(playerPoke.getName());
      System.out.println();
    }

    if(playerPoke.getHp()<=0){
      return;
    }
    
  }   
}
      
        
        
      
                          
