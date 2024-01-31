package competitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AMScompetitor {

    private static List<AMScompetitor> competitors = new ArrayList<>();
    private static int competitorCount = 0;
    private int competitorNumber;
    private final String name;
    private final Date dob;
    private static String email;
    private String country;
    private static String category;
    private final String level;
    private Verification verification;
    private int[] scores;


    //Constructor
    public AMScompetitor(String name, Date dob, String email, String category, String level) {
        this.name = name;
        this.dob = dob;
        AMScompetitor.email = email;
        AMScompetitor.category = category;
        this.level = level;
    }

    public static String getEmail(){
        return email;
    }
    public static String getCategory() {
        return category;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public int registerCompetitor(String name, Date dob, String email, String category, String level) {

        //Validate data
        Verification verification = new Verification(name, dob, email, category, level);
        if (!verification.validateData()) {
            System.out.println("Registration failed. Please check the provided data.");
            return -1;
        }

        // Check for existing competitor with the same email and category
        AMScompetitor existingCompetitor = findCompetitorByEmailAndCategory(email, category);
        if (existingCompetitor != null) {
            if (getCategory().equals(category)) {
                System.out.println("Registration refused. Competitor with the same email and category already exists.");
                return -1;
            } else {
                //Registration accepted with a different category
                competitorNumber = ++competitorCount; // Allocate a new competitor number
                AMScompetitor newCompetitor = new AMScompetitor(name, dob, email, category, level);
                System.out.println("Competitor registered successfully with a different category!");
                return newCompetitor.getCompetitorNumber();
            }
        } else {
            //Registration accepted
            competitorNumber = ++competitorCount; // Allocate a new competitor number
            AMScompetitor newCompetitor = new AMScompetitor(name, dob, email, category, level);
            competitors.add(newCompetitor);
            System.out.println("Competitor registered successfully!");
            return getCompetitorNumber();
        }
    }

     //Find competitor Filter
    private AMScompetitor findCompetitorByEmailAndCategory(String email, String category) {
        for (AMScompetitor competitor : competitors) {
            if (competitor.getEmail().equals(email) && competitor.getCategory().equals(category)) {
                return competitor;
            }
        }
        return null; // No match found
    }

    public int getCompetitorNumber() {

        return ++competitorCount;
    }

    public double getOverallScore(){
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.length;
    }
    public String getfullDetails(){
        return String.format("Competitor number %d, name %s, country %s.\n%s is a %s aged %d and received these scores: %s\nThis gives him an overall score of %.2f.",
                competitorNumber, name, country, name, level, dob, Arrays.toString(scores), getOverallScore());
    }

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

    public int[] getScoreArray() {
        return scores;
    }
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

}
