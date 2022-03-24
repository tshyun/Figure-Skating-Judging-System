package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReaderListOfProgramComponents {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderListOfProgramComponents(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfProgramComponents read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfProgramComponents(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfProgramComponents from JSON object and returns it
    private ListOfProgramComponents parseListOfProgramComponents(JSONObject jsonObject) {
        ListOfProgramComponents lop = new ListOfProgramComponents();
        addElementToListOfProgramComponent(lop, jsonObject);
        return lop;
    }

    // MODIFIES: lop
    // EFFECTS: parses elements from JSON object and adds them to ListOfProgramComponents
    private void addElementToListOfProgramComponent(ListOfProgramComponents lop, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Program Components");
        for (Object json : jsonArray) {
            JSONObject nextElement = (JSONObject) json;
            addJudgeScoresToProgramComponent(lop, nextElement);
        }
    }

    // MODIFIES: lop
    // EFFECTS: parses scores from JSON object and adds them to ListOfProgramComponents
    private void addJudgeScoresToProgramComponent(ListOfProgramComponents lop, JSONObject jsonObject) {
        String name = jsonObject.getString("Program Component Name");
        double factor = jsonObject.getDouble("factor");
        ListOfJudgeScoresMenLadiesAndPairs ls;
        ls = parseListOfScore(jsonObject.getJSONObject("scores"));
        ProgramComponent e = new ProgramComponent(name,factor,ls);
        lop.addElement(e);
    }

    // EFFECTS: parses ListOfJudgeScores from JSON object and returns it
    public ListOfJudgeScoresMenLadiesAndPairs parseListOfScore(JSONObject jsonObject) {
        ListOfJudgeScoresMenLadiesAndPairs js = new ListOfJudgeScoresMenLadiesAndPairs();
        addScoreToJudgeScores(js, jsonObject);
        return js;
    }

    // MODIFIES: js
    // EFFECTS: parses scores from JSON object and adds them to ListOfJudgeScores
    private void addScoreToJudgeScores(ListOfJudgeScoresMenLadiesAndPairs js, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scores");
        for (Object json : jsonArray) {
            JSONObject d = (JSONObject) json;
            double score;
            score = d.getDouble("score");
            js.addScores(score);
        }
    }
}

