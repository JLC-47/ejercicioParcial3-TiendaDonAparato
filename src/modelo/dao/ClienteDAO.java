package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import conexion.Conexion;
import controller.Coordinador;
import modelo.dto.ClienteDTO;


public class ClienteDAO {
	private Coordinador miCoordinador;
	
	
	Connection connection = null;
	Conexion conexion = new Conexion();
	PreparedStatement preStatement = null;
	
	

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}
	
	private String conectar() {
		conexion = new Conexion();
		String resultado=conexion.conectar();
		if (resultado.equals("conectado")) {
			connection = conexion.getConnection();
		}else {
			JOptionPane.showMessageDialog(null, resultado,"Error",JOptionPane.ERROR_MESSAGE);
		}
		return resultado;
	}
	

	public String registarCliente(ClienteDTO cliente) throws SQLException {
		
		String resultado = "";
		
		if (!conectar().equals("conectado")) {
			return "error";
		}
		
		String consulta = "INSERT INTO cliente (documento,nombre,edad,tipo,apellido,telefono)"
				+ "  VALUES (?,?,?,?,?,?)";
		

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, cliente.getDocumento());
			preStatement.setString(2, cliente.getNombre());
			preStatement.setInt(3, cliente.getEdad());
			preStatement.setString(4, cliente.getTipo());
			preStatement.setString(5, cliente.getApellido());
			preStatement.setString(6, cliente.getTelefono());
			
			preStatement.execute();

			resultado = "ok";

		} catch (SQLException e) {
			System.out.println("No se pudo registrar el cliente: " + e.getMessage());
			e.printStackTrace();
			resultado = "No se pudo registrar el cliente";
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}

		return resultado;
	}
	
	public ClienteDTO consultarCliente(String doc) throws SQLException {
		ClienteDTO clienteDTO=null;
		
		if (!conectar().equals("conectado")) {
			return clienteDTO;
		}
		
		ResultSet result=null;
		
		String consulta="SELECT documento,nombre,apellido,edad,tipo,telefono"
				+ " FROM cliente where documento= ? ";
		
		try {
			preStatement=connection.prepareStatement(consulta);
			preStatement.setString(1, doc);
				
			result=preStatement.executeQuery();
				
			if(result.next()){
				clienteDTO=new ClienteDTO();
				clienteDTO.setDocumento(result.getString("documento"));
				clienteDTO.setNombre(result.getString("nombre"));
				clienteDTO.setApellido(result.getString("apellido"));
				clienteDTO.setEdad(result.getInt("edad"));
				clienteDTO.setTipo(result.getString("tipo"));
				clienteDTO.setTelefono(result.getString("telefono"));
			}		
			   
		} catch (SQLException e) {
			System.out.println("Error en la consulta del cliente: "+e.getMessage());
		}finally {
			result.close();
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		
		return clienteDTO;
	}
	
	
	public String actualizarCliente(ClienteDTO clienteDto) throws SQLException {
		
		String resultado="";

		if (!conectar().equals("conectado")) {
			return "error";
		}

		try{
			String consulta="UPDATE cliente "
					+ "SET nombre = ? , "
					+ "apellido= ? , "
					+ "tipo= ? , "
					+ "edad= ? , "
					+ "telefonoo= ?  "
					+ "WHERE documento= ?;";
			
			preStatement = connection.prepareStatement(consulta);

			preStatement.setString(1, clienteDto.getNombre());
			preStatement.setString(2, clienteDto.getApellido());
			preStatement.setString(3, clienteDto.getTipo());
			preStatement.setInt(4, clienteDto.getEdad());
			preStatement.setString(5, clienteDto.getTelefono());

			preStatement.setString(6, clienteDto.getDocumento());
			preStatement.executeUpdate();
			
          resultado="ok";
          
        }catch(SQLException	 e){
            System.out.println("Ocurrió una excepcion de SQL "
            		+ "al momento de actualizar: "+e);
            
            resultado="error";
            
        }finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		return resultado;
	}
	
	
	public ArrayList<ClienteDTO> consultarListaClientes() throws SQLException {
		
		ArrayList<ClienteDTO> listaClientes=new ArrayList<ClienteDTO>();
		
		if (!conectar().equals("conectado")) {
			return listaClientes;
		}
		
		ResultSet result=null;
		ClienteDTO miCliente=null;
		
		String consulta="SELECT documento,nombre,apellido,edad,tipo,telefono"
				+ " FROM cliente;";
		
		try {
			preStatement=connection.prepareStatement(consulta);
				
			result=preStatement.executeQuery();
				
			while(result.next()==true){
				miCliente=new ClienteDTO();
				miCliente.setDocumento(result.getString("documento"));
				miCliente.setNombre(result.getString("nombre"));
				miCliente.setApellido(result.getString("apellido"));
				miCliente.setEdad(result.getInt("edad"));
				miCliente.setTipo(result.getString("tipo"));
				miCliente.setTelefono(result.getString("telefono"));
				listaClientes.add(miCliente);
			}		
			   
		} catch (SQLException e) {
			System.out.println("Error en la consulta de clientes: "+e.getMessage());
		}finally {
			result.close();
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
			return listaClientes;
	}
	
	
	public String eliminarCliente(String doc) throws SQLException {
		
		String respuesta="";
		
		if (!conectar().equals("conectado")) {
			return "error";
		}
		
		
		try {
			String sentencia="DELETE FROM cliente WHERE documento= ? ";

			preStatement = connection.prepareStatement(sentencia);
			preStatement.setString(1, doc);
			
			preStatement.executeUpdate();
						
			respuesta="ok";
			
		} catch (SQLException e) {
            System.out.println("Ocurrió una excepcion de SQL "
            		+ "al momento de eliminar "+e);
			respuesta="error";
		}finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return respuesta;
	}
	
	
	
	
	
	
	
}
