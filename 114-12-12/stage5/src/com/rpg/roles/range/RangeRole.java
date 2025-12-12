package com.rpg.roles.range;
import com.rpg.core.Role;

// RangeRole.java
public abstract class RangeRole extends Role {
    private int range;          // 射程
    private int energy;         // 當前能量
    private int maxEnergy;      // 最大能量
    private String rangedAttackType;

    public RangeRole(String name, int health, int attackPower,
                     int range, int maxEnergy, String rangedAttackType) {
        super(name, health, attackPower);
        this.range = range;
        this.maxEnergy = maxEnergy;
        this.energy = maxEnergy;
        this.rangedAttackType = rangedAttackType;
    }

    public int getRange() {
        return range;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public String getRangedAttackType() {
        return rangedAttackType;
    }

    protected boolean consumeEnergy(int cost) {
        if (energy < cost) {
            return false;
        }
        energy -= cost;
        return true;
    }

    public void recoverEnergy(int amount) {
        energy += amount;
        if (energy > maxEnergy) energy = maxEnergy;
    }
}
