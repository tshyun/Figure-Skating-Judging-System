package ui;

import model.FinalReport;

import javax.swing.*;
import java.awt.*;

// EFFECTS: represents user interface for final report control
public class FinalReportUI extends JInternalFrame {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;

    private FinalReport report;
    private String name;
    private String nationality;
    private double totalElementScore;
    private double totalProgramComponentScore;
    private double totalSegmentScore;

    JTextField nameText;
    JTextField nationalityText;
    JTextField totalElementScoreText;
    JTextField totalProgramComponentScoreText;
    JTextField totalSegmentScoreText;


// EFFECTS: initialization
    public FinalReportUI(FinalReport report, Component parent) {

        super("Final Report", true, true, true, true);
        this.report = report;
        name = report.getName();
        nationality = report.getNation();
        totalElementScore = report.getTotalElementScore();
        totalProgramComponentScore = report.getTotalProgramComponentScore();
        totalSegmentScore = report.getTotalSegmentScore();

        nameText = new JTextField("Skater Name is..." + name);
        nationalityText = new JTextField("Skater Nationality is..." + nationality);
        totalElementScoreText = new JTextField("Skater Total Element Score is..." + totalElementScore);
        totalProgramComponentScoreText = new JTextField("Skater Total Program Component Score is..."
                + totalProgramComponentScore);
        totalSegmentScoreText = new JTextField("Skater Total Segment Score is..." + totalSegmentScore);

        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        cp.add(nameText);
        cp.add(nationalityText);
        cp.add(totalElementScoreText);
        cp.add(totalProgramComponentScoreText);
        cp.add(totalSegmentScoreText);

        setSize(WIDTH, HEIGHT);
        setLocation(WIDTH / 2, HEIGHT / 2);
        setVisible(true);

    }

}
