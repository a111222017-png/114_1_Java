package com.rpg.interfaces;
import com.rpg.core.Role;

// Healable.java
public interface Healable {

    /**
     * 對目標進行治療
     */
    void heal(Role target);

    /**
     * 治療量
     */
    int getHealPower();

    default boolean canHeal() {
        return getHealPower() > 0;
    }

    default void showHealInfo() {
        System.out.println("    → 治療量：" + getHealPower());
    }
}
