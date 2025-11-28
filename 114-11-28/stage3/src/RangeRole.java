public abstract class RangeRole extends Role {

    private int range;
    private int energy;
    private int maxEnergy;
    private String rangedAttackType;

    public RangeRole(String name, int health, int attackPower,
                     int range, int maxEnergy, String rangedAttackType) {

        super(name, health, attackPower);
        this.range = range;
        this.energy = maxEnergy;
        this.maxEnergy = maxEnergy;
        this.rangedAttackType = rangedAttackType;
    }

    public int getRange() {
        return range;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public String getRangedAttackType() {
        return rangedAttackType;
    }

    public boolean consumeEnergy(int cost) {
        if (energy < cost) return false;
        energy -= cost;
        return true;
    }

    public void recoverEnergy(int amount) {
        energy = Math.min(maxEnergy, energy + amount);
    }
}
