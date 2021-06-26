package platform.codingnomads.co.corespring.examples.springbeanlifecycle;

import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SpringBean implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    @Override
    public void setBeanName(@NonNull String name) {
        System.out.println("Bean name provided, bean name set to: ".concat(name));
    }

    @Override
    public void setBeanClassLoader(@NonNull ClassLoader classLoader) {
        System.out.println("setBeanClassLoader method call.");
    }

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) {
        System.out.println("setBeanFactory method call.");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("Bean definition count: ".concat(String.valueOf(applicationContext.getBeanDefinitionCount())));
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet() method call post bean construct, " +
                "after setting all bean properties.");
    }

    @Override
    public void destroy() {
        System.out.println("destroy() method call on destruction of a bean.");
    }
}
