package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
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
import modelo.dto.CompraDTO;

public class GestionCompra extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtIdEliminar;
	private JButton btnEliminar, btnListar, btnLimpiar;
	private JTable tablaCompras;
	private DefaultTableModel modelo;
	private Coordinador miCoordinador;
	private VentanaPrincipal miPadre;

	public GestionCompra(VentanaPrincipal padre) {
		this.miPadre = padre;
		setTitle("Don Aparato - Gestión de Compras");
		setSize(700, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(miPadre);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("GESTIÓN Y CONSULTA DE COMPRAS", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setBounds(10, 10, 664, 30);
		contentPane.add(lblTitulo);

		JLabel lbl1 = new JLabel("ID Compra a eliminar:");
		lbl1.setBounds(30, 60, 150, 25);
		contentPane.add(lbl1);

		txtIdEliminar = new JTextField();
		txtIdEliminar.setBounds(180, 60, 100, 25);
		contentPane.add(txtIdEliminar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(290, 60, 100, 25);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

		btnListar = new JButton("Cargar Historial");
		btnListar.setBounds(450, 60, 180, 25);
		btnListar.addActionListener(this);
		contentPane.add(btnListar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 110, 620, 230);
		contentPane.add(scrollPane);

		tablaCompras = new JTable();
		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new String[] { "ID", "Detalle", "Descuento", "Subtotal", "Total" });
		tablaCompras.setModel(modelo);
		scrollPane.setViewportView(tablaCompras);

		btnLimpiar = new JButton("Limpiar Tabla");
		btnLimpiar.setBounds(250, 360, 180, 30);
		btnLimpiar.addActionListener(this);
		contentPane.add(btnLimpiar);
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnListar) {
			llenarTabla();
		} else if (e.getSource() == btnEliminar) {
			eliminar();
		} else if (e.getSource() == btnLimpiar) {
			limpiar();
		}
	}

	private void llenarTabla() {
		try {
			ArrayList<CompraDTO> lista = miCoordinador.consultarListaCompras();
			modelo.setRowCount(0);
			
			for (CompraDTO c : lista) {
				Object[] fila = {
					c.getIdCompra(),
					c.getDetalleCompra(),
					c.getDescuento(),
					c.getPrecioCompra(),
					c.getTotal()
				};
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar los datos");
		}
	}

	private void eliminar() {
		try {
			int id = Integer.parseInt(txtIdEliminar.getText());
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la compra " + id + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
			
			if (respuesta == JOptionPane.YES_OPTION) {
				String res = miCoordinador.eliminarCompra(id);
				if (res.equals("ok")) {
					JOptionPane.showMessageDialog(null, "Eliminado correctamente");
					llenarTabla();
					txtIdEliminar.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar");
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Debe ingresar un ID numérico");
		}
	}

	private void limpiar() {
		modelo.setRowCount(0);
		txtIdEliminar.setText("");
	}
}