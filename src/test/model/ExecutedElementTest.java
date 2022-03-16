package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecutedElementTest {

    private ExecutedElement e1;
    private ExecutedElement e2;
    
    @BeforeEach
    public void before() {
        ListOfJudgeScores score1 = new ListOfJudgeScores();
        score1.addScores(4);
        score1.addScores(4);
        score1.addScores(4);
        score1.addScores(5);
        score1.addScores(3);
        score1.addScores(5);
        score1.addScores(4);
        score1.addScores(3);
        score1.addScores(3);
        e1 = new ExecutedElement
                ("4Lz", 11.50, 0, score1);

        ListOfJudgeScores score2 = new ListOfJudgeScores();
        score2.addScores(2);
        score2.addScores(0);
        score2.addScores(-1);
        score2.addScores(-1);
        score2.addScores(-1);
        score2.addScores(2);
        score2.addScores(-1);
        score2.addScores(-1);
        score2.addScores(-1);
        e2 = new ExecutedElement
                ("4T+3T", 13.70, 0, score2);


    }

    @Test
    public void calculateGOEPositiveTest(){
        ListOfJudgeScores judge = e1.getJudgeScore();
        assertEquals(4.44, e1.calculateGOE(judge.average()));
    }

    @Test
    public void calculateGOENegativeTest(){
        ListOfJudgeScores judge = e2.getJudgeScore();
        assertEquals(-0.59, e2.calculateGOE(judge.average()));
    }

    @Test
    public void calculatePanelScoresNotHalfTest () {
        String name = e1.getElementName();
        double bv = e1.getBaseValue();
        int half = e1.getHalfProgram();
        ListOfJudgeScores judge = e1.getJudgeScore();
        double goe = e1.calculateGOE(judge.average());
        e1.calculateBaseValue(bv,half);
        double baseValue = e1.getBaseValue();
        assertEquals("4Lz", name);
        assertEquals(4.44, e1.getGoe());
        assertEquals(15.94, e1.calculatePanelScores(baseValue,goe));
    }

    @Test
    public void calculatePanelScoresHalfTest () {
        ListOfJudgeScores scores = new ListOfJudgeScores();
        scores.addScores(4);
        scores.addScores(4);
        scores.addScores(4);
        scores.addScores(5);
        scores.addScores(3);
        scores.addScores(5);
        scores.addScores(4);
        scores.addScores(3);
        scores.addScores(3);
        ExecutedElement e = new ExecutedElement
                ("3A", 8.00, 1, scores);
        ListOfJudgeScores judge = e.getJudgeScore();
        String name = e.getElementName();
        double bv = e.getBaseValue();
        int half = e.getHalfProgram();
        double goe = e.calculateGOE(judge.average());
        e.calculateBaseValue(bv,half);
        double baseValue = e.getBaseValue();
        assertEquals("3A", name);
        assertEquals(3.09, e.getGoe());
        assertEquals(11.89, e.calculatePanelScores(baseValue,goe));
    }
}