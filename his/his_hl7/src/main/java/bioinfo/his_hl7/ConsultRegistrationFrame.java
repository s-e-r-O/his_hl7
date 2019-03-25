package bioinfo.his_hl7;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;

import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import upb.bio.models.Doctor;
import upb.bio.models.Patient;
import com.github.lgooddatepicker.components.TimePicker;
import javax.swing.Box;

public class ConsultRegistrationFrame extends JFrame {

	private JPanel contentPane;
	private PatientManager patientManager;
	private DoctorManager doctorManager;
	private DefaultListModel<Patient> patientsModel;
	private DefaultListModel<Doctor> doctorsModel;

	/**
	 * Create the frame.
	 */
	public ConsultRegistrationFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 434, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				
		JLabel lblRegistrarConsulta = new JLabel("Registrar consulta\r\n");
		lblRegistrarConsulta.setBounds(17, 5, 391, 20);
		lblRegistrarConsulta.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblRegistrarConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(17, 57, 50, 16);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(89, 54, 70, 22);
		
		JButton btnRegistrarPaciente = new JButton("Registrar paciente");
		btnRegistrarPaciente.setBounds(89, 94, 327, 25);
		btnRegistrarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PatientRegistrationFrame frame = new PatientRegistrationFrame();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setBounds(17, 98, 67, 16);
		
		JLabel lblDoctor = new JLabel("Doctor:");
		lblDoctor.setBounds(17, 265, 42, 16);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(17, 409, 51, 16);
		

		
		JButton button = new JButton("Confirmar");
		button.setBounds(17, 442, 192, 25);
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date());
		
		
		
		
		JButton button_1 = new JButton("Cancelar");
		button_1.setBounds(221, 442, 195, 25);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 133, 399, 119);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(17, 294, 399, 99);
		
		DatePicker d = new DatePicker();
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(284, 409, 44, 16);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(67, 407, 193, 22);
		datePicker.setDateToToday();
		
		TimePicker timePicker = new TimePicker();
		timePicker.setBounds(340, 406, 76, 23);
		timePicker.setTimeToNow();
		
		
		final Calendar cal = Calendar.getInstance();
		cal.setTime(java.sql.Date.valueOf(datePicker.getDate()));
		cal.add(Calendar.HOUR_OF_DAY, timePicker.getTime().getHour());
		cal.add(Calendar.MINUTE, timePicker.getTime().getMinute());
		cal.getTime();
		
        
		doctorsModel = new DefaultListModel<Doctor>();
		final JList<Doctor> list_1 = new JList<Doctor>(doctorsModel);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setColumnHeaderView(list_1);
		
		patientsModel = new DefaultListModel<Patient>();
		contentPane.setLayout(null);
		final JList<Patient> list = new JList<Patient>(patientsModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultManager handler = ConsultManager.getInstance();
				handler.registerRoutineConsult(list.getSelectedValue(), list_1.getSelectedValue(), cal.getTime());
			}
		});
		
		contentPane.add(scrollPane);
		contentPane.add(lblDoctor);
		contentPane.add(lblRegistrarConsulta);
		contentPane.add(lblPaciente);
		contentPane.add(btnRegistrarPaciente);
		contentPane.add(lblTipo);
		contentPane.add(comboBox);
		contentPane.add(button);
		contentPane.add(button_1);
		contentPane.add(lblFecha);
		contentPane.add(datePicker);
		contentPane.add(lblHora);
		contentPane.add(timePicker);
		contentPane.add(scrollPane_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(17, 27, 399, 9);
		contentPane.add(separator);
		
		initializeValues();
	}
	
	private void initializeValues() {
		patientManager = PatientManager.getInstance();
		List<Patient> patients = patientManager.getPatients();
		for (Patient p: patients) {
			addPatient(p);
		}
		
		doctorManager = DoctorManager.getInstance();
		List<Doctor> doctors = doctorManager.getPatients();
		for (Doctor d: doctors) {
			addDoctor(d);
		}
		patientManager.registerObserver(this);
	}
	
	public void addPatient(Patient patient) {
		patientsModel.add(patientsModel.getSize(), patient);
	}
	
	public void addDoctor(Doctor doctor) {
		doctorsModel.add(doctorsModel.getSize(), doctor);
	}
}

