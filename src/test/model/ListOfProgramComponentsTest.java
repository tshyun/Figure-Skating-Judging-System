package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfProgramComponentsTest {

    public ListOfProgramComponents list;
    public ProgramComponent e1;
    public ProgramComponent e2;

    @Test
    public void sumTest() {
        list = new ListOfProgramComponents();
        ListOfJudgeScoresMenLadiesAndPairs score1 = new ListOfJudgeScoresMenLadiesAndPairs();
        score1.addScores(8.75);
        score1.addScores(9.00);
        score1.addScores(8.75);
        score1.addScores(8.50);
        score1.addScores(8.00);
        score1.addScores(8.50);
        score1.addScores(8.75);
        score1.addScores(8.50);
        score1.addScores(8.75);
        e1 = new ProgramComponent("Skating Skills", 1.00, score1);//8.64
        ListOfJudgeScoresMenLadiesAndPairs score2 = new ListOfJudgeScoresMenLadiesAndPairs();
        score2.addScores(8.75);
        score2.addScores(9.25);
        score2.addScores(8.50);
        score2.addScores(8.50);
        score2.addScores(8.75);
        score2.addScores(9.00);
        score2.addScores(8.75);
        score2.addScores(8.50);
        score2.addScores(8.50);
        e2 = new ProgramComponent("Transitions", 1.00, score2);//8.68
        list.addElement(e1);
        list.addElement(e2);
        assertEquals(17.32, list.sumScoresOfPanel());
    }
}
