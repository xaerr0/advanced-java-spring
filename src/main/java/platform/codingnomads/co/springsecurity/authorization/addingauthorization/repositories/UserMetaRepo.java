package platform.codingnomads.co.springsecurity.authorization.addingauthorization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springsecurity.authorization.addingauthorization.models.UserMeta;

@Repository
public interface UserMetaRepo extends JpaRepository<UserMeta, Long> {
}
