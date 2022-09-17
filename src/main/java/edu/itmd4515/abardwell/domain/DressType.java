package edu.itmd4515.abardwell.domain;

public enum DressType {

    MERMAID("Amy"),
    EMPIRE("Elizabeth"),
    BALL("Belle"),
    SHEATH("Harlow"),
    WRAP("Rachel"),
    BABYDOLL("Olivia"),
    SSUN("Autumn"),
    BLOUSON("Pierre"),
    BODYCON("Alexander"),
    SLIP("Alessa"),
    BOUFFANT("Avery");

    private String label;

    private DressType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}


