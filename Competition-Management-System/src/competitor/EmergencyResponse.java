package competitor;

//Emergency response staff child class
public class  EmergencyResponse extends staffMember {
    //Inherent properties from parent staff class 
    public EmergencyResponse(String role) {
        super(role);
    }
    
    //method to provide support 
    public void ProvideSupport(){
        System.out.println("providing emergency support... "); ;
    }
}
