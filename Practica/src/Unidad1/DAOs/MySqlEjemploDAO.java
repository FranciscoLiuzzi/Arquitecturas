package Unidad1.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import Unidad1.Objects.Ejemplo;

public class MySqlEjemploDAO implements EjemploDAO{
	private Connection conn;
	
	public MySqlEjemploDAO(Connection conn) {
		this.conn = conn;
	}
	
//	El DAO cuenta con una conexion pasada por el constructor
	
	@Override
	public int insert(Ejemplo ins) throws Exception {
		String insert = "INSERT INTO Tabla (id, name) VALUES (?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		try {
            ps.setInt(1, ins.getId());
            ps.setString(2, ins.getName());
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
	public boolean delete(Integer id) throws Exception {
		String delete = "DELETE FROM Tabla WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(delete);
		try {
			ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo borrar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		ps.close();
		conn.commit();
		return false;
	}

	@Override
	public boolean update(Ejemplo upd) throws Exception {
		String update = "UPDATE Ejemplo SET name = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(update);
		try {
			ps.setString(1, upd.getName());
			ps.setInt(2, upd.getId());
			if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo actualizar");
            }	
		} catch (SQLException e) {
            e.printStackTrace();
        }
		ps.close();
		conn.commit();
		return false;
	}

	@Override
	public Ejemplo select(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ejemplo> listarEjemplosPorID() {
		// TODO Auto-generated method stub
		return null;
	}
}
