package platform.codingnomads.co.corespring.examples.dependsonannotation;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DependsOnDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(DependsOnDemoConfig.class);
        ctx.refresh();
        final SpringDeveloper springDeveloper = ctx.getBean(SpringDeveloper.class);
        ctx.close();
    }
}
