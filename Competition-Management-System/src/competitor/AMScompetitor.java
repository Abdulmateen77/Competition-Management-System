package competitor;

import java.io.IOException;
import java.util.Arrays;

//Competitor Class
public class AMScompetitor {

    private static int competitorCount = 0;
    
    //Instance variables for competitor details
    private int competitorNumber;
    public String name;
    public int age;
    private String gender;
    private static String email;
    private String country;
    private static String category;
    public String level;
    private static int[] scores;
    private Verification verification;

    //Static variable to store a list of competitors
    
    public static CompetitorList competitorList;

    //Constructor for creating a competitor with basic details
    
    public AMScompetitor(String name, int age, String email, String category, String level) {
        this.name = name;
        this.age = age;
        AMScompetitor.email = email;
        AMScompetitor.category = category;
        this.level = level;
    }

    //Constructor for creating a competitor with additional details
    public AMScompetitor(int competitorNumber, String name, int age, String gender, String country, int[] scores) {
        this.competitorNumber = competitorNumber;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.scores = scores;
    }

    //Getter methods for accessing competitor details
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getGender() {
        return gender;
    }

    public static String getEmail() {
        return email;
    }

    public static String getCategory() {
        return category;
    }

    public int getCompetitorNumber() {
        return competitorNumber;
    }

    public static int[] getScores() {
        return scores;
    }
    //Method to set scores for a competitor
    public static void setScores(double score1, double score2, double score3, double score4) {
        scores = new int[]{(int) score1, (int) score2, (int) score3, (int) score4};
    }

    //Method to register a new competitor
    public int registerCompetitor(String name, int age, String email, String category, String level) throws IOException {

        //Validate data using the Verification class
        Verification verification = new Verification(name, age, email, category, level);
        if (!verification.validateData()) {
            System.out.println("Registration failed. Please check the provided data.");
            return -1;
        }

        //Check for existing competitors with the same email and category
        AMScompetitor existingCompetitor = findCompetitorByEmailAndCategory(email, category);
        if (existingCompetitor != null) {
            if (getCategory().equals(category)) {
                System.out.println("Registration refused. Competitor with the same email and category already exists.");
                return -1;
            } else {
                // Registration accepted with a different category
                competitorNumber = ++competitorCount;
                AMScompetitor newCompetitor = new AMScompetitor(name, age, email, category, level);
                System.out.println("Competitor registered successfully with a different category!");
                return getCompetitorNumber();
            }
        } else {
            //Registration accepted
            competitorNumber = ++competitorCount;
            AMScompetitor newCompetitor = new AMScompetitor(name, age, email, category, level);
            competitorList.addCompetitor(newCompetitor);
            System.out.println("Competitor registered successfully!");

            //Save competitor details to a CSV fil
            competitorList.addCompetitor(newCompetitor);
            competitorList.writeCompetitorDetails("RunCompetitor.csv");

            return getCompetitorNumber();
        }
    }

    //Helper method to find a competitor by email and category
    private static AMScompetitor findCompetitorByEmailAndCategory(String email, String category) {
        for (AMScompetitor competitor : competitorList.getCompetitors()) {
            if (getEmail().equals(email) && getCategory().equals(category)) {
                return competitor;
            }
        }
        return null; // No match found
    }

    //Method to calculate the overall score of a competitor
    public double getOverallScore() {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.length;
    }

    //Method to get a formatted string with competitor data
    public String getCompetitorsdata() {
        return String.format("Competitor Number: %d, Name %s, %s Age:, Gender: %s, Country %s Received these scores: %s overall Score: %.2f.",
                competitorNumber, name, age, gender, country, Arrays.toString(scores), getOverallScore());
    }

    //Method to get a formatted string with competitor details
    public String getCompetitorDetails() {
        return String.format("Competitor Number: %d \n Name: %s\n Age: %d \n Gender: %s \n Country: %s", competitorNumber, name, age, gender, country, Arrays.toString(scores));
    }

    //Method to get a formatted string with full competitor details
    public String getfullDetails() {
        return String.format("Competitor Number: %d, Name %s, %s Age:, Gender: %s, Country %s.\n%s is a from %s aged %d and received these scores: %s\nThis gives him an overall score of %.2f.",
                competitorNumber, name, age, gender, country, name, country, age, Arrays.toString(scores), getOverallScore());
    }

    //Method to get a short formatted string with competitor details
    public String getShortDetails() {
        return String.format("CN %d (%s) has an overall score of %.2f.",
                competitorNumber, getInitials(), getOverallScore());
    }

    //Helper method to get initials from the name
    private String getInitials() {
        String[] nameParts = name.split(" ");
        StringBuilder initials = new StringBuilder();

        for (String part : nameParts) {
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }

        return initials.toString().toUpperCase();
    }

    //Method to calculate the overall score (alternative implementation)
    public double calculateOverallScore() {
        if (scores.length == 0) {
            return 0.0;
        }

        int sum = 0;
        for (int score : scores) {
            sum += score;
        }

        return (double) sum / scores.length;
    }

    //Getter method for the competition level
    public String getLevel() {
        return level;
    }

    //Getter method for the array of scores
    public int[] getScoreArray() {
        return scores;
    }
}
