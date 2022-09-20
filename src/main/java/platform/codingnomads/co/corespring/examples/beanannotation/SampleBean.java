package platform.codingnomads.co.corespring.examples.beanannotation;

public class SampleBean {

    public SampleBean() {
        System.out.println("bean is getting ready!");
    }

    public void init() {
        System.out.println("bean init() is gathering resources..");
    }

    public void cleanup() {
        System.out.println("time to cleanup() and head home..");
    }

    public void doStuff() {
        System.out.println("doStuff() - bean is doing its thing");
    }
}
