package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import modelo.dto.ProductoDTO;

public class VentanaProducto extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtId, txtNombre, txtPrecio, txtCantidad, txtDescripcion;
	private JButton btnConsultar, btnActualizar, btnEliminar, btnListar, btnLimpiar;
	private JTable tablaProductos;
	private DefaultTableModel modelo;
	private Coordinador miCoordinador;
	private VentanaPrincipal miPadre;

	public VentanaProducto(VentanaPrincipal padre) {
		this.miPadre = padre;
		setTitle("Gestión de Productos - Don Aparato");
		setSize(850, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(miPadre);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("GESTIÓN DE PRODUCTOS", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitulo.setBounds(10, 10, 814, 30);
		contentPane.add(lblTitulo);

		JLabel lbl1 = new JLabel("ID Producto:");
		lbl1.setBounds(30, 60, 100, 25);
		contentPane.add(lbl1);
		txtId = new JTextField();
		txtId.setBounds(110, 60, 150, 25);
		contentPane.add(txtId);

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

		JLabel lbl3 = new JLabel("Precio:");
		lbl3.setBounds(30, 140, 100, 25);
		contentPane.add(lbl3);
		txtPrecio = new JTextField();
		txtPrecio.setBounds(110, 140, 240, 25);
		contentPane.add(txtPrecio);

		JLabel lbl4 = new JLabel("Cantidad:");
		lbl4.setBounds(30, 180, 100, 25);
		contentPane.add(lbl4);
		txtCantidad = new JTextField();
		txtCantidad.setBounds(110, 180, 240, 25);
		contentPane.add(txtCantidad);

		JLabel lbl5 = new JLabel("Descripción:");
		lbl5.setBounds(30, 220, 100, 25);
		contentPane.add(lbl5);
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(110, 220, 240, 25);
		contentPane.add(txtDescripcion);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(30, 280, 150, 30);
		btnActualizar.addActionListener(this);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(200, 280, 150, 30);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

		btnListar = new JButton("Cargar Inventario");
		btnListar.setBounds(30, 320, 320, 30);
		btnListar.addActionListener(this);
		contentPane.add(btnListar);

		btnLimpiar = new JButton("Limpiar Campos");
		btnLimpiar.setBounds(30, 360, 320, 30);
		btnLimpiar.addActionListener(this);
		contentPane.add(btnLimpiar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(380, 60, 430, 330);
		contentPane.add(scrollPane);

		tablaProductos = new JTable();
		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new String[] { "ID", "Nombre", "Precio", "Stock" });
		tablaProductos.setModel(modelo);
		scrollPane.setViewportView(tablaProductos);

		tablaProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaProductos.getSelectedRow();
				if (fila != -1) {
					consultarIndividual(Integer.parseInt(modelo.getValueAt(fila, 0).toString()));
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
			consultarIndividual(Integer.parseInt(txtId.getText()));
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

	private void consultarIndividual(int id) {
		try {
			ProductoDTO producto = miCoordinador.consultarProducto(id);
			if (producto != null) {
				txtId.setText(String.valueOf(producto.getIdProducto()));
				txtNombre.setText(producto.getNombre());
				txtPrecio.setText(String.valueOf(producto.getPrecio()));
				txtCantidad.setText(String.valueOf(producto.getCantidad()));
				txtDescripcion.setText(producto.getDescripcion());
			} else {
				JOptionPane.showMessageDialog(null, "Producto no encontrado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al buscar");
		}
	}

	private void llenarTabla() {
		ArrayList<ProductoDTO> lista = miCoordinador.getListaProductos();
		modelo.setRowCount(0);
		for (ProductoDTO p : lista) {
			modelo.addRow(new Object[] { p.getIdProducto(), p.getNombre(), p.getPrecio(), p.getCantidad() });
		}
	}

	private void actualizar() {
		try {
			ProductoDTO p = new ProductoDTO(
				Integer.parseInt(txtId.getText()),
				txtNombre.getText(),
				Integer.parseInt(txtCantidad.getText()),
				Double.parseDouble(txtPrecio.getText()),
				txtDescripcion.getText()
			);

			String res = miCoordinador.actualizarProducto(p);
			if (res.equals("ok")) {
				JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
				llenarTabla();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en los datos del producto");
		}
	}

	private void eliminar() {
		int resp = JOptionPane.showConfirmDialog(null, "¿Eliminar producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
		if (resp == JOptionPane.YES_OPTION) {
			String res = miCoordinador.eliminarProducto(Integer.parseInt(txtId.getText()));
			if (res.equals("ok")) {
				JOptionPane.showMessageDialog(null, "Producto eliminado");
				limpiar();
				llenarTabla();
			}
		}
	}

	private void limpiar() {
		txtId.setText("");
		txtNombre.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
		txtDescripcion.setText("");
		modelo.setRowCount(0);
	}
}