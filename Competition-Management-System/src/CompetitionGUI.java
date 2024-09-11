import competitor.AMScompetitor;
import competitor.CompetitorList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Class for managing the competition GUI
public class CompetitionGUI extends Component {

    //List of competitors
    private CompetitorList competitorList;
    private JFrame frame;
    
     //Method to set up the GUI components
    public CompetitionGUI() {
        this.competitorList = new CompetitorList();
        initialize();
    }
     
    // Method to set up the GUI components
    private void initialize() {
        //Creates a JFrame
        frame = new JFrame("Competition Management System");
        frame.setSize(800, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //1: View Table of Competitors
        JButton ViewCompetitorbutton = new JButton("View List of Competitors");
        //2: View and alter Competitors details
        JButton ViewandAlterbutton = new JButton("View and Alter Competitor Score");
        // 3: View Specific Competitors details
        JButton ViewDetailsbutton = new JButton("View Competitor Details");
        // 4: View Full or Short of the Competitors
        JButton ViewFulldetailsbutton = new JButton("View Full or Short Competitor Details");
        // 5 Edit Competitors details
        JButton EditCmpetitorbutton = new JButton("Edit Competitor's Details");
        // 6: Remove Competitors from the List
        JButton Removesbutton = new JButton("Remove Competitor");

        //Adds ActionListener to the button
        ViewCompetitorbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCompetitorList();
            }
        });
        
        ViewDetailsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCompetitorDetails();
            }
        });
        
        ViewFulldetailsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCompetitorFullorShortdetails();
            }
        });
        ViewandAlterbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewandAlterCompetitordetails();
            }
        });
        EditCmpetitorbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCompetitorDetails();
            }
        });
        Removesbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCompetitor();
            }
        });

        JPanel panel = new JPanel();

        //Adds button to panel
        panel.add(ViewCompetitorbutton);
        panel.add(ViewandAlterbutton);
        panel.add(ViewDetailsbutton);
        panel.add(ViewFulldetailsbutton);
        panel.add(EditCmpetitorbutton);
        panel.add(Removesbutton);

        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        frame.add(panel);
        //Make the JFrame visible
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
    }
    

    private void ViewCompetitorList() {
        competitorList.readCompetitorDetails("RunCompetitor.csv");

        java.util.List<AMScompetitor> competitors = competitorList.getCompetitors();

        //JFrame for displaying the table
        JFrame tableCompetitor = new JFrame("Competitor List Table");
        tableCompetitor.setSize(1000, 700);


        Object[][] competitorData = new Object[competitors.size()][10];

        for (int i = 0; i < competitors.size(); i++) {
            AMScompetitor competitor = competitors.get(i);
            competitorData[i][0] = competitor.getCompetitorNumber();
            competitorData[i][1] = competitor.getName();
            competitorData[i][2] = competitor.getAge();
            competitorData[i][3] = competitor.getGender();
            competitorData[i][4] = competitor.getCountry();
            competitorData[i][5] = competitor.getScores()[0];
            competitorData[i][6] = competitor.getScores()[1];
            competitorData[i][7] = competitor.getScores()[2];
            competitorData[i][8] = competitor.getScores()[3];
            competitorData[i][9] = competitor.getOverallScore();

        }

        //column names
        String[] columnNames = {"Competitor Number", "Name", "Age", "Gender", "Country", " FirstScore", "SecondScore", "ThirdScore", "FourthScore", "OverAllScore"};

        //JTable with improved aesthetics
        JTable table = new JTable(competitorData, columnNames);
        table.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(table);

        tableCompetitor.add(sp);

        tableCompetitor.setLocationRelativeTo(null);
        tableCompetitor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableCompetitor.setVisible(true);
    }

    public void ViewandAlterCompetitordetails(){
        competitorList.readCompetitorDetails("RunCompetitor.csv");

        java.util.List<AMScompetitor> competitors = competitorList.getCompetitors();
        String competitorNumberInput = JOptionPane.showInputDialog(
                this,
                "Enter Competitor Number:",
                "Alter Competitor Scores",
                JOptionPane.PLAIN_MESSAGE);
        if (competitorNumberInput != null && !competitorNumberInput.isEmpty()) {
            try {
                int competitorNumber = Integer.parseInt(competitorNumberInput);

                //Finds the selected competitor
                AMScompetitor selectedCompetitor = competitors.stream()
                        .filter(comp -> comp.getCompetitorNumber() == competitorNumber)
                        .findFirst()
                        .orElse(null);

                if (selectedCompetitor != null) {
                    //Displays a GUI for altering scores
                    JTextField firstScoreField = new JTextField();
                    JTextField secondScoreField = new JTextField();
                    JTextField thirdScoreField = new JTextField();
                    JTextField fourthScoreField = new JTextField();

                    //Set current scores as default values in the fields
                    firstScoreField.setText(String.valueOf(selectedCompetitor.getScores()[0]));
                    secondScoreField.setText(String.valueOf(selectedCompetitor.getScores()[1]));
                    thirdScoreField.setText(String.valueOf(selectedCompetitor.getScores()[2]));
                    fourthScoreField.setText(String.valueOf(selectedCompetitor.getScores()[3]));

                    //Creates a panel to hold the input fields
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridLayout(5, 2));
                    panel.add(new JLabel("First Score:"));
                    panel.add(firstScoreField);
                    panel.add(new JLabel("Second Score:"));
                    panel.add(secondScoreField);
                    panel.add(new JLabel("Third Score:"));
                    panel.add(thirdScoreField);
                    panel.add(new JLabel("Fourth Score:"));
                    panel.add(fourthScoreField);

                    //Shows input dialog with the panel
                    int result = JOptionPane.showConfirmDialog(
                            this,
                            panel,
                            "Edit Competitor Scores",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

                    //Checks if the user clicked "OK"
                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            //Update the scores with the values entered by the user
                            selectedCompetitor.setScores(
                                    Double.parseDouble(firstScoreField.getText()),
                                    Double.parseDouble(secondScoreField.getText()),
                                    Double.parseDouble(thirdScoreField.getText()),
                                    Double.parseDouble(fourthScoreField.getText())
                            );

                            //Saves the updated data to the CSV file or your data store
                            competitorList.saveCompetitorScoresToFile("RunCompetitor.csv", selectedCompetitor);

                            JOptionPane.showMessageDialog(
                                    this,
                                    "Scores updated successfully.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Invalid score. Please enter valid numeric values.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Competitor not found.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid competitor number. Please enter a valid number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }


    }
    public void ViewCompetitorDetails() {
        competitorList.readCompetitorDetails("RunCompetitor.csv");

        java.util.List<AMScompetitor> competitors = competitorList.getCompetitors();
        String competitorNumberInput = JOptionPane.showInputDialog(
                this,
                "Enter Competitor Number:",
                "View Competitor Details",
                JOptionPane.PLAIN_MESSAGE);

        //Checks if the user entered a competitor number
        if (competitorNumberInput != null && !competitorNumberInput.isEmpty()) {
            try {
                int competitorNumber = Integer.parseInt(competitorNumberInput);

                //Finds the selected competitor
                AMScompetitor selectedCompetitor = competitors.stream()
                        .filter(comp -> comp.getCompetitorNumber() == competitorNumber)
                        .findFirst()
                        .orElse(null);

                if (selectedCompetitor != null) {
                    //Displays the full details of the selected competitor
                    String details = selectedCompetitor.getCompetitorDetails();
                    JOptionPane.showMessageDialog(
                            this,
                            details,
                            "Competitor Details",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Competitor not found.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid competitor number. Please enter a valid number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void ViewCompetitorFullorShortdetails(){
        competitorList.readCompetitorDetails("RunCompetitor.csv");

        java.util.List<AMScompetitor> competitors = competitorList.getCompetitors();

        String[] viewOptions = {"Short Details", "Full Details"};
        JComboBox<String> viewComboBox = new JComboBox<>(viewOptions);

        //Creates a custom JPanel to enhance the input dialog
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Select View Details Format: "));
        inputPanel.add(viewComboBox);

        //Shows the input dialog with the JComboBox
        int option = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Select View",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        //Checks if the user clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            //Get the selected view option
            String selectedView = (String) viewComboBox.getSelectedItem();

            //Prompts the user to enter a competitor number
            String competitorNumberInput = JOptionPane.showInputDialog(
                    this,
                    "Enter Competitor Number:",
                    "View Competitor Details",
                    JOptionPane.PLAIN_MESSAGE);

            //Checks if the user entered a competitor number
            if (competitorNumberInput != null && !competitorNumberInput.isEmpty()) {
                try {
                    int competitorNumber = Integer.parseInt(competitorNumberInput);

                    // Finds the selected competitor
                    AMScompetitor selectedCompetitor = competitors.stream()
                            .filter(comp -> comp.getCompetitorNumber() == competitorNumber)
                            .findFirst()
                            .orElse(null);

                    if (selectedCompetitor != null) {
                        //Display details based on the selected view
                        if ("Short Details".equals(selectedView)) {
                            String details = selectedCompetitor.getShortDetails();
                            JOptionPane.showMessageDialog(
                                    this,
                                    details,
                                    "Competitor Short Details",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else if ("Full Details".equals(selectedView)) {
                            String details = selectedCompetitor.getfullDetails();
                            JOptionPane.showMessageDialog(
                                    this,
                                    details,
                                    "Competitor Full Details",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Invalid view option.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Competitor not found.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid competitor number. Please enter a valid number.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    
    public void EditCompetitorDetails(){
        competitorList.readCompetitorDetails("RunCompetitor.csv");

        java.util.List<AMScompetitor> competitors = competitorList.getCompetitors();

        Object[][] competitorData = new Object[competitors.size()][10];

        for (int i = 0; i < competitors.size(); i++) {
            AMScompetitor competitor = competitors.get(i);
            competitorData[i][0] = competitor.getCompetitorNumber();
            competitorData[i][1] = competitor.getName();
            competitorData[i][2] = competitor.getAge();
            competitorData[i][3] = competitor.getGender();
            competitorData[i][4] = competitor.getCountry();
            competitorData[i][5] = competitor.getScores()[0];
            competitorData[i][6] = competitor.getScores()[1];
            competitorData[i][7] = competitor.getScores()[2];
            competitorData[i][8] = competitor.getScores()[3];
            competitorData[i][9] = competitor.getOverallScore();

        }

        String[] columnNames = {"Competitor Number", "Name", "Age", "Gender", "Country", " FirstScore", "SecondScore", "ThirdScore", "FourthScore", "OverAllScore"};

        //JTable with improved aesthetics
        JTable table = new JTable(competitorData, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        //Creates a new JFrame to display the editable table
        JFrame tableFrame = new JFrame("Edit Competitor Details");

        //Shows a confirmation dialog
        int result = JOptionPane.showConfirmDialog(
                null,
                scrollPane,
                "Edit Competitor Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {

            JOptionPane.showMessageDialog(
                    null,
                    "Competitor details successfully updated and saved.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void removeCompetitor(){
        competitorList.readCompetitorDetails("RunCompetitor.csv");

        java.util.List<AMScompetitor> competitors = new ArrayList<>(competitorList.getCompetitors());

        String competitorNumberInput = JOptionPane.showInputDialog(
                null,
                "Enter Competitor Number to Remove:",
                "Remove Competitor",
                JOptionPane.PLAIN_MESSAGE);

        //Checks if the user entered a competitor number
        if (competitorNumberInput != null && !competitorNumberInput.isEmpty()) {
            try {
                int competitorNumberToRemove = Integer.parseInt(competitorNumberInput);

                // Finds the index of the competitor to remove
                int indexToRemove = -1;
                for (int i = 0; i < competitors.size(); i++) {
                    if (competitors.get(i).getCompetitorNumber() == competitorNumberToRemove) {
                        indexToRemove = i;
                        break;
                    }
                }

                //Checks if the competitor was found
                if (indexToRemove != -1) {
                    // Remove the competitor from the list
                    competitors.remove(indexToRemove);

                    // Saves the updated list to the file
                    JOptionPane.showMessageDialog(
                            null,
                            "Competitor removed successfully.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Competitor not found.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid competitor number. Please enter a valid number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}

