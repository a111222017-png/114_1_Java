public class Archer extends RangeRole {

    public Archer(String name, int health, int attackPower,
                  int range, int maxEnergy) {

        super(name, health, attackPower, range, maxEnergy, "弓箭攻擊");
    }

    @Override
    public void attack(Role opponent) {
        if (!consumeEnergy(5)) {
            System.out.println("❌ " + getName() + " 沒箭了，用匕首攻擊！");
            opponent.takeDamage(getAttackPower() / 2);
            return;
        }

        System.out.println("🏹 " + getName() + " 射出一箭攻擊 " + opponent.getName());
        opponent.takeDamage(getAttackPower());
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("🏹【連珠箭】三連射快速出擊！");
    }

    @Override
    public void prepareBattle() {
        System.out.println("🏹 " + getName() + " 調整弓弦張力。");
    }

    @Override
    public void afterBattle() {
        System.out.println("🏹 " + getName() + " 補充箭矢。");
        recoverEnergy(5);
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 倒下，弓掉落在地上…");
        System.out.println("---");
    }
}
