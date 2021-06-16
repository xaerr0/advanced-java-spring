package platform.codingnomads.co.springfundamentals.example;

import org.springframework.stereotype.Service;

@Service
public class StatusService {

    public String processStatus(boolean arriving) {
        if (arriving) {
            return "hello";
        } else {
            return "goodbye";
        }
    }
}
