package platform.codingnomads.co.springdata.example.dml.lifecyclecallback;

import org.springframework.data.repository.CrudRepository;

public interface PrintEntityRepository extends CrudRepository<PrintEntity, Long> {
}
