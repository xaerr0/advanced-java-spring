package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata.models.DatabaseFile;

public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, Long> { }
