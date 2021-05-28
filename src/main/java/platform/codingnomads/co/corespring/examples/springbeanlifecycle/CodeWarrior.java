package platform.codingnomads.co.corespring.examples.springbeanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CodeWarrior implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name Provided, Bean name is :".concat(name));
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("setBeanClassLoader method call ");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory method call");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Bean Definition count :".concat(String.valueOf(applicationContext.getBeanDefinitionCount())));
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Method executed on destruction of a bean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean post construct after  setting all bean properties. ");
    }
}
