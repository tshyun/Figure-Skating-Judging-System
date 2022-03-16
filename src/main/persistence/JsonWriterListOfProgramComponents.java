package persistence;

import model.ListOfProgramComponents;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of ListOfProgramComponents to file
public class JsonWriterListOfProgramComponents {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    public JsonWriterListOfProgramComponents(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ListOfProgramComponents to file
    public void write(ListOfProgramComponents lop) {
        JSONObject json = lop.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}