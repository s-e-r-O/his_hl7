package bioinfo.pis_hl7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class VisitFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VisitFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblConsulta = new JLabel("Consulta");
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblFechaYHora = new JLabel("Fecha y hora:");
		
		JLabel lbldate = new JLabel("DD/MM/YYYY hh:mm:ss");
		
		JLabel lblPaciente = new JLabel("Paciente:");
		
		JLabel lblName = new JLabel("name");
		
		JButton btnNewButton = new JButton("Ver historial");
		
		JButton btnIngresarDiagnostico = new JButton("Ingresar diagnostico");
		
		JButton btnSolicitarMedicamentos = new JButton("Solicitar medicamentos");
		
		JButton btnPedirLaboratorio = new JButton("Pedir laboratorio");
		
		JButton btnPedirRadiografia = new JButton("Pedir radiografia");
		
		JButton btnDarDeAlta = new JButton("Dar de alta");
		
		JButton btnFinalizarLaConsulta = new JButton("Finalizar la consulta");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addComponent(lblConsulta, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFechaYHora, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbldate, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPaciente, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.addComponent(btnIngresarDiagnostico, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSolicitarMedicamentos, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPedirLaboratorio, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPedirRadiografia, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDarDeAlta, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFinalizarLaConsulta, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
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
					.addGap(18)
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
					.addContainerGap(81, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
