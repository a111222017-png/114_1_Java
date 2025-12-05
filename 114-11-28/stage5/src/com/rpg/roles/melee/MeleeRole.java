package com.rpg.roles.melee;
import com.rpg.core.Role;

// MeleeRole.java
public abstract class MeleeRole extends Role {
    private int armor;
    private String weaponType;

    public MeleeRole(String name, int health, int attackPower, int armor, String weaponType) {
        super(name, health, attackPower);
        this.armor = armor;
        this.weaponType = weaponType;
    }

    public int getArmor() {
        return armor;
    }

    public String getWeaponType() {
        return weaponType;
    }

    @Override
    public void takeDamage(int damage) {
        int reduced = damage - armor;
        if (reduced < 0) reduced = 0;
        System.out.println("  → " + getName() + " 的護甲減免 " + armor + " 點傷害");
        super.takeDamage(reduced);
    }
}
