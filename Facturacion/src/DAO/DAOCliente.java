package DAO;

import java.sql.SQLException;
import java.util.List;

import Facturacion.Cliente;

public interface DAOCliente {

	public void crearTabla(String nombreTabla) throws SQLException;
	public void agregarCliente(Cliente c) throws SQLException;
	public List<Cliente> listarClientes() throws SQLException;
	public Cliente obtenerCliente(int id) throws SQLException;
	public void cargarDesdeCsv()throws SQLException;
}