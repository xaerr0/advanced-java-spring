package platform.codingnomads.co.ioc.examples.dependencylookup;

public class MyGreetingProvider implements GreetingProvider {
    @Override
    public String getGreeting() {
        return "Why hello out there!";
    }
}