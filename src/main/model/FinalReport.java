package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Random;

public class FinalReport implements Writable {
    String name;
    String type;
    String nation;
    double totalSegmentScore;
    double totalElementScore;
    double totalProgramComponentScore;
    ListOfExecutedElements loe;
    ListOfProgramComponents lop;

    public FinalReport() {}


    // setter
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }


    public void setTotalSegmentScore(double d) {
        this.totalSegmentScore = d;
    }

    public void setTotalElementScore(double d) {
        totalElementScore = d;
    }

    public void setTotalProgramComponentScore(double d) {
        totalProgramComponentScore = d;
    }

    public void setLoe(ListOfExecutedElements loe) {
        this.loe = loe;
    }

    public void setLop(ListOfProgramComponents lop) {
        this.lop = lop;
    }

    // getter

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getNation() {
        return nation;
    }

    public double getTotalSegmentScore() {
        return totalSegmentScore;
    }

    public double getTotalElementScore() {
        return totalElementScore;
    }

    public double getTotalProgramComponentScore() {
        return totalProgramComponentScore;
    }

    public ListOfExecutedElements getLoe() {
        return loe;
    }

    public ListOfProgramComponents getLop() {
        return lop;
    }

    // MODIFIES : this
    // EFFECTS : add totalElementScore and totalProgramComponentScore
    public void totalSegmentScore() {
        totalSegmentScore = totalElementScore + totalProgramComponentScore;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name",name);
        json.put("Type", type);
        json.put("Nation", nation);
        json.put("Total Segment Score", totalSegmentScore);
        json.put("Total Element Score", totalElementScore);
        json.put("Total Program Component Score", totalProgramComponentScore);
        return json;
    }




}