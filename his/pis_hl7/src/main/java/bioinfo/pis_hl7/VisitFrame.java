package bioinfo.pis_hl7;

import java.awt.Font;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import upb.bio.models.Consultation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VisitFrame extends JFrame {

	private JPanel contentPane;
	private VisitManager manager;
	/**
	 * Create the frame.
	 */
	public VisitFrame(Consultation c) {
		setResizable(false);
		this.manager = new VisitManager(c);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblConsulta = new JLabel("Consulta");
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblFechaYHora = new JLabel("Fecha y hora:");
		LocalDateTime ldt = LocalDateTime.ofInstant(manager.getConsultation().getConsultationDate().toInstant(), ZoneId.systemDefault());
		JLabel lbldate = new JLabel(ldt.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", Locale.ENGLISH)));
		
		JLabel lblPaciente = new JLabel("Paciente:");
		
		JLabel lblName = new JLabel(manager.getConsultation().getPatient().getFullName());
		
		JButton btnNewButton = new JButton("Ver historial");
		
		JButton btnIngresarDiagnostico = new JButton("Ingresar diagnostico");
		
		JButton btnSolicitarMedicamentos = new JButton("Solicitar medicamentos");
		
		JButton btnPedirLaboratorio = new JButton("Pedir laboratorio");
		
		JButton btnPedirRadiografia = new JButton("Pedir radiografia");
		btnPedirRadiografia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HL7.sendO01Message(manager.getConsultation().getPatient(), new String[] { "algo", "mas"});
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton btnDarDeAlta = new JButton("Dar de alta");
		
		JButton btnFinalizarLaConsulta = new JButton("Finalizar la consulta");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(lblConsulta, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFechaYHora, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbldate, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPaciente, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(btnIngresarDiagnostico, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(btnSolicitarMedicamentos, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(btnPedirLaboratorio, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(btnPedirRadiografia, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(btnDarDeAlta, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(btnFinalizarLaConsulta, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblConsulta, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFechaYHora)
						.addComponent(lbldate))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPaciente)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnIngresarDiagnostico)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSolicitarMedicamentos)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPedirLaboratorio)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPedirRadiografia)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDarDeAlta)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnFinalizarLaConsulta)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
