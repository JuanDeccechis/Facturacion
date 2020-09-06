package Facturacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import DAO.DAOCliente;
import DAO.DAOFactura;
import DAO.DAOProducto;
import DAOImplements.DAOClienteImpl;
import DAOImplements.DAOFacturaImpl;
import DAOImplements.DAOProductoImpl;

public class Facturacion {
	
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		Cliente c = new Cliente(1, "Belen", "belenenemar@gmail.com");
		Producto p = new Producto(1, "cubierto", (float) 12.5);
		Factura f = new Factura(1, 1);
		DAOCliente daoc = new DAOClienteImpl();
		DAOProducto daop= new DAOProductoImpl();
		DAOFactura daof= new DAOFacturaImpl();
		
		//creacion de tablas
		daoc.crearTabla("cliente_prueba");
		daop.crearTabla("producto_prueba");
		daof.crearTabla("factura_prueba");
		
		daoc.cargarDesdeCsv();
		daop.cargarDesdeCsv();
		daof.cargarDesdeCsv();


//		daof.agregarFacturaProducto(1, 1, 4);
		daof.obtenerProductoMejorRecaudacion();
		daof.agregarFacturaProducto(1, 2, 5);
		daof.obtenerProductoMejorRecaudacion();
		
		/*** TEST UNITARIOS
		 * CLIENTE
		 * Cliente c = new Cliente(1, "Belen", "belenenemar@gmail.com");
		 * DAOCliente daoc = new DAOClienteImpl();
		 * daoc.crearTabla("clienteTest");
		 * daoc.agregarCliente(c); // para csv: daoc.cargarDesdeCsv();
		 * List<Cliente> clientes = daoc.listarClientes();
		 * clientes.forEach(System.out::println);
		 * 
		 * PRODUCTO
		 * Producto p = new Producto(1, "cubierto", (float) 12.5);
		 * DAOProducto daop= new DAOProductoImpl();
		 * daop.crearTabla("productoTest");
		 * daop.agregarProducto(p);  // para csv: daop.cargarDesdeCsv();
		 * List<Producto> productos= daop.listarProductos();
		 * productos.forEach(System.out::println);
		 * 
		 * FACTURA
		 * Factura f = new Factura(1, 1);
		 * DAOFactura daof= new DAOFacturaImpl();
		 * daof.crearTabla("facturaTest");
		 * daof.agregarFactura(f);  // para csv: daof.cargarDesdeCsv();
		 * List<Factura> facturas= daof.listarFacturas();
		 * facturas.forEach(System.out::println);
		 * 		 
		 * FACTURA_PRODUCTO
		 * daof.agregarFacturaProducto(1, 1, 4); 
		 * daof.obtenerFacturaProductos()
		 * 
		 * 
		 * TEST DE INTEGRACION
		 * Cliente c = new Cliente(1, "Belen", "belenenemar@gmail.com");
		 * Producto p = new Producto(1, "cubierto", (float) 12.5);
		 * Factura f = new Factura(1, 1);
		 * Cliente c2 = new Cliente(2, "Mateo", "mateo@gmail.com");
		 * Producto p2 = new Producto(2, "plato", (float) 22.5);
		 * Factura f2 = new Factura(2, 2);
		 * 
		 * daof.agregarFacturaProducto(1, 1, 4);
		 * daof.obtenerProductoMejorRecaudacion();
		 * daof.agregarFacturaProducto(1, 2, 5);
		 * daof.obtenerProductoMejorRecaudacion();
		 * ***/
	}
}
