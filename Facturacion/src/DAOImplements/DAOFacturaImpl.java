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

import DAO.DAOFactura;
import Facturacion.Conexion;
import Facturacion.Factura;

public class DAOFacturaImpl extends Conexion  implements DAOFactura{

private String tabla = "";
private String sufijo = "";
private String path = "tp1-archivos\\";
private String delimitador = "_";
	
	@Override
	public void crearTabla(String nombreTabla) throws SQLException {
		this.tabla = nombreTabla;
		String[] parts = nombreTabla.split(delimitador);
		if (parts.length > 1) {
			sufijo = parts[parts.length-1];	
		}
		else {
			delimitador = "";
		}
		this.conectar();
		String table="CREATE TABLE IF NOT EXISTS "+ tabla + "(" + 
				"     id INT NOT NULL," + 
				"     idcliente int NOT NULL," + 
				"     PRIMARY KEY (id)," + 
				"	  FOREIGN KEY (idcliente) REFERENCES cliente" + delimitador + sufijo + "(id)"+
				");";
		
		String RELACION_FACTURA_PRODUCTO ="CREATE TABLE IF NOT EXISTS factura_producto" + delimitador + sufijo + "(" + 
				"	  idfactura INT NOT NULL," + 
				"	  idproducto INT NOT NULL," + 
				"	  cantidad INT NOT NULL," + 
				"	  PRIMARY KEY (idfactura, idproducto)," + 
				"	  FOREIGN KEY (idfactura)" + 
				"	  REFERENCES factura" + delimitador + sufijo + "(id),"+ 
				"	  FOREIGN KEY (idproducto)" + 
				"	  REFERENCES producto" + delimitador + sufijo + "(id)" +
				");";
		
		this.conn.prepareStatement(table).executeUpdate();
		this.conn.commit();
		this.conn.prepareStatement(RELACION_FACTURA_PRODUCTO).executeUpdate();
		this.conn.commit();
	}

	@Override
	public void agregarFactura(Factura f) throws SQLException {
		String insert= "INSERT INTO " + tabla +"(id,idCliente) VALUES (?,?)";
		PreparedStatement ps = this.conn.prepareStatement(insert);
		ps.setInt(1, f.getId());
		ps.setInt(2, f.getCliente());
		ps.executeUpdate();
		ps.close();
		this.conn.commit();
	}

	public void agregarFacturaProducto(int idFactura,int idProducto, int cantidad) throws SQLException {
		String insert = "INSERT INTO factura_producto" + delimitador + sufijo + "(idFactura, idProducto, cantidad) VALUES (?,?,?)";
		PreparedStatement ps = this.conn.prepareStatement(insert);
		ps.setInt(1, idFactura);
		ps.setInt(2, idProducto);
		ps.setInt(3, cantidad);
		ps.executeUpdate();
		ps.close();
		this.conn.commit();
	}
	
	@Override
	public List<Factura> listarFacturas() throws SQLException {
		String select = "SELECT * FROM " + tabla;
		PreparedStatement ps = this.conn.prepareStatement(select);
		ResultSet rs=ps.executeQuery();
		List<Factura> result = new ArrayList<Factura>();
		while (rs.next()) {
			Factura f = new Factura();
			f.setId(rs.getInt("id"));
			f.setCliente(rs.getInt("idcliente"));
			result.add(f);
		}
		rs.close();
		return result;
	}

	@Override
	public Factura obtenerFactura(int id) throws SQLException {
		String select = "SELECT * FROM " + tabla + " WHERE id = ?";
		PreparedStatement ps = this.conn.prepareStatement(select);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Factura f = null;
		while (rs.next()) {
			f = new Factura(rs.getInt("id"), rs.getInt("idCliente"));
		}
		rs.close();
		return f;
	}

	@Override
	public void cargarDesdeCsv() throws SQLException, FileNotFoundException, IOException {
		CSVParser parser;
		Factura f = null;
		parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path+"facturas.csv"));
		for(CSVRecord row: parser) {
			f=new Factura(Integer.parseInt(row.get("idFactura")),Integer.parseInt(row.get("idCliente")));
			this.agregarFactura(f);	
		}
		CSVParser parserRelacion;
		
		parserRelacion = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + "facturas-productos.csv"));
		for(CSVRecord row: parserRelacion) {
			this.agregarFacturaProducto(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad")));;	
		}
	}
}
