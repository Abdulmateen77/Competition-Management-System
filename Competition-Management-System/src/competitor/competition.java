package competitor;

public class competition {
    public static int competitionID;
    public static String competitionName;
    public static String Category;
    public static int ageLimit;
    public static String Level;

    public static int getCompetitionID(){
        return competitionID;
    }
    public competition(int competitionID, String competitionName, String Category, int ageLimit, String Level){
        competition.competitionID = competitionID;
        competition.competitionName = competitionName;
        competition.Category = Category;
        competition.ageLimit = ageLimit;
        competition.Level = Level;
    }

    public static String getCompetitionDetails(){
        return competitionName + "with and ID " + competitionID + " in" + Category + " with an age limit of " + ageLimit + " for " + Level;
    }
}
