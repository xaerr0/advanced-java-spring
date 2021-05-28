package platform.codingnomads.co.springtest.understandingandusingprofiles;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("understanding_and_using_profiles")
public interface CoffeePreferenceRepo extends JpaRepository<CoffeePreference, Long> {

}
