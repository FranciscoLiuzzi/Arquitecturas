package tp1.personas.DAOs;

public interface dbDAO<T> {
	public int insert(T ins);
	public boolean delete(Integer id);
	public boolean update(T upd);
	public T select(Integer id);
}
