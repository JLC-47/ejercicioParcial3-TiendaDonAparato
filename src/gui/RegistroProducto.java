package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controller.Coordinador;
import modelo.dto.ProductoDTO;

public class RegistroProducto extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtId, txtNombre, txtPrecio, txtCantidad, txtDescripcion;
	private JButton btnRegistrar, btnCancelar;
	private Coordinador miCoordinador;
	private VentanaPrincipal miPadre;

	public RegistroProducto(VentanaPrincipal padre) {
		this.miPadre = padre;
		setTitle("Registro de Productos");
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(miPadre);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("REGISTRAR PRODUCTO", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setBounds(10, 20, 364, 30);
		contentPane.add(lblTitulo);

		JLabel lbl1 = new JLabel("ID Producto:");
		lbl1.setBounds(50, 80, 100, 25);
		contentPane.add(lbl1);
		txtId = new JTextField();
		txtId.setBounds(150, 80, 180, 25);
		contentPane.add(txtId);

		JLabel lbl2 = new JLabel("Nombre:");
		lbl2.setBounds(50, 130, 100, 25);
		contentPane.add(lbl2);
		txtNombre = new JTextField();
		txtNombre.setBounds(150, 130, 180, 25);
		contentPane.add(txtNombre);

		JLabel lbl3 = new JLabel("Precio:");
		lbl3.setBounds(50, 180, 100, 25);
		contentPane.add(lbl3);
		txtPrecio = new JTextField();
		txtPrecio.setBounds(150, 180, 180, 25);
		contentPane.add(txtPrecio);

		JLabel lbl4 = new JLabel("Cantidad:");
		lbl4.setBounds(50, 230, 100, 25);
		contentPane.add(lbl4);
		txtCantidad = new JTextField();
		txtCantidad.setBounds(150, 230, 180, 25);
		contentPane.add(txtCantidad);

		JLabel lbl5 = new JLabel("Descripción:");
		lbl5.setBounds(50, 280, 100, 25);
		contentPane.add(lbl5);
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(150, 280, 180, 25);
		contentPane.add(txtDescripcion);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(60, 350, 110, 35);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(200, 350, 110, 35);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
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
			int id = Integer.parseInt(txtId.getText());
			String nombre = txtNombre.getText();
			int cant = Integer.parseInt(txtCantidad.getText());
			double precio = Double.parseDouble(txtPrecio.getText());
			String desc = txtDescripcion.getText();

			ProductoDTO producto = new ProductoDTO(id, nombre, cant, precio, desc);
			String res = miCoordinador.registrarProducto(producto);

			if (res.equals("ok")) {
				JOptionPane.showMessageDialog(null, "Producto registrado con éxito");
				limpiar();
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo registrar el producto", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "ID, Cantidad y Precio deben ser numéricos", "Error de datos", JOptionPane.WARNING_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al procesar el registro");
		}
	}

	private void limpiar() {
		txtId.setText("");
		txtNombre.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
		txtDescripcion.setText("");
	}
}