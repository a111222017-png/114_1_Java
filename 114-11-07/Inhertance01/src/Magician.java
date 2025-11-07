public class Magician extends Role {
    private int healPower; // 治癒力

    // 建構子：初始化魔法師的名稱、生命值、攻擊力和治癒力
    public Magician(String name, int health, int attackPower, int healPower) {
        super(name, health, attackPower);
        this.healPower = healPower;
    }

    // 取得治癒力
    public int getHealPower() {
        return healPower;
    }

    // 攻擊對手
    public void attack(Role opponent) {
        opponent.setHealth(opponent.getHealth() - getAttackPower());
        System.out.println(getName() + " 攻擊 " + opponent.getName() +
                " 造成 " + getAttackPower() + " 點傷害！");
    }

    // 治癒隊友
    public void heal(Role ally) {
        ally.setHealth(ally.getHealth() + healPower);
        System.out.println(getName() + " 治療 " + ally.getName() +
                " 回復 " + healPower + " 點生命值！");
    }
}
