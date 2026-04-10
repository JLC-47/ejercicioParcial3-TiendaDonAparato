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
import modelo.dto.ClienteDTO;
import modelo.dto.CompraDTO;
import modelo.dto.ProductoDTO;

public class CompraProducto extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtDocCliente, txtIdProducto, txtCantidad, txtPrecio, txtDescuento, txtTotal;
	private JButton btnRegistrar, btnCancelar, btnCalcular;
	private Coordinador miCoordinador;
	private VentanaPrincipal miPadre;
	private CompraDTO compraActual;

	public CompraProducto(VentanaPrincipal padre) {
		this.miPadre = padre;
		setTitle("Don Aparato - Registro de Compra");
		setSize(450, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(miPadre);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("REGISTRAR COMPRA", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitulo.setBounds(10, 20, 414, 30);
		contentPane.add(lblTitulo);

		JLabel lbl1 = new JLabel("Doc. Cliente:");
		lbl1.setBounds(50, 80, 100, 25);
		contentPane.add(lbl1);
		txtDocCliente = new JTextField();
		txtDocCliente.setBounds(160, 80, 200, 25);
		contentPane.add(txtDocCliente);

		JLabel lbl2 = new JLabel("ID Producto:");
		lbl2.setBounds(50, 130, 100, 25);
		contentPane.add(lbl2);
		txtIdProducto = new JTextField();
		txtIdProducto.setBounds(160, 130, 200, 25);
		contentPane.add(txtIdProducto);

		JLabel lblCant = new JLabel("Cantidad:");
		lblCant.setBounds(50, 180, 100, 25);
		contentPane.add(lblCant);
		txtCantidad = new JTextField();
		txtCantidad.setBounds(160, 180, 200, 25);
		contentPane.add(txtCantidad);

		JLabel lbl4 = new JLabel("Precio Base:");
		lbl4.setBounds(50, 230, 100, 25);
		contentPane.add(lbl4);
		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(160, 230, 200, 25);
		contentPane.add(txtPrecio);

		JLabel lbl5 = new JLabel("Descuento:");
		lbl5.setBounds(50, 280, 100, 25);
		contentPane.add(lbl5);
		txtDescuento = new JTextField();
		txtDescuento.setEditable(false);
		txtDescuento.setBounds(160, 280, 200, 25);
		contentPane.add(txtDescuento);

		JLabel lbl6 = new JLabel("Total:");
		lbl6.setBounds(50, 330, 100, 25);
		contentPane.add(lbl6);
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(160, 330, 200, 25);
		contentPane.add(txtTotal);

		btnCalcular = new JButton("Validar y Calcular");
		btnCalcular.setBounds(130, 380, 200, 30);
		btnCalcular.addActionListener(this);
		contentPane.add(btnCalcular);

		btnRegistrar = new JButton("Registrar Compra");
		btnRegistrar.setBounds(80, 450, 140, 35);
		btnRegistrar.addActionListener(this);
		btnRegistrar.setEnabled(false);
		contentPane.add(btnRegistrar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(230, 450, 120, 35);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCalcular) {
			procesarCalculo();
		} else if (e.getSource() == btnRegistrar) {
			registrar();
		} else if (e.getSource() == btnCancelar) {
			this.dispose();
		}
	}

	private void procesarCalculo() {
		try {
			String doc = txtDocCliente.getText();
			int idProd = Integer.parseInt(txtIdProducto.getText());
			int cant = Integer.parseInt(txtCantidad.getText());

			ClienteDTO cliente = miCoordinador.consultarCliente(doc);
			ProductoDTO producto = miCoordinador.consultarProducto(idProd);

			if (cliente != null && producto != null) {
				compraActual = miCoordinador.calcularDescuento(cliente, producto, cant);
				
				txtPrecio.setText(String.valueOf(compraActual.getPrecioCompra()));
				txtDescuento.setText(String.valueOf(compraActual.getDescuento()));
				txtTotal.setText(String.valueOf(compraActual.getTotal()));
				
				btnRegistrar.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Cliente o Producto no existen en el sistema");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Revise los campos numéricos");
		}
	}

	private void registrar() {
		try {
			String doc = txtDocCliente.getText();
			int idProd = Integer.parseInt(txtIdProducto.getText());
			
			String res = miCoordinador.registrarCompra(compraActual, doc, idProd);

			if (res.equals("ok")) {
				JOptionPane.showMessageDialog(null, "La compra se registró correctamente");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Error al guardar");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error en el proceso");
		}
	}
}