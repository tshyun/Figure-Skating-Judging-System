package model;

import org.json.JSONObject;
import persistence.Writable;

// represent each program component
// have 5 components (Skating skills, Translations, Performance, Composition, Interpretation of the Music)
public class ProgramComponent implements Writable {
    private String performanceName; // the name for program components
    private double factor; // the factor for this component, usually 0.8,1.0,1.2
    private ListOfJudgeScores judgeScore; // 9 judge's score for this component
    private double scoreOfPanel; // factor * judge's average

    public ProgramComponent(String nm, double factor, ListOfJudgeScores score) {
        this.performanceName = nm;
        this.factor = factor;
        this.judgeScore = score;
        EventLog.getInstance().logEvent(new Event("Add program component: " + nm + ", with factor " + factor));
        EventLog.getInstance().logEvent(new Event("Added judge score for " + nm + " is" + score.scoresToEventLog()));
        this.scoreOfPanel = roundOff(factor * judgeScore.average());
        EventLog.getInstance().logEvent(new Event("Panel score for " + nm + " is" + this.scoreOfPanel));
    }

    // MODIFIES : this
    // EFFECTS : round up the given double format number to 2 decimal places
    public double roundOff(double d) {
        d = Math.round(d * 100.0) / 100.0;
        return d;
    }

    // lots of getter

    public String getPerformanceName() {
        return performanceName;
    }

    public double getFactor() {
        return factor;
    }

    public double getScoreOfPanel() {
        return scoreOfPanel;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Program Component Name", performanceName);
        json.put("factor", factor);
        json.put("scores", judgeScore.toJson());
        json.put("Score for this element", scoreOfPanel);
        return json;
    }



}
