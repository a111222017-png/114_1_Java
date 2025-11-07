public class SwordsMan extends Role {
    // 建構子：呼叫父類別的建構子
    public SwordsMan(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    // 攻擊對手
    public void attack(Role opponent) {
        opponent.setHealth(opponent.getHealth() - getAttackPower());
        System.out.println(getName() + " 攻擊 " + opponent.getName() +
                " 造成 " + getAttackPower() + " 點傷害！");
    }
}
