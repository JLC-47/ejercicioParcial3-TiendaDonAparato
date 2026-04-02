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
	
	public Relaciones (){
		
		// Se instancia las clases
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		VentanaCliente ventanaCliente = new VentanaCliente();
		VentanaProducto ventanaProducto = new VentanaProducto();
		RegistroCliente registroCliente = new RegistroCliente();
		RegistroProducto registroProducto = new RegistroProducto();
		CompraProducto compraProducto = new CompraProducto();
		GestionCompra gestionCompra = new GestionCompra();
		Procesos misProcesos = new Procesos();
		ClienteDAO miClienteDAO = new ClienteDAO();
		ProductoDAO miProductoDAO = new ProductoDAO();
		CompraDAO miCompraDAO = new CompraDAO();
		Conexion miConexion = new Conexion();
		Coordinador miCoordinador = new Coordinador();
		
		
		// Se establecen las relaciones entre clases
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
		
		
		
		
		// Se establecen relaciones con la clase coordinador
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
	
}
