package persistence;

import model.FinalReport;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestFinalReportWriter {

    @Test
    void testWriterInvalidFile() {
        try {
            FinalReport report = new FinalReport();
            JsonWriterFinalReport writer = new JsonWriterFinalReport("./data/my\0:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralFinalReport() {
        try {
            FinalReport report = new FinalReport();

            report.setName("Chan");
            report.setType("Men");
            report.setNation("USA");
            report.setTotalElementScore(26.60);
            report.setTotalProgramComponentScore(29.40);
            report.setTotalSegmentScore(26.60 + 29.40);
            JsonWriterFinalReport writer = new JsonWriterFinalReport("./data/testGeneralFinalReport.json");
            writer.open();
            writer.write(report);
            writer.close();

            JsonReaderFinalReport reader = new JsonReaderFinalReport("./data/testGeneralFinalReport.json");
            report = reader.read();
            assertEquals("Chan", report.getName());
            assertEquals("Men", report.getType());
            assertEquals("USA", report.getNation());
            assertEquals(56.00, report.getTotalSegmentScore());
            assertEquals(26.60, report.getTotalElementScore());
            assertEquals(29.40, report.getTotalProgramComponentScore());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
