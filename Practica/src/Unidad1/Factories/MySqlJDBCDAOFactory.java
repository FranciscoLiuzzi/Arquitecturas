package Unidad1.Factories;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Unidad1.DAOs.EjemploDAO;
import Unidad1.abstracts.DAOFactory;

public class MySqlJDBCDAOFactory extends DAOFactory {
	
	private static MySqlJDBCDAOFactory instance = null;
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String uri = "jdbc:mysql://localhost:3306/Arquitecturas";
	public static Connection conn;
	
	private MySqlJDBCDAOFactory() {
	}
	
	//Abre la conexión (Videos)
	public static Connection createConnection() { 
        if (conn != null) {
            return conn;
        } 
        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
	    try {
           conn = DriverManager.getConnection(uri, "root", "naranja");
           conn.setAutoCommit(false); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
	}
		
	@Override
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
		
	//Retorna la instancia
	public static MySqlJDBCDAOFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
			instance = new MySqlJDBCDAOFactory();
			return instance;
		}
	}
	
	@Override
	public EjemploDAO getEjemploDAO() {
		return null;
	}
}