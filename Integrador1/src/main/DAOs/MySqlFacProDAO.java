package main.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.Objects.FacPro;

public class MySqlFacProDAO implements FacProDAO{

	private Connection conn;
	
	public MySqlFacProDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int insert(FacPro ins) throws Exception{
		String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		try {
			ps.setInt(1, ins.getIdFac());
			ps.setInt(2, ins.getIdPro());
			ps.setInt(3, ins.getCant());
			if (ps.executeUpdate() == 0) {
				throw new Exception("No se pudo insertar");
			}
		} catch (SQLException e) {
            e.printStackTrace();
        }
		ps.close();
		conn.commit();
		return 0;
	}

	@Override
	public boolean delete(Integer id) throws Exception{
		//A implementar
		return true;
	}

	@Override
	public boolean update(FacPro upd) throws Exception{
		//A implementar
		return false;
	}

	@Override
	public FacPro select(Integer id) {
		//A implementar
		return null;
	}
	
}
