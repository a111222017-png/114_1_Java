public class Magician extends RangeRole implements Healable {

    public Magician(String name, int health, int attackPower,
                    int range, int maxEnergy) {

        super(name, health, attackPower, range, maxEnergy, "魔法攻擊");
    }

    @Override
    public void attack(Role opponent) {
        if (!consumeEnergy(10)) {
            System.out.println("❌ " + getName() + " 魔力不足，改為普通攻擊！");
            opponent.takeDamage(getAttackPower() / 2);
            return;
        }

        System.out.println("✨ " + getName() + " 施放火球術攻擊 " + opponent.getName());
        opponent.takeDamage(getAttackPower() + 10);
    }

    @Override
    public void heal(Role target) {
        int amount = 20;
        System.out.println("💖 " + getName() + " 對 " + target.getName() + " 使用治癒術 +" + amount);
        target.setHealth(target.getHealth() + amount);
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("🔮【奧義：星落術】無數魔力星辰降臨！");
    }

    @Override
    public void prepareBattle() {
        System.out.println("📖 " + getName() + " 開始吟唱咒語…");
    }

    @Override
    public void afterBattle() {
        System.out.println("🧘 " + getName() + " 冥想恢復魔力。");
        recoverEnergy(15);
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 生命之火熄滅…");
        System.out.println("✨ 化為魔力粒子消散。");
        System.out.println("---");
    }
}
