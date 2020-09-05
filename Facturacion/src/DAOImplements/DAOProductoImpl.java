package DAOImplements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DAOProducto;
import Facturacion.Conexion;
import Facturacion.Producto;

public class DAOProductoImpl extends Conexion implements DAOProducto {

	private String tabla;

	@Override
	public void crearTabla(String nombreTabla) throws SQLException {
		this.tabla = nombreTabla;
		this.conectar();
		String table="CREATE TABLE IF NOT EXISTS "+ tabla + "(" + 
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
		String insert= "INSERT INTO "+ tabla + " (id,nombre,valor) VALUES (?,?,?)";
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
		String select = "SELECT * FROM " + tabla;
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
		String select = "SELECT * FROM " + tabla + " WHERE id = ?";
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
	public void cargarDesdeCsv() throws SQLException {
		/*CSVParser parser;
		Producto p;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("C://Users/Grido/Desktop/Facultad/arqui web/csvs/productos.csv"));
			for(CSVRecord row: parser) {
				 p=new Producto(Integer.parseInt(row.get("idProducto")),row.get("nombre"),Float.parseFloat(row.get("valor")));
				System.out.println(row.get("idProducto"));
				System.out.println(row.get("nombre"));
				System.out.println(row.get("valor"));
				this.agregarProducto(p);
				
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}


}
