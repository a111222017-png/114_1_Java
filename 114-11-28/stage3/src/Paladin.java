public class Paladin extends MeleeRole implements Healable {

    public Paladin(String name, int health, int attackPower, int armor) {
        super(name, health, attackPower, armor, "聖錘");
    }

    @Override
    public void attack(Role opponent) {
        System.out.println("🔨 " + getName() + " 以聖錘擊打 " + opponent.getName());
        opponent.takeDamage(getAttackPower());
    }

    @Override
    public void heal(Role target) {
        int amount = 25;
        System.out.println("✨ " + getName() + " 對 " + target.getName() + " 施放神聖治癒 +" + amount);
        target.setHealth(target.getHealth() + amount);
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("🔥【聖光審判】聖光落下傷害敵人！");
    }

    @Override
    public void prepareBattle() {
        System.out.println("🙏 " + getName() + " 祈禱並充滿聖光。");
    }

    @Override
    public void afterBattle() {
        System.out.println("✨ " + getName() + " 以聖光治療自身的小傷。");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 倒下，聖光逐漸消散…");
        System.out.println("---");
    }
}
