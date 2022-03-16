package persistence;

import model.ListOfExecutedElements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestListOfExecutedElementReader {
    @Test
    void testReaderNonExistentFile() {
        JsonReaderListOfExecutedElements reader = new JsonReaderListOfExecutedElements("./data/noSuchFile1.json");
        try {
            ListOfExecutedElements e = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralScores() {
        JsonReaderListOfExecutedElements reader = new JsonReaderListOfExecutedElements
                ("./data/testGeneralListOfExecutedElement.json");
        try {
            ListOfExecutedElements lee = reader.read();
            assertEquals(20.50, lee.sumBaseValue());
            assertEquals(16.49, lee.sumScoresOfPanel());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}
