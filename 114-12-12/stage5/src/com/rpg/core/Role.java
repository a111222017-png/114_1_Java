package com.rpg.core;

public abstract class Role {

    // ===== 角色基本資料 =====
    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;



    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(health, maxHealth));
    }

    // 受傷（沿用你原本邏輯，有需要可以再加護甲等計算）
    public void takeDamage(int dmg) {
        if (dmg <= 0) return;
        this.health -= dmg;
        System.out.println("   " + name + " 受到 " + dmg + " 點傷害，目前 HP = " + health);

        if (!isAlive()) {
            this.health = 0;
            onDeath();
        }
    }

    // ====== 第五階段：Template Method Pattern 重點 ======

    /**
     * 模板方法：定義「一次戰鬥」的流程
     * final：不讓子類別改變流程順序
     */
    public final void performBattle(Role opponent) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println(" ⚔️  " + getName() + " vs " + opponent.getName());
        System.out.println("╚════════════════════════════════════╝");

        // 步驟 1：戰鬥前檢查（具體方法）
        if (!preBattleCheck(opponent)) {
            System.out.println("❌ 戰鬥無法進行（有人已經死掉了）\n");
            return;
        }

        // 步驟 2：戰鬥準備（原本的抽象方法）
        prepareBattle();

        // 步驟 3：攻擊前行為（Hook，可選擇覆寫）
        beforeAttack(opponent);

        // 步驟 4：執行攻擊（原本的抽象方法）
        attack(opponent);

        // 步驟 5：攻擊後行為（Hook，可選擇覆寫）
        afterAttack(opponent);

        System.out.println("────────────────────────────────────\n");
    }

    /**
     * 具體方法：戰前檢查
     *（所有角色共用，不給子類別改，參考老師範例）
     */
    private boolean preBattleCheck(Role opponent) {
        if (!this.isAlive()) {
            System.out.println("❌ " + getName() + " 已經陣亡，無法戰鬥。");
            return false;
        }
        if (!opponent.isAlive()) {
            System.out.println("❌ " + opponent.getName() + " 已經陣亡，無法戰鬥。");
            return false;
        }
        return true;
    }

    // Hook Method：子類別可選擇要不要覆寫
    protected void beforeAttack(Role opponent) {
        // 預設不做事
    }

    // Hook Method：子類別可選擇要不要覆寫
    protected void afterAttack(Role opponent) {
        // 預設不做事
    }

    // ===== 你原本就有的抽象/必須實作的方法 =====

    /** 真正的攻擊動作（每個子類別自己寫） */
    public abstract void attack(Role opponent);

    /** 戰鬥前準備（每個子類別自己寫） */
    public abstract void prepareBattle();

    /** 戰鬥結束後的行為（第四階段原本就有） */
    public abstract void afterBattle();

    /** 顯示特殊技能（第四階段原本就有） */
    public abstract void showSpecialSkill();

    /** 死亡時要做什麼（第四階段原本就有） */
    public abstract void onDeath();

    @Override
    public String toString() {
        return getName() + " [HP=" + health + "/" + maxHealth + "]";
    }
    // 原本老師 Stage5 範例的版本先保留，改成呼叫三參數那個
    public Role(String name, int maxHealth) {
        this(name, maxHealth, 0);  // 預設攻擊力 0
    }

    // ★ 新增：包含 attackPower 的建構子，讓子類別可以 super(..., attackPower)
    public Role(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

}
