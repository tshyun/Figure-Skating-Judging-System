package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecutedElementTest {

    private ExecutedElementMenAndLadies e1;
    private ExecutedElementMenAndLadies e2;
    
    @BeforeEach
    public void before() {
        ListOfJudgeScoresMenLadiesAndPairs score1 = new ListOfJudgeScoresMenLadiesAndPairs();
        score1.addScores(4);
        score1.addScores(4);
        score1.addScores(4);
        score1.addScores(5);
        score1.addScores(3);
        score1.addScores(5);
        score1.addScores(4);
        score1.addScores(3);
        score1.addScores(3);
        e1 = new ExecutedElementMenAndLadies
                ("4Lz", 0, score1);

        ListOfJudgeScoresMenLadiesAndPairs score2 = new ListOfJudgeScoresMenLadiesAndPairs();
        score2.addScores(2);
        score2.addScores(0);
        score2.addScores(-1);
        score2.addScores(-1);
        score2.addScores(-1);
        score2.addScores(2);
        score2.addScores(-1);
        score2.addScores(-1);
        score2.addScores(-1);
        e2 = new ExecutedElementMenAndLadies
                ("4T+3T",0, score2);


    }

    @Test
    public void calculateGOEPositiveTest(){
        ListOfJudgeScoresMenLadiesAndPairs judge = e1.getJudgeScore();
        assertEquals(4.44, e1.calculateGOE(judge.average()));
    }

    @Test
    public void calculateGOENegativeTest(){
        ListOfJudgeScoresMenLadiesAndPairs judge = e2.getJudgeScore();
        assertEquals(-0.59, e2.calculateGOE(judge.average()));
    }

    @Test
    public void calculatePanelScoresNotHalfTest () {
        String name = e1.getElementName();
        double bv = e1.getBaseValue();
        int half = e1.getHalfProgram();
        ListOfJudgeScoresMenLadiesAndPairs judge = e1.getJudgeScore();
        double goe = e1.calculateGOE(judge.average());
        e1.calculateBaseValue(bv,half);
        double baseValue = e1.getBaseValue();
        assertEquals("4Lz", name);
        assertEquals(4.44, e1.getGoe());
        assertEquals(15.94, e1.calculatePanelScores(baseValue,goe));
    }

    @Test
    public void calculatePanelScoresHalfTest () {
        ListOfJudgeScoresMenLadiesAndPairs scores = new ListOfJudgeScoresMenLadiesAndPairs();
        scores.addScores(4);
        scores.addScores(4);
        scores.addScores(4);
        scores.addScores(5);
        scores.addScores(3);
        scores.addScores(5);
        scores.addScores(4);
        scores.addScores(3);
        scores.addScores(3);
        ExecutedElementMenAndLadies e = new ExecutedElementMenAndLadies
                ("3A", 1, scores);
        ListOfJudgeScoresMenLadiesAndPairs judge = e.getJudgeScore();
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