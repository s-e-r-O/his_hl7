package bioinfo.pis_hl7;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import upb.bio.models.Patient;

public class HistoryFrame extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> model;
	private JList list;
	/**
	 * Create the frame.
	 */
	public HistoryFrame(Patient p) {
		model = new DefaultListModel<String>();
		model.addElement("Test 1");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 443, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHistorialMedico = new JLabel("Historial medico");
		lblHistorialMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialMedico.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHistorialMedico.setBounds(12, 13, 411, 20);
		contentPane.add(lblHistorialMedico);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 40, 411, 9);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 61, 411, 157);
		contentPane.add(scrollPane);
		
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1) {
					JOptionPane.showMessageDialog(null, list.getSelectedValue().toString(), "Diagnostico", JOptionPane.INFORMATION_MESSAGE);					
				}
			}
		});
		btnVer.setBounds(12, 231, 411, 25);
		contentPane.add(btnVer);
	}
}
