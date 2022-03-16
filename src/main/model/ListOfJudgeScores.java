package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.geom.Arc2D;
import java.nio.file.Watchable;
import java.util.*;

// represent all 9 judge's score
public class ListOfJudgeScores implements Writable {

    public static final int SIZE = 9;
    private List<Double> scores;

    public ListOfJudgeScores() {
        scores = new ArrayList<>();
    }

    public List<Double> getScores() {
        return scores;
    }

    public String scoresToEventLog() {
        return getScores().toString();
    }

    // EFFECTS : add judge scores to list
    public void addScores(double s) {
        scores.add(s);
    }

    // MODIFIES : this
    // EFFECTS : round up the given double format number to 2 decimal places
    public double roundOff(double d) {
        d = Math.round(d * 100.0) / 100.0;
        return d;
    }

    // EFFECTS : return the average of list of scores by deleting highest and lowest score
    public double average() {
        Collections.sort(scores);
        double total = 0.0;
        for (int i = 1; i < SIZE - 1; i++) {
            total = total + scores.get(i);
        }
        return roundOff(total / (SIZE - 2));
    }

    public int getSize() {
        return scores.size();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("scores", scoresToJson());
        return json;
    }

    // EFFECTS: returns scores in this ListOfScores as a JSON array
    private JSONArray scoresToJson() {
        JSONArray jsonArray = new JSONArray();
        for (double d : scores) {
            JSONObject json = new JSONObject();
            json.put("score", d);
            jsonArray.put(json);
        }
        return jsonArray;
    }

}
