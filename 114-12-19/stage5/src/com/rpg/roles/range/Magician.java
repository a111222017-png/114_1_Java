package com.rpg.roles.range;
import com.rpg.core.Role;
import com.rpg.interfaces.Healable;

// Magician.java
public class Magician extends RangeRole implements Healable {

    // 治療量
    private int healPower = 15;

    public Magician(String name,
                    int health,
                    int attackPower,
                    int range,
                    int maxEnergy,
                    int healPower) {

        super(name, health, attackPower, range, maxEnergy, "魔法攻擊");
        this.healPower = healPower;
    }

    @Override
    public void attack(Role opponent) {
        // 消耗 10 點能量施放魔法攻擊
        if (!consumeEnergy(10)) {
            System.out.println("✖ " + getName() + " 魔力不足，改為普通攻擊！");
            opponent.takeDamage(getAttackPower());
            return;
        }

        System.out.println("✨ " + getName() + " 施放火球術攻擊 " + opponent.getName() + "！");
        opponent.takeDamage(getAttackPower() + 10);
    }

    @Override
    public void prepareBattle() {
        System.out.println("✨ " + getName() + " 調整魔力波動，準備戰鬥。");
    }

    @Override
    public void afterBattle() {
        System.out.println("✨ " + getName() + " 回收魔力，休息中。");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("✨ " + getName() + " 施展『流星雨』！");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 的魔力消散於空中……");
    }

    // ==== Healable 介面實作 ====
    @Override
    public void heal(Role target) {
        int amount = getHealPower();
        System.out.println("💚 " + getName() + " 對 " + target.getName()
                + " 使用治癒術，恢復 " + amount + " HP！");
        target.setHealth(target.getHealth() + amount);
    }

    @Override
    public int getHealPower() {
        return healPower;
    }
}
