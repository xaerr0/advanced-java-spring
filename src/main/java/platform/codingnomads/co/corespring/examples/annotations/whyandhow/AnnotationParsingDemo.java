package platform.codingnomads.co.corespring.examples.annotations.whyandhow;


import java.lang.reflect.Method;

public class AnnotationParsingDemo {
    public static void main(String[] args) {
        try {
            Class annotationDemoClass = CodeWarriorService.class;
            for (Method method : annotationDemoClass.getMethods()) {
                if (method.isAnnotationPresent(CodeWarriorInfo.class)) {
                    CodeWarriorInfo codeWarriorInfo = method.getAnnotation(CodeWarriorInfo.class);
                    System.out.println("CodeWarrior Info:  " + codeWarriorInfo.info());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
