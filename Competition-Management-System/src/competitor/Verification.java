package competitor;

public class Verification {
    public static String name;
    private static int age;
    private static String email;
    private static String category;
    private static String level;

    public Verification(String name, int age, String email, String category, String level) {
        Verification.name = name;
        Verification.age = age;
        Verification.email = email;
        Verification.category = category;
        Verification.level = level;
    }

    public boolean validateData() {
        // Basics validation checks
        if (name == null || name.isEmpty()) {
            System.out.println("Name is required.");
            return false;
        }
        if (age == 0) {
            System.out.println("age is required.");
            return false;
        }
        if (email == null){
            System.out.println("email address is required");
            return false;
        }
        if (category == null){
            System.out.println("category is required");
            return false;
        }
        if (level == null){
            System.out.println("level is required");
        }
        return true;
    }

    public static void resubmitForm() {

        System.out.println("Resubmit the Form");
    }
}
