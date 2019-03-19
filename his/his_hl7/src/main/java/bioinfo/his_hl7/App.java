package bioinfo.his_hl7;

import java.util.Date;

public class App 
{	
    public static void main(String[] args) throws Exception { 
    	PatientRegistrationHandler handler = new PatientRegistrationHandler();
    	handler.registerNewPatient("Isabella", "Defilippis", new Date("01/08/1997"));
    }	
}
