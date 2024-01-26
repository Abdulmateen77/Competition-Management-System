package competitor;
// Emergency response staff child class
public class  EmergencyResponse extends staffMember {

    public EmergencyResponse(String role) {
        super(role);
    }

    public void ProvideSupport(){
        System.out.println("providing emergency support... "); ;
    }
}
