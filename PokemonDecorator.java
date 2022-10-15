public abstract class PokemonDecorator extends Pokemon{
    private Pokemon pokemon;

    public PokemonDecorator(Pokemon p, String extraName, int extraHp){
        super(p.getName() + " " + extraName, p.getHp() +extraHp, p.getMaxHp() + extraHp);
        pokemon = p;
    }
    @Override
    public String getAttackMenu(int atkType){
        return pokemon.getAttackMenu(atkType);
    }

    @Override
    public int getNumAttackMenuItems(int atkType) {
        return pokemon.getNumAttackMenuItems(atkType);
    }

    @Override
    public String getAttackString(int atkType, int move) {
        return pokemon.getAttackString(atkType, move);
    }

    @Override
    public int getAttackDamage(int atkType, int move) {
        return pokemon.getAttackDamage(atkType, move);
    }

    @Override
    public double getAttackMultiplier(Pokemon p, int atkType) {
        return pokemon.getAttackMultiplier(p, atkType);
    }

    @Override
    public int getAttackBonus(int atkType) {
        return pokemon.getAttackBonus(atkType);
    }

    @Override
    public int getType() {
        return pokemon.getType();
    }
}