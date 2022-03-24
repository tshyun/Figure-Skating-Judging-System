package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderInJava {

    public List<Element> readBooksFromCSV(String fileName) {
        List<Element> elements = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Element element = createElement(attributes);
                elements.add(element);
                line = br.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return elements;
    }
    public Element createElement(String[] metadata) {
        String elementName = metadata[0];
        double baseValue = Double.parseDouble(metadata[1]);
        return new Element(elementName, baseValue);
    }
}

