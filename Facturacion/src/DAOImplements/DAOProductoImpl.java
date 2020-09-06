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

import DAO.DAOProducto;
import Facturacion.Conexion;
import Facturacion.Producto;

public class DAOProductoImpl extends Conexion implements DAOProducto {

	private String path = "tp1-archivos\\";

	@Override
	public void crearTabla() throws SQLException {
		this.conectar();
		String table="CREATE TABLE IF NOT EXISTS producto (" + 
				"     id INT NOT NULL," + 
				"     nombre VARCHAR(45) NOT NULL," + 
				"     valor FLOAT NOT NULL," + 
				"     PRIMARY KEY (id)" + 
				");";
		
		this.conn.prepareStatement(table).executeUpdate();
		this.conn.commit();	
	}
	
	@Override
	public void agregarProducto(Producto p) throws SQLException {
		String insert= "INSERT INTO producto (id,nombre,valor) VALUES (?,?,?)";
		PreparedStatement ps = this.conn.prepareStatement(insert);
		ps.setInt(1, p.getId());
		ps.setString(2, p.getNombre());
		ps.setFloat(3, p.getValor());
		ps.executeUpdate();
		ps.close();
		this.conn.commit();
	}

	@Override
	public List<Producto> listarProductos() throws SQLException {
		String select = "SELECT * FROM producto";
		PreparedStatement ps = this.conn.prepareStatement(select);
		ResultSet rs=ps.executeQuery();
		List<Producto> result = new ArrayList<Producto>();
		while (rs.next()) {
			Producto p = new Producto();
			p.setId(rs.getInt("id"));
			p.setNombre(rs.getString("nombre"));
			p.setValor(rs.getFloat("valor"));
			result.add(p);
		}
		rs.close();
		return result;
	}

	@Override
	public Producto obtenerProducto(int id) throws SQLException {
		String select = "SELECT * FROM producto WHERE id = ?";
		PreparedStatement ps = this.conn.prepareStatement(select);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Producto p = null;
		while (rs.next()) {
			p = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("valor"));
		}
		rs.close();
		return p;
	}

	@Override
	public void cargarDesdeCsv() throws SQLException, FileNotFoundException, IOException {
		CSVParser parser;
		Producto p;
		parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + "productos.csv"));
		for(CSVRecord row: parser) {
			p=new Producto(Integer.parseInt(row.get("idProducto")),row.get("nombre"),Float.parseFloat(row.get("valor")));
			this.agregarProducto(p);
		}	
	}

}
