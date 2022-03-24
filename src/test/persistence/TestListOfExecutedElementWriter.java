package persistence;

import model.ExecutedElementMenAndLadies;
import model.ListOfExecutedElements;
import model.ListOfJudgeScoresMenLadiesAndPairs;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestListOfExecutedElementWriter {

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfJudgeScoresMenLadiesAndPairs scores = new ListOfJudgeScoresMenLadiesAndPairs();
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            ExecutedElementMenAndLadies js = new ExecutedElementMenAndLadies("name", 1, scores);
            JsonWriterListOfExecutedElements writer = new JsonWriterListOfExecutedElements("./data/my\0:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralListOfExecutedElement(){
        try {
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
            ExecutedElementMenAndLadies e1 = new ExecutedElementMenAndLadies("4T", 0, score1);
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
            ExecutedElementMenAndLadies e2 = new ExecutedElementMenAndLadies("4F",  0, score2);
            ListOfExecutedElements lee = new ListOfExecutedElements();
            lee.addElement(e1);
            lee.addElement(e2);
            JsonWriterListOfExecutedElements writer = new JsonWriterListOfExecutedElements
                    ("./data/testGeneralListOfExecutedElement.json");
            writer.open();
            writer.write(lee);
            writer.close();

            JsonReaderListOfExecutedElements reader = new JsonReaderListOfExecutedElements
                    ("./data/testGeneralListOfExecutedElement.json");
            lee = reader.read();
            assertEquals(20.50, lee.sumBaseValue());
            assertEquals(16.49, lee.sumScoresOfPanel());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
