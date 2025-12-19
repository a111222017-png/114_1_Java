package com.rpg.roles.range;
import com.rpg.core.Role;

// Archer.java
public class Archer extends RangeRole {

    private int arrowCount;

    public Archer(String name,
                  int health,
                  int attackPower,
                  int range,
                  int maxEnergy,
                  int arrowCount) {

        super(name, health, attackPower, range, maxEnergy, "弓箭射擊");
        this.arrowCount = arrowCount;
    }

    public int getArrowCount() {
        return arrowCount;
    }

    @Override
    public void attack(Role opponent) {
        if (arrowCount <= 0) {
            System.out.println("❌ " + getName() + " 沒有箭矢了，只能徒手揮舞！");
            opponent.takeDamage(getAttackPower() / 2);
            return;
        }

        if (!consumeEnergy(5)) {
            System.out.println("✖ " + getName() + " 體力不足，射擊無力！");
            opponent.takeDamage(getAttackPower() / 2);
            return;
        }

        arrowCount--;
        System.out.println("🏹 " + getName() + " 射出箭矢攻擊 " + opponent.getName()
                + "！（剩餘箭矢：" + arrowCount + "）");
        opponent.takeDamage(getAttackPower());
    }

    @Override
    public void prepareBattle() {
        System.out.println("🏹 " + getName() + " 整理箭矢與弓弦，準備戰鬥。");
    }

    @Override
    public void afterBattle() {
        System.out.println("🏹 " + getName() + " 回收仍可使用的箭矢。");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("🏹 " + getName() + " 施展『多重箭』！");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 的弓靜靜躺在地上……");
    }
}
