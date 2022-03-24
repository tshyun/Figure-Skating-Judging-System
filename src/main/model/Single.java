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
        for (Element element : elements) {
            if (element.getElementName().equals(name)) {
                baseValue = element.getBaseValue();
            }
        }
        return baseValue;
    }
}
