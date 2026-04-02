package controller;

import java.sql.SQLException;
import java.util.ArrayList;

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
import modelo.dto.ClienteDTO;

public class Coordinador {
	private VentanaPrincipal ventanaPrincipal;
	private VentanaCliente ventanaCliente;
	private VentanaProducto ventanaProducto;
	private RegistroCliente registroCliente;
	private RegistroProducto registroProducto;
	private CompraProducto compraProducto;
	private GestionCompra gestionCompra;
	private Procesos misProcesos;
	private ClienteDAO miClienteDAO;
	private ProductoDAO miProductoDAO;
	private CompraDAO miCompraDAO;
	private Conexion miConexion;
	
	
	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		
	}


	public void setVentanaCliente(VentanaCliente ventanaCliente) {
		this.ventanaCliente = ventanaCliente;
		
	}


	public void setVentanaProducto(VentanaProducto ventanaProducto) {
		this.ventanaProducto = ventanaProducto;
		
	}


	public void setRegistroCliente(RegistroCliente registroCliente) {
		this.registroCliente = registroCliente;
		
	}


	public void setRegistroProducto(RegistroProducto registroProducto) {
		this.registroProducto = registroProducto;
		
	}


	public void setCompraProducto(CompraProducto compraProducto) {
		this.compraProducto = compraProducto;
		
	}


	public void setGestionCompra(GestionCompra gestionCompra) {
		this.gestionCompra = gestionCompra;
		
	}


	public void setMisProcesos(Procesos misProcesos) {
		this.misProcesos = misProcesos;
		
	}


	public void setMiClienteDAO(ClienteDAO miClienteDAO) {
		this.miClienteDAO = miClienteDAO;
		
	}


	public void setMiProductoDAO(ProductoDAO miProductoDAO) {
		this.miProductoDAO = miProductoDAO;
		
	}


	public void setMiCompraDAO(CompraDAO miCompraDAO) {
		this.miCompraDAO = miCompraDAO;
		
	}


	public void setMiConexion(Conexion miConexion) {
		this.miConexion = miConexion;
		
	}
	
	
	
	public String registrarEnBD(ClienteDTO cliente)  {
		try {
			return miClienteDAO.registarCliente(cliente);
		} catch (SQLException e) {
			return "error";
		}
	}
	
	
	
	public String actualizarCliente(ClienteDTO miCliente)  {
		try {
			return miClienteDAO.actualizarCliente(miCliente);
		} catch (SQLException e) {
			
			return "error"; 
		}
	}
	
	
	
	
	public String eliminarCliente(String doc)  {
		try {
			return miClienteDAO.eliminarCliente(doc);
		} catch (SQLException e) {
			
			return "error"; 
		}
	}
	
	
	
	public ArrayList<ClienteDTO> getListaClientes() {
		
		try {
			return miClienteDAO.consultarListaClientes();
		} catch (SQLException e) {
			return new ArrayList<ClienteDTO>();
		}
	}
	
	
}
