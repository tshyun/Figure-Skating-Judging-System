package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfExecutedElementsTest {

    public ListOfExecutedElements list;
    public ExecutedElementMenAndLadies e1;
    public ExecutedElementMenAndLadies e2;

    @Test
    public void sumTest() {
        list = new ListOfExecutedElements();
        ListOfJudgeScoresMenLadiesAndPairs score1 = new ListOfJudgeScoresMenLadiesAndPairs();
        score1.addScores(1);
        score1.addScores(2);
        score1.addScores(1);
        score1.addScores(2);
        score1.addScores(3);
        score1.addScores(2);
        score1.addScores(2);
        score1.addScores(1);
        score1.addScores(1);
        e1 = new ExecutedElementMenAndLadies("4T", 0, score1);
        ListOfJudgeScoresMenLadiesAndPairs score2 = new ListOfJudgeScoresMenLadiesAndPairs();
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        e2 = new ExecutedElementMenAndLadies("4F", 0, score2);
        list.addElement(e1);
        list.addElement(e2);
        assertEquals(20.50, list.sumBaseValue());
        assertEquals(16.49, list.sumScoresOfPanel());
    }
}
