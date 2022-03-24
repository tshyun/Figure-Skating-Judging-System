package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgramComponentTest {

    private ProgramComponent p;

    @BeforeEach
    public void before() {
        ListOfJudgeScoresMenLadiesAndPairs scores = new ListOfJudgeScoresMenLadiesAndPairs();
        scores.addScores(8.75);
        scores.addScores(9.00);
        scores.addScores(8.75);
        scores.addScores(8.50);
        scores.addScores(8.00);
        scores.addScores(8.50);
        scores.addScores(8.75);
        scores.addScores(8.50);
        scores.addScores(8.75);
        p = new ProgramComponent ("Skating Skills", 1.00, scores);
    }

    @Test
    public void scoreTest () {
        assertEquals("Skating Skills", p.getPerformanceName());
        assertEquals(1.00, p.getFactor());
        assertEquals (8.64, p.getScoreOfPanel());
    }
}
