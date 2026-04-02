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

	public String registrarCompra(CompraDTO compra) throws SQLException {
		String resultado = "";
		if (!conectar().equals("conectado")) {
			
			return "error";
		}

		String consulta = "INSERT INTO compra (idCompra, detalleCompra, descuento, precioCompra, total) VALUES (?,?,?,?,?)";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setInt(1, compra.getIdCompra());
			preStatement.setString(2, compra.getDetalleCompra());
			preStatement.setDouble(3, compra.getDescuento());
			preStatement.setDouble(4, compra.getPrecioCompra());
			preStatement.setDouble(5, compra.getTotal());
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