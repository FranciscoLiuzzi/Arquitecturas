package main.Factories;

import main.DAOs.ClienteDAO;
import main.DAOs.FacProDAO;
import main.DAOs.FacturaDAO;
import main.DAOs.ProductoDAO;

public class DerbyJDBCDAOFactory extends DAOFactory {

	@Override
	public FacturaDAO getFacturaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoDAO getProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacProDAO getFacProDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

}
