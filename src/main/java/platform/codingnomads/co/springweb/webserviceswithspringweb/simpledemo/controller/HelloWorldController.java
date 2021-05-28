package platform.codingnomads.co.springweb.webserviceswithspringweb.simpledemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
public class HelloWorldController {

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello Code Warriors!";
    }

    @ResponseBody
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting(@PathVariable(name = "name") String name) {
        return "Hello " + name + "!";
    }


}




