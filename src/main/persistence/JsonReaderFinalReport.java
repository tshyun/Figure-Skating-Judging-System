package persistence;

import model.FinalReport;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class  JsonReaderFinalReport {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderFinalReport(String source) {
        this.source = source;
    }

    // EFFECTS: reads FinalReport from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FinalReport read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFinalReport(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES : fr
    // EFFECTS: parses FinalReport from JSON object, and add them to FinalReport, and return it
    private FinalReport parseFinalReport(JSONObject jsonObject) {
        FinalReport fr = new FinalReport();
        String name = jsonObject.getString("Name");
        String type = jsonObject.getString("Type");
        String nation = jsonObject.getString("Nation");
        Double totalSegmentScore = jsonObject.getDouble("Total Segment Score");
        Double totalElementScore = jsonObject.getDouble("Total Element Score");
        Double totalProgramComponentScore = jsonObject.getDouble("Total Program Component Score");
        fr.setName(name);
        fr.setType(type);
        fr.setNation(nation);
        fr.setTotalElementScore(totalElementScore);
        fr.setTotalProgramComponentScore(totalProgramComponentScore);
        fr.setTotalSegmentScore(totalElementScore + totalProgramComponentScore);
        return fr;
    }



}
