package persistence;

import model.ListOfJudgeScores;
import model.ListOfProgramComponents;
import model.ProgramComponent;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestListOfProgramComponentsWriter {

    @Test
    void testWriterInvalidFile(){
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
            ProgramComponent p = new ProgramComponent("Skating", 1.00, scores);
            JsonWriterListOfProgramComponents writer =
                    new JsonWriterListOfProgramComponents("./data/my\0:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralListOfProgramComponents() {
        try {
            ListOfProgramComponents list = new ListOfProgramComponents();
            ListOfJudgeScores score1 = new ListOfJudgeScores();
            score1.addScores(8.75);
            score1.addScores(9.00);
            score1.addScores(8.75);
            score1.addScores(8.50);
            score1.addScores(8.00);
            score1.addScores(8.50);
            score1.addScores(8.75);
            score1.addScores(8.50);
            score1.addScores(8.75);
            ProgramComponent e1 = new ProgramComponent("Skating Skills", 1.00, score1);//8.64
            ListOfJudgeScores score2 = new ListOfJudgeScores();
            score2.addScores(8.75);
            score2.addScores(9.25);
            score2.addScores(8.50);
            score2.addScores(8.50);
            score2.addScores(8.75);
            score2.addScores(9.00);
            score2.addScores(8.75);
            score2.addScores(8.50);
            score2.addScores(8.50);
            ProgramComponent e2 = new ProgramComponent("Transitions", 1.00, score2);//8.68
            list.addElement(e1);
            list.addElement(e2);
            JsonWriterListOfProgramComponents writer =
                    new JsonWriterListOfProgramComponents("./data/testGeneralListOfProgramComponents.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReaderListOfProgramComponents reader =
                    new JsonReaderListOfProgramComponents("./data/testGeneralListOfProgramComponents.json");
            list = reader.read();
            assertEquals(17.32, list.sumScoresOfPanel());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
