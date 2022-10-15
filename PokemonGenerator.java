import java.util.HashMap;
import java.io.*;
import java.util.*;

public class PokemonGenerator {
    private HashMap<String,String> pokemon = new HashMap<String,String>();
    private static PokemonGenerator instance = null;

    private PokemonGenerator(){
//        File pokemonList = new File("PokemonList.txt");
        try{
            File pokemonList = new File("PokemonList.txt");
            Scanner reader = new Scanner(pokemonList);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                String[] temp = line.split(",");
                if(temp.length >= 2) {
                    String key = temp[0];
                    String value = temp[1];
                    pokemon.put(temp[0], temp[1]);
                }
//                }else{
//                    System.out.println("Error in PokemonGenerator reading file.");
//                }
//                reader.close();
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    public static PokemonGenerator getInstance(){
        if(instance == null){
            instance = new PokemonGenerator();
        }
        return instance;
    }

    public Pokemon generateRandomPokemon(int level){
        Random rand = new Random();
        int randpoke = rand.nextInt((3-1)+1) + 1;
        Pokemon poke = null;

        Set<String> keySet = pokemon.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        int size = keyList.size();
        int rand1 = new Random().nextInt(size);

        String randomKey = keyList.get(rand1);
        String randomValue = pokemon.get(randomKey);

        if(level == 1){
            if(randomValue.equalsIgnoreCase("Fire")){
                poke = new Fire(randomKey, 20,20);
//                return poke;
            }
            else if(randomValue.equalsIgnoreCase("Water")){
                poke = new Water(randomKey, 20,20);
//                return poke;
            }
            else if(randomValue.equalsIgnoreCase("Grass")){
                poke = new Grass(randomKey, 20,20);
//                return poke;
            }
        }
        else {
            for (int i = 1; i < level; i++) {
                if (randomValue.equalsIgnoreCase("Fire")) {
                    poke = new Fire(randomKey, 20, 20);
//                return poke;
                } else if (randomValue.equalsIgnoreCase("Water")) {
                    poke = new Water(randomKey, 20, 20);
//                return poke;
                } else if (randomValue.equalsIgnoreCase("Grass")) {
                    poke = new Grass(randomKey, 20, 20);
//                return poke;
                }
                for (int counter = 1; counter < i; counter++) {
//                Pokemon temp = addRandomBuff(poke);
                    poke = addRandomBuff(poke);
                }
                poke = addRandomBuff(poke);
            }
        }

        return poke;
    }

    public Pokemon getPokemon(String name){
        Pokemon poke = null;
        if(pokemon.get(name).equalsIgnoreCase("Fire")){
            poke = new Fire(name, 20,20);
            return poke;
        }
        else if(pokemon.get(name).equalsIgnoreCase("Grass")){
            poke = new Grass(name, 20,20);
            return poke;
        }
        else if(pokemon.get(name).equalsIgnoreCase("Water")){
            poke = new Water(name, 20,20);
            return poke;
        }
        return poke;
    }

    public Pokemon addRandomBuff(Pokemon p){
        Random rand = new Random();
        int randBuff = rand.nextInt((2-1)+1) + 1;
        if(randBuff==1){
            p = new AttackUp(p);
            return p;
        }
        else if(randBuff==2){
            p = new HpUp(p);
            return p;
        }
        return null;
    }

    public Pokemon addRandomDebuff(Pokemon p){
        Random rand = new Random();
        int randBuff = rand.nextInt((2-1)+1) + 1;
        if(randBuff==1){
            p = new AttackDown(p);
            return p;
        }
        else if(randBuff==2){
            p = new HpDown(p);
            return p;
        }
        return null;
    }
}
