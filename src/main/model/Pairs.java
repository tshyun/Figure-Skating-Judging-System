package model;

public class Pairs extends ElementsList {

    public Pairs() {
        this.elements = csvReaderInJava.readBooksFromCSV("2253Pairs.txt");
    }
}
