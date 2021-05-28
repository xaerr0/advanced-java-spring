package platform.codingnomads.co.springmvc.modelandmodelview;

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
        map.put("name", "Java Code Warrior!");
        map.put("language", "Java");
        map.put("framework", "Spring");
        model.mergeAttributes(map);
        return "model_view_page";
    }

    @GetMapping("/modelAndViewPage")
    public ModelAndView passParametersWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("modelAndViewPage");
        modelAndView.addObject("message", "Hello Code Warrior!!");
        return modelAndView;
    }
}
