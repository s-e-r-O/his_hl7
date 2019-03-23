package bioinfo.pis_hl7;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Date;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ScheduleFrame extends JFrame {
	
	private ScheduleManager manager;
	
	private JPanel contentPane;
	private JList<Consultation> list;
	private DefaultListModel<Consultation> dlm;
	private JButton btnVerConsulta;
	private VisitFrame visitFrame;
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
		
		JComboBox<Doctor> comboBoxDoctor = new JComboBox<Doctor>();
		
		JComboBox<Date> comboBoxDate = new JComboBox<Date>();
		
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(btnVerConsulta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(lblDoctor, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(comboBoxDoctor, 0, 268, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBoxDate, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
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
		dlm = new DefaultListModel<Consultation>();
		list = new JList<Consultation>(dlm);
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
		dlm.add(dlm.getSize(), c);
		checkListContents();
		
	}
	
	private void checkListContents() {
		list.setEnabled(dlm.getSize() > 0);
		btnVerConsulta.setEnabled(dlm.getSize() > 0 && list.getSelectedIndex() >= 0);
	}
}
