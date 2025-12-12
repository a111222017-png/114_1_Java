package com.rpg.roles.melee;
import com.rpg.core.Role;
import com.rpg.interfaces.Defendable;
import com.rpg.interfaces.Healable;

// Paladin.java
public class Paladin extends MeleeRole implements Defendable, Healable {

    private int defenseCapacity;   // 額外防禦值
    private int healPower;         // 治療量
    private int holyPower;         // 聖能（0~100）
    private boolean defending = false;

    public Paladin(String name,
                   int health,
                   int attackPower,
                   int armor,
                   int defenseCapacity,
                   int healPower,
                   int holyPower) {

        super(name, health, attackPower, armor, "聖劍");
        this.defenseCapacity = defenseCapacity;
        this.healPower = healPower;
        this.holyPower = holyPower;
    }

    public int getHolyPower() {
        return holyPower;
    }

    private void useHolyPower(int amount) {
        holyPower -= amount;
        if (holyPower < 0) holyPower = 0;
    }

    // ==== 攻擊 ====
    @Override
    public void attack(Role opponent) {
        System.out.println("✨⚔️ " + getName() + " 揮舞聖劍斬擊 " + opponent.getName());
        opponent.takeDamage(getAttackPower() + 3);
        useHolyPower(5);
    }

    @Override
    public void prepareBattle() {
        System.out.println("✨ " + getName() + " 誦念聖言，覆以聖光。");
    }

    @Override
    public void afterBattle() {
        System.out.println("✨ " + getName() + " 進行戰後祈禱，恢復心神。");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("✨ " + getName() + " 施展『聖光審判』！");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 倒下，但信念不滅……");
    }

    // ==== Defendable 實作 ====
    @Override
    public void defend() {
        if (!canDefend()) {
            System.out.println("❌ " + getName() + " 暫時無法防禦！");
            return;
        }
        defending = true;
        System.out.println("🛡✨ " + getName() + " 展開聖光護盾，防禦值 +" + defenseCapacity);
        useHolyPower(5);
    }

    @Override
    public int getDefenseCapacity() {
        return defenseCapacity;
    }

    @Override
    public void takeDamage(int damage) {
        int value = damage;
        if (defending && defenseCapacity > 0) {
            value = damage - defenseCapacity;
            if (value < 0) value = 0;
            System.out.println("🛡✨ " + getName() + " 的聖光護盾減免 " + defenseCapacity + " 傷害！");
            defending = false;
        }
        super.takeDamage(value);
    }

    // ==== Healable 實作 ====
    @Override
    public void heal(Role target) {
        if (!canHeal()) {
            System.out.println("❌ " + getName() + " 聖能不足，無法治療！");
            return;
        }
        int amount = getHealPower();
        System.out.println("💚✨ " + getName() + " 對 " + target.getName()
                + " 施放『聖光治癒』，恢復 " + amount + " HP！");
        target.setHealth(target.getHealth() + amount);
        useHolyPower(10);
    }

    @Override
    public int getHealPower() {
        return healPower;
    }
    @Override
    protected void beforeAttack(Role opponent) {
        System.out.println("✨ " + getName() + " 說：「聖光將審判你！」");
    }

    @Override
    protected void afterAttack(Role opponent) {
        if (opponent.isAlive()) {
            System.out.println("✨ " + getName() + " 聚集聖能，準備下一次攻擊。");
        }
    }

}
