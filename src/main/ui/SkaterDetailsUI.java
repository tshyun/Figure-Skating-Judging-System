package ui;

import model.SkaterDetails;

import javax.swing.*;
import java.awt.*;

// EFFECTS: represents user interface for skater details control
public class SkaterDetailsUI extends JInternalFrame {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private SkaterDetails details;
    private String type;
    private String name;
    private String nation;

    JTextField typeText;
    JTextField nameText;
    JTextField nationText;

    // EFFECTS: initialization
    public SkaterDetailsUI(SkaterDetails details, Component parent) {
        super("Skater Detail", true, true, true, true);
        this.details = details;
        type = details.getType();
        name = details.getName();
        nation = details.getNation();

        typeText = new JTextField("Skater Type is..." + type);
        nameText = new JTextField("Skater Name is..." + name);
        nationText = new JTextField("Skater Nationality is ..." + nation);

        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        cp.add(typeText);
        cp.add(nameText);
        cp.add(nationText);

        setSize(WIDTH, HEIGHT);
        setLocation(WIDTH / 2, HEIGHT / 2);
        setVisible(true);
    }
}
