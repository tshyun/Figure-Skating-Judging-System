package model;


import org.json.JSONObject;
import persistence.Writable;

import java.nio.file.Watchable;
import java.util.List;

import static java.lang.Math.E;
import static java.lang.Math.abs;

// represent a element in execued elements
public class ExecutedElement implements Writable {

    private String elementName; // the name of element
    private double baseValue; // base value of this element
    private int halfProgram; // 0 for false, 1 for true
    private double goe; // the score for skater to represent (good or bad, between [-5,5])
    private ListOfJudgeScores judgeScore; // list of judge's score for this element, 9 judges
    private double scoresOfPanel; // total score for this element, base value + GOE

    public ExecutedElement(String en,
                           double baseValue,
                           int halfProgram,
                           ListOfJudgeScores judge) {
        this.elementName = en;
        this.baseValue = baseValue;
        this.halfProgram = halfProgram;
        EventLog.getInstance().logEvent(new Event("Added executed element: " + en + ", with base value: " + baseValue
                + ", half program?(0 for no, 1 for yes): " + halfProgram));
        EventLog.getInstance().logEvent(new Event("Added judge score for " + en + " is " + judge.scoresToEventLog()));
        this.goe = calculateGOE(judge.average());
        this.judgeScore = judge;
        this.scoresOfPanel = calculatePanelScores(baseValue, goe);



    }

    // lots of getter

    public double getGoe() {
        return goe;
    }

    public String getElementName() {
        return elementName;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public int getHalfProgram() {
        return halfProgram;
    }

    public ListOfJudgeScores getJudgeScore() {
        return judgeScore;
    }

    public double getScoresOfPanel() {
        return scoresOfPanel;
    }

    // MODIFIES : this
    // EFFECTS : round up the given double format number to 2 decimal places
    public double roundOff(double d) {
        d = Math.round(d * 100.0) / 100.0;
        return d;
    }


    // EFFECTS : calculate GOE value
    public double calculateGOE(double p) {
        double d = roundOff((p / 10.000) * baseValue);
        EventLog.getInstance().logEvent(new Event("GOE for " + elementName + " is " + d));
        return d;
    }

    // EFFECTS : calculate base value,
    //           if halfProgram is true, factor 1.1,
    //           otherwise remain same
    public void calculateBaseValue(double bv, int halfProgram) {
        if (halfProgram == 0) {
            baseValue = roundOff(bv);
        } else {
            baseValue = roundOff(1.1 * bv);
        }
        EventLog.getInstance().logEvent(new Event("Base value for " + elementName + " is " + baseValue));
    }

    // EFFECTS : calculate scores of panel by adding BV and GOE
    public double calculatePanelScores(double bv, double goe) {
        double d = roundOff(bv + goe);
        EventLog.getInstance().logEvent(new Event("Panel score for " + elementName + " is " + d));
        return d;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Executed Element Name", elementName);
        json.put("Half Program", halfProgram);
        json.put("Base Value",baseValue);
        json.put("GOE", goe);
        json.put("scores", judgeScore.toJson());
        json.put("Score for this element", scoresOfPanel);
        return json;
    }




}
