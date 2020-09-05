package DAOImplements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import DAO.DAOCliente;
import Facturacion.Cliente;
import Facturacion.Conexion;

public class DAOClienteImpl  extends Conexion implements DAOCliente {

	private String tabla;
	
	@Override
	public void crearTabla(String nombreTabla) throws SQLException {
		this.tabla = nombreTabla;
		this.conectar();
		String table="CREATE TABLE IF NOT EXISTS "+ tabla + "(" + 
				"     id INT NOT NULL," + 
				"     nombre VARCHAR(500) NOT NULL," + 
				"     mail VARCHAR(150)," + 
				"     PRIMARY KEY (id)" + 
				");";
		this.conn.prepareStatement(table).executeUpdate();
		this.conn.commit();		
	}

	@Override
	public void agregarCliente(Cliente c) throws SQLException {
		String insert= "INSERT INTO " + tabla +"(id,nombre,mail) VALUES (?,?,?)";
		PreparedStatement ps = this.conn.prepareStatement(insert);
		ps.setInt(1, c.getId());
		ps.setString(2, c.getNombre());
		ps.setString(3, c.getEmail());
		ps.executeUpdate();
		ps.close();
		this.conn.commit();
		
	}

	@Override
	public List<Cliente> listarClientes() throws SQLException {
		String select = "SELECT * FROM " + tabla;
		PreparedStatement ps = this.conn.prepareStatement(select);
		ResultSet rs=ps.executeQuery();
		List<Cliente> result = new ArrayList<Cliente>();
		while (rs.next()) {
			Cliente c = new Cliente();
			c.setId(rs.getInt("id"));
			c.setNombre(rs.getString("nombre"));
			c.setEmail(rs.getString("mail"));
			result.add(c);
		}
		rs.close();
		return result;
	}

	@Override
	public Cliente obtenerCliente(int id) throws SQLException {
		String select = "SELECT * FROM " + tabla + " WHERE id = ?";
		PreparedStatement ps = this.conn.prepareStatement(select);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Cliente c = null;
		while (rs.next()) {
			c = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("mail"));
		}
		rs.close();
		return c;
	}

	@Override
	public void cargarDesdeCsv() throws SQLException, FileNotFoundException, IOException {
		CSVParser parser;
		Cliente c;
		parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("tp1-archivos\\clientes.csv"));
		for(CSVRecord row: parser) {
			c=new Cliente(Integer.parseInt(row.get("idCliente")),row.get("nombre"),row.get("email"));
			System.out.println(row.get("idCliente"));
			System.out.println(row.get("nombre"));
			System.out.println(row.get("email"));
			this.agregarCliente(c);
		}
	}


}
