package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Facturacion.Factura;


public interface DAOFactura {

	public void crearTabla(String nombreTabla) throws SQLException;
	public void agregarFactura(Factura f) throws SQLException;
	public List<Factura> listarFacturas() throws SQLException;
	public Factura obtenerFactura(int id) throws SQLException;
	public void cargarDesdeCsv() throws SQLException, FileNotFoundException, IOException;
}
