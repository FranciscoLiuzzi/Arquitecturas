package main.Repositories;

import java.util.List;

public interface dbRepository <T> {
	public T save(T entity);

	public T findById(int id);

	public List<T> findAll();

	public void delete(T entity);
}
