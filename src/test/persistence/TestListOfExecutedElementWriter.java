package persistence;

import model.ExecutedElement;
import model.ListOfExecutedElements;
import model.ListOfJudgeScores;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestListOfExecutedElementWriter {

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfJudgeScores scores = new ListOfJudgeScores();
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            scores.addScores(1);
            ExecutedElement js = new ExecutedElement("name", 0, 1, scores);
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
            ExecutedElement e1 = new ExecutedElement("4T", 9.50, 0, score1);
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
            ExecutedElement e2 = new ExecutedElement("4F",  11.00, 0, score2);
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
