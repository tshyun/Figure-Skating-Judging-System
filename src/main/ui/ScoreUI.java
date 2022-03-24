//package ui;
//
//import model.Event;
//import model.*;
//import persistence.*;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//// Represents application's main window frame
//public class ScoreUI extends JFrame {
//    private static final int WIDTH = 800;
//    private static final int HEIGHT = 600;
//
//    private JDesktopPane desktopPane;
//    private JInternalFrame scorePanel;
//
//    private BufferedImage skatingImage;
//
//    private ListOfExecutedElements loe;
//    private ListOfProgramComponents lop;
//    private FinalReport report;
//
//    private JsonWriterListOfProgramComponents jsonWriterListOfProgramComponents;
//    private JsonWriterListOfExecutedElements jsonWriterListOfExecutedElement;
//    private JsonWriterFinalReport jsonWriterFinalReport;
//    private JsonReaderFinalReport jsonReaderFinalReport;
//    private JsonReaderListOfProgramComponents jsonReaderListOfProgramComponents;
//    private JsonReaderListOfExecutedElements jsonReaderListOfExecutedElement;
//
//    private static final String STORE_PATH_LIST_OF_EXECUTED_ELEMENTS =
//            "./data/Skater'sAllExecutedElementsScores.json";
//    private static final String STORE_PATH_LIST_OF_PROGRAM_COMPONENTS =
//            "./data/Skater'sAllProgramComponentsScores.json";
//    private static final String STORE_PATH_FINAL_REPORT =
//            "./data/Skater'sFinalReport.json";
//
//
//    // EFFECTS: initialization
//    public ScoreUI() throws FileNotFoundException {
//        CSVReaderInJava csvReaderInJava = new CSVReaderInJava();
//        List<Element> elements = csvReaderInJava.readBooksFromCSV("2253MenAndLadies.txt");
//
//        jsonReaderListOfExecutedElement = new JsonReaderListOfExecutedElements(STORE_PATH_LIST_OF_EXECUTED_ELEMENTS);
//        jsonReaderListOfProgramComponents =
//                new JsonReaderListOfProgramComponents(STORE_PATH_LIST_OF_PROGRAM_COMPONENTS);
//        jsonReaderFinalReport = new JsonReaderFinalReport(STORE_PATH_FINAL_REPORT);
//        jsonWriterListOfExecutedElement = new JsonWriterListOfExecutedElements(STORE_PATH_LIST_OF_EXECUTED_ELEMENTS);
//        jsonWriterListOfProgramComponents =
//                new JsonWriterListOfProgramComponents(STORE_PATH_LIST_OF_PROGRAM_COMPONENTS);
//        jsonWriterFinalReport = new JsonWriterFinalReport(STORE_PATH_FINAL_REPORT);
//
//        loe = new ListOfExecutedElements();
//        lop = new ListOfProgramComponents();
//        report = new FinalReport();
//
//        addBackgroundImage();
//        desktopPane.addMouseListener(new DesktopFocusAction());
//
//        setContentPane(desktopPane);
//        setTitle("CPSC 210 : Figure Skating Scores");
//        setSize(WIDTH, HEIGHT);
//
//        addControlPanel();
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        centreOnScreen();
//        setVisible(true);
//
//    }
//
//    // EFFECTS: add background image
//
//    private void addBackgroundImage() {
//        desktopPane = new JDesktopPane() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(skatingImage.getScaledInstance(800, 600, Image.SCALE_DEFAULT), 0, 0, null);
//            }
//
//            @Override
//            public Dimension getPreferredSize() {
//                return new Dimension(skatingImage.getWidth(), skatingImage.getHeight());
//            }
//        };
//    }
//
//
//    // EFFECTS: add a control panel to the desktop frame
//    private void addControlPanel() {
//        scorePanel = new JInternalFrame("Score Panel for Skater", true, true, true, true);
//        try {
//            skatingImage = ImageIO.read(new File("./images/skating image2.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        scorePanel.setSize(WIDTH * 2 / 7, HEIGHT);
//        scorePanel.getContentPane().setBackground(Color.YELLOW);
//        scorePanel.setVisible(true);
//
//        //desktopPane.add(scorePanel);
//        addButtonPanel();
//    }
//
//
//    // EFFECTS: add button on internalPanel, for user to click and do interaction
//    private void addButtonPanel() {
//        JButton addSkater = new JButton(new AddSkaterInformation());
//        addSkater.setBounds(0, 0, 200, 40);
//        JButton addExecuted = new JButton(new AddExecutedElementAction());
//        addExecuted.setBounds(0, 40, 200, 40);
//        JButton addProgram = new JButton(new AddProgramComponentAction());
//        addProgram.setBounds(0, 80, 200, 40);
//        JButton print = new JButton(new PrintFinalReportAction());
//        print.setBounds(0,120,200,40);
//        JButton save = new JButton(new SaveFinalReportAction());
//        save.setBounds(0, 160, 200, 40);
//        JButton load = new JButton(new LoadFinalReportAction());
//        load.setBounds(0, 200, 200, 40);
//        JButton quit = new JButton(new QuitAction());
//        quit.setBounds(0,240,200,40);
//        desktopPane.add(addSkater);
//        desktopPane.add(addExecuted);
//        desktopPane.add(addProgram);
//        desktopPane.add(print);
//        desktopPane.add(save);
//        desktopPane.add(load);
//        desktopPane.add(quit);
//
//    }
//
//
//    // EFFECTS: represents action to be taken when user wants to add a new element to the system
//    private class AddExecutedElementAction extends AbstractAction {
//
//
//        AddExecutedElementAction() {
//            super("Add Executed Element");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String elementName = JOptionPane.showInputDialog(null,
//                    "Element Name...", "Enter Element Detail",
//                    JOptionPane.QUESTION_MESSAGE);
//            String baseValue = JOptionPane.showInputDialog(null,
//                    "Element Base Value...", "Enter Element Detail",
//                    JOptionPane.QUESTION_MESSAGE);
//            String halfProgram = JOptionPane.showInputDialog(null,
//                    "Half Program? 0 for false, 1 for true...", "Enter Element Detail",
//                    JOptionPane.QUESTION_MESSAGE);
//            ListOfJudgeScores scores = new ListOfJudgeScores();
//            for (int i = 1; i <= 9; i++) {
//                String judgeScore = JOptionPane.showInputDialog(null,
//                        "Judge Score", "Enter the Judge Score: " + i,
//                        JOptionPane.QUESTION_MESSAGE);
//                scores.addScores(Double.parseDouble(judgeScore));
//            }
//            ExecutedElement element = new ExecutedElement(elementName, Double.parseDouble(baseValue),
//                    Integer.parseInt(halfProgram), scores);
//
//            desktopPane.add(new ExecutedElementUI(element,ScoreUI.this)).setBounds(200,0,200,150);
//            loe.addElement(element);
//        }
//    }
//
//    // EFFECTS: represents action to be taken when user wants to add a new program component to the system
//    private class AddProgramComponentAction extends AbstractAction {
//        String programComponentName;
//        String[] listSelection = {"null", "Skating Skills", "Transitions", "Performance",
//                "Composition", "Interpretation of the Music"};
//
//        AddProgramComponentAction() {
//            super("Add Program Component");
//        }
//
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            programComponentName = (String)JOptionPane.showInputDialog(null, "Choose the Program Component Name",
//                    "Program Component Name", JOptionPane.PLAIN_MESSAGE, null, listSelection, listSelection[0]);
//            String factor = JOptionPane.showInputDialog(null, "Factor...",
//                    "Enter Element Detail", JOptionPane.QUESTION_MESSAGE);
//            ListOfJudgeScores scores = new ListOfJudgeScores();
//            for (int i = 1; i <= 9; i++) {
//                String judgeScore = JOptionPane.showInputDialog(null, "Judge Score",
//                        "Enter the Judge Score: " + i, JOptionPane.QUESTION_MESSAGE);
//                scores.addScores(Double.parseDouble(judgeScore));
//            }
//            ProgramComponent element = new ProgramComponent(programComponentName, Double.parseDouble(factor), scores);
//
//            desktopPane.add(new ProgramComponentUI(element,ScoreUI.this)).setBounds(200,0,200,150);
//            lop.addElement(element);
//
//
//        }
//    }
//
//    // represents action to be taken when user wants to add a new skater to the system
//    private class AddSkaterInformation extends AbstractAction {
//        String[] listSelection = {"null", "Men", "Ladies", "Pairs",
//                "Ice Dance"};
//
//        AddSkaterInformation() {
//            super("Add This Skater's Information");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String type = (String)JOptionPane.showInputDialog(null, "Choose the Skater Category: ",
//                    "Skater Category: ", JOptionPane.PLAIN_MESSAGE, null, listSelection, listSelection[0]);
//            String nation = JOptionPane.showInputDialog(null,
//                    "Competitor nation...",
//                    "Skater Detail",
//                    JOptionPane.QUESTION_MESSAGE);
//            String name = JOptionPane.showInputDialog(null,
//                    "Competitor name...",
//                    "Skater Detail",
//                    JOptionPane.QUESTION_MESSAGE);
//            SkaterDetails details = new SkaterDetails(type, name, nation);
//            report.setType(type);
//            report.setName(name);
//            report.setNation(nation);
//
//            desktopPane.add(new SkaterDetailsUI(details, ScoreUI.this)).setBounds(0,280,200,HEIGHT - 280);
////            desktopPane.add(new SkaterDetailsUI(details, ScoreUI.this));
//
//        }
//    }
//
//    // represents the action to be taken when the user wants to print the final report
//    private class PrintFinalReportAction extends AbstractAction {
//        PrintFinalReportAction() {
//            super("Print the Final Report");
//        }
//
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            report.setLop(lop);
//            report.setLoe(loe);
//            double totalElementScore = loe.sumScoresOfPanel();
//            double totalProgramComponentScore = lop.sumScoresOfPanel();
//            double totalSegmentScore = totalElementScore + totalProgramComponentScore;
//            report.setTotalElementScore(totalElementScore);
//            report.setTotalProgramComponentScore(totalProgramComponentScore);
//            report.setTotalSegmentScore(totalSegmentScore);
//
//
//            JInternalFrame finalReport = new JInternalFrame(report.getType() + "Report for " + report.getName(),
//                    true, true, true,true);
//            finalReport.setLayout(new BorderLayout());
//
//            finalReport.add(new FinalReportUI(report, ScoreUI.this));
//            finalReport.setBounds(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
//            finalReport.setVisible(true);
//
//            desktopPane.add(finalReport);
//        }
//    }
//
//    // represents the action to be taken when the user wants to save the final reports
//    private class SaveFinalReportAction extends AbstractAction {
//
//        SaveFinalReportAction() {
//            super("Save the Final Report");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            try {
//                jsonWriterListOfExecutedElement.open();
//                jsonWriterListOfExecutedElement.write(loe);
//                jsonWriterListOfExecutedElement.close();
//                System.out.println("Saved Skater " + report.getName() + " all Executed Elements' scores to "
//                        + STORE_PATH_LIST_OF_EXECUTED_ELEMENTS);
//
//                jsonWriterListOfProgramComponents.open();
//                jsonWriterListOfProgramComponents.write(lop);
//                jsonWriterListOfProgramComponents.close();
//                System.out.println("Saved Skater " + report.getName() + " all Program Components' scores to "
//                        + STORE_PATH_LIST_OF_PROGRAM_COMPONENTS);
//
//                jsonWriterFinalReport.open();
//                jsonWriterFinalReport.write(report);
//                jsonWriterFinalReport.close();
//                System.out.println("Saved Skater " + report.getName() + " final report to "
//                        + STORE_PATH_FINAL_REPORT);
//            } catch (FileNotFoundException exception) {
//                System.out.println("Unable to write to file ");
//            }
//        }
//    }
//
//    // represents the action to be taken when the user wants to load final report
//    private class LoadFinalReportAction extends AbstractAction {
//
//        LoadFinalReportAction() {
//            super("Load the Final Report");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            try {
//                loe = jsonReaderListOfExecutedElement.read();
//                System.out.println("All Executed Elements scores from "
//                        + STORE_PATH_LIST_OF_EXECUTED_ELEMENTS);
//                lop = jsonReaderListOfProgramComponents.read();
//                System.out.println("All Program Components scores from "
//                        + STORE_PATH_LIST_OF_PROGRAM_COMPONENTS);
//                report = jsonReaderFinalReport.read();
//                System.out.println("Loaded Skater " + report.getName() + " final report from "
//                        + STORE_PATH_FINAL_REPORT);
//            } catch (IOException exception) {
//                System.out.println("Unable to write to file ");
//            }
//
//        }
//    }
//
//    /**
//     * Represents the action to be taken when the user wants to
//     * print the event log.
//     */
//    private class QuitAction extends AbstractAction {
//        QuitAction() {
//            super("Quit!");
//        }
//
//
//        public void printLog(EventLog el) {
//            for (Event next : el) {
//                System.out.println(next.toString() + "\n");
//            }
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            printLog(EventLog.getInstance());
//            System.exit(0);
//        }
//
//
//    }
//
//
//    /**
//     * Helper to centre main application window on desktop
//     */
//    private void centreOnScreen() {
//        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
//        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
//        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
//    }
//
//    /**
//     * Represents action to be taken when user clicks desktop
//     * to switch focus. (Needed for key handling.)
//     */
//    private class DesktopFocusAction extends MouseAdapter {
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            ScoreUI.this.requestFocusInWindow();
//        }
//    }
//
//    // starts the application
//    public static void main(String[] args) {
//        try {
//            new ScoreUI();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
