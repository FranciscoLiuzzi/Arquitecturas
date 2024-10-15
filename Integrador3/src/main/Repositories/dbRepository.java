package main.Repositories;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.Repository;

public interface dbRepository <T, ID extends Serializable> extends Repository<T, ID> {
	public T save(T entity);

	public T findById(int id);

	public List<T> findAll();

	public void delete(T entity);
}
