package platform.codingnomads.co.springweb.springrestcontrollers.restcontrollerandrequestmapping.greeting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class GreetingController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String helloGreeting() {
        return "Hello CodeWarriors!!";
    }

    @RequestMapping(
            value = {"/greeting-advanced-1", "/greeting-advanced-2"},
            method = RequestMethod.GET)
    public String multiple() {
        return "Hello EveryOne";
    }
}
