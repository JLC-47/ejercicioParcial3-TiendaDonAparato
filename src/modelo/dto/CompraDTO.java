package modelo.dto;

public class CompraDTO {
	private int idCompra;
	private String detalleCompra;
	private double descuento;
	private double precioCompra;
	private double total;
	
	public CompraDTO() {
		
	}
	
	
	
	public CompraDTO(int idCompra, String detalleCompra, double descuento, double precioCompra, double total) {
		super();
		this.idCompra = idCompra;
		this.detalleCompra = detalleCompra;
		this.descuento = descuento;
		this.precioCompra = precioCompra;
		this.total = total;
	}



	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}



	public String getDetalleCompra() {
		return detalleCompra;
	}
	public void setDetalleCompra(String detalleCompra) {
		this.detalleCompra = detalleCompra;
	}



	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}



	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}



	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}



	@Override
	public String toString() {
		return "CompraDTO [idCompra=" + idCompra + ", detalleCompra=" + detalleCompra + ", descuento=" + descuento
				+ ", precioCompra=" + precioCompra + ", total=" + total + "]";
	}
	
	
	
	
}
