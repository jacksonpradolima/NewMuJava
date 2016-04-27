/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.enums;

/**
 *
 * @author Jackson Lima
 */
public enum DebugType {

    DETAILED_LEVEL("Detailed log", "3"),
    SIMPLE_LEVEL("Simple log", "2"),
    EMPTY_LEVEL("Without log", "1");

    private final String description;
    private final String value;

    DebugType(String description, String value) {
        this.description = description;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static DebugType getEnum(String value) {
        for (DebugType v : values()) {
            if (v.getValue().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
    
    public static DebugType getEnumByDescription(String description) {
        for (DebugType v : values()) {
            if (v.getDescription().equalsIgnoreCase(description)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
};
