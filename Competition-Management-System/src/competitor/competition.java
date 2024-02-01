package competitor;

//The competition class represents a generic competition with various attributes.
public class competition {
    
    //Static attributes to store information about the competition.
    public static int competitionID;
    public static String competitionName;
    public static String Category;
    public static int ageLimit;
    public static String Level;

    //Getter method to retrieve the competition ID.
    public static int getCompetitionID(){
        return competitionID;
    }

    //Constructor to initialize the competition object with provided details.
    public competition(int competitionID, String competitionName, String Category, int ageLimit, String Level){
        competition.competitionID = competitionID;
        competition.competitionName = competitionName;
        competition.Category = Category;
        competition.ageLimit = ageLimit;
        competition.Level = Level;
    }

    //Method to get a formatted string containing competition details.
    public static String getCompetitionDetails(){
        return competitionName + " with an ID " + competitionID + " in " + Category + " with an age limit of " + ageLimit + " for " + Level;
    }
}
