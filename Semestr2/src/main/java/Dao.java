import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById (long id);

    List<T> findAll ();

    void deleteById (long id);

    T save (T entity);

}
