package persistence;

import model.ListOfJudgeScores;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestListOfJudgeScoresReader {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderListOfJudgeScores reader = new JsonReaderListOfJudgeScores("./data/noSuchFile.json");
        try {
            ListOfJudgeScores js = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyScores() {
        JsonReaderListOfJudgeScores reader = new JsonReaderListOfJudgeScores("./data/testEmptyScores.json");
        try {
            ListOfJudgeScores ls = reader.read();
            assertEquals(0, ls.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralScores() {
        JsonReaderListOfJudgeScores reader = new JsonReaderListOfJudgeScores("./data/testGeneralLOS.json");
        try {
            ListOfJudgeScores ls = reader.read();
            assertEquals(9, ls.getSize());
            assertEquals(4.14, ls.average());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
