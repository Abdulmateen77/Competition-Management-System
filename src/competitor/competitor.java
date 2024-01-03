
package competitor;

import java.util.Arrays;
import java.util.Date;

public class competitor {

    private static int competitorCount = 0;

    private int competitorNumber;
    private String name;
    private Date dob;
    private String country;
    private String category;
    private String level;
    private Verification verification;
    private int[] scores;

    // Constructor
    public competitor(String name, Date dob, String country, String category, String level) {
        this.name = name;
        this.dob = dob;
        this.country = country;
        this.category = category;
        this.level = level;
        this.competitorNumber = ++competitorCount;
    }

    public String getCategory() {
        return category;
    }

    public void registerCompetitor(String name, Date dob, String email, String category, String level) {
        // Validate data
        Verification verification = new Verification(name, dob, email, category, level);
        if (!verification.validateData()) {
            System.out.println("Registration failed. Please check the provided data.");
            return;
        }

        // Check for existing competitor with the same email and category
        Competitor existingCompetitor = findCompetitorByEmailAndCategory(email, category);
        if (existingCompetitor != null) {
            if (existingCompetitor.getCategory().equals(category)) {
                System.out.println("Registration refused. Competitor with the same email and category already exists.");
            } else {
                // Registration accepted with a different category
                competitorNumber = ++competitorCount; // Allocate a new competitor number
                System.out.println("Competitor registered successfully with a different category!");
            }
        } else {
            // Registration accepted
            competitorNumber = ++competitorCount; // Allocate a new competitor number
            System.out.println("Competitor registered successfully!");
        }
    }

    private Competitor findCompetitorByEmailAndCategory(String email, String category) {
        // Placeholder implementation, replace with your actual logic
        return null;
    }


    public double getOverallScore(){
        return 5;
    }
    public String getfullDetails(){
        return String.format("Competitor number %d, name %s, country %s.\n%s is a %s aged %d and received these scores: %s\nThis gives him an overall score of %.2f.",
                competitorNumber, name, country, name, level, dob, Arrays.toString(scores), getOverallScore());
    }

    public String getShortDetails() {
        return String.format("CN %d (%s) has an overall score of %.2f.",
                competitorNumber, getInitials(), getOverallScore());
    }

    // Helper method to get initials from the name
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
        // Placeholder values, replace with your actual scores
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
