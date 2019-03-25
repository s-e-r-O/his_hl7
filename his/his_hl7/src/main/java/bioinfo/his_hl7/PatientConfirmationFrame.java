package bioinfo.his_hl7;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import upb.bio.models.Consultation;

@SuppressWarnings("serial")
public class PatientConfirmationFrame extends JFrame {

	private JPanel contentPane;
	private JList<Consultation> list;
	
	/**
	 * Create the frame.
	 */
	public PatientConfirmationFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblConfirmarLlegada = new JLabel("Registro de llegada");
		lblConfirmarLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmarLlegada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("Confirmar");
		
		JButton button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		DatePicker datePicker = new DatePicker();
		datePicker.setDateToToday();
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
									.addComponent(lblConfirmarLlegada, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
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
					.addComponent(lblConfirmarLlegada, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button))
					.addContainerGap())
		);
		
		list = new JList<Consultation>();
		scrollPane.setViewportView(list);
		contentPane.setLayout(gl_contentPane);
	}
}
