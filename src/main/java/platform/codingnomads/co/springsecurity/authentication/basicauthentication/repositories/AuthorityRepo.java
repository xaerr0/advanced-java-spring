package platform.codingnomads.co.springsecurity.authentication.basicauthentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springsecurity.authentication.basicauthentication.models.Authority;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {
}