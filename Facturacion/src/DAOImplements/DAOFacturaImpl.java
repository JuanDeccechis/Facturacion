package DAOImplements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DAOFactura;
import Facturacion.Cliente;
import Facturacion.Conexion;
import Facturacion.Factura;

public class DAOFacturaImpl extends Conexion  implements DAOFactura{

private String tabla;
	
	@Override
	public void crearTabla(String nombreTabla) throws SQLException {
		this.tabla = nombreTabla;
		this.conectar();
		String table="CREATE TABLE IF NOT EXISTS "+ tabla + "(" + 
				"     id INT NOT NULL," + 
				"     idcliente int NOT NULL," + 
				"     PRIMARY KEY (id)," + 
				"	  FOREIGN KEY (idcliente) REFERENCES cliente(id)"+
				");";
		
		String RELACION_FACTURA_PRODUCTO ="CREATE TABLE IF NOT EXISTS " + tabla +"_producto (" + 
				"	  idfactura INT NOT NULL," + 
				"	  idproducto INT NOT NULL," + 
				"	  cantidad INT NOT NULL," + 
				"	  PRIMARY KEY (idfactura, idproducto)," + 
				"	  FOREIGN KEY (idfactura)" + 
				"	  REFERENCES factura (id),"+ 
				"	  FOREIGN KEY (idproducto)" + 
				"	  REFERENCES producto (id)" +
				");";
		
		this.conn.prepareStatement(table).executeUpdate();
		this.conn.commit();
		//this.conn.prepareStatement(RELACION_FACTURA_PRODUCTO).executeUpdate();
		//this.conn.commit();
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

	public void insertarFacturaProducto(int idFactura,int idProducto, int cantidad) throws SQLException {
		String insert = "INSERT INTO " + tabla +"_producto (idFactura,idProducto, cantidad) VALUES (?,?,?)";
		PreparedStatement ps = this.conn.prepareStatement(insert);
		ps.setInt(1, idFactura);
		ps.setInt(2, idProducto);
		ps.setInt(2, cantidad);
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
	public void cargarDesdeCsv() throws SQLException {
		/*CSVParser parser;
		Factura f;
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
