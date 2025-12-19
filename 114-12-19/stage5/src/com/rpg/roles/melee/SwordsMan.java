package com.rpg.roles.melee;
import com.rpg.core.Role;


// SwordsMan.java
public class SwordsMan extends MeleeRole {

    public SwordsMan(String name, int health, int attackPower, int armor) {
        super(name, health, attackPower, armor, "長劍");
    }

    @Override
    public void attack(Role opponent) {
        System.out.println("⚔️  " + getName() + " 揮舞長劍攻擊 " + opponent.getName());
        opponent.takeDamage(getAttackPower());
    }

    @Override
    public void prepareBattle() {
        System.out.println("⚔️  " + getName() + " 擦亮長劍，戰前準備完畢！");
    }

    @Override
    public void afterBattle() {
        System.out.println("⚔️  " + getName() + " 收起長劍，整理裝備。");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println("⚔️  " + getName() + " 施展『連續斬擊』！");
    }

    @Override
    public void onDeath() {
        System.out.println("💀 " + getName() + " 倒下了……");
    }
    @Override
    protected void beforeAttack(Role opponent) {
        // 攻擊前戰吼（Hook Method）
        System.out.println(" " + getName() + "：「受死吧！」");
    }

    @Override
    protected void afterAttack(Role opponent) {
        // 只有對方還活著才做事
        if (opponent.isAlive()) {
            System.out.println("⚔️ " + getName() + " 擺出防禦架式，觀察敵人動向……");
        }
    }

}
