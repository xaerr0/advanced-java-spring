package platform.codingnomads.co.springmvc.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Developer");
        map.put("language", "Java");
        map.put("framework", "Spring");
        model.mergeAttributes(map);
        return "model-page";
    }

    @GetMapping("/model-and-view-page")
    public ModelAndView passDataWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("model-and-view");
        modelAndView.addObject("message", "CodingNomads!");
        return modelAndView;
    }
}
