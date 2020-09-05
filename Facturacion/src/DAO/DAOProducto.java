package DAO;

import java.sql.SQLException;
import java.util.List;

import Facturacion.Producto;

public interface DAOProducto {
	
	public void crearTabla(String nombreTabla) throws SQLException;
	public void agregarProducto(Producto pr) throws SQLException;
	public List<Producto> listarProductos() throws SQLException;
	public Producto obtenerProducto(int id) throws SQLException;
	public void cargarDesdeCsv() throws SQLException;
}
