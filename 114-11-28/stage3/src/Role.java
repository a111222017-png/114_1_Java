public abstract class Role {

    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;

    public Role(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setHealth(int newHealth) {
        if (newHealth > maxHealth) newHealth = maxHealth;
        if (newHealth < 0) newHealth = 0;
        this.health = newHealth;
    }

    // 被攻擊（統一入口）
    public void takeDamage(int dmg) {
        setHealth(this.health - dmg);
    }

    // === 抽象方法 ===
    public abstract void attack(Role opponent);
    public abstract void prepareBattle();
    public abstract void afterBattle();
    public abstract void showSpecialSkill();
    public abstract void onDeath();
}
