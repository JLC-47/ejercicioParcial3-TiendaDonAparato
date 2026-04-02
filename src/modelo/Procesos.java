package modelo;

import controller.Coordinador;
import modelo.dto.ClienteDTO;
import modelo.dto.CompraDTO;
import modelo.dto.ProductoDTO;

public class Procesos {
	private Coordinador miCoordinador;

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
		
	}

	
	public CompraDTO calcularDescuento(ClienteDTO cliente, ProductoDTO producto, int cantidad) {
	    double precioBase = producto.getPrecio() * cantidad;
	    double porcentaje = 0;
	    
	    String tipo;
	    if (cliente.getTipo() != null) {
	        tipo = cliente.getTipo().toUpperCase();
	    } else {
	        tipo = "";
	    }

	    
	    if (cliente.getTipo().equalsIgnoreCase("A")) {
	        porcentaje = 0.40; 
	    } else if (cliente.getTipo().equalsIgnoreCase("B")) {
	        porcentaje = 0.20; 
	    } else if (cliente.getTipo().equalsIgnoreCase("C")) {
	        porcentaje = 0.10; 
	    } else {
	        porcentaje = 0; 
	    }

	    double valorDescuento = precioBase * porcentaje;
	    double totalReal = precioBase - valorDescuento;

	    String detalle = "Compra de " + cantidad + " " + producto.getNombre() + "(s)";
	    
	    return new CompraDTO(0, "Compra: " + producto.getNombre(), valorDescuento, precioBase, totalReal);
	}
}
