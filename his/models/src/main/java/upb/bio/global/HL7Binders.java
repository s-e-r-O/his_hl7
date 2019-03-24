package upb.bio.global;

import ca.uhn.hl7v2.model.v24.datatype.XCN;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import upb.bio.models.Consultation;
import upb.bio.models.Patient;

public class HL7Binders {
	public static void bind(PID pid, Patient patient) throws Exception{
		pid.getPatientName(0).getFamilyName().getSurname().setValue(patient.getFamilyName());
		pid.getPatientName(0).getGivenName().setValue(patient.getGivenName());
		pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue(patient.getAddress());
		pid.getPhoneNumberHome(0).getPhoneNumber().setValue(patient.getPhone());
		pid.getMaritalStatus().getCe1_Identifier().setValue(patient.getMaritalStatus());
		pid.getAdministrativeSex().setValue(patient.getGender()+"");
		pid.getPatientIdentifierList(0).getID().setValue(patient.getId()+"");
	}
	
	public static void bind(PV1 pv1, Consultation consultation) throws Exception{
		pv1.getPatientClass().setValue(consultation.getType());
		pv1.insertConsultingDoctor(0);
		XCN doc = pv1.getConsultingDoctor(0);
		doc.getGivenName().setValue(consultation.getDoctor().getGivenName());
		doc.getFamilyName().getSurname().setValue(consultation.getDoctor().getFamilyName());
		pv1.getConsultingDoctor(0).getIDNumber().setValue(consultation.getDoctor().getId()+"");
	}
}
