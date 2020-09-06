package Facturacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import DAO.DAOCliente;
import DAO.DAOFactura;
import DAO.DAOProducto;
import DAOImplements.DAOClienteImpl;
import DAOImplements.DAOFacturaImpl;
import DAOImplements.DAOProductoImpl;

public class Facturacion {
	
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		DAOCliente daoc = new DAOClienteImpl();
		DAOProducto daop= new DAOProductoImpl();
		DAOFactura daof= new DAOFacturaImpl();
		
		//creacion de tablas
		daoc.crearTabla();
		daop.crearTabla();
		daof.crearTabla();
		
		daoc.cargarDesdeCsv();
		daop.cargarDesdeCsv();
		daof.cargarDesdeCsv();
		
		daof.obtenerProductoMejorRecaudacion();
		daoc.obtenerClientesFavoritos();
		
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
		 * Cliente c2 = new Cliente(2, "mateo", "mateo@gmail.com");
		 * Producto p2 = new Producto(2, "plato", (float) 22.5);
		 * Factura f2 = new Factura(2, 2);
		 * Cliente c3 = new Cliente(3, "juan", "juandeccechis@gmail.com");
		 * Producto p3 = new Producto(3, "vaso", (float) 10);
		 * Factura f3 = new Factura(3, 3);
		 * DAOCliente daoc = new DAOClienteImpl();
		 * DAOProducto daop= new DAOProductoImpl();
		 * DAOFactura daof= new DAOFacturaImpl();
		
		 * //creacion de tablas
		 * daoc.crearTabla("cliente_prueba");
		 * daop.crearTabla("producto_prueba");
		 * daof.crearTabla("factura_prueba");
		
		 * daoc.agregarCliente(c);
		 * daoc.agregarCliente(c2);
		 * daoc.agregarCliente(c3);
		 * daop.agregarProducto(p);
		 * daop.agregarProducto(p2);
		 * daop.agregarProducto(p3);
		 * daof.agregarFactura(f);
		 * daof.agregarFactura(f2);
		 * daof.agregarFactura(f3);
		 * daof.agregarFacturaProducto(1, 1, 2);
		 * daof.agregarFacturaProducto(1, 3, 2);
		 * //total fact 45
		 * daof.agregarFacturaProducto(2, 1, 1);
		 * daof.agregarFacturaProducto(2, 2, 1);
		 * daof.agregarFacturaProducto(2, 3, 1);
		 * //total fact 45
		 * daof.agregarFacturaProducto(3, 3, 4);
		 * //total fact 40
		
		 * daof.obtenerProductoMejorRecaudacion();
		 * //p3 = 7* 10, vaso
		 * daoc.obtenerClientesFavoritos();
		 * //1 y 2 (B, M), total 45
		 * daof.agregarFacturaProducto(3, 1, 3);
		 * //total fact 4* 10 + 3* 12.5 = 77.5
		 * daof.obtenerProductoMejorRecaudacion();
		 * //p1 = 2 + 1 + 3 = 6* 12.5 = 75, cubierto
		 * daoc.obtenerClientesFavoritos();
		 * //3 (J), total 77.5
		 *  
		 * ***/
	}
}
