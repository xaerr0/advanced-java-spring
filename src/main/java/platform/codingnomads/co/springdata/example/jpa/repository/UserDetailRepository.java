package platform.codingnomads.co.springdata.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springdata.example.jpa.domain.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {
}
