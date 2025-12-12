package com.rpg.roles.range;
import com.rpg.core.Role;


public class Priest extends Role {

    private int groupHealPower;   // 群體治療量

    public Priest(String name, int health, int attackPower, int groupHealPower) {
        super(name, health, attackPower);
        this.groupHealPower = groupHealPower;
    }

    @Override
    public void attack(Role opponent) {
        opponent.setHealth(opponent.getHealth() - this.getAttackPower());
        System.out.println(
                this.getName() + " 發出神聖之光攻擊 " +
                        opponent.getName() + " 造成 " +
                        this.getAttackPower() + " 點傷害！");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("==================================");
        System.out.println("│  " + this.getName() + " 的特殊技能");
        System.out.println("│--------------------------------");
        System.out.println("│ 技能名稱：群體治療");
        System.out.println("│ 技能描述：治療所有隊友");
        System.out.println("│ 治癒量：" + groupHealPower + " 點生命值");
        System.out.println("==================================");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 倒下了，聖光慢慢消散。");
    }

    @Override
    public void prepareBattle() {
        System.out.println("✝ " + getName() + " 雙手合十，祈禱戰鬥順利。");
    }

    @Override
    public void afterBattle() {
        System.out.println("🙏 " + getName() + " 為所有隊友祝福並協助恢復體力。");
    }

    // 給主程式用的 getter
    public int getGroupHealPower() {
        return groupHealPower;
    }
}
