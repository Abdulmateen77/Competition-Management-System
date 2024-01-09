package competitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class staffMember {
    private static int staffIDCounter = 0;
    private int staffID;
    private String role;

    // Map to store competitors based on their competitorNumber
    private static Map<Integer, AMScompetitor> competitorRegistry = new HashMap<>();

    // Constructor
    public staffMember(String role) {
        this.role = role;
        this.staffID = ++staffIDCounter;
    }

    // Validates access level
    public boolean validateAccess() {
        return Objects.equals(role, "official") || Objects.equals(role, "staff member");
    }

    // Get competitor details
    public String getCompetitorDetails(int competitorNumber) {
        if (validateAccess()) {
            AMScompetitor competitor = competitorRegistry.get(competitorNumber);
            return competitor != null ? competitor.getfullDetails() : "Competitor not found.";
        } else {
            return "Access Denied";
        }
    }

    // Get competitor results
    public int[] getResult(int competitorNumber) {
        if (validateAccess()) {
            return scoreClass.getOverallScore (competitorNumber);
        } else {
            return null;
        }
    }

    // Get summary report
    public String getSummaryReport(int competitorNumber) {
        return scoreClass.getSummary(competitorNumber);
    }

    // Enter competitor score
    protected int enterScore(int competitorNumber, double score1, double score2, double score3, double score4) {
        if (validateAccess()) {
            AMScompetitor competitor = competitorRegistry.get(competitorNumber);
            if (competitor != null) {
                competitor.setScores(score1, score2,score3,score4);
                return 0; // Score entered successfully
            } else {
                return -1; // Competitor not found
            }
        } else {
            return -2; // Access Denied
        }
    }

    // Register new competitor
//    protected void registerCompetitor(String name, int age, String email, String category, String level) throws IOException {
//        if (validateAccess()) {
//            AMScompetitor.registerCompetitor(name, age, email, category, level);
//        }
//    }

    // Remove competitor
    protected void removeCompetitor(int competitorNumber) {
        if (validateAccess()) {
            competitorRegistry.remove(competitorNumber);
        }
    }

//     Amend competitor details
//    protected void amendCompetitor(AMScompetitor updatedCompetitor) {
//        if (validateAccess()) {
//            competitorRegistry.put(AMScompetitor.getCompetitorNumber(), updatedCompetitor);
//        }
//    }
}

