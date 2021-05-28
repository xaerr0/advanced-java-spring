package platform.codingnomads.co.springweb.springrestcontrollers.stringtobinaryexample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {


    @RequestMapping(
            path = "/binary",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String returnSomeRandomBinary() throws Exception{
        String binary = "";
        char[] chars = "this is what the binary is based on".toCharArray();

        for(char c: chars) {
            binary += "   " + Integer.toBinaryString(c);
        }

        return binary;
    }
}
