package competitor;

import java.util.HashMap;
import java.util.Map;

public class ScoreClass {
    //Counter for unique competition and competitor IDs
    private static int competitionIDCounter = 0;
    private static int competitorIDCounter = 0;

    //Instance variables for scoreClass
    private int competitionID;
    public int competitorID;
    private String level;
    private int[] scores;

    //Map to store competitors' scores based on their competitorID
    private static final Map<Integer, ScoreClass> competitorScores = new HashMap<>();

    //Constructor that takes data from Competitor class
    public ScoreClass(AMSCompetitor competitor) {
        this.level = competitor.getLevel();
        this.scores = competitor.getScoreArray();
        this.competitionID = competitor.getCompetitionID();
        competitorScores.put(competitorID, this);
    }

    //Method to get basic score for a specific competitor
    public int[] getBasicScore(int competitorNumber) {
        ScoreClass competitor = competitorScores.get(competitorNumber);
        return competitor != null ? competitor.scores : null;
    }

    //Method to get overall score for a specific competitor
    public static int[] getOverallScore(int competitorNumber) {
        ScoreClass competitor = competitorScores.get(competitorNumber);
        return competitor != null ? competitor.scores : null;
    }

    //Method to get a summary of a competitor's information
    public static String getSummary(int competitorNumber) {
        ScoreClass competitor = competitorScores.get(competitorNumber);
        if (competitor != null) {
            return String.format("Competitor number %d, Competition ID %d, Level %s, Scores: %s",
                    competitor.competitorID, competitor.competitionID, competitor.level, arrayToString(competitor.scores));
        } else {
            return "Competitor not found.";
        }
    }

    // Helper method to convert an array to string
    private static String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
