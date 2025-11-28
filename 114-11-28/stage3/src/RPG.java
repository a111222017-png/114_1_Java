public class RPG {

    public static void main(String[] args) {

        System.out.println("=== RPG 第四階段測試啟動 ===");

        // 建立測試用簡易角色（避免報錯）
        TestRole a = new TestRole("角色A", 100, 10);
        TestRole b = new TestRole("角色B", 100, 15);

        a.prepareBattle();
        a.attack(b);

        b.prepareBattle();
        b.attack(a);

        System.out.println(a.getName() + " HP = " + a.getHealth());
        System.out.println(b.getName() + " HP = " + b.getHealth());
    }
}

// ========== 測試用角色（避免錯誤）==========
class TestRole extends Role {

    public TestRole(String name, int health, int atk) {
        super(name, health, atk);
    }

    @Override
    public void attack(Role opponent) {
        System.out.println(getName() + " 攻擊 " + opponent.getName());
        opponent.takeDamage(getAttackPower());
    }

    @Override
    public void prepareBattle() {
        System.out.println(getName() + " 戰前準備中…");
    }

    @Override
    public void afterBattle() {
        System.out.println(getName() + " 戰後收尾。");
    }

    @Override
    public void showSpecialSkill() {
        System.out.println(getName() + " 展示技能（測試用）");
    }

    @Override
    public void onDeath() {
        System.out.println(getName() + " 死亡（測試用）");
    }
}
