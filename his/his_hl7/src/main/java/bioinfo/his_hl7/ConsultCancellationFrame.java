package bioinfo.his_hl7;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import upb.bio.models.Consultation;
import upb.bio.models.Patient;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

public class ConsultCancellationFrame extends JFrame {

	private JPanel contentPane;
	private JList list;
	private DatePicker datePicker;
	private ConsultManager consultManager;
	private DefaultListModel<Consultation> consultsModel;
	private List<Consultation> consults;
	private LocalDate currentDate;
	/**
	 * Create the frame.
	 */
	public ConsultCancellationFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblCancelarVisita = new JLabel("Cancelar consulta");
		lblCancelarVisita.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelarVisita.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("Confirmar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!list.isSelectionEmpty()) {
					consultManager.cancelConsult((Consultation) list.getSelectedValue());
					int index = list.getSelectedIndex();
					removeItemFromConsults(consultsModel.getElementAt(index));
					consultsModel.removeElementAt(index);
					dispose();
				}
			}
		});
		
		JButton button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		datePicker = new DatePicker();
		datePicker.setDateToToday();
		datePicker.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent event) {
				changeDate();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblCancelarVisita, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
								.addGap(11))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(20, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblCancelarVisita, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button))
					.addContainerGap())
		);
		
		consultsModel = new DefaultListModel<Consultation>();
		list = new JList<Consultation>(consultsModel);
		scrollPane.setViewportView(list);
		contentPane.setLayout(gl_contentPane);
		
		initializeValues();
	}
	
	private void initializeValues() {
		setCurrentDateValue();
		consultManager = ConsultManager.getInstance();
		consults = consultManager.getConsults();
		for (Consultation c: consults) {
			addConsultIfItIsValid(c);
		}
		consultManager.registerObserver(this);
	}
	
	public void addConsult(Consultation consult) {
		consults.add(consult);
		addConsultIfItIsValid(consult);
	}
	
	public void addConsultIfItIsValid(Consultation consult) {
		boolean isValid = false;
		LocalDate localDate = convertDateToLocalDate(consult.getConsultationDate());
		if (localDate.compareTo(currentDate) == 0) {
			if (localDate.equals(LocalDate.now())) {
				//check hours
				if (LocalDateTime.now().compareTo(convertDateToLocalDateTime(consult.getConsultationDate())) <= 0) {
					isValid = true;
				}
			}
			else {
				isValid = true;
			}
		}
		if (isValid) {
			consultsModel.add(consultsModel.getSize(), consult);
			if (consultsModel.getSize() == 1) {
				list.setSelectedIndex(0);
			}
		}
	}
	
	private LocalDate convertDateToLocalDate(Date date) {
		return date.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
	}
	
	private LocalDateTime convertDateToLocalDateTime(Date date) {
		return date.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
	}
	
	private void setCurrentDateValue() {
		String str = datePicker.getDateStringOrEmptyString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		currentDate = LocalDate.parse(str, formatter);
	}
	
	private void changeDate() {
		setCurrentDateValue();
		consultsModel.clear();
		for (Consultation c: consults) {
			addConsultIfItIsValid(c);
		}
	}
	
	private void removeItemFromConsults(Consultation consult) {
		int index;
		if ((index = consults.indexOf(consult)) != -1) {
			consults.remove(index);
		}
	}
}