import competitor.AMScompetitor;
import competitor.CompetitorList;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

import static competitor.CompetitorList.readCompetitorDetails;

public class Manager {
    public void main(String[] args) {

        SwingUtilities.invokeLater(() -> new CompetitionGUI());

        try {

            // Create a scanner for user input
            Scanner scanner = new Scanner(System.in);

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

                // Create a competitor.CompetitorList and read competitor details from CSV
                CompetitorList competitorList = readCompetitorDetails(relativePath);


                if (choice == 1) {
                    AMScompetitor[] competitorsArray = competitorList.getCompetitors().toArray(new AMScompetitor[0]);
                    for (AMScompetitor competitor : competitorsArray) {
                        System.out.println(competitor.getfullDetails());
                    }
                } else if (choice == 2) {
                    System.out.print("Enter competitor number to display short details: ");
                    int competitorNumber = scanner.nextInt();
                    competitorList.displayShortDetails(competitorNumber);
                } else if (choice == 3) {
                    competitorList.generateFinalReport( "FinalReport.txt");
                    System.out.println("view Final Report txt file for the summary report");
                } else if (choice == 4) {
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}



