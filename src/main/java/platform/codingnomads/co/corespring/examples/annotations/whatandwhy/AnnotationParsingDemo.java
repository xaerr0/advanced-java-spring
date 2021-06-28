package platform.codingnomads.co.corespring.examples.annotations.whatandwhy;

import java.lang.reflect.Method;

public class AnnotationParsingDemo {
    public static void main(String[] args) {
        try {
            Class<AnnotationDemoService> annotationDemoService = AnnotationDemoService.class;
            for (Method method : annotationDemoService.getMethods()) {
                if (method.isAnnotationPresent(ModernInfo.class)) {
                    ModernInfo modernInfo = method.getAnnotation(ModernInfo.class);
                    System.out.println("Info Received: " + modernInfo.info());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
