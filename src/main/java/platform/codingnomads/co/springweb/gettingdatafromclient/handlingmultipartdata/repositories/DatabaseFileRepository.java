package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata.models.DatabaseFile;

import java.util.List;
import java.util.Optional;

public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, Long> {
    Optional<DatabaseFile> findByFileName(String fileName);

    List<DatabaseFile> findByFileNameContainingIgnoreCase(String fileName);

    Optional<DatabaseFile> duplicateFile(Long fileId);
}