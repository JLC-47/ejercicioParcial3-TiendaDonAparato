package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import controller.Coordinador;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private Coordinador miCoordinador;
	private JPanel contentPane;
	private JPanel panelBotones;
	private JLabel lblTitulo;
	private JButton btnRegistroCliente, btnRegistroProducto, btnVerClientes, btnVerProductos, btnCompra, btnGestion;

	public VentanaPrincipal() {
		setTitle("Sistema Don Aparato - Menú Principal");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(10, 20));
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		lblTitulo = new JLabel("Panel de Control - Don Aparato", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
		contentPane.add(lblTitulo, BorderLayout.NORTH);

		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(3, 2, 15, 15));
		contentPane.add(panelBotones, BorderLayout.CENTER);

		btnRegistroCliente = new JButton("Registrar Cliente");
		btnRegistroProducto = new JButton("Registrar Producto");
		btnVerClientes = new JButton("Gestión Clientes");
		btnVerProductos = new JButton("Gestión Productos");
		btnCompra = new JButton("Módulo de Compras");
		btnGestion = new JButton("Historial / Reportes");

		btnRegistroCliente.addActionListener(this);
		btnRegistroProducto.addActionListener(this);
		btnVerClientes.addActionListener(this);
		btnVerProductos.addActionListener(this);
		btnCompra.addActionListener(this);
		btnGestion.addActionListener(this);

		panelBotones.add(btnRegistroCliente);
		panelBotones.add(btnRegistroProducto);
		panelBotones.add(btnVerClientes);
		panelBotones.add(btnVerProductos);
		panelBotones.add(btnCompra);
		panelBotones.add(btnGestion);
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public Coordinador getCoordinador() {
		return miCoordinador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistroCliente) {
			miCoordinador.mostrarRegistroCliente();
		} else if (e.getSource() == btnRegistroProducto) {
			miCoordinador.mostrarRegistroProducto();
		} else if (e.getSource() == btnVerClientes) {
			miCoordinador.mostrarConsultaCliente();
		} else if (e.getSource() == btnVerProductos) {
			miCoordinador.mostrarConsultaProducto();
		} else if (e.getSource() == btnCompra) {
			miCoordinador.mostrarCompraProducto();
		} else if (e.getSource() == btnGestion) {
			miCoordinador.mostrarGestionCompra();
		}
	}
}