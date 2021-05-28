package platform.codingnomads.co.springdata.example.dml.introducingrepositories.crudrepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

}
