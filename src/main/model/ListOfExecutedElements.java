package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// represent all the executed elements the skater represent in one program, totally 7 for short program
public class ListOfExecutedElements implements Writable {

    private LinkedList<ExecutedElementMenAndLadies> elements;

    // EFFECTS: construct a new empty list
    public ListOfExecutedElements() {
        elements = new LinkedList<>();
    }

    // EFFECTS: add new executed element to end of this list
    public void addElement(ExecutedElementMenAndLadies e) {
        elements.addLast(e);
    }

    // MODIFIES : this
    // EFFECTS : round up the given double format number to 2 decimal places
    public double roundOff(double d) {
        d = Math.round(d * 100.0) / 100.0;
        return d;
    }

    // EFFECTS: return the sum of all executed elements' Base Value
    public double sumBaseValue() {
        double total = 0.0;
        for (ExecutedElementMenAndLadies d : elements) {
            total = total + d.getBaseValue();
        }
        return total;
    }

    // EFFECTS: return the sum of all executed elements' Scores of Panel
    public double sumScoresOfPanel() {
        double total = 0.0;
        for (ExecutedElementMenAndLadies d : elements) {
            total = total + d.getScoresOfPanel();
        }
        return roundOff(total);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Executed Elements", elementsToJson());
        json.put("Total Elements Score", sumScoresOfPanel());
        return json;
    }

    // EFFECTS : returns elements in this ListOfExecutedElements as a JSON array
    private JSONArray elementsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (ExecutedElementMenAndLadies e : elements) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }
}
