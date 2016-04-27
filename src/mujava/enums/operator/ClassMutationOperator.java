/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.enums.operator;

/**
 * List of names of class mutation operators
 *
 * @author Jackson Lima
 */
public enum ClassMutationOperator {

    /**
     *
     */
    AMC("AMC", "AMC"),    

    /**
     *
     */
    EAM("EAM", "EAM"),

    /**
     *
     */
    EMM("EMM", "EMM"),

    /**
     *
     */
    EOA("EOA", "EOA"),

    /**
     *
     */
    EOC("EOC", "EOC"),

    /**
     *
     */
    IHD("IHD", "IHD"),

    /**
     *
     */
    IHI("IHI", "IHI"),    

    /**
     *
     */
    IOD("IOD", "IOD"),

    /**
     *
     */
    IOP("IOP", "IOP"),

    /**
     *
     */
    IOR("IOR", "IOR"),

    /**
     *
     */
    IPC("IPC", "IPC"),

    /**
     *
     */
    ISD("ISD", "ISD"),
    //ISI("ISI", "ISI"),    

    /**
     *
     */
    JDC("JDC", "JDC"),

    /**
     *
     */
    JID("JID", "JID"),

    /**
     *
     */
    JSD("JSD", "JSD"),    

    /**
     *
     */
    JSI("JSI", "JSI"),    

    /**
     *
     */
    JTD("JTD", "JTD"),    

    /**
     *
     */
    JTI("JTI", "JTI"),    

    /**
     *
     */
    OAN("OAN", "OAN"),

    /**
     *
     */
    OMD("OMD", "OMD"),

    /**
     *
     */
    OMR("OMR", "OMR"),        

    /**
     *
     */
    PCC("PCC", "PCC"),

    /**
     *
     */
    PCD("PCD", "PCD"),

    /**
     *
     */
    PCI("PCI", "PCI"),        

    /**
     *
     */
    PMD("PMD", "PMD"),    

    /**
     *
     */
    PNC("PNC", "PNC"),    

    /**
     *
     */
    PPD("PPD", "PPD"),    

    /**
     *
     */
    PRV("PRV", "PRV");        
    
    private String description;
    private String value;

    ClassMutationOperator(String description, String value) {
        this.description = description;
        this.value = value;
    }

    /**
     *
     * @param value
     * @return
     */
    public static ClassMutationOperator getEnum(String value) {
        for (ClassMutationOperator v : values()) {
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
    public static ClassMutationOperator getEnumByDescription(String description) {
        for (ClassMutationOperator v : values()) {
            if (v.getDescription().equalsIgnoreCase(description)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

}
