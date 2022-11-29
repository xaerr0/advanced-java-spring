package platform.codingnomads.co.corespring.examples.annotations.whatandwhy;

public class AnnotationDemoService implements LegacyInfoProvider {

    @ModernInfo
    @Override
    public String info() {
        return "legacy api fetching information";
    }

    @SecondaryData
    @Override
    public String data() {
        return "primary data";
    }
}