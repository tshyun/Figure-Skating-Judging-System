package persistence;

import model.ListOfProgramComponents;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestListOfProgramComponentsReader {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderListOfProgramComponents reader =
                new JsonReaderListOfProgramComponents("./data/noSuchFile1.json");
        try {
            ListOfProgramComponents lop = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralListOfProgramComponents() {
        JsonReaderListOfProgramComponents reader =
                new JsonReaderListOfProgramComponents("./data/testGeneralListOfProgramComponents.json");
        try {
            ListOfProgramComponents list = reader.read();
            assertEquals(17.32, list.sumScoresOfPanel());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }
}
