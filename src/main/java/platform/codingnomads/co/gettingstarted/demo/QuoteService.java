package platform.codingnomads.co.gettingstarted.demo;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class QuoteService {
    // create an empty array to hold the quotes
    private String[] quotes = new String[10];

    // constructor that will populate the quotes in the array
    public QuoteService(){
        populateQuotes();
    }

    // service method to get a random quote
    protected String getRandomQuote(){
        Random r = new Random();
        int i = r.nextInt(10);
        return quotes[i];
    }

    // populating quotes into array
    private void populateQuotes(){
        quotes[0] = "\"Programming isn't about what you know; it's about what you can figure out.\" - Chris Pine";
        quotes[1] = "\"The only way to learn a new programming language is by writing programs in it.\" - Dennis Ritchie";
        quotes[2] = "\"Sometimes it's better to leave something alone, to pause, and that's very true of programming.\" - Joyce Wheeler";
        quotes[3] = "\"Testing leads to failure, and failure leads to understanding.\" - Burt Rutan";
        quotes[4] = "\"The best error message is the one that never shows up.\" - Thomas Fuchs";
        quotes[5] = "\"The most damaging phrase in the language is.. it's always been done this way\" - Grace Hopper";
        quotes[6] = "\"Don't write better error messages, write code that doesn't need them.\" - Jason C. McDonald";
        quotes[7] = "\"Any fool can write code that a computer can understand. Good programmers write code that humans can understand.\" ― Martin Fowler";
        quotes[8] = "\"I'm not a great programmer; I'm just a good programmer with great habits.\" ― Kent Beck";
        quotes[9] = "\"The most important property of a program is whether it accomplishes the intention of its user.\" ― C.A.R. Hoare";
    }
}