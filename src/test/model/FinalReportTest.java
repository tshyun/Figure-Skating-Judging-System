package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinalReportTest {
    private FinalReport report;

    private ListOfExecutedElements list;
    private ExecutedElementMenAndLadies e1;
    private ExecutedElementMenAndLadies e2;

    private ListOfProgramComponents list1;
    private ProgramComponent p1;
    private ProgramComponent p2;

    @BeforeEach
    void before() {
        report = new FinalReport();
    }

    @Test
    void testSegmentScore() {
        report.setTotalElementScore(23);
        report.setTotalProgramComponentScore(23.9);
        report.totalSegmentScore();
        assertEquals(46.9, report.getTotalSegmentScore());
    }

    @Test
    void testSetLoe() {
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
        report.setLoe(list);
        assertEquals(16.49, (report.getLoe()).sumScoresOfPanel());
    }

    @Test
    void testSetLop() {
        list1 = new ListOfProgramComponents();
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
        p1 = new ProgramComponent("Skating Skills", 1.00, score1);//8.64
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
        p2 = new ProgramComponent("Transitions", 1.00, score2);//8.68
        list1.addElement(p1);
        list1.addElement(p2);
        report.setLop(list1);
        assertEquals(17.32, (report.getLop()).sumScoresOfPanel());
    }
}
