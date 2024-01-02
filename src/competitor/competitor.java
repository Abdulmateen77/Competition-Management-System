
package competitor;

public class competitor {

    private static int competitorCount = 0;

    private int competitorNumber;
    private String name;
    private int age;
    private String country;
    private String category;
    private String level;
    private Verification verification;

    // Constructor
    public competitor(String name, int age, String country, String category, String level) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.category = category;
        this.level = level;
        this.competitorNumber = ++competitorCount;
        this.verification = new Verification();
    }


    public void resgisterCompetitor(String name, int age, String email, String category, String level){
        Verification verification = new verfication(name, dob, email, category, level);

        if (verification.validateData()){
            Competitor competitor = new competitor(name, age, email, category, level);
            System.out.println("Competitor registered successfully!");
        } else{
            System.out.println("Registeration failed. Please check the provided date");
        }
    }

    public double getOverallScore(){
        return 5;
    }
    public String getfullDetails(){
        return  name + " is a " + level + " aged " + age + " from" +  country;
    }

    public String getShortDetails(){
        return name;
    }

    private int[] scores;
    int numScores = (int) (Math.random() * 3) + 4;
    scores = new int[numScores];

    // Fill the array with random scores between 0 and 5
    for(int i = 0; i < scores.length; i++) {
        scores[i] = (int) (Math.random() * 6);
    }
    public int[] getScoreArray() {
        // Placeholder values, replace with your actual scores
        return scores;
    }
    public double calculateOverallScore() {
        int[] scores = getScoreArray();

        // Placeholder calculation, replace with your logic
        double sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum / scores.length;
    }

}
