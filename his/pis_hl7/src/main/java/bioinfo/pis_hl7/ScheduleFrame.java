package bioinfo.pis_hl7;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import upb.bio.models.Consultation;
import upb.bio.models.Doctor;

@SuppressWarnings("serial")
public class ScheduleFrame extends JFrame {
	
	private ScheduleManager manager;
	
	private JPanel contentPane;
	private JList<Consultation> list;
	private List<Consultation> allVisits;
	private DefaultListModel<Consultation> modelVisits;
	private DefaultComboBoxModel<Doctor> modelDoctors;
	private JButton btnVerConsulta;
	private VisitFrame visitFrame;
	private JComboBox<Doctor> comboBoxDoctor;
	public ScheduleManager getManager() {
		if (manager == null) {
			manager = new ScheduleManager();
		}
		return manager;
	}

	/*
	 * Create the application.
	 */
	public ScheduleFrame() {
		initialize();
		subscribeToManager();
	}

	private void subscribeToManager() {
		getManager().subscribe(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		allVisits = new ArrayList<Consultation>();
		setTitle("Doctor");
		setBounds(100, 100, 450, 451);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator = new JSeparator();
		modelDoctors = new DefaultComboBoxModel<Doctor>();
		comboBoxDoctor = new JComboBox<Doctor>(modelDoctors);
		comboBoxDoctor.setEnabled(false);
		comboBoxDoctor.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	            changeDoctor();
	        }
	    });
		
		JComboBox<String> comboBoxDate = new JComboBox<String>();
		
		comboBoxDate.setModel(new DefaultComboBoxModel<String>(new String[] 
				{
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.ENGLISH))
				}));
		comboBoxDate.setEnabled(false);
		
		btnVerConsulta = new JButton("Ver consulta");
		btnVerConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							if (visitFrame != null) {
								visitFrame.setVisible(false);
							}
							visitFrame = new VisitFrame(list.getSelectedValue());
							visitFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnVerConsulta.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(btnVerConsulta, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(lblDoctor, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBoxDoctor, 0, 308, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDoctor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxDoctor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnVerConsulta)
					.addContainerGap())
		);
		modelVisits = new DefaultListModel<Consultation>();
		list = new JList<Consultation>(modelVisits);
		list.setEnabled(false);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                  checkListContents();
                }
            }
        });
		scrollPane.setViewportView(list);
		contentPane.setLayout(groupLayout);
		checkListContents();
	}
	
	public void putVisit(Consultation c) {
		allVisits.add(c);
		if (modelDoctors.getIndexOf(c.getDoctor()) == -1) {
			modelDoctors.addElement(c.getDoctor());
			if (modelDoctors.getSize() == 1) {
				modelDoctors.setSelectedItem(c.getDoctor());
			}
		} else if (modelDoctors.getSelectedItem().equals(c.getDoctor())){
			modelVisits.add(modelVisits.getSize(), c);
		}
		checkListContents();
		
	}
	
	private void changeDoctor() {
		if (modelDoctors.getSelectedItem() != null) {
				modelVisits.clear();
				for (Consultation c : allVisits) {
					if (modelDoctors.getSelectedItem().equals(c.getDoctor())) {
						modelVisits.add(modelVisits.getSize(), c);			
					}
				}
				checkListContents();			
		}
	}
	
	private void checkListContents() {
		comboBoxDoctor.setEnabled(modelDoctors.getSize() > 1);
		list.setEnabled(modelVisits.getSize() > 0);
		btnVerConsulta.setEnabled(modelVisits.getSize() > 0 && list.getSelectedIndex() >= 0);
	}
}
