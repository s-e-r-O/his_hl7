package bioinfo.pis_hl7;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScheduleFrame {

	private JFrame frmDoctor;

	/**
	 * Create the application.
	 */
	public ScheduleFrame() {
		initialize();
		frmDoctor.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoctor = new JFrame();
		frmDoctor.setTitle("Doctor");
		frmDoctor.setBounds(100, 100, 450, 451);
		frmDoctor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator = new JSeparator();
		
		JComboBox comboBox = new JComboBox();
		
		JComboBox comboBox_1 = new JComboBox();
		
		JButton btnVerConsulta = new JButton("Ver consulta");
		btnVerConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							VisitFrame frame = new VisitFrame();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JList list = new JList();
		
		JLabel label = new JLabel("Name");
		
		JLabel label_1 = new JLabel("Consulta seleccionada:");
		
		GroupLayout groupLayout = new GroupLayout(frmDoctor.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVerConsulta, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(lblDoctor, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox, 0, 268, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label))
					.addGap(18)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnVerConsulta)
					.addContainerGap())
		);
		frmDoctor.getContentPane().setLayout(groupLayout);
	}
}
