package main.Factories;
import main.DAOs.*;

public abstract class DAOFactory {
	
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	public static final int JPA_HIBERNATE = 3;
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL_JDBC : return MySqlJDBCDAOFactory.getInstance();
		case DERBY_JDBC : return new DerbyJDBCDAOFactory(); //No existe
		case JPA_HIBERNATE : return new JpaHibernateJDBCDAOFactory(); //No existe
		default: return null;
		}
	}
	
	public abstract FacturaDAO getFacturaDAO();
	public abstract ProductoDAO getProductoDAO();
	public abstract ClienteDAO getClienteDAO();
	public abstract FacProDAO getFacProDAO();
	public abstract void closeConnection();
}
