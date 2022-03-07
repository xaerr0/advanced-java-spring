package platform.codingnomads.co.springweb.springrestcontrollers.simpledemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiplePathExampleController {

    @RequestMapping(
            path = {"/this-path", "/another-path"},
            method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmation() {
        return "this String confirms that the path you entered works!";
    }
}
