public abstract class MeleeRole extends Role {

    private int armor;
    private String weaponType;

    public MeleeRole(String name, int health, int attackPower, int armor, String weaponType) {
        super(name, health, attackPower);
        this.armor = armor;
        this.weaponType = weaponType;
    }

    public int getArmor() {
        return armor;
    }

    public String getWeaponType() {
        return weaponType;
    }

    @Override
    public void takeDamage(int dmg) {
        int reduced = Math.max(0, dmg - armor);
        super.takeDamage(reduced);
    }
}
