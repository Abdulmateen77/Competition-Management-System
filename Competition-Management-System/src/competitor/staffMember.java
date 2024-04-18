package competitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a staff member in the competition management system.
 */


public class StaffMember {

    private static int staffIDCounter = 0;
    private int staffID;
    private String role;

    // Map to store competitors based on their competitorNumber
    private static Map<Integer, AMSCompetitor> competitorRegistry = new HashMap<>();

    /**
     * Constructor for creating a new staff member with a specified role.
     *
     * @param role The role of the staff member (e.g., "official" or "staff member").
     */
    public StaffMember(String role) {
        this.role = role;
        this.staffID = ++staffIDCounter;
    }

    /**
     * Validates the access level of the staff member.
     *
     * @return True if the access level is valid, otherwise false.
     */
    public boolean validateAccess() {
        return Objects.equals(role, "official") || Objects.equals(role, "staff member");
    }

    /**
     * Retrieves details of a competitor based on the competitor number.
     *
     * @param competitorNumber The number identifying the competitor.
     * @return Details of the competitor or an error message if not found or access denied.
     */
    public String getCompetitorDetails(int competitorNumber) {
        if (validateAccess()) {
            AMSCompetitor competitor = competitorRegistry.get(competitorNumber);
            return competitor != null ? competitor.getFullDetails() : "Competitor not found.";
        } else {
            return "Access Denied";
        }
    }

    /**
     * Retrieves the results of a competitor based on the competitor number.
     *
     * @param competitorNumber The number identifying the competitor.
     * @return An array of scores or null if access is denied.
     */
    public int[] getResult(int competitorNumber) {
        if (validateAccess()) {
            return ScoreClass.getOverallScore(competitorNumber);
        } else {
            return null;
        }
    }

    /**
     * Retrieves a summary report for a competitor based on the competitor number.
     *
     * @param competitorNumber The number identifying the competitor.
     * @return The summary report or an error message if access is denied.
     */
    public String getSummaryReport(int competitorNumber) {
        return ScoreClass.getSummary(competitorNumber);
    }

    /**
     * Enters scores for a competitor identified by the competitor number.
     *
     * @param competitorNumber The number identifying the competitor.
     * @param score1           The first score.
     * @param score2           The second score.
     * @param score3           The third score.
     * @param score4           The fourth score.
     * @return 0 if the scores are entered successfully, -1 if the competitor is not found, -2 if access is denied.
     */
    protected int enterScore(int competitorNumber, double score1, double score2, double score3, double score4) {
        if (validateAccess()) {
            AMSCompetitor competitor = competitorRegistry.get(competitorNumber);
            if (competitor != null) {
                competitor.setScores(score1, score2, score3, score4);
                return 0; // Score entered successfully
            } else {
                return -1; // Competitor not found
            }
        } else {
            return -2; // Access Denied
        }
    }

    /**
     * Registers a new competitor with the specified details.
     *
     * @param name     The name of the competitor.
     * @param age      The age of the competitor.
     * @param email    The email address of the competitor.
     * @param category The category of the competitor.
     * @param level    The level of the competitor.
     * @throws IOException if an I/O error occurs during competitor registration.
     */
    protected void registerCompetitor(String name, int age, String email, String category, String level) throws IOException {
        if (validateAccess()) {
            AMSCompetitor.registerCompetitor(name, age, email, category, level);
        }
    }

    /**
     * Removes a competitor based on the competitor number.
     *
     * @param competitorNumber The number identifying the competitor to be removed.
     */
    protected void removeCompetitor(int competitorNumber) {
        if (validateAccess()) {
            competitorRegistry.remove(competitorNumber);
        }
    }

    /**
     * Amends competitor details with the provided updated competitor information.
     *
     * @param updatedCompetitor The updated competitor information.
     */
    protected void amendCompetitor(AMSCompetitor updatedCompetitor) {
        if (validateAccess()) {
            competitorRegistry.put(AMSCompetitor.getCompetitorNumber(), updatedCompetitor);
        }
    }
}
