package ui;

import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the SkatingScore application
public class ScoreApp {

    private static final String STORE_PATH_LIST_OF_EXECUTED_ELEMENTS =
            "./data/Skater'sAllExecutedElementsScores.json";
    private static final String STORE_PATH_LIST_OF_PROGRAM_COMPONENTS =
            "./data/Skater'sAllProgramComponentsScores.json";
    private static final String STORE_PATH_FINAL_REPORT =
            "./data/Skater'sFinalReport.json";
    private ListOfExecutedElements loe;
    private ListOfProgramComponents lop;
    private JsonWriterListOfProgramComponents jsonWriterListOfProgramComponents;
    private JsonWriterListOfExecutedElements jsonWriterListOfExecutedElement;
    private JsonWriterFinalReport jsonWriterFinalReport;
    private JsonReaderFinalReport jsonReaderFinalReport;
    private JsonReaderListOfProgramComponents jsonReaderListOfProgramComponents;
    private JsonReaderListOfExecutedElements jsonReaderListOfExecutedElement;

    private Scanner input;
    private FinalReport report;


    // EFFECTS: constructs SkatingScore and runs application
    public ScoreApp() throws FileNotFoundException {
        loe = new ListOfExecutedElements();
        lop = new ListOfProgramComponents();
        report = new FinalReport();
        jsonReaderListOfExecutedElement = new JsonReaderListOfExecutedElements(STORE_PATH_LIST_OF_EXECUTED_ELEMENTS);
        jsonReaderListOfProgramComponents =
                new JsonReaderListOfProgramComponents(STORE_PATH_LIST_OF_PROGRAM_COMPONENTS);
        jsonReaderFinalReport = new JsonReaderFinalReport(STORE_PATH_FINAL_REPORT);
        jsonWriterListOfExecutedElement = new JsonWriterListOfExecutedElements(STORE_PATH_LIST_OF_EXECUTED_ELEMENTS);
        jsonWriterListOfProgramComponents =
                new JsonWriterListOfProgramComponents(STORE_PATH_LIST_OF_PROGRAM_COMPONENTS);
        jsonWriterFinalReport = new JsonWriterFinalReport(STORE_PATH_FINAL_REPORT);
        runScore();
    }


    // MODIFIES: this
    // EFFECTS: process user input
    private void runScore() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you!");

    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: display menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add program scores");
        System.out.println("\tp -> print report");
        System.out.println("\ts -> save final report to file");
        System.out.println("\tl -> load final report from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doExecutedAndProgram();
        } else if (command.equals("p")) {
            printReports();
        } else if (command.equals("s")) {
            saveDetails();
        } else if (command.equals("l")) {
            loadDetails();
        } else {
            System.out.println("Not valid selection, please try another one");
        }
    }

    // MODIFIES: report
    // EFFECTS: combine Executed and Program part together and prompt user for name, type, nation
    public void doExecutedAndProgram() {
        System.out.println("Enter the program type (Men/Ladies/Pairs/IceDance): ");
        report.setType(input.next());
        System.out.println("Enter the skater name: ");
        report.setName(input.next());
        System.out.println("Enter the skater nation: ");
        report.setNation(input.next());
        doExecuted();
        doProgramComponents();
    }

    // MODIFIES: report
    // EFFECTS : do the executed elements part and prompt user for element scores
    public void doExecuted() {
        System.out.println("Please enter the score for each executed element...");
        for (int j = 1; j <= 2; j++) {
            System.out.println("The number of executed element: " + j);
            System.out.println("Enter the executed element name: ");
            String executedName = input.next();
            System.out.println("Enter if the half program, 0 for false, 1 for true");
            Integer half = input.nextInt();
            System.out.println("Enter the base value: ");
            Double baseValue = input.nextDouble();
            ListOfJudgeScoresMenLadiesAndPairs scores = new ListOfJudgeScoresMenLadiesAndPairs();
            System.out.println("There are totally nine judge scores");
            for (int i = 0; i < 9; i++) {
                System.out.println("Enter the judge score for this element, an integer from [-5,+5]: ");
                double score = input.nextDouble();
                scores.addScores(score);
            }
            ExecutedElementMenAndLadies e = new ExecutedElementMenAndLadies(executedName, half, scores);
            e.calculateBaseValue(baseValue,half);
            loe.addElement(e);
        }
        report.setLoe(loe);
        report.setTotalElementScore(loe.sumScoresOfPanel());
    }

    // MODIFIES: report
    // EFFECTS : do the program components part and prompt user for program scores
    public void doProgramComponents() {
        System.out.println("\nPlease enter the score for each program component...");
        for (int j = 1; j <= 2; j++) {
            System.out.println("The number of program components element: " + j);
            System.out.println("Enter the program components name: ");
            String programComponentsName = input.next();
            System.out.println("Enter the factor: ");
            Double factor = input.nextDouble();
            ListOfJudgeScoresMenLadiesAndPairs scores = new ListOfJudgeScoresMenLadiesAndPairs();
            System.out.println("There are totally nine judge scores");
            for (int i = 0; i < 9; i++) {
                System.out.println("Enter the judge score for this components: ");
                double score = input.nextDouble();
                scores.addScores(score);
            }
            ProgramComponent e = new ProgramComponent(programComponentsName, factor, scores);
            lop.addElement(e);
        }
        report.setLop(lop);
        report.setTotalProgramComponentScore(lop.sumScoresOfPanel());
        report.setTotalSegmentScore(report.getTotalElementScore() + report.getTotalProgramComponentScore());
    }

    // EFFECTS: prints final report, executed elements details and program components details to the console
    private void printReports() {
        System.out.printf("\nProgram Type: %s", report.getType());
        System.out.printf("\nSkater Name: %s", report.getName());
        System.out.printf("\nSkater Nation: %s", report.getNation());
        System.out.printf("\nTotal Elements Score: %f", report.getTotalElementScore());
        System.out.printf("\nTotal Program Component Score (factored): %f", report.getTotalProgramComponentScore());
        System.out.printf("\nTotal Segment Score: %f", report.getTotalSegmentScore());
    }

    // MODIFIES: report
    // EFFECTS: saves the final report, executed elements details and program components details to files
    private void saveDetails() {
        try {
            jsonWriterListOfExecutedElement.open();
            jsonWriterListOfExecutedElement.write(loe);
            jsonWriterListOfExecutedElement.close();
            System.out.println("Saved Skater " + report.getName() + " all Executed Elements' scores to "
                    + STORE_PATH_LIST_OF_EXECUTED_ELEMENTS);

            jsonWriterListOfProgramComponents.open();
            jsonWriterListOfProgramComponents.write(lop);
            jsonWriterListOfProgramComponents.close();
            System.out.println("Saved Skater " + report.getName() + " all Program Components' scores to "
                    + STORE_PATH_LIST_OF_PROGRAM_COMPONENTS);

            jsonWriterFinalReport.open();
            jsonWriterFinalReport.write(report);
            jsonWriterFinalReport.close();
            System.out.println("Saved Skater " + report.getName() + " final report to "
                    + STORE_PATH_FINAL_REPORT);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file ");
        }
    }

    // MODIFIES: report
    // EFFECTS: loads the final report, executed elements details and program components details from files
    private void loadDetails() {
        try {
            loe = jsonReaderListOfExecutedElement.read();
            System.out.println("All Executed Elements scores from "
                    + STORE_PATH_LIST_OF_EXECUTED_ELEMENTS);
            lop = jsonReaderListOfProgramComponents.read();
            System.out.println("All Program Components scores from "
                    + STORE_PATH_LIST_OF_PROGRAM_COMPONENTS);
            report = jsonReaderFinalReport.read();
            System.out.println("Loaded Skater " + report.getName() + " final report from "
                    + STORE_PATH_FINAL_REPORT);
        } catch (IOException e) {
            System.out.println("Unable to write to file ");
        }
    }



}