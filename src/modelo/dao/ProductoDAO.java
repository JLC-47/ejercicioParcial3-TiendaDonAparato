package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexion.Conexion;
import controller.Coordinador;
import modelo.dto.ProductoDTO;

public class ProductoDAO {
	private Coordinador miCoordinador;
	
	Connection connection = null;
	Conexion conexion = new Conexion();
	PreparedStatement preStatement = null;

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	
	private String conectar() {
		conexion = new Conexion();
		String resultado = conexion.conectar();
		if (resultado.equals("conectado")) {
			connection = conexion.getConnection();
		} else {
			JOptionPane.showMessageDialog(null, resultado, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return resultado;
	}

	public String registrarProducto(ProductoDTO producto) throws SQLException {
		String resultado = "";
		if (!conectar().equals("conectado")) {
			
			return "error";
		}

		String consulta = "INSERT INTO producto (idProducto, nombre, cantidad, precio, descripcion) VALUES (?,?,?,?,?)";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setInt(1, producto.getIdProducto());
			preStatement.setString(2, producto.getNombre());
			preStatement.setInt(3, producto.getCantidad());
			preStatement.setDouble(4, producto.getPrecio());
			preStatement.setString(5, producto.getDescripcion());
			preStatement.execute();
			resultado = "ok";
		} catch (SQLException e) {
			System.out.println("No se pudo registrar el producto: " + e.getMessage());
			resultado = "No se pudo registrar el producto";
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return resultado;
	}

	public ProductoDTO consultarProducto(int id) throws SQLException {
		ProductoDTO productoDTO = null;
		if (!conectar().equals("conectado")) {
			return null;
		}

		ResultSet result = null;
		String consulta = "SELECT idProducto, nombre, cantidad, precio, descripcion FROM producto WHERE idProducto = ?";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setInt(1, id);
			result = preStatement.executeQuery();

			if (result.next()) {
				productoDTO = new ProductoDTO(
					result.getInt("idProducto"),
					result.getString("nombre"),
					result.getInt("cantidad"),
					result.getDouble("precio"),
					result.getString("descripcion")
				);
			}
		} finally {
			if (result != null) result.close();
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return productoDTO;
	}

	public String actualizarProducto(ProductoDTO producto) throws SQLException {
		String resultado = "";
		if (!conectar().equals("conectado")) {
			
			return "error";
		}

		try {
			String consulta = "UPDATE producto SET nombre=?, cantidad=?, precio=?, descripcion=? WHERE idProducto=?";
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, producto.getNombre());
			preStatement.setInt(2, producto.getCantidad());
			preStatement.setDouble(3, producto.getPrecio());
			preStatement.setString(4, producto.getDescripcion());
			preStatement.setInt(5, producto.getIdProducto());
			preStatement.executeUpdate();
			resultado = "ok";
		} catch (SQLException e) {
			System.out.println("Error al actualizar producto: " + e.getMessage());
			resultado = "error";
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return resultado;
	}

	public ArrayList<ProductoDTO> consultarListaProductos() throws SQLException {
		ArrayList<ProductoDTO> listaProductos = new ArrayList<ProductoDTO>();
		if (!conectar().equals("conectado")) {
			
			return listaProductos;
		}

		ResultSet result = null;
		String consulta = "SELECT idProducto, nombre, cantidad, precio, descripcion FROM producto";

		try {
			preStatement = connection.prepareStatement(consulta);
			result = preStatement.executeQuery();
			while (result.next()) {
				listaProductos.add(new ProductoDTO(
					result.getInt("idProducto"),
					result.getString("nombre"),
					result.getInt("cantidad"),
					result.getDouble("precio"),
					result.getString("descripcion")
				));
			}
		} finally {
			if (result != null) result.close();
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return listaProductos;
	}

	public String eliminarProducto(int id) throws SQLException {
		String respuesta = "";
		if (!conectar().equals("conectado")) {
			
			return "error";
		}

		try {
			String sentencia = "DELETE FROM producto WHERE idProducto = ?";
			preStatement = connection.prepareStatement(sentencia);
			preStatement.setInt(1, id);
			preStatement.executeUpdate();
			respuesta = "ok";
		} catch (SQLException e) {
			respuesta = "error";
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return respuesta;
	}
}