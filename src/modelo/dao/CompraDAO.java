package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexion.Conexion;
import controller.Coordinador;
import modelo.dto.CompraDTO;

public class CompraDAO {
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

	public String registrarCompra(CompraDTO compra, String documentoCliente, int idProducto) throws SQLException {
		String resultado = "";
		if (!conectar().equals("conectado")) {
			return "error";
		}

		String consulta = "INSERT INTO compra (detalleCompra, descuento, precioCompra, total, fechaCompra, documento, idProducto) VALUES (?,?,?,?,NOW(),?,?)";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, compra.getDetalleCompra());
			preStatement.setDouble(2, compra.getDescuento());
			preStatement.setDouble(3, compra.getPrecioCompra());
			preStatement.setDouble(4, compra.getTotal());
			preStatement.setString(5, documentoCliente);
			preStatement.setInt(6, idProducto);
			preStatement.execute();
			resultado = "ok";
		} catch (SQLException e) {
			System.out.println("No se pudo registrar la compra: " + e.getMessage());
			resultado = "No se pudo registrar la compra";
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return resultado;
	}

	public CompraDTO consultarCompra(int id) throws SQLException {
		CompraDTO compra = null;
		if (!conectar().equals("conectado")) {
			return null;
		}

		ResultSet result = null;
		String consulta = "SELECT * FROM compra WHERE idCompra = ?";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setInt(1, id);
			result = preStatement.executeQuery();

			if (result.next()) {
				compra = new CompraDTO();
				compra.setIdCompra(result.getInt("idCompra"));
				compra.setDetalleCompra(result.getString("detalleCompra"));
				compra.setDescuento(result.getDouble("descuento"));
				compra.setPrecioCompra(result.getDouble("precioCompra"));
				compra.setTotal(result.getDouble("total"));
			}
		} finally {
			if (result != null) result.close();
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return compra;
	}

	public String actualizarCompra(CompraDTO compra) throws SQLException {
		String respuesta = "";
		if (!conectar().equals("conectado")) {
			return "error";
		}

		String consulta = "UPDATE compra SET detalleCompra=?, descuento=?, precioCompra=?, total=? WHERE idCompra=?";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, compra.getDetalleCompra());
			preStatement.setDouble(2, compra.getDescuento());
			preStatement.setDouble(3, compra.getPrecioCompra());
			preStatement.setDouble(4, compra.getTotal());
			preStatement.setInt(5, compra.getIdCompra());
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

	public ArrayList<CompraDTO> consultarListaCompras() throws SQLException {
		ArrayList<CompraDTO> listaCompras = new ArrayList<CompraDTO>();
		if (!conectar().equals("conectado")) {
			return listaCompras;
		}

		ResultSet result = null;
		String consulta = "SELECT idCompra, detalleCompra, descuento, precioCompra, total FROM compra";

		try {
			preStatement = connection.prepareStatement(consulta);
			result = preStatement.executeQuery();
			while (result.next()) {
				listaCompras.add(new CompraDTO(
					result.getInt("idCompra"),
					result.getString("detalleCompra"),
					result.getDouble("descuento"),
					result.getDouble("precioCompra"),
					result.getDouble("total")
				));
			}
		} catch (SQLException e) {
			System.out.println("Error en la lista de compras: " + e.getMessage());
		} finally {
			if (result != null) result.close();
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return listaCompras;
	}

	public String eliminarCompra(int id) throws SQLException {
		String respuesta = "";
		if (!conectar().equals("conectado")) {
			return "error";
		}

		try {
			String sentencia = "DELETE FROM compra WHERE idCompra = ?";
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