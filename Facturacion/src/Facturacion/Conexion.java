package Facturacion;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	protected Connection conn;
	protected String uri="jdbc:mysql://localhost:3306/facturacion?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	protected String user = "root";
	protected String pass = "";
	protected String driver="com.mysql.cj.jdbc.Driver";
	
	public void conectar() {
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			conn= DriverManager.getConnection(uri,user,pass);
			conn.setAutoCommit(false);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void cerrarConexion() throws SQLException {
		if (conn != null) {
			if (!conn.isClosed()){
				conn.close();		
			}
		}
	}

	public Conexion() {
	}

}
