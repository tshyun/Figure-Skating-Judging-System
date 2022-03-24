package ui;

import model.ProgramComponent;

import javax.swing.*;
import java.awt.*;

// EFFECTS: represents user interface for program component control
class ProgramComponentUI extends JInternalFrame {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private ProgramComponent element;
    private String performanceName;
    private double factor;
    private double scoresOfPanel;


    JInternalFrame executedElementFrame = new JInternalFrame();
    JTextField performanceNameText;
    JTextField factorText;
    JTextField scoresOfPanelText;

// EFFECTS: initialization
    public ProgramComponentUI(ProgramComponent element, Component parent) {

        super("Program Component Detail", true, true, true, true);
        this.element = element;
        performanceName = element.getPerformanceName();
        factor = element.getFactor();
        scoresOfPanel = element.getScoreOfPanel();

        performanceNameText = new JTextField("Program Component Name is... " + performanceName);
        factorText = new JTextField("Factor is... " + factor);
        scoresOfPanelText = new JTextField("This element's score of panel is... " + scoresOfPanel);

        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        cp.add(performanceNameText);
        cp.add(factorText);
        cp.add(scoresOfPanelText);

        setSize(WIDTH, HEIGHT);
        setLocation(WIDTH / 2, HEIGHT / 2);
        setVisible(true);
    }





}
