package persistence;

import model.ExecutedElementMenAndLadies;
import model.ListOfExecutedElements;


import model.ListOfJudgeScoresMenLadiesAndPairs;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads ListOfExecutedElements from JSON data stored in file
public class JsonReaderListOfExecutedElements {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderListOfExecutedElements(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfExecutedElements from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfExecutedElements read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfExecutedElements(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS : parses ListOfExecutedElements from JSON object and returns it
    private ListOfExecutedElements parseListOfExecutedElements(JSONObject jsonObject) {
        ListOfExecutedElements lee = new ListOfExecutedElements();
        addElementToListOfExecutedElements(lee, jsonObject);
        return lee;
    }

    // MODIFIES: lee
    // EFFECTS: parses elements from JSON object and adds them to ListOfExecutedElements
    private void addElementToListOfExecutedElements(ListOfExecutedElements lee, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Executed Elements");
        for (Object json : jsonArray) {
            JSONObject nextElement = (JSONObject) json;
            addJudgeScoresToElement(lee, nextElement);
        }
    }

    // MODIFIES: lee
    // EFFECTS: parses scores from JSON object and adds them to ListOfExecutedElements
    private void addJudgeScoresToElement(ListOfExecutedElements lee, JSONObject jsonObject) {
        String name = jsonObject.getString("Executed Element Name");
        int half = jsonObject.getInt("Half Program");
        double baseValue = jsonObject.getDouble("Base Value");
        ListOfJudgeScoresMenLadiesAndPairs ls;
        ls = parseListOfScore(jsonObject.getJSONObject("scores"));
        ExecutedElementMenAndLadies e = new ExecutedElementMenAndLadies(name, half, ls);
        lee.addElement(e);
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
