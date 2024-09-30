package Unidad1.DAOs;

public interface dbDAO<T> {
	public int insert(T ins) throws Exception;
	public boolean delete(Integer id) throws Exception;
	public boolean update(T upd) throws Exception;
	public T select(Integer id) throws Exception;
	
	//Interfaz con las operaciones CRUD de las BBDD para que implementen los demás DAOs
}
