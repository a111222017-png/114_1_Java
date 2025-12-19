package com.rpg.interfaces;

// Defendable.java
public interface Defendable {

    /**
     * 執行防禦動作
     */
    void defend();

    /**
     * 防禦力數值
     */
    int getDefenseCapacity();

    /**
     * 檢查是否可以防禦
     */
    default boolean canDefend() {
        return getDefenseCapacity() > 0;
    }
}
