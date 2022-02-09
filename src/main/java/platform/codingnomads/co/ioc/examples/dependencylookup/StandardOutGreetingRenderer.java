package platform.codingnomads.co.ioc.examples.dependencylookup;

public class StandardOutGreetingRenderer implements GreetingRenderer {

    private GreetingProvider greetingProvider;

    @Override
    public void render() {
        if (greetingProvider == null) {
            throw new RuntimeException(" You must set the property greetingProvider of a class: "
                    + StandardOutGreetingRenderer.class.getName());
        }
        System.out.println(greetingProvider.getGreeting());
    }

    @Override
    public void setGreetingProvider(GreetingProvider greetingProvider) {
        this.greetingProvider = greetingProvider;
    }

    @Override
    public GreetingProvider getGreetingProvider() {
        return this.greetingProvider;
    }
}
