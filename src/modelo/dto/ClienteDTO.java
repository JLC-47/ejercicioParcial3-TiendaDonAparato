package modelo.dto;

public class ClienteDTO {
	private String documento;
	private String nombre;
	private String apellido;
	private int edad;
	private String tipo;
	private String telefono;
	
	
	
	
	



	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	@Override
	public String toString() {
		return "ClienteDTO [documento=" + documento + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", tipo=" + tipo + ", telefono=" + telefono + "]";
	}
	

}
