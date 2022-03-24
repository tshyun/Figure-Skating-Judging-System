package model;

import java.util.List;

public class Single {
    public List<Element> elements;
    public CSVReaderInJava csvReaderInJava = new CSVReaderInJava();

    public Single() {
        this.elements = csvReaderInJava.readBooksFromCSV("2253.txt");
    }

    public double getBaseValue(String name){
        double baseValue = 0.0;
        if (name.contains("+")) {
            String[] s = name.split("\\+");
            String element1 = s[0];
            String element2 = s[1];
            double baseValue1 = 0.0;
            double baseValue2 = 0.0;
            for (Element element : elements) {
                if (element.getElementName().equals(element1)) {
                    baseValue1 = element.getBaseValue();
                }
            }
            for (Element element : elements) {
                if (element.getElementName().equals(element2)) {
                    baseValue2 = element.getBaseValue();
                }
            }
            baseValue = baseValue1 + baseValue2;
        } else {
            for (Element element : elements) {
                if (element.getElementName().equals(name)) {
                    baseValue = element.getBaseValue();
                }
            }
        }
        return baseValue;
    }
}
