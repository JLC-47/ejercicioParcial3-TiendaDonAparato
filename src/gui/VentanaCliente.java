package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controller.Coordinador;
import modelo.dto.ClienteDTO;

public class VentanaCliente extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtDoc, txtNombre, txtApellido, txtEdad, txtTelefono;
	private JComboBox<String> comboTipo;
	private JButton btnConsultar, btnActualizar, btnEliminar, btnListar, btnLimpiar;
	private JTable tablaClientes;
	private DefaultTableModel modelo;
	private Coordinador miCoordinador;
	private VentanaPrincipal miPadre;

	public VentanaCliente(VentanaPrincipal padre) {
		this.miPadre = padre;
		setTitle("Gestión de Clientes - Don Aparato");
		setSize(850, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(miPadre);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("GESTIÓN DE CLIENTES", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitulo.setBounds(10, 10, 814, 30);
		contentPane.add(lblTitulo);

		JLabel lbl1 = new JLabel("Documento:");
		lbl1.setBounds(30, 60, 100, 25);
		contentPane.add(lbl1);
		txtDoc = new JTextField();
		txtDoc.setBounds(110, 60, 150, 25);
		contentPane.add(txtDoc);

		btnConsultar = new JButton("Buscar");
		btnConsultar.setBounds(270, 60, 80, 25);
		btnConsultar.addActionListener(this);
		contentPane.add(btnConsultar);

		JLabel lbl2 = new JLabel("Nombre:");
		lbl2.setBounds(30, 100, 100, 25);
		contentPane.add(lbl2);
		txtNombre = new JTextField();
		txtNombre.setBounds(110, 100, 240, 25);
		contentPane.add(txtNombre);

		JLabel lbl3 = new JLabel("Apellido:");
		lbl3.setBounds(30, 140, 100, 25);
		contentPane.add(lbl3);
		txtApellido = new JTextField();
		txtApellido.setBounds(110, 140, 240, 25);
		contentPane.add(txtApellido);

		JLabel lbl4 = new JLabel("Edad:");
		lbl4.setBounds(30, 180, 50, 25);
		contentPane.add(lbl4);
		txtEdad = new JTextField();
		txtEdad.setBounds(110, 180, 60, 25);
		contentPane.add(txtEdad);

		JLabel lbl5 = new JLabel("Tipo:");
		lbl5.setBounds(190, 180, 50, 25);
		contentPane.add(lbl5);
		comboTipo = new JComboBox<>(new String[] { "A", "B", "C" });
		comboTipo.setBounds(230, 180, 120, 25);
		contentPane.add(comboTipo);

		JLabel lbl6 = new JLabel("Teléfono:");
		lbl6.setBounds(30, 220, 100, 25);
		contentPane.add(lbl6);
		txtTelefono = new JTextField();
		txtTelefono.setBounds(110, 220, 240, 25);
		contentPane.add(txtTelefono);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(30, 280, 150, 30);
		btnActualizar.addActionListener(this);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(200, 280, 150, 30);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

		btnListar = new JButton("Cargar Tabla");
		btnListar.setBounds(30, 320, 320, 30);
		btnListar.addActionListener(this);
		contentPane.add(btnListar);

		btnLimpiar = new JButton("Limpiar Pantalla");
		btnLimpiar.setBounds(30, 360, 320, 30);
		btnLimpiar.addActionListener(this);
		contentPane.add(btnLimpiar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(380, 60, 430, 330);
		contentPane.add(scrollPane);

		tablaClientes = new JTable();
		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new String[] { "Documento", "Nombre", "Apellido", "Tipo" });
		tablaClientes.setModel(modelo);
		scrollPane.setViewportView(tablaClientes);

		tablaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaClientes.getSelectedRow();
				if (fila != -1) {
					consultarIndividual(modelo.getValueAt(fila, 0).toString());
				}
			}
		});
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConsultar) {
			consultarIndividual(txtDoc.getText());
		} else if (e.getSource() == btnActualizar) {
			actualizar();
		} else if (e.getSource() == btnEliminar) {
			eliminar();
		} else if (e.getSource() == btnListar) {
			llenarTabla();
		} else if (e.getSource() == btnLimpiar) {
			limpiar();
		}
	}

	private void consultarIndividual(String doc) {
		try {
			ClienteDTO cliente = miCoordinador.consultarCliente(doc);
			if (cliente != null) {
				txtDoc.setText(cliente.getDocumento());
				txtNombre.setText(cliente.getNombre());
				txtApellido.setText(cliente.getApellido());
				txtEdad.setText(String.valueOf(cliente.getEdad()));
				txtTelefono.setText(cliente.getTelefono());
				comboTipo.setSelectedItem(cliente.getTipo());
			} else {
				JOptionPane.showMessageDialog(null, "No existe el cliente");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al buscar");
		}
	}

	private void llenarTabla() {
		ArrayList<ClienteDTO> lista = miCoordinador.getListaClientes();
		modelo.setRowCount(0);
		for (ClienteDTO c : lista) {
			modelo.addRow(new Object[] { c.getDocumento(), c.getNombre(), c.getApellido(), c.getTipo() });
		}
	}

	private void actualizar() {
		try {
			ClienteDTO cliente = new ClienteDTO();
			cliente.setDocumento(txtDoc.getText());
			cliente.setNombre(txtNombre.getText());
			cliente.setApellido(txtApellido.getText());
			cliente.setEdad(Integer.parseInt(txtEdad.getText()));
			cliente.setTipo(comboTipo.getSelectedItem().toString());
			cliente.setTelefono(txtTelefono.getText());

			String res = miCoordinador.actualizarCliente(cliente);
			if (res.equals("ok")) {
				JOptionPane.showMessageDialog(null, "Cliente actualizado");
				llenarTabla();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Verifique los datos");
		}
	}

	private void eliminar() {
		int resp = JOptionPane.showConfirmDialog(null, "¿Desea eliminar?", "Alerta", JOptionPane.YES_NO_OPTION);
		if (resp == JOptionPane.YES_OPTION) {
			String res = miCoordinador.eliminarCliente(txtDoc.getText());
			if (res.equals("ok")) {
				JOptionPane.showMessageDialog(null, "Borrado exitoso");
				limpiar();
				llenarTabla();
			}
		}
	}

	private void limpiar() {
		txtDoc.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEdad.setText("");
		txtTelefono.setText("");
		comboTipo.setSelectedIndex(0);
		modelo.setRowCount(0);
	}
}