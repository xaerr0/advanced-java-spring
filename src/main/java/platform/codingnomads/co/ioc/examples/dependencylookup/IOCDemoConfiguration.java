package platform.codingnomads.co.ioc.examples.dependencylookup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOCDemoConfiguration {

    @Bean
    public GreetingProvider provider() {
        return new CodingNomadsGreetingProvider();
    }

    @Bean
    public GreetingRenderer renderer() {
        GreetingRenderer renderer =
                new StandardOutGreetingRenderer();
        renderer.setGreetingProvider(provider());
        return renderer;
    }@Bean
    public GreetingRenderer renderer2() {
        GreetingRenderer renderer2 = new StandardOutGreetingRenderer();
        renderer2.setGreetingProvider(myGreetingProvider());
        return renderer2;
    }

    @Bean
    public MyGreetingProvider myGreetingProvider() {
        return new MyGreetingProvider();
    }
}