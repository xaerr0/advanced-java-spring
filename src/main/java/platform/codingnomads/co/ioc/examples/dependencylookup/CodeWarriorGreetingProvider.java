package platform.codingnomads.co.ioc.examples.dependencylookup;

public class CodeWarriorGreetingProvider implements GreetingProvider {
    @Override
    public String getGreeting() {
        return "Hello Code Warriors!";
    }

}
