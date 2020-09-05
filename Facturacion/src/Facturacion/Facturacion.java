package Facturacion;

import java.sql.SQLException;
import java.util.List;

import DAO.DAOCliente;
import DAO.DAOFactura;
import DAO.DAOProducto;
import DAOImplements.DAOClienteImpl;
import DAOImplements.DAOFacturaImpl;
import DAOImplements.DAOProductoImpl;

public class Facturacion {

	public static void main(String[] args) throws SQLException {
		Cliente c = new Cliente(1, "Belen", "belenenemar@gmail.com");
		Producto p = new Producto(1, "cubierto", (float) 12.5);
		Factura f = new Factura(1, 1);
				DAOCliente daoc = new DAOClienteImpl();
				DAOProducto daop= new DAOProductoImpl();
				DAOFactura daof= new DAOFacturaImpl();
				/*
				DAOFactProd daofp= new DAOFactProdImpl();*/
				
				//creacion de tablas
				/*daoc.crearTabla("clienteTest");
				 * daop.crearTabla("productoTest");
				daof.crearTabla();
				daofp.crearTabla();
				daop.cargarDesdeCsv();*/
				daof.crearTabla("facturaTest");
				
				//daoc.agregarCliente(c);
				//daop.agregarProducto(p);
				daof.agregarFactura(f);

				/*List<Cliente> clientes = daoc.listarClientes();
				clientes.forEach(System.out::println);
				System.out.println("primer cliente:");
				System.out.println(daoc.obtenerCliente(1));
				*/
				List<Factura> facturas= daof.listarFacturas();
				facturas.forEach(System.out::println);
				/*** TEST UNITARIOS
				 * CLIENTE
				 * Cliente c = new Cliente(1, "Belen", "belenenemar@gmail.com");
				 * DAOCliente daoc = new DAOClienteImpl();
				 * daoc.crearTabla("clienteTest");
				 * daoc.agregarCliente(c);
				 * List<Cliente> clientes = daoc.listarClientes();
				 * clientes.forEach(System.out::println);
				 * 
				 * PRODUCTO
				 * Producto p = new Producto(1, "cubierto", (float) 12.5);
				 * DAOProducto daop= new DAOProductoImpl();
				 * daop.crearTabla("productoTest");
				 * daop.agregarProducto(p);
				 * List<Producto> productos= daop.listarProductos();
				 * productos.forEach(System.out::println);
				 * ***/
			}
}
