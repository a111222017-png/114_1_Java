public class RPG {
    public static void main(String[] args) {

        // 建立劍士與魔法師的角色
        SwordsMan swordsMan_Light = new SwordsMan("光明劍士", 100, 20);
        SwordsMan swordsMan_Dark = new SwordsMan("黑暗劍士", 100, 25);

        Magician magician_Light = new Magician("光明法師", 80, 15, 10);
        Magician magician_Dark = new Magician("黑暗法師", 80, 20, 5);

        // 戰鬥過程
        System.out.println("戰鬥開始！");

        swordsMan_Light.attack(swordsMan_Dark);
        System.out.println(" → " + swordsMan_Dark); // 顯示狀態

        magician_Dark.attack(magician_Light);
        System.out.println(" → " + magician_Light); // 顯示狀態

        magician_Dark.heal(swordsMan_Dark);
        System.out.println(" → " + swordsMan_Dark); // 顯示狀態
    }
}
