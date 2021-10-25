package platform.codingnomads.co.springsecurity.authorization.custompermissions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springsecurity.authorization.custompermissions.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    void deleteByEmail(String email);
}
