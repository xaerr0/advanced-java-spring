package platform.codingnomads.co.springtest.junit;

public class ExampleClass {

    /*
        If we pass in 2.2 for "a" and 3.3 for "b" we should get 290.4
        (2.2 / 2) * (3.3 * 3) / .0375 = 290.4
     */
    public static double calculate(double a, double b){
        double result = (a / 2) * (b * 3) / .0375;
        return result;
    }

    /*
        If we pass in "This" for "a", "is" for "b" and "expected" for c
        we should expect the return String to be "this-is-expected"
     */
    public static String concatDash(String a, String b, String c){
        StringBuilder sb = new StringBuilder();

        sb.append(a)
                .append("-")
                .append(b)
                .append("-")
                .append(c);

        return sb.toString();
    }

}
