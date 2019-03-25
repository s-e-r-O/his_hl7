package upb.bio.global;

import ca.uhn.hl7v2.model.v24.datatype.XCN;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import upb.bio.models.Consultation;
import upb.bio.models.Doctor;
import upb.bio.models.Patient;

public class HL7Binders {
	public static void bind(PID pid, Patient patient) throws Exception {
		pid.getPatientName(0).getFamilyName().getSurname().setValue(patient.getFamilyName());
		pid.getPatientName(0).getGivenName().setValue(patient.getGivenName());
		pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue(patient.getAddress());
		pid.getPhoneNumberHome(0).getPhoneNumber().setValue(patient.getPhone());
		pid.getMaritalStatus().getCe1_Identifier().setValue(patient.getMaritalStatus());
		pid.getAdministrativeSex().setValue(patient.getGender()+"");
		pid.getPatientIdentifierList(0).getID().setValue(patient.getId()+"");
	}
	
	public static void bind(PV1 pv1, Consultation consultation) throws Exception {
		pv1.getPatientClass().setValue(consultation.getType());
		pv1.insertConsultingDoctor(0);
		XCN doc = pv1.getConsultingDoctor(0);
		doc.getGivenName().setValue(consultation.getDoctor().getGivenName());
		doc.getFamilyName().getSurname().setValue(consultation.getDoctor().getFamilyName());
		pv1.getConsultingDoctor(0).getIDNumber().setValue(consultation.getDoctor().getId()+"");
	}
	
	public static Patient constructPatient(PID pid) throws Exception {
		Patient p = new Patient();
    	p.setFamilyName(pid.getPatientName(0).getFamilyName().getSurname().getValue());
    	p.setGivenName(pid.getPatientName(0).getGivenName().getValue());
    	//p.setBirthDate(pid.getDateTimeOfBirth().getTimeOfAnEvent().getValueAsDate());
    	p.setAddress(pid.getPatientAddress(0).getStreetAddress().getStreetName().getValue());
    	p.setPhone(pid.getPhoneNumberHome(0).getPhoneNumber().getValue());
    	p.setMaritalStatus(pid.getMaritalStatus().getCe1_Identifier().getValue());
    	p.setGender(pid.getAdministrativeSex().getValue().charAt(0));
    	p.setId(Integer.parseInt(pid.getPatientIdentifierList(0).getID().getValue()));
    	return p;
	}
	
	public static Doctor constructDoctor(PV1 pv1) throws Exception {
		Doctor d = new Doctor();
    	d.setGivenName(pv1.getConsultingDoctor(0).getGivenName().getValue());
    	d.setFamilyName(pv1.getConsultingDoctor(0).getFamilyName().getSurname().getValue());
    	d.setId(Integer.parseInt(pv1.getConsultingDoctor(0).getIDNumber().getValue()));
    	return d;
	}
	
	public static Consultation constructConsultation(ADT_A01 adt) throws Exception {
		Consultation c = new Consultation();
    	c.setPatient(constructPatient(adt.getPID()));
    	c.setDoctor(constructDoctor(adt.getPV1()));
    	c.setConsultationDate(adt.getEVN().getRecordedDateTime().getTimeOfAnEvent().getValueAsDate());
    	return c;
	}
}
