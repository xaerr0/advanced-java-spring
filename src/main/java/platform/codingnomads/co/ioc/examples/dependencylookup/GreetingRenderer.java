package platform.codingnomads.co.ioc.examples.dependencylookup;

public interface GreetingRenderer {
    void render();

    void setGreetingProvider(GreetingProvider greetingProvider);

    GreetingProvider getGreetingProvider();

}
