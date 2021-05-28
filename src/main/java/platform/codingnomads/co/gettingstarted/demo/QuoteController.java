package platform.codingnomads.co.gettingstarted.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @GetMapping("/quote")
    public String quote (Model model) {
        // Get a random quote from the service
        String quote = quoteService.getRandomQuote();

        // Attach that quote to the data model
        model.addAttribute("quote", quote);

        // Return the view "getting_started/quote.html"
        // this view can be found at "src/main/resources/templates/getting_started/quote.html
        return "getting_started/quote";
    }

}
