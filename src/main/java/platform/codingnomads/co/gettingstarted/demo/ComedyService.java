package platform.codingnomads.co.gettingstarted.demo;

import org.springframework.stereotype.Service;
import java.util.Random;

// @Service annotation indicates this a Spring Bean to be "injected" into other class
@Service
public class ComedyService {

    // declare a simple String array of length 10
    private String[] jokes = new String[10];

    // simple constructor that invokes the populateJokes() method
    public ComedyService(){
        populateJokes();
    }

    // simple method that gets a random number between 0 & 9 (inclusive)
    // then returns the String from the joke array at that random index
    protected String getRandomJoke(){
        Random r = new Random();
        int i = r.nextInt(10);
        return jokes[i];
    }

    // simple method that populates the jokes array with a few jokes
    private void populateJokes(){
        jokes[0] = "Q: Did you hear about the mathematician who’s afraid of negative numbers? " +
                "\n\nA: He’ll stop at nothing to avoid them.";
        jokes[1] = "Q: How many programmers does it take to change a light bulb?" +
                "\n\nA: none, that's a hardware problem";
        jokes[2] = "Q: Why did the programmer quit their job?" +
                "\n\nA: Because he didn't get arrays.";
        jokes[3] = "Q: What did the Java code say to the C code?" +
                "\n\nA: You've got no class.";
        jokes[4] = "Q: What is the most used language in programming?" +
                "\n\nA: Profanity.";
        jokes[5] = "Q: Why are Assembly programmers always soaking wet?" +
                "\n\nA: They work below C-level.";
        jokes[6] = "There are 10 types of programmers." +
                "\n\nThose who understand binary, and those who don't.";
        jokes[7] = "Q: Why do Java developers wear glasses?" +
                "\n\nA: Because they can't C#";
        jokes[8] = "Q: What's the Object-Oriented way to become wealthy?" +
                "\n\nA: Inheritance.";
        jokes[9] = "Q: How do you tell HTML from HTML5?" +
                "\n\nA: Try it out in Internet Explorer. Did it work? No? It's HTML5.";
    }
}