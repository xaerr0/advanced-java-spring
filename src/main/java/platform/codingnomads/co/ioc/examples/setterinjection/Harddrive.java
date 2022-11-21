package platform.codingnomads.co.ioc.examples.setterinjection;

public class Harddrive {
    private String platter;
    private String actuator;

    public Harddrive(String platter, String actuator) {
        this.platter = platter;
        this.actuator = actuator;
    }
}