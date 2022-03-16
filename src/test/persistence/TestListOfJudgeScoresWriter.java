package persistence;

import model.ListOfJudgeScores;
import org.json.JSONWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestListOfJudgeScoresWriter {

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfJudgeScores js = new ListOfJudgeScores();
            JsonWriterListOfJudgeScores writer = new JsonWriterListOfJudgeScores("./data/my\0:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyScores() {
        try {
            ListOfJudgeScores js = new ListOfJudgeScores();
            JsonWriterListOfJudgeScores writer = new JsonWriterListOfJudgeScores("./data/testEmptyScores.json");
            writer.open();
            writer.write(js);
            writer.close();

            JsonReaderListOfJudgeScores reader = new JsonReaderListOfJudgeScores("./data/testEmptyScores.json");
            js = reader.read();
            assertEquals(0, js.getSize());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralScores() {
        try {
            ListOfJudgeScores ls = new ListOfJudgeScores();
            ls.addScores(4);
            ls.addScores(4);
            ls.addScores(4);
            ls.addScores(5);
            ls.addScores(4);
            ls.addScores(3);
            ls.addScores(4);
            ls.addScores(4);
            ls.addScores(5);
            JsonWriterListOfJudgeScores writer = new JsonWriterListOfJudgeScores("./data/testGeneralLOS.json");
            writer.open();
            writer.write(ls);
            writer.close();

            JsonReaderListOfJudgeScores reader = new JsonReaderListOfJudgeScores("./data/testGeneralLOS.json");
            ls = reader.read();
            assertEquals(4.14, ls.average());
            assertEquals(9, ls.getSize());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
