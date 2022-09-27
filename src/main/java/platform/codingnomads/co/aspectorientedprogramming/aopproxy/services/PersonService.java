package platform.codingnomads.co.aspectorientedprogramming.aopproxy.services;

import org.springframework.stereotype.Service;
import platform.codingnomads.co.aspectorientedprogramming.aopproxy.models.Person;

@Service
public class PersonService {

    public String getPersonFullName(Person person) {
        return person.getFullName();
    }
}
