package com.rpg.roles.melee;
import com.rpg.core.Role;
import com.rpg.interfaces.Defendable;

public class ShieldSwordsMan extends MeleeRole implements Defendable {

    // 盾牌防禦值
    private int shieldValue;
    // 是否正在防禦中
    private boolean defending = false;

    public ShieldSwordsMan(String name,
                           int health,
                           int attackPower,
                           int armor,
                           int shieldValue) {
        super(name, health, attackPower, armor, "盾劍");
        this.shieldValue = shieldValue;
    }

    // 取得防禦能力（給 Defendable 介面用）
    @Override
    public int getDefenseCapacity() {
        return shieldValue;
    }

    // 進入防禦狀態
    @Override
    public void defend() {
        defending = true;
        System.out.println("🛡 " + getName()
                + " 進入防禦狀態（減傷 " + shieldValue + " 點）！");
    }



    // 攻擊行為
    @Override
    public void attack(Role opponent) {
        System.out.println("🗡 " + getName()
                + " 用盾劍突擊 " + opponent.getName() + "！");
        opponent.takeDamage(getAttackPower());
    }

    // 受到傷害時，如果在防禦就減傷
    @Override
    public void takeDamage(int dmg) {
        if (defending) {
            int reduced = Math.max(0, dmg - shieldValue);
            System.out.println("🛡 防禦成功！傷害從 "
                    + dmg + " 降為 " + reduced + "！");
            defending = false; // 用完一次防禦就解除
            super.takeDamage(reduced);
        } else {
            super.takeDamage(dmg);
        }
    }

    @Override
    public void prepareBattle() {
        System.out.println(getName() + " 檢查盾牌與劍，準備戰鬥！");
    }

    @Override
    public void afterBattle() {
        System.out.println(getName() + " 檢查裝備並進行修復。");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println(getName() + " 展示技能：『盾擊震退』！");
    }

    @Override
    public void onDeath() {
        System.out.println(getName() + " 倒下了，盾牌掉落在地上…。");
    }
    @Override
    protected void beforeAttack(Role opponent) {
        System.out.println("🛡️ " + getName() + " 舉起盾牌，小心接近 " + opponent.getName() + "。");
    }

    @Override
    protected void afterAttack(Role opponent) {
        System.out.println("🛡️ " + getName() + " 收回盾牌，重新調整站位。");
    }

}
