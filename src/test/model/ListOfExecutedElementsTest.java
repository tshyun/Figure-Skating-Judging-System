package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfExecutedElementsTest {

    public ListOfExecutedElements list;
    public ExecutedElement e1;
    public ExecutedElement e2;

    @Test
    public void sumTest() {
        list = new ListOfExecutedElements();
        ListOfJudgeScores score1 = new ListOfJudgeScores();
        score1.addScores(1);
        score1.addScores(2);
        score1.addScores(1);
        score1.addScores(2);
        score1.addScores(3);
        score1.addScores(2);
        score1.addScores(2);
        score1.addScores(1);
        score1.addScores(1);
        e1 = new ExecutedElement("4T", 9.50, 0, score1);
        ListOfJudgeScores score2 = new ListOfJudgeScores();
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        score2.addScores(-5);
        e2 = new ExecutedElement("4F",  11.00, 0, score2);
        list.addElement(e1);
        list.addElement(e2);
        assertEquals(20.50, list.sumBaseValue());
        assertEquals(16.49, list.sumScoresOfPanel());
    }
}
