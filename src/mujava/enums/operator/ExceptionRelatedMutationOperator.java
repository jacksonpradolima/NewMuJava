/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.enums.operator;

/**
 * List of names of exception-related mutation operators
 *
 * @author Jackson Lima
 */
public enum ExceptionRelatedMutationOperator {    

    /**
     *
     */
    EFD("EFD", "EFD"),

    /**
     *
     */
    EHC("EHC", "EHC"),

    /**
     *
     */
    EHD("EHD", "EHD"),

    /**
     *
     */
    EHI("EHI", "EHI"),

    /**
     *
     */
    ETC("ETC", "ETC"),

    /**
     *
     */
    ETD("ETD", "ETD");

    private String description;
    private String value;

    ExceptionRelatedMutationOperator(String description, String value) {
        this.description = description;
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    /**
     *
     * @param value
     * @return
     */
    public static ExceptionRelatedMutationOperator getEnum(String value) {
        for (ExceptionRelatedMutationOperator v : values()) {
            if (v.getValue().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     *
     * @param description
     * @return
     */
    public static ExceptionRelatedMutationOperator getEnumByDescription(String description) {
        for (ExceptionRelatedMutationOperator v : values()) {
            if (v.getDescription().equalsIgnoreCase(description)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
