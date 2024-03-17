package competitor;
/**
 * The Verification class is responsible for validating and storing competitor information.
 */

public class Verification {

    //Member variables to store competitor details
    public static String name;
    private static int age;
    private static String email;
    private static String category;
    private static String level;
    /**
     * Constructor for creating a Verification object with competitor details.
     * @param name Competitor's name
     * @param age Competitor's age
     * @param email Competitor's email address
     * @param category Competitor's category
     * @param level Competitor's level
     */
    public Verification(String name, int age, String email, String category, String level) {
        Verification.name = name;
        Verification.age = age;
        Verification.email = email;
        Verification.category = category;
        Verification.level = level;
    }
    
    /**
     * Validates the competitor data by performing basic checks.
     * @return True if data is valid, false otherwise
     */
    
    public boolean validateData() {
        //Basics validation checks
        if (name == null || name.isEmpty()) {
            System.out.println("Name is required.");
            return false;
        }
        
        if (age == 0) {
            System.out.println("Age is required.");
            return false;
        }
        if (email == null){
            System.out.println("Email address is required.");
            return false;
        }
        if (category == null){
            System.out.println("Category is required.");
            return false;
        }
        if (level == null){
            System.out.println("Level is required.");
            return false;
        }
        return true;
    }

    /**
     * Prints a message indicating the need to resubmit the form.
     */
    
    public static void resubmitForm() {
        System.out.println("Resubmit the Form");
    }
}
