package persistence;

import model.ListOfJudgeScoresMenLadiesAndPairs;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads ListOfJudgeScores from JSON data stored in file
public class JsonReaderListOfJudgeScores {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderListOfJudgeScores(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfJudgeScores from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfJudgeScoresMenLadiesAndPairs read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfScore(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfJudgeScores from JSON object and returns it
    public ListOfJudgeScoresMenLadiesAndPairs parseListOfScore(JSONObject jsonObject) {
        ListOfJudgeScoresMenLadiesAndPairs js = new ListOfJudgeScoresMenLadiesAndPairs();
        addScore(js, jsonObject);
        return js;
    }

    // MODIFIES: js
    // EFFECTS: parses scores from JSON object and adds them to ListOfJudgeScores
    private void addScore(ListOfJudgeScoresMenLadiesAndPairs js, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scores");
        for (Object json : jsonArray) {
            JSONObject d = (JSONObject) json;
            double score;
            score = d.getDouble("score");
            js.addScores(score);
        }

    }
}
