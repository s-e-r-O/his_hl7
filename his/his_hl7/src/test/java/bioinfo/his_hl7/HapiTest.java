package bioinfo.his_hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.parser.Parser;
import junit.framework.TestCase;

public class HapiTest extends TestCase {
	private HapiContext context;
	
	public HapiTest(String testName) {
		super(testName);
	}

	@SuppressWarnings("unchecked")
	public void testBasicUsage() throws Exception {
		ADT_A01 adt = new ADT_A01();
		adt.initQuickstart("ADT", "A01", "P");
		// Populate the MSH Segment
		MSH mshSegment = adt.getMSH();
		mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
		mshSegment.getSequenceNumber().setValue("123");
		
		// Populate the PID Segment
		PID pid = adt.getPID(); 
		pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
		pid.getPatientName(0).getGivenName().setValue("John");
		pid.getPatientIdentifierList(0).getID().setValue("123456");
		
		/*
		* In a real situation, of course, many more segments and fields would be populated
		*/
		context = new DefaultHapiContext();
		Parser parser = context.getPipeParser();
		String encodedMessage = parser.encode(adt);
		System.out.println("Printing ER7 Encoded Message:");
		System.out.println(encodedMessage);
	}
}
