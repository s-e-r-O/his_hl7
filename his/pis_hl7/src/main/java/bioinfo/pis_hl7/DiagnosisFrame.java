package bioinfo.pis_hl7;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bioinfo.pis_hl7.requests.OrderManager;
import upb.bio.models.Consultation;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JEditorPane;

public class DiagnosisFrame extends JFrame {
	
	JEditorPane editorPane;
	private JButton button_1;
	private DiagnosisManager manager;
	/**
	 * Create the frame.
	 */
	public DiagnosisFrame(Consultation c) {
		manager = new DiagnosisManager(c);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 445, 305);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico");
		lblDiagnostico.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiagnostico.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiagnostico.setBounds(12, 13, 411, 20);
		getContentPane().add(lblDiagnostico);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 40, 411, 9);
		getContentPane().add(separator);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(12, 56, 411, 158);
		if (c.getDiagnosis()!= null) {
			editorPane.setText(c.getDiagnosis());
		}
		getContentPane().add(editorPane);
		editorPane.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    	trigger();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  	trigger();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  	trigger();
			  }

			  public void trigger() {
				  button_1.setEnabled(!editorPane.getText().trim().equals(""));
			  }
		});
		
		JButton button = new JButton("Cancelar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(210, 227, 212, 25);
		getContentPane().add(button);
		
		button_1 = new JButton("Confirmar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!editorPane.getText().trim().equals("")) {
					manager.updateDiagnosis(editorPane.getText().trim());
					dispose();					
				}
			}
		});
		button_1.setEnabled(false);
		button_1.setBounds(12, 227, 186, 25);
		getContentPane().add(button_1);
		
	}
}
