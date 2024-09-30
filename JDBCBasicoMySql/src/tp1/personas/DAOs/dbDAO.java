package tp1.personas.DAOs;

public interface dbDAO<T> {
	public int insert(T ins) throws Exception;
	public boolean delete(Integer id) throws Exception;
	public boolean update(T upd) throws Exception;
	public T select(Integer id) throws Exception;
}
