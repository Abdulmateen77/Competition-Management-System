package competitor;

import java.util.HashMap;
import java.util.Map;

public class scoreClass {
    private static int competitionIDCounter = 0;
    private static int competitorIDCounter = 0;
    private int competitionID;
    public int competitorID;
    private String level;
    private int[] scores;


    // Map to store competitors' scores based on their competitorID
    private static final Map<Integer, scoreClass> competitorScores = new HashMap<>();

    // Constructor that takes data from Competitor class
    public scoreClass(AMScompetitor competitor) {
        this.level = competitor.getLevel();
        this.scores = competitor.getScoreArray();
        this.competitionID = competition.getCompetitionID();
        competitorScores.put(competitorID, this);
    }

    // Method to get basic score
    public int[] getBasicScore(int competitorNumber) {
        scoreClass competitor = competitorScores.get(competitorNumber);
        return competitor != null ? competitor.scores : null;
    }

    // Method to get overall score
    public static int[] getOverallScore(int competitorNumber) {
        scoreClass competitor = competitorScores.get(competitorNumber);
        return competitor != null ? competitor.scores : null;
    }

    // Method to get summary
    public static String getSummary(int competitorNumber) {
        scoreClass competitor = competitorScores.get(competitorNumber);
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
