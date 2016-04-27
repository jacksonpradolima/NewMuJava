/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mujava.enums.operator;

/**
 * List of names of traditional mutation operators
 * @author Jackson Lima
 */
public enum TraditionalMutationOperator {         

    /**
     *
     */
    AODS("AODS","AODS"),

    /**
     *
     */
    AODU("AODU","AODU"),

    /**
     *
     */
    AOIS("AOIS","AOIS"),

    /**
     *
     */
    AOIU("AOIU","AOIU"),    

    /**
     *
     */
    AORB("AORB", "AORB"),

    /**
     *
     */
    AORS("AORS","AORS"),
    //AORU("AORU", "AORU"),

    /**
     *
     */
    ASRS("ASRS","ASRS"),

    /**
     *
     */
    CDL("CDL","CDL"),

    /**
     *
     */
    COD("COD","COD"),

    /**
     *
     */
    COI("COI","COI"),

    /**
     *
     */
    COR("COR","COR"),

    /**
     *
     */
    LOD("LOD","LOD"),

    /**
     *
     */
    LOI("LOI","LOI"),

    /**
     *
     */
    LOR("LOR","LOR"),

    /**
     *
     */
    ODL("ODL","ODL"),    

    /**
     *
     */
    ROR("ROR","ROR"),

    /**
     *
     */
    SDL("SDL","SDL"),        

    /**
     *
     */
    SOR("SOR","SOR"),    

    /**
     *
     */
    VDL("VDL","VDL");
        
    private String description;
    private String value;

    TraditionalMutationOperator(String description, String value) {
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
    public static TraditionalMutationOperator getEnum(String value) {
        for (TraditionalMutationOperator v : values()) {
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
    public static TraditionalMutationOperator getEnumByDescription(String description) {
        for (TraditionalMutationOperator v : values()) {
            if (v.getDescription().equalsIgnoreCase(description)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
