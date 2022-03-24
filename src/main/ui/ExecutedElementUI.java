package ui;

import model.ExecutedElementMenAndLadies;

import javax.swing.*;
import java.awt.*;

// EFFECTS: represents user interface for executed element control

class ExecutedElementUI extends JInternalFrame {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private ExecutedElementMenAndLadies element;
    private String elementName;
    private int halfProgram;
    private double baseValue;
    private double goe;
    private double scoresOfPanel;

    JTextField elementNameText;
    JTextField halfProgramText;
    JTextField baseValueText;
    JTextField goeText;
    JTextField scoresOfPanelText;

    // EFFECTS: initialization
    public ExecutedElementUI(ExecutedElementMenAndLadies element, Component parent) {

        super("Executed Element Detail", true, true, true, true);
        this.element = element;
        elementName = element.getElementName();
        halfProgram = element.getHalfProgram();
        baseValue = element.getBaseValue();
        goe = element.getGoe();
        scoresOfPanel = element.getScoresOfPanel();

        elementNameText = new JTextField("Element Name is... " + elementName);
        halfProgramText = new JTextField("If half Program(0 for false, 1 for true)... " + halfProgram);
        baseValueText = new JTextField("Base Value is... " + baseValue);
        goeText = new JTextField("GOE is..." + goe);
        scoresOfPanelText = new JTextField("This element's score of panel is... " + scoresOfPanel);

        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        cp.add(elementNameText);
        cp.add(halfProgramText);
        cp.add(baseValueText);
        cp.add(goeText);
        cp.add(scoresOfPanelText);

        setSize(WIDTH, HEIGHT);
        setLocation(WIDTH / 2, HEIGHT / 2);
        setVisible(true);
    }
}
