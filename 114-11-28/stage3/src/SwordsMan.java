public class SwordsMan extends MeleeRole {

    public SwordsMan(String name, int health, int attackPower, int armor) {
        super(name, health, attackPower, armor, "長劍");
    }

    @Override
    public void attack(Role opponent) {
        System.out.println("🗡️ " + getName() + " 揮劍攻擊 " + opponent.getName());
        opponent.takeDamage(getAttackPower());
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("✨ 【劍氣斬】" + getName() + " 釋放一道強力劍氣！");
    }

    @Override
    public void prepareBattle() {
        System.out.println("🗡️ " + getName() + " 擦拭劍刃，準備迎戰！");
    }

    @Override
    public void afterBattle() {
        System.out.println("🗡️ " + getName() + " 將劍收入劍鞘。");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 倒下了…");
        System.out.println("⚔️ 劍掉落在地上。");
        System.out.println("---");
    }
}
