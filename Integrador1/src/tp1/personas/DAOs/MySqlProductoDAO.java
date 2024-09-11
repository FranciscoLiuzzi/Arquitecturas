package tp1.personas.DAOs;

import java.sql.Connection;

import tp1.personas.Objects.Producto;

public class MySqlProductoDAO implements ProductoDAO{
	
	private Connection conn;
	
	public MySqlProductoDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insert(Producto ins) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Producto upd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Producto select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
