package platform.codingnomads.co.springtest.usingtestresttemplate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.codingnomads.co.springtest.usingtestresttemplate.models.CoffeePreference;

public interface CoffeePreferenceRepo extends JpaRepository<CoffeePreference, Long> {
}
