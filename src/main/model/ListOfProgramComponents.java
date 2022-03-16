package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// represent all five program components score
public class ListOfProgramComponents implements Writable {

    private LinkedList<ProgramComponent> elements;
    private double totalProgramComponent;

    // EFFECTS: construct a new empty list
    public ListOfProgramComponents() {
        elements = new LinkedList<>();
    }

    // EFFECTS: add new executed element to end of this list
    public void addElement(ProgramComponent e) {
        elements.addLast(e);
    }

    // MODIFIES : this
    // EFFECTS : round up the given double format number to 2 decimal places
    public double roundOff(double d) {
        d = Math.round(d * 100.0) / 100.0;
        return d;
    }


    // EFFECTS: return the sum of all executed elements' Scores of Panel
    public double sumScoresOfPanel() {
        double total = 0.0;
        for (ProgramComponent d : elements) {
            total = total + d.getScoreOfPanel();
        }
        return roundOff(total);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Program Components", elementsToJson());
        json.put("Total Program Components Score", sumScoresOfPanel());
        return json;
    }

    // EFFECTS : return elements in this ListOfProgramComponents as a JSON array
    private JSONArray elementsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (ProgramComponent e : elements) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }


}
