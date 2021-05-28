package platform.codingnomads.co.gettingstarted.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

/**
 *  Joke Sources:
 *  	* https://www.rd.com/list/short-jokes/
 *  	* https://devdojo.com/devdojo/10-awesome-programming-jokes
 *  	* https://levelup.gitconnected.com/10-programming-jokes-for-programmers-b9c436f3e023
 *		* https://www.hongkiat.com/blog/programming-jokes/
 */

// The @Controller annotation indicates that this class is a web service
@Controller
public class ComedyController {

    // The line below "injects" a bean of "ComedyService" into this class
    @Autowired
    ComedyService comedyService;

    // This method will be invoked when the baseUrl + "/comedy" is hit
	@GetMapping("/comedy")
	public String comedy (Model model) {
	    // Get a random joke from the service
		String joke = comedyService.getRandomJoke();

		// Attach that joke to the data model
		model.addAttribute("joke", joke);

		// Return the view "getting_started/comedy.html"
        // this view can be found at "src/main/resources/templates/getting_started/comedy.html
		return "getting_started/comedy";
	}
}
