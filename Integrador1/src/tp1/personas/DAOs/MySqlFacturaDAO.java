package tp1.personas.DAOs;

import java.sql.Connection;

import tp1.personas.Objects.Factura;

public class MySqlFacturaDAO implements FacturaDAO{
	
	private Connection conn;
	
	public MySqlFacturaDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int insert(Factura ins) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Factura upd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Factura select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
