
package dao;

import java.util.List;


public interface IDAO<T,K> {
    public boolean create(T entity);
    public List<T> readAll();
    public T readbyID(K id );
    public boolean update (T entity);
    public List<T> search(String searchTerm);
}
