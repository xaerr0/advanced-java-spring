package platform.codingnomads.co.gettingstarted.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Joke Sources:
 *  	* https://www.rd.com/list/short-jokes/
 *  	* https://devdojo.com/devdojo/10-awesome-programming-jokes
 *  	* https://levelup.gitconnected.com/10-programming-jokes-for-programmers-b9c436f3e023
 *		* https://www.hongkiat.com/blog/programming-jokes/
 */

// The @Controller annotation indicates that this class is a Spring MVC web controller
@Controller
public class ComedyController {

    // The line below "injects" a bean of "ComedyService" into this class
    @Autowired
    ComedyService comedyService;

	/* This method will be invoked when the baseUrl:8080 + "/comedy" is hit.
       The baseUrl is the IP address of your server, or "localhost".
       8080 is the port at which Apache Tomcat will be running and serving this app.
       To hit this method on your local machine it will be "localhost:8080/comedy" */
	@GetMapping("/comedy")
	public String comedy (Model model) {
		// Get a random joke from the comedy service "bean"
		String joke = comedyService.getRandomJoke();

		// Attach that joke to the data model
		// (insert the joke into the HTML that will be returned to user)
		model.addAttribute("joke", joke);

		// Return the view "getting_started/comedy.html"
		// this view can be found at "src/main/resources/templates/getting_started/comedy.html
		return "getting_started/comedy";
	}
}