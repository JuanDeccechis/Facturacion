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
				/*
				DAOFactProd daofp= new DAOFactProdImpl();*/
				
				//creacion de tablas
				//daoc.crearTabla("clientePrueba");
				/* daop.crearTabla("productoTest");
				 * daof.crearTabla("facturaTest");
				daofp.crearTabla();*/
				//daoc.cargarDesdeCsv();
				daof.crearTabla("facturaPrueba");
				daof.cargarDesdeCsv();
				
				
				//daoc.agregarCliente(c);
				//daop.agregarProducto(p);
				//daof.agregarFactura(f);

				List<Factura> facturas= daof.listarFacturas();
				facturas.forEach(System.out::println);
				/*System.out.println("primer cliente:");
				System.out.println(daoc.obtenerCliente(1));
				*/
			
				
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
				 * 
				 * FACTURA
				 * Factura f = new Factura(1, 1);
				 * DAOFactura daof= new DAOFacturaImpl();
				 * daof.crearTabla("facturaTest");
				 * daof.agregarFactura(f);
				 * List<Factura> facturas= daof.listarFacturas();
				 * facturas.forEach(System.out::println);
				 * ***/
			}
}
