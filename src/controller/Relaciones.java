package controller;

import conexion.Conexion;
import gui.CompraProducto;
import gui.GestionCompra;
import gui.RegistroCliente;
import gui.RegistroProducto;
import gui.VentanaCliente;
import gui.VentanaPrincipal;
import gui.VentanaProducto;
import modelo.Procesos;
import modelo.dao.ClienteDAO;
import modelo.dao.CompraDAO;
import modelo.dao.ProductoDAO;

public class Relaciones {
	private Coordinador miCoordinador;
	
	public Relaciones (){
		miCoordinador = new Coordinador();
		
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		VentanaCliente ventanaCliente = new VentanaCliente(ventanaPrincipal);
		VentanaProducto ventanaProducto = new VentanaProducto(ventanaPrincipal);
		RegistroCliente registroCliente = new RegistroCliente(ventanaPrincipal);
		RegistroProducto registroProducto = new RegistroProducto(ventanaPrincipal);
		CompraProducto compraProducto = new CompraProducto(ventanaPrincipal);
		GestionCompra gestionCompra = new GestionCompra(ventanaPrincipal);
		
		Procesos misProcesos = new Procesos();
		ClienteDAO miClienteDAO = new ClienteDAO();
		ProductoDAO miProductoDAO = new ProductoDAO();
		CompraDAO miCompraDAO = new CompraDAO();
		Conexion miConexion = new Conexion();
		
		ventanaPrincipal.setCoordinador(miCoordinador);
		ventanaCliente.setCoordinador(miCoordinador);
		ventanaProducto.setCoordinador(miCoordinador);
		registroCliente.setCoordinador(miCoordinador);
		registroProducto.setCoordinador(miCoordinador);
		compraProducto.setCoordinador(miCoordinador);
		gestionCompra.setCoordinador(miCoordinador);
		misProcesos.setCoordinador(miCoordinador);
		miClienteDAO.setCoordinador(miCoordinador);
		miProductoDAO.setCoordinador(miCoordinador);
		miCompraDAO.setCoordinador(miCoordinador);
		miConexion.setCoordinador(miCoordinador);
		
		miCoordinador.setVentanaPrincipal(ventanaPrincipal);
		miCoordinador.setVentanaCliente(ventanaCliente);
		miCoordinador.setVentanaProducto(ventanaProducto);
		miCoordinador.setRegistroCliente(registroCliente);
		miCoordinador.setRegistroProducto(registroProducto);
		miCoordinador.setCompraProducto(compraProducto);
		miCoordinador.setGestionCompra(gestionCompra);
		miCoordinador.setMisProcesos(misProcesos);
		miCoordinador.setMiClienteDAO(miClienteDAO);
		miCoordinador.setMiProductoDAO(miProductoDAO);
		miCoordinador.setMiCompraDAO(miCompraDAO);
		miCoordinador.setMiConexion(miConexion);
	}
	
	public void iniciar() {
		miCoordinador.mostrarVentanaPrincipal();
	}
}