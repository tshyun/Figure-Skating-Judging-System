package model;

public class Element {
    private String elementName;
    private double baseValue;

    public Element(String elementName, double baseValue) {
        this.elementName = elementName;
        this.baseValue = baseValue;
    }

    public String getElementName() {
        return elementName;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

}
