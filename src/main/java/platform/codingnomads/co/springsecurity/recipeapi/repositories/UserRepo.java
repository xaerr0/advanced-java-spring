package platform.codingnomads.co.springsecurity.recipeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.springsecurity.recipeapi.models.securitymodels.CustomUserDetails;

@Repository
public interface UserRepo extends JpaRepository<CustomUserDetails, Long> {

    CustomUserDetails findByUsername(String username);
}
