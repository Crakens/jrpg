package baseDeDatos;

import java.sql.*;
import javax.swing.*;

import peticiones.CodigoPeticion;

public class SQLiteConnection {
	private static Connection conn = null;
	
	public SQLiteConnection () {
		conn = SQLiteConnection.dbConnector();
	}
		
	public static Connection dbConnector(){ //va a devolver la conexión
		conn = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:.\\bd\\baseDeDatos\\BDPrueba.sqlite");
			//BORRAR
			JOptionPane.showMessageDialog(null,"Conexión exitosa"); //se supone que debería mostrar el error
			//BORRAR
			return conn;
		}catch(Exception e){
			 JOptionPane.showMessageDialog(null,e); //se supone que debería mostrar el error
			 return null;
		}
	}
	
	public int login(String usuario, String password) {
		PreparedStatement pst = null;		
		try{
			String query = "select * from Usuario where nombre = ? and password = ?";
			pst= conn.prepareStatement(query); 
			pst.setString(1, usuario); //0 es el primer ? que pongo en la query
			pst.setString(2, password); //0 es el primer ? que pongo en la query
			
			ResultSet rs = pst.executeQuery();
			int count = 0;
			while(rs.next()){
				count++;
			}
			if (count == 1){
				return CodigoPeticion.LOGEO_CORRECTO;
			}
			else {
				 return CodigoPeticion.LOGEO_INCORRECTO;
			}
		}catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return CodigoPeticion.LOGEO_INCORRECTO;
	}
	
	public boolean agregarJugador(String usuario, String password, String mail){
		PreparedStatement pst = null;
		try{
			String query = "insert into Usuario (nombre, mail, password) values (?,?,?)";
			pst= conn.prepareStatement(query); 
			pst.setString(1, usuario); //1 es el primer ? que pongo en la query
			pst.setString(2, mail);
			pst.setString(3, password);
			
			pst.executeQuery();
			return true;
		}catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
