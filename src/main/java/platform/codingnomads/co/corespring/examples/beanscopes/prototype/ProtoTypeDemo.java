package platform.codingnomads.co.corespring.examples.beanscopes.prototype;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProtoTypeDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(PrototypeDemoConfig.class);
        ctx.refresh();

        SpringBean springBean1 = ctx.getBean("springBean", SpringBean.class);
        System.out.println("Hash code: " + springBean1.hashCode());

        SpringBean springBean2 = ctx.getBean("springBean", SpringBean.class);
        System.out.println("Hash code: " + springBean2.hashCode());

        SpringBean springBean3 = ctx.getBean("springBean2", SpringBean.class);
        System.out.println("Hash code: " + springBean3.hashCode());

        SpringBean springBean4 = ctx.getBean("springBean2", SpringBean.class);
        System.out.println("Hash code: " + springBean4.hashCode());

        ctx.close();
    }
}