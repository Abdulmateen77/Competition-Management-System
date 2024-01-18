//Import necessary classes from the competitor package and other standard Java libraries
import competitor.AMScompetitor;
import competitor.CompetitorList;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

import static competitor.CompetitorList.readCompetitorDetails;

//The main class responsible for managing competitor information and user interaction
public class Manager {
    //The main method to initiate the program
    public void main(String[] args) {

        //Set up the GUI for competition using Swing
        SwingUtilities.invokeLater(() -> new CompetitionGUI());

        try {
            // Create a scanner for user input
            Scanner scanner = new Scanner(System.in);

            // Continue running the program until user chooses to exit
            while (true) {
                // Display menu options
                System.out.println("\nChoose an option:");
                System.out.println("1 View Full Details for All Competitors");
                System.out.println("2 View Short Details for a Specific Competitor");
                System.out.println("3 Generate and Display the Final Report");
                System.out.println("4 Exit");

                // Read user choice
                int choice = scanner.nextInt();
                String relativePath = "RunCompetitor.csv";

                // Create a CompetitorList and read competitor details from CSV file
                CompetitorList competitorList = readCompetitorDetails(relativePath);

                // Perform actions based on user's choice
                if (choice == 1) {
                    // Display full details for all competitors
                    AMScompetitor[] competitorsArray = competitorList.getCompetitors().toArray(new AMScompetitor[0]);
                    for (AMScompetitor competitor : competitorsArray) {
                        System.out.println(competitor.getfullDetails());
                    }
                } else if (choice == 2) {
                    // Display short details for a specific competitor
                    System.out.print("Enter competitor number to display short details: ");
                    int competitorNumber = scanner.nextInt();
                    competitorList.displayShortDetails(competitorNumber);
                } else if (choice == 3) {
                    // Generate and display the final report
                    competitorList.generateFinalReport("FinalReport.txt");
                    System.out.println("View FinalReport.txt file for the summary report");
                } else if (choice == 4) {
                    // Exit the program
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                } else {
                    // Inform the user of an invalid choice
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (IOException e) {
            // Handle IO exception
            System.out.println("Error: " + e.getMessage());
        }
    }
}
