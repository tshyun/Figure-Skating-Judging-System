package persistence;

import model.FinalReport;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestFinalReportReader {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderFinalReport reader = new JsonReaderFinalReport("./data/noSuchFile1.json");
        try {
            FinalReport report = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralScores() {
        JsonReaderFinalReport reader = new JsonReaderFinalReport("./data/testGeneralFinalReport.json");
        try {
            FinalReport report = reader.read();
            assertEquals("Chan", report.getName());
            assertEquals("Men", report.getType());
            assertEquals("USA", report.getNation());
            assertEquals(56.00, report.getTotalSegmentScore());
            assertEquals(26.60, report.getTotalElementScore());
            assertEquals(29.40, report.getTotalProgramComponentScore());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




}
