package platform.codingnomads.co.gettingstarted.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuoteController {

    // "inject" a bean of the QuoteService into this class
    @Autowired
    QuoteService quoteService;

    // The path your endpoint will be mapped to (http://localhost:8080/quote)
    @GetMapping("/quote")
    public String quote (Model model) {
        // Get a random quote from the service
        String quote = quoteService.getRandomQuote();

        // Attach that quote to the data model
        model.addAttribute("quote", quote);

        // Return the view "getting_started/quote.html"
        // This is the file you created at "src/main/resources/templates/getting_started/quote.html
        return "getting_started/quote";
    }
}