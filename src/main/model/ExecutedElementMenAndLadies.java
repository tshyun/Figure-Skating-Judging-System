package model;


// represent a element in execued elements
public class ExecutedElementMenAndLadies extends ExecutedElement {

    private MenAndLadies single;

    public ExecutedElementMenAndLadies(String en,
                                       int halfProgram,
                                       ListOfJudgeScoresMenLadiesAndPairs judge) {
        super(en, halfProgram, judge);
        single = new MenAndLadies();
        this.baseValue = single.getBaseValue(en);
        this.goe = calculateGOE(judge.average());
        EventLog.getInstance().logEvent(new Event("Added executed element: " + en + ", with base value: " + baseValue
                + ", half program?(0 for no, 1 for yes): " + halfProgram));
        EventLog.getInstance().logEvent(new Event("Added judge score for " + en + " is " + judge.scoresToEventLog()));
        this.scoresOfPanel = calculatePanelScores(baseValue, goe);



    }


    // lots of getter


}
