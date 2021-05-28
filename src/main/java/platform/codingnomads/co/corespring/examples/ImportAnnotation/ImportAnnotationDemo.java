package platform.codingnomads.co.corespring.examples.ImportAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ImportAnnotationConfig.class);
        ctx.refresh();
        final CodeWarrior codeWarrior = ctx.getBean(CodeWarrior.class);
        final Framework framework = ctx.getBean(Framework.class);
        ctx.close();
    }
}
