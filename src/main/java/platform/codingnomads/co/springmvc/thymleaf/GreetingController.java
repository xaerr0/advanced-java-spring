package platform.codingnomads.co.springmvc.thymleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        model.addAttribute("name", "Code Warrior!!!"); 
        return "greeting"; 
    }
}