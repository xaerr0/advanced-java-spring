package platform.codingnomads.co.corespring.examples.beanscopes.singleton;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SingletonDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SingletonDemoConfig.class);
        ctx.refresh();

        SpringBean springBean1 = ctx.getBean(SpringBean.class);
        System.out.println("Hash code: " + springBean1.hashCode());

        SpringBean springBean2 = ctx.getBean(SpringBean.class);
        System.out.println("Hash code: " + springBean2.hashCode());

        SpringBean springBean3 = ctx.getBean(SpringBean.class);
        System.out.println("Hash code: " + springBean3.hashCode());

        GreenBean greenBean = ctx.getBean(GreenBean.class);
        System.out.println("Hash code: " + greenBean.hashCode());

        GreenBean greenBean2 = ctx.getBean(GreenBean.class);
        System.out.println("Hash code: " + greenBean2.hashCode());
        ctx.close();
    }
}