package platform.codingnomads.co.springmvc.thymleafjsandcss;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String index() {
        return "thymeleaf-include-js-css";
    }
}