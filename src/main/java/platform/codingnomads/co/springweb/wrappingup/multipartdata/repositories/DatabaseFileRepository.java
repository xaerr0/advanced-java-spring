package platform.codingnomads.co.springweb.wrappingup.multipartdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springweb.wrappingup.multipartdata.models.DatabaseFile;

public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, Long> {

}
