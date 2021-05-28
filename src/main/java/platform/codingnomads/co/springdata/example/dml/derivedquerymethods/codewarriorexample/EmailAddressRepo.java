package platform.codingnomads.co.springdata.example.dml.derivedquerymethods.codewarriorexample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAddressRepo extends JpaRepository<EmailAddress, Long> {

    EmailAddress findByEmailAddress(String emailAddress);
}
