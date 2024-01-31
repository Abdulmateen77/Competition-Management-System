
package competitor;

import java.util.Date;
class Verification {
    private String name;
    private Date dob;
    private String email;
    private String category;
    private String level;

    public Verification(String name, Date dob, String email, String category, String level) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.category = category;
        this.level = level;
    }


    public boolean validateData() {
        // Basic validation checks
        if (name == null || name.isEmpty()) {
            System.out.println("Name is required.");
            return false;
        }
        if (dob == null) {
            System.out.println("Date of birth is required.");
            return false;
        }
        // Add more validation checks as needed (e.g., email format, valid category/level)
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

    public void resubmitForm() {
        // Implement form resubmission logic, potentially using a GUI framework or web API
        System.out.println("Resubmitting form with current data...");
        // Send data to a server or display a form for resubmission
    }
}
