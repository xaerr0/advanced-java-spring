package platform.codingnomads.co.springsecurity.authentication.usernamepassword.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springsecurity.authentication.usernamepassword.models.Authority;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {
}