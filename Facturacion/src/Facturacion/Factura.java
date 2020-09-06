package Facturacion;

import java.sql.SQLException;

public class Factura {
	private int id;
	private int idCliente;
	
	public Factura(int id, int idCliente) {
		super();
		this.id = id;
		this.idCliente = idCliente;
	}
	
	public Factura() {
		super();
	}
	
	public int getCliente() {
		return this.idCliente;
	}
	
	public void setCliente(int cliente) {
		this.idCliente = cliente;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int factura) {
		this.id= factura;
	}

	public void agregarFacturaProducto(int idFactura,int idProducto, int cantidad) throws SQLException {
	
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", idCliente=" + idCliente + "]";
	}
}
