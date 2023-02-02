package platform.codingnomads.co.springtest.usingmockmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//tell Spring to start completely and indicate the location of the bootstrapping class
@SpringBootTest(classes = MockMvcMain.class)
//indicate that Spring should autoconfigure the MockMvc object
@AutoConfigureMockMvc
public class TestingWebServices {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloShouldReturnDefaultMessage() throws Exception {
        //use mockMvc to start a request
        mockMvc
                //.perform is used to indicate what mockMvc should do
                .perform(
                        //the get method and the path passed in as a parameter is used to indicate the
                        // HTTP method and the url path used to make request
                        get("/hello"))
                //print the response
                .andDo(print())
                //the response should have status 200 OK
                .andExpect(status().isOk())
                //test that this response has a body that contains a "Hello Back" String
                .andExpect(content().string(containsString("Hello Back")));

    }

    @Test
    public void baseURLShouldReturnGreetingViewName() throws Exception {
        //use mockMvc to start a request
        mockMvc
                //indicate what HTTP method and url path should be used to make the request
                .perform(get("/"))
                //the result should be printed
                .andDo(print())
                //the view name expected is greeting
                .andExpect(view().name("greeting"));
    }

    @Test
    public void ciaoShouldHaveExclamation() throws Exception {
        mockMvc
                .perform(get("/ciao"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("ciao!"))
                .andExpect(content().string(containsString("ciao!")));

    }

    @Test
    public void pathDoesNotExist() throws Exception {
        mockMvc
                .perform(get("/greeting2"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testForModelAttribute() throws Exception {
        mockMvc
                .perform(get("/greeting2"))
                .andExpect(model().attribute("city", "The Big City"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting2"))
                .andDo(print());
    }
}