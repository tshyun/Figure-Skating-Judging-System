package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//如果是IceDance的话，定级B和1的goe都是0.15, 0.30, 0.45, 0.60, 0.75递增，四舍五入两位小数
//但定级2，3，4跟定级1的一样
//1. For Pattern Dance: FO1Sq-FO4Sq, FT1Sq-FT4Sq, RF1Sq-RF4Sq, SW1Sq1Se-SW1Sq2Se, SW2Sq1Se-SW2Sq2Se, QS1Sq-QS4Sq

public class ListOfJudgeScores implements Writable {
    public static final int SIZE = 9;
    protected List<Double> scores;

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
