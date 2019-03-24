package bioinfo.pis_hl7;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;
import upb.bio.models.Consultation;
import upb.bio.models.Doctor;
import upb.bio.models.Patient;

public class MedicalReceiverApplication implements ReceivingApplication {
    /**
     * {@inheritDoc}
     */
	@Override
	public Message processMessage(Message theMessage, Map<String, Object> theMetadata)
			throws ReceivingApplicationException, HL7Exception {
		String encodedMessage = new DefaultHapiContext().getPipeParser().encode(theMessage);
        System.out.println("Received message:\n" + encodedMessage + "\n\n");
        try {
        	// ADT_A01 or ADT_A04
        	Consultation c = new Consultation();
        	c.setPatient(new Patient(((ADT_A01)theMessage).getPID()));
        	// TO-DO: Get this info from HL7 msg
        	c.setConsultationDate(Calendar.getInstance().getTime());
        	// TO-DO: Get this info from HL7 msg
        	Doctor d;
        	if (App.getManager().getVisits().size() > 0){
        		d = new Doctor("Maria", "Bonita");
        		d.setId(123);
        	} else {
        		d = new Doctor("Dr", "Seuss");
        		d.setId(1234);
        	}
        	c.setDoctor(d);        		
        	App.getManager().putVisit(c);
        	return theMessage.generateACK();
        } catch (IOException e) {
            throw new HL7Exception(e);
        }

	}
	
    /**
     * {@inheritDoc}
     */
	@Override
	public boolean canProcess(Message theMessage) {
		return true;
	}

}
