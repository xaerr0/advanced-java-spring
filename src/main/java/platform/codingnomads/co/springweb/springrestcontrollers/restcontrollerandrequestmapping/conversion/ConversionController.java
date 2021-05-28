package platform.codingnomads.co.springweb.springrestcontrollers.restcontrollerandrequestmapping.conversion;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {

    private String text = "this is the text this is all based on.";

    @RequestMapping(
            path = "/binary",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String returnSomeRandomBinary() throws Exception {
        String binary = "";
        char[] chars = text.toCharArray();

        for(char c: chars) {
            binary += "   " + Integer.toBinaryString(c);
        }

        return binary;
    }

    @RequestMapping(
            path = "/utf8",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String returnTheRandomString() {
        return text;
    }

}
