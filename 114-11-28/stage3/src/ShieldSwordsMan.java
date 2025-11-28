public class ShieldSwordsMan extends MeleeRole implements Defendable {

    private int shieldValue;
    private boolean defending = false;

    public ShieldSwordsMan(String name, int health, int attackPower, int armor, int shieldValue) {
        super(name, health, attackPower, armor, "盾劍");
        this.shieldValue = shieldValue;
    }

    @Override
    public void attack(Role opponent) {
        System.out.println("🛡️⚔️ " + getName() + " 用盾與劍同時攻擊 " + opponent.getName());
        opponent.takeDamage(getAttackPower());
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("✨【盾擊震懾】" + getName() + " 用盾牌擊退敵人！");
    }

    @Override
    public void prepareBattle() {
        System.out.println("🛡️ " + getName() + " 調整盾牌與劍的姿勢，準備戰鬥！");
    }

    @Override
    public void afterBattle() {
        System.out.println("🛡️ " + getName() + " 檢查盾牌損傷並修復。");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 力竭倒下…");
        System.out.println("🛡️ 盾牌重重落地。");
        System.out.println("⚔️ 劍也掉落在地上。");
        System.out.println("---");
    }

    @Override
    public void defence() {
        defending = true;
        System.out.println("🛡️ " + getName() + " 進入防禦狀態（減傷 + " + shieldValue + "）！");
    }

    @Override
    public void takeDamage(int dmg) {
        if (defending) {
            int reduced = Math.max(0, dmg - shieldValue);
            System.out.println("🛡️ 防禦成功！傷害從 " + dmg + " 降為 " + reduced);
            defending = false;
            super.takeDamage(reduced);
        } else {
            super.takeDamage(dmg);
        }
    }
}
