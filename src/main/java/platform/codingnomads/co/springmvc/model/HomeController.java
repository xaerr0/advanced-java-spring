package platform.codingnomads.co.springmvc.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    // using addAttribute for each attribute
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Developer");
        model.addAttribute("language", "Java");
        model.addAttribute("framework", "Spring");
        return "model-page";
    }

    // using mergeAttributes to merge a Map of attributes
    @GetMapping("/map")
    public String indexMap(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Developer");
        map.put("language", "Java");
        model.mergeAttributes(map);

        model.addAttribute("framework", "Spring");

        return "model-page";
    }

    @GetMapping("/model-and-view-page")
    public ModelAndView passDataWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("model-and-view");
        modelAndView.addObject("message", "CodingNomads!");
        return modelAndView;
    }
}
