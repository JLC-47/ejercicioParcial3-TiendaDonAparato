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
import modelo.dto.CompraDTO;
import modelo.dto.ProductoDTO;

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
	
	
	public void mostrarVentanaPrincipal() {
		ventanaPrincipal.setVisible(true);
	}

	public void mostrarRegistroCliente() {
		registroCliente.setVisible(true);
	}

	public void mostrarRegistroProducto() {
		registroProducto.setVisible(true);
	}

	public void mostrarConsultaCliente() {
		ventanaCliente.setVisible(true);
	}

	public void mostrarConsultaProducto() {
		ventanaProducto.setVisible(true);
	}

	public void mostrarCompraProducto() {
		compraProducto.setVisible(true);
	}

	public void mostrarGestionCompra() {
		gestionCompra.setVisible(true);
	}
	
	
	
	public String registarCliente(ClienteDTO cliente) {
	    try {
	        if (miClienteDAO == null) {
	            return "Error: DAO no inicializado";
	        }
	        return miClienteDAO.registarCliente(cliente);
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return "error";
	    }
	}	
	
	
	
	public ClienteDTO consultarCliente(String doc) {
	    try {
	        return miClienteDAO.consultarCliente(doc);
	    } catch (SQLException e) {
	        return null;
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

	
	public String registrarProducto(ProductoDTO producto) {
	    try {
	        if (miProductoDAO == null) {
	            return "Error: DAO de producto no inicializado";
	        }
	        return miProductoDAO.registrarProducto(producto);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
	
	
	public ProductoDTO consultarProducto(int id) {
		try {
			return miProductoDAO.consultarProducto(id);
		} catch (SQLException e) {
			return null;
		}
	}

	
	public String actualizarProducto(ProductoDTO producto) {
		try {
			return miProductoDAO.actualizarProducto(producto);
		} catch (SQLException e) {
			return "error";
		}
	}
	
	
	public String eliminarProducto(int id) {
		try {
			return miProductoDAO.eliminarProducto(id);
		} catch (SQLException e) {
			return "error";
		}
	}
	
	
	public ArrayList<ProductoDTO> getListaProductos() {
		try {
			return miProductoDAO.consultarListaProductos();
		} catch (SQLException e) {
			return new ArrayList<ProductoDTO>();
		}
	}
	

	public CompraDTO calcularDescuento(ClienteDTO cliente, ProductoDTO producto, int cantidad) {
	    return misProcesos.calcularDescuento(cliente, producto, cantidad);
	}
	
	
	public String registrarCompra(CompraDTO compra, String documentoCliente, int idProducto) {
	    try {
	        return miCompraDAO.registrarCompra(compra, documentoCliente, idProducto);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
	
	
	public ArrayList<CompraDTO> consultarListaCompras() {
	    try {
	        return miCompraDAO.consultarListaCompras();
	    } catch (SQLException e) {
	        return new ArrayList<CompraDTO>();
	    }
	}
	
	
	public String eliminarCompra(int id) {
	    try {
	        return miCompraDAO.eliminarCompra(id);
	    } catch (SQLException e) {
	        return "error";
	    }
	}
	
}
