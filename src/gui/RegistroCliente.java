package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.dto.ClienteDTO;

public class RegistroCliente extends VentanaPrincipal implements ActionListener {

	private JPanel contentPane;
	private JTextField txtDoc, txtNombre, txtApellido, txtEdad, txtTelefono;
	private JComboBox<String> comboTipo;
	private JButton btnRegistrar, btnCancelar;

	public RegistroCliente(VentanaPrincipal ventanaPrincipal) {
		setTitle("Don Aparato - Registro de Clientes");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(450, 500);
		setLocationRelativeTo(ventanaPrincipal);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("REGISTRAR NUEVO CLIENTE", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setBounds(10, 20, 414, 30);
		contentPane.add(lblTitulo);

		JLabel lbl1 = new JLabel("Documento:");
		lbl1.setBounds(50, 80, 100, 25);
		contentPane.add(lbl1);

		txtDoc = new JTextField();
		txtDoc.setBounds(160, 80, 200, 25);
		contentPane.add(txtDoc);

		JLabel lbl2 = new JLabel("Nombre:");
		lbl2.setBounds(50, 120, 100, 25);
		contentPane.add(lbl2);

		txtNombre = new JTextField();
		txtNombre.setBounds(160, 120, 200, 25);
		contentPane.add(txtNombre);

		JLabel lbl3 = new JLabel("Apellido:");
		lbl3.setBounds(50, 160, 100, 25);
		contentPane.add(lbl3);

		txtApellido = new JTextField();
		txtApellido.setBounds(160, 160, 200, 25);
		contentPane.add(txtApellido);

		JLabel lbl4 = new JLabel("Edad:");
		lbl4.setBounds(50, 200, 100, 25);
		contentPane.add(lbl4);

		txtEdad = new JTextField();
		txtEdad.setBounds(160, 200, 200, 25);
		contentPane.add(txtEdad);

		JLabel lbl5 = new JLabel("Tipo (A, B, C):");
		lbl5.setBounds(50, 240, 100, 25);
		contentPane.add(lbl5);

		comboTipo = new JComboBox<>(new String[] { "A", "B", "C", "Ninguno" });
		comboTipo.setBounds(160, 240, 200, 25);
		contentPane.add(comboTipo);

		JLabel lbl6 = new JLabel("Teléfono:");
		lbl6.setBounds(50, 280, 100, 25);
		contentPane.add(lbl6);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(160, 280, 200, 25);
		contentPane.add(txtTelefono);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(90, 360, 120, 35);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(230, 360, 120, 35);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			guardar();
		} else if (e.getSource() == btnCancelar) {
			this.dispose();
		}
	}

	private void guardar() {
		try {
			ClienteDTO cliente = new ClienteDTO();
			cliente.setDocumento(txtDoc.getText());
			cliente.setNombre(txtNombre.getText());
			cliente.setApellido(txtApellido.getText());
			cliente.setEdad(Integer.parseInt(txtEdad.getText()));
			cliente.setTipo(comboTipo.getSelectedItem().toString());
			cliente.setTelefono(txtTelefono.getText());

			String respuesta = getCoordinador().registarCliente(cliente);

			if (respuesta != null && respuesta.equals("ok")) {
				JOptionPane.showMessageDialog(null, "Cliente registrado con éxito");
				limpiar();
			} else {
				JOptionPane.showMessageDialog(null, "Error al registrar", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Verifique los datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiar() {
		txtDoc.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEdad.setText("");
		txtTelefono.setText("");
		comboTipo.setSelectedIndex(0);
	}
}